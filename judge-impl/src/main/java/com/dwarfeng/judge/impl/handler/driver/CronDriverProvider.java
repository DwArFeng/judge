package com.dwarfeng.judge.impl.handler.driver;

import com.dwarfeng.judge.impl.handler.DriverProvider;
import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.exception.DriverException;
import com.dwarfeng.judge.stack.handler.Driver;
import com.dwarfeng.judge.stack.service.EvaluateService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Cron驱动提供器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class CronDriverProvider implements DriverProvider {

    public static final String SUPPORT_TYPE = "cron_driver";

    private final CronDriver cronDriver;

    public CronDriverProvider(CronDriver cronDriver) {
        this.cronDriver = cronDriver;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(SUPPORT_TYPE, type);
    }

    @Override
    public Driver provide() {
        return cronDriver;
    }

    @Component
    public static class CronDriver implements Driver {

        private final ThreadPoolTaskScheduler scheduler;
        private final EvaluateService evaluateService;

        private final Lock lock = new ReentrantLock();
        private final Set<ScheduledFuture<?>> scheduledFutures = new HashSet<>();
        private final Set<CronProcessor> cronProcessors = new HashSet<>();

        public CronDriver(ThreadPoolTaskScheduler scheduler, EvaluateService evaluateService) {
            this.scheduler = scheduler;
            this.evaluateService = evaluateService;
        }

        @Override
        public void register(DriverInfo driverInfo) throws DriverException {
            lock.lock();
            try {
                LongIdKey sectionKey = driverInfo.getSectionKey();
                String cron = driverInfo.getContent();
                CronProcessor cronProcessor = new CronProcessor(
                        evaluateService,
                        sectionKey
                );
                CronTrigger cronTrigger = new CronTrigger(cron);
                ScheduledFuture<?> scheduledFuture = scheduler.schedule(cronProcessor, cronTrigger);
                cronProcessors.add(cronProcessor);
                scheduledFutures.add(scheduledFuture);
            } catch (Exception e) {
                throw new DriverException(e);
            } finally {
                lock.unlock();
            }
        }

        @Override
        public void unregisterAll() {
            lock.lock();
            try {
                for (ScheduledFuture<?> scheduledFuture : scheduledFutures) {
                    scheduledFuture.cancel(true);
                }
                for (CronProcessor cronProcessor : cronProcessors) {
                    cronProcessor.shutdown();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private static class CronProcessor implements Runnable {

        private static final Logger LOGGER = LoggerFactory.getLogger(CronProcessor.class);

        private final EvaluateService evaluateService;
        private final LongIdKey sectionKey;

        private final Lock lock = new ReentrantLock();
        private boolean runningFlag = true;

        private CronProcessor(EvaluateService evaluateService, LongIdKey sectionKey) {
            this.evaluateService = evaluateService;
            this.sectionKey = sectionKey;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                if (!runningFlag) {
                    return;
                }

                LOGGER.debug("计划时间已到达, cron驱动器驱动 " + sectionKey + " 部件执行评估动作...");
                evaluateService.evaluate(sectionKey);
            } catch (Exception e) {
                LOGGER.warn("记录 " + sectionKey + " 时出现异常, 放弃本次记录", e);
            } finally {
                lock.unlock();
            }
        }

        void shutdown() {
            lock.lock();
            try {
                runningFlag = false;
            } finally {
                lock.unlock();
            }
        }
    }
}
