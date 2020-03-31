package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.dcti.stack.bean.dto.DataInfo;
import com.dwarfeng.dutil.basic.mea.TimeMeasurer;
import com.dwarfeng.dutil.develop.backgr.AbstractTask;
import com.dwarfeng.judge.stack.handler.ConsumeHandler;
import com.dwarfeng.judge.stack.handler.Judger;
import com.dwarfeng.judge.stack.handler.RepositoryHandler;
import com.dwarfeng.judge.stack.handler.SinkHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class ConsumeHandlerImpl implements ConsumeHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumeHandlerImpl.class);

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private RepositoryHandler repositoryHandler;
    @Autowired
    private SinkHandler sinkHandler;

    private final List<ConsumeTask> processingConsumeTasks = new ArrayList<>();
    private final List<ConsumeTask> endingConsumeTasks = new ArrayList<>();

    @Value("${consume.consumer_thread}")
    private int consumerThread;
    @Value("${consume.buffer_size}")
    private int bufferSize;

    private final Lock lock = new ReentrantLock();
    private final ConsumeBuffer consumeBuffer = new ConsumeBuffer();

    private boolean startFlag = false;
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
                LOGGER.info("Judge consumeer handler 开启消费线程...");
                consumeBuffer.block();
                for (int i = 0; i < thread; i++) {
                    ConsumeTask consumeTask = new ConsumeTask(consumeBuffer, repositoryHandler, sinkHandler);
                    threadPoolTaskExecutor.execute(consumeTask);
                    processingConsumeTasks.add(consumeTask);
                }
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
                processingConsumeTasks.forEach(ConsumeTask::shutdown);
                endingConsumeTasks.addAll(processingConsumeTasks);
                processingConsumeTasks.clear();
                consumeBuffer.unblock();
                Judger judger;
                while (Objects.nonNull(judger = consumeBuffer.poll())) {
                    try {
                        LOGGER.info("判断 judge consume handler 中剩余的元素 1 个...");
                        consume(judger);
                    } catch (Exception e) {
                        LOGGER.warn("进行判断工作时发生异常, 抛弃 1 次判断", e);
                    }
                }
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

    private void consume(Judger judger) throws Exception {
        DataInfo dataInfo = judger.judge(repositoryHandler);
        sinkHandler.sinkData(dataInfo);
    }

    @Override
    public void accept(Judger judger) {
        consumeBuffer.accept(judger);
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
                        ConsumeTask consumeTask = new ConsumeTask(consumeBuffer, repositoryHandler, sinkHandler);
                        threadPoolTaskExecutor.execute(consumeTask);
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

    private static final class ConsumeTask extends AbstractTask {

        private static final Logger LOGGER = LoggerFactory.getLogger(ConsumeTask.class);

        private final ConsumeBuffer consumeBuffer;
        private final RepositoryHandler repositoryHandler;
        private final SinkHandler sinkHandler;
        private final AtomicBoolean runningFlag = new AtomicBoolean(true);

        public ConsumeTask(ConsumeBuffer consumeBuffer, RepositoryHandler repositoryHandler, SinkHandler sinkHandler) {
            this.consumeBuffer = consumeBuffer;
            this.repositoryHandler = repositoryHandler;
            this.sinkHandler = sinkHandler;
        }

        @Override
        protected void todo() {
            while (runningFlag.get()) {
                Judger judger = null;
                try {
                    judger = consumeBuffer.poll();
                    if (Objects.nonNull(judger)) {
                        consume(judger);
                    }
                } catch (Exception e) {
                    if (Objects.nonNull(judger)) {
                        LOGGER.warn("进行判断工作时发生异常, 抛弃 1 次判断", e);
                    }
                }
            }
            LOGGER.info("消费线程退出...");
        }

        public void shutdown() {
            runningFlag.set(false);
        }

        private void consume(Judger judger) throws Exception {
            TimeMeasurer tm = new TimeMeasurer();
            tm.start();
            DataInfo dataInfo = judger.judge(repositoryHandler);
            sinkHandler.sinkData(dataInfo);
            tm.stop();
            LOGGER.info("消费者完成消费, 生成数据对象为 " + dataInfo + ", 用时 " + tm.getTimeMs() + " 毫秒");
        }
    }

    private static class ConsumeBuffer {

        private final Lock lock = new ReentrantLock();
        private final Condition provideCondition = lock.newCondition();
        private final Condition consumeCondition = lock.newCondition();
        private final List<Judger> buffer = new ArrayList<>();

        private int bufferSize;
        private boolean blockEnabled = true;

        public void accept(Judger judger) {
            lock.lock();
            try {
                while (buffer.size() >= bufferSize) {
                    try {
                        provideCondition.await();
                    } catch (InterruptedException ignored) {
                    }
                }

                buffer.add(judger);
                consumeCondition.signalAll();
            } finally {
                lock.unlock();
            }
        }

        public Judger poll() {
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
                    Judger judger = buffer.remove(0);
                    provideCondition.signalAll();
                    return judger;
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
}
