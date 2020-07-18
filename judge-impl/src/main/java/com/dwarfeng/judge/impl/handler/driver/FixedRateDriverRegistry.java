package com.dwarfeng.judge.impl.handler.driver;

import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.exception.DriverException;
import com.dwarfeng.judge.stack.handler.Driver;
import com.dwarfeng.judge.stack.service.EvaluateService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 固定间隔驱动提供器。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
@Component
public class FixedRateDriverRegistry extends AbstractDriverRegistry {

    public static final String DRIVER_TYPE = "fixed_rate_driver";

    @Autowired
    private FixedRateDriver fixedRateDriver;

    public FixedRateDriverRegistry() {
        super(DRIVER_TYPE);
    }

    @Override
    public String provideLabel() {
        return "固定频率驱动器";
    }

    @Override
    public String provideDescription() {
        return "根据指定的间隔定时驱动，如果某一次驱动晚于间隔，则后续驱动的时间会提前，以保持频率不变。";
    }

    @Override
    public String provideExampleContent() {
        return "60000";
    }

    @Override
    public Driver provide() {
        return fixedRateDriver;
    }

    @Override
    public String toString() {
        return "FixedRateDriverRegistry{" +
                "fixedRateDriver=" + fixedRateDriver +
                ", driverType='" + driverType + '\'' +
                '}';
    }

    @Component
    public static class FixedRateDriver implements Driver {

        @Autowired
        private ThreadPoolTaskScheduler scheduler;
        @Autowired
        private EvaluateService evaluateService;

        private final Lock lock = new ReentrantLock();
        private final Set<ScheduledFuture<?>> scheduledFutures = new HashSet<>();
        private final Set<FixedRateProcessor> fixedRateProcessors = new HashSet<>();

        @Override
        public void register(DriverInfo driverInfo) throws DriverException {
            lock.lock();
            try {
                LongIdKey sectionKey = driverInfo.getSectionKey();
                long rate = Long.parseLong(driverInfo.getContent());
                FixedRateProcessor fixedRateProcessor = new FixedRateProcessor(
                        evaluateService,
                        sectionKey
                );
                ScheduledFuture<?> scheduledFuture =
                        scheduler.scheduleAtFixedRate(fixedRateProcessor, rate);
                fixedRateProcessors.add(fixedRateProcessor);
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
                for (FixedRateProcessor fixedRateProcessor : fixedRateProcessors) {
                    fixedRateProcessor.shutdown();
                }
            } finally {
                lock.unlock();
            }
        }
    }

    private static class FixedRateProcessor implements Runnable {

        private static final Logger LOGGER = LoggerFactory.getLogger(FixedRateProcessor.class);

        private final EvaluateService evaluateService;
        private final LongIdKey sectionKey;

        private final Lock lock = new ReentrantLock();
        private boolean runningFlag = true;

        private FixedRateProcessor(EvaluateService evaluateService, LongIdKey sectionKey) {
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

                LOGGER.debug("计划时间已到达, fixed rate 驱动器驱动 " + sectionKey + " 部件执行评估动作...");
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
