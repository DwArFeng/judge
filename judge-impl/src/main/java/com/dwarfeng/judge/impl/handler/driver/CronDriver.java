package com.dwarfeng.judge.impl.handler.driver;

import com.dwarfeng.judge.impl.handler.Driver;
import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.handler.JudgeAssignHandler;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Cron驱动器。
 *
 * <p> 根据Cron定时触发的驱动器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class CronDriver implements Driver {

    public static final String SUPPORT_TYPE = "cron";

    @Autowired
    private ThreadPoolTaskScheduler scheduler;
    @Autowired
    private JudgeAssignHandler judgeAssignHandler;

    private final Lock lock = new ReentrantLock();
    private final Set<ScheduledFuture<?>> scheduledFutures = new HashSet<>();
    private final Set<CronProcessor> cronProcessors = new HashSet<>();

    @Override
    public boolean supportType(String type) {
        return Objects.equals(SUPPORT_TYPE, type);
    }

    @Override
    public void register(DriverInfo driverInfo) {
        lock.lock();
        try {
            LongIdKey sectionKey = driverInfo.getSectionKey();
            String cron = driverInfo.getContent();
            CronProcessor cronProcessor = new CronProcessor(
                    judgeAssignHandler,
                    sectionKey
            );
            CronTrigger cronTrigger = new CronTrigger(cron);
            ScheduledFuture<?> scheduledFuture = scheduler.schedule(cronProcessor, cronTrigger);
            cronProcessors.add(cronProcessor);
            scheduledFutures.add(scheduledFuture);
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

    @Override
    public String provideType() {
        return SUPPORT_TYPE;
    }

    @Override
    public String provideLabel() {

        return null;
    }

    @Override
    public String provideDescription() {
        return null;
    }

    @Override
    public String provideExampleContent() {
        return null;
    }

    private static class CronProcessor implements Runnable {

        private static final Logger LOGGER = LoggerFactory.getLogger(CronProcessor.class);

        private final JudgeAssignHandler judgeAssignHandler;
        private final LongIdKey sectionKey;

        private final Lock lock = new ReentrantLock();
        private boolean runningFlag = true;

        private CronProcessor(JudgeAssignHandler judgeAssignHandler, LongIdKey sectionKey) {
            this.judgeAssignHandler = judgeAssignHandler;
            this.sectionKey = sectionKey;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                if (!runningFlag) {
                    return;
                }

                judgeAssignHandler.judge(sectionKey);
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
