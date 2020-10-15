package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.dutil.basic.mea.TimeMeasurer;
import com.dwarfeng.dutil.develop.backgr.AbstractTask;
import com.dwarfeng.judge.sdk.util.Constants;
import com.dwarfeng.judge.stack.bean.EvaluateInfo;
import com.dwarfeng.judge.stack.bean.dto.JudgerReport;
import com.dwarfeng.judge.stack.bean.dto.JudgerResult;
import com.dwarfeng.judge.stack.bean.dto.SectionReport;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.handler.ConsumeHandler;
import com.dwarfeng.judge.stack.handler.Judger;
import com.dwarfeng.judge.stack.handler.RepositoryHandler;
import com.dwarfeng.judge.stack.handler.SinkHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class ConsumeHandlerImpl implements ConsumeHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumeHandlerImpl.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ThreadPoolTaskExecutor executor;
    @Autowired
    private ThreadPoolTaskScheduler scheduler;

    @Autowired
    private EvaluateInfoConsumer evaluateInfoConsumer;
    @Autowired
    private ConsumeBuffer consumeBuffer;

    private final List<ConsumeTask> processingConsumeTasks = new ArrayList<>();
    private final List<ConsumeTask> endingConsumeTasks = new ArrayList<>();

    @Value("${consume.consumer_thread}")
    private int consumerThread;
    @Value("${consume.buffer_size}")
    private int bufferSize;
    @Value("${consume.threshold.warn}")
    private double warnThreshold;

    private final Lock lock = new ReentrantLock();

    private boolean startFlag = false;
    ScheduledFuture<?> capacityCheckFuture = null;
    private int thread;

    @PostConstruct
    public void init() {
        thread = Math.max(1, consumerThread);
        setBufferSize(bufferSize);
    }

    @Override
    public boolean isStart() {
        lock.lock();
        try {
            return startFlag;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void start() {
        lock.lock();
        try {
            if (!startFlag) {
                LOGGER.info("Judge consumer handler 开启消费线程...");
                consumeBuffer.block();
                for (int i = 0; i < thread; i++) {
                    ConsumeTask consumeTask = applicationContext.getBean(ConsumeTask.class);
                    executor.execute(consumeTask);
                    processingConsumeTasks.add(consumeTask);
                }
                capacityCheckFuture = scheduler.scheduleAtFixedRate(() -> {
                    double ratio = (double) consumeBuffer.bufferedSize() / (double) consumeBuffer.getBufferSize();
                    if (ratio >= warnThreshold) {
                        LOGGER.warn("消费者的待消费元素占用缓存比例为 {}，超过报警值 {}，请检查",
                                ratio,
                                warnThreshold
                        );
                    }
                }, Constants.SCHEDULER_CHECK_INTERVAL);
                startFlag = true;
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void stop() {
        lock.lock();
        try {
            if (startFlag) {
                LOGGER.info("Judge consume handler 结束消费线程...");
                if (Objects.nonNull(capacityCheckFuture)) {
                    capacityCheckFuture.cancel(true);
                    capacityCheckFuture = null;
                }
                processingConsumeTasks.forEach(ConsumeTask::shutdown);
                endingConsumeTasks.addAll(processingConsumeTasks);
                processingConsumeTasks.clear();
                consumeBuffer.unblock();
                processRemainingElement();
                endingConsumeTasks.removeIf(AbstractTask::isFinished);
                if (!endingConsumeTasks.isEmpty()) {
                    LOGGER.info("Consume handler 中的线程还未完全结束, 等待线程结束...");
                    endingConsumeTasks.forEach(
                            task -> {
                                try {
                                    task.awaitFinish();
                                } catch (InterruptedException ignored) {
                                }
                            }
                    );
                }
                processingConsumeTasks.clear();
                endingConsumeTasks.clear();
                LOGGER.info("Consume handler 已经妥善处理数据, 消费线程结束");
                startFlag = false;
            }
        } finally {
            lock.unlock();
        }
    }

    private void processRemainingElement() {
        // 如果没有剩余元素，直接跳过。
        if (consumeBuffer.bufferedSize() <= 0) return;
        LOGGER.info("消费 judge handler 中剩余的元素 {} 个...", consumeBuffer.bufferedSize());
        LOGGER.info("Judge handler 中剩余的元素过多时，需要较长时间消费，请耐心等待...");
        ScheduledFuture<?> scheduledFuture = scheduler.scheduleAtFixedRate(() ->
                        LOGGER.info("消费 consume handler 中剩余的元素 {} 个，请耐心等待...", consumeBuffer.bufferedSize()),
                new Date(System.currentTimeMillis() + Constants.SCHEDULER_CHECK_INTERVAL), Constants.SCHEDULER_CHECK_INTERVAL);
        EvaluateInfo evaluateInfo2Consume;
        while (Objects.nonNull(evaluateInfo2Consume = consumeBuffer.poll())) {
            try {
                evaluateInfoConsumer.consume(evaluateInfo2Consume);
            } catch (Exception e) {
                LOGGER.warn("消费元素时发生异常, 抛弃 DataInfo: " + evaluateInfo2Consume.toString(), e);
            }
        }
        scheduledFuture.cancel(true);
    }

    @Override
    public void accept(EvaluateInfo evaluateInfo) {
        consumeBuffer.accept(evaluateInfo);
    }

    @Override
    public int bufferedSize() {
        lock.lock();
        try {
            return consumeBuffer.bufferedSize();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getBufferSize() {
        return consumeBuffer.getBufferSize();
    }

    @Override
    public void setBufferSize(int size) {
        consumeBuffer.setBufferSize(size);
    }

    @Override
    public int getThread() {
        lock.lock();
        try {
            return thread;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void setThread(int thread) {
        lock.lock();
        try {
            thread = Math.max(thread, 1);
            int delta = thread - this.thread;
            this.thread = thread;
            if (startFlag) {
                if (delta > 0) {
                    for (int i = 0; i < delta; i++) {
                        ConsumeTask consumeTask = applicationContext.getBean(ConsumeTask.class);
                        executor.execute(consumeTask);
                        processingConsumeTasks.add(consumeTask);
                    }
                } else if (delta < 0) {
                    endingConsumeTasks.removeIf(AbstractTask::isFinished);
                    for (int i = 0; i < -delta; i++) {
                        ConsumeTask consumeTask = processingConsumeTasks.remove(0);
                        consumeTask.shutdown();
                        endingConsumeTasks.add(consumeTask);
                    }
                }
            }
        } finally {
            lock.unlock();
        }
    }

    @Override
    public boolean isIdle() {
        lock.lock();
        try {
            if (consumeBuffer.bufferedSize() > 0) {
                return false;
            }
            if (!processingConsumeTasks.isEmpty()) {
                return false;
            }
            endingConsumeTasks.removeIf(AbstractTask::isFinished);
            return endingConsumeTasks.isEmpty();
        } finally {
            lock.unlock();
        }
    }

    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class ConsumeTask extends AbstractTask {

        private static final Logger LOGGER = LoggerFactory.getLogger(ConsumeTask.class);

        @Autowired
        private ConsumeBuffer consumeBuffer;
        @Autowired
        private EvaluateInfoConsumer evaluateInfoConsumer;

        private final AtomicBoolean runningFlag = new AtomicBoolean(true);

        @Override
        protected void todo() {
            while (runningFlag.get()) {
                EvaluateInfo evaluateInfo = null;
                try {
                    evaluateInfo = consumeBuffer.poll();
                    if (Objects.nonNull(evaluateInfo)) {
                        evaluateInfoConsumer.consume(evaluateInfo);
                    }
                } catch (Exception e) {
                    if (Objects.nonNull(evaluateInfo)) {
                        LOGGER.warn("进行判断工作时发生异常, 抛弃 1 次判断", e);
                    }
                }
            }
            LOGGER.info("消费线程退出...");
        }

        public void shutdown() {
            runningFlag.set(false);
        }
    }

    @Component
    public static class ConsumeBuffer {

        private final Lock lock = new ReentrantLock();
        private final Condition provideCondition = lock.newCondition();
        private final Condition consumeCondition = lock.newCondition();
        private final List<EvaluateInfo> buffer = new ArrayList<>();

        private int bufferSize;
        private boolean blockEnabled = true;

        public void accept(EvaluateInfo evaluateInfo) {
            lock.lock();
            try {
                while (buffer.size() >= bufferSize) {
                    try {
                        provideCondition.await();
                    } catch (InterruptedException ignored) {
                    }
                }

                buffer.add(evaluateInfo);
                consumeCondition.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public EvaluateInfo poll() {
            lock.lock();
            try {
                while (buffer.isEmpty() && blockEnabled) {
                    try {
                        consumeCondition.await();
                    } catch (InterruptedException ignored) {
                    }
                }

                // 取出缓冲器中的第一个元素。
                if (buffer.isEmpty()) {
                    return null;
                } else {
                    EvaluateInfo evaluateInfo = buffer.remove(0);
                    provideCondition.signalAll();
                    return evaluateInfo;
                }
            } finally {
                lock.unlock();
            }
        }

        public int bufferedSize() {
            lock.lock();
            try {
                return buffer.size();
            } finally {
                lock.unlock();
            }
        }

        public int getBufferSize() {
            lock.lock();
            try {
                return bufferSize;
            } finally {
                lock.unlock();
            }
        }

        public void setBufferSize(int bufferSize) {
            lock.lock();
            try {
                this.bufferSize = Math.max(1, bufferSize);
                provideCondition.signalAll();
                consumeCondition.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public void block() {
            lock.lock();
            try {
                this.blockEnabled = true;
                this.provideCondition.signalAll();
                this.consumeCondition.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public void unblock() {
            lock.lock();
            try {
                this.blockEnabled = false;
                this.provideCondition.signalAll();
                this.consumeCondition.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }

    @Component
    public static class EvaluateInfoConsumer {

        private static final Logger LOGGER = LoggerFactory.getLogger(EvaluateInfoConsumer.class);

        @Autowired
        private RepositoryHandler repositoryHandler;
        @Autowired
        private SinkHandler sinkHandler;

        public void consume(EvaluateInfo evaluateInfo) throws Exception {
            TimeMeasurer tm = new TimeMeasurer();
            tm.start();
            double sum = 0;
            List<JudgerReport> judgerReports = new ArrayList<>();
            for (Map.Entry<JudgerInfo, Judger> entry : evaluateInfo.getJudgerMap().entrySet()) {
                JudgerInfo judgerInfo = entry.getKey();
                Judger judger = entry.getValue();
                JudgerResult judgerResult = judger.judge(repositoryHandler);
                sum += judgerResult.getValue();
                judgerReports.add(new JudgerReport(
                        judgerInfo.getKey(),
                        judgerResult.getValue(),
                        judgerResult.getMessage(),
                        judgerResult.getContextData(),
                        judgerInfo.getType(),
                        judgerInfo.getContent()
                ));
            }
            Section section = evaluateInfo.getSection();
            double normalization = normalization(sum, section.getExpected(), section.getVariance());
            sinkHandler.sinkData(new SectionReport(
                    section.getKey(),
                    new Date(),
                    normalization,
                    sum,
                    section.getExpected(),
                    section.getVariance(),
                    judgerReports
            ));
            tm.stop();
            LOGGER.debug("消费者完成消费, 部件主键为 " + section.getKey() + ", 判断值为 " +
                    normalization + ", 用时 " + tm.getTimeMs() + " 毫秒");
        }

        private double normalization(double sum, double expected, double variance) {
            sum = (sum - expected) / Math.sqrt(variance);
            return 1 / (1 + Math.exp(-1.70174454109 * sum));
        }
    }
}
