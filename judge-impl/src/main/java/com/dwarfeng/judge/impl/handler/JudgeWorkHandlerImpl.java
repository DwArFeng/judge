package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.dcti.stack.bean.dto.DataInfo;
import com.dwarfeng.dutil.develop.backgr.AbstractTask;
import com.dwarfeng.judge.stack.handler.JudgeWorkHandler;
import com.dwarfeng.judge.stack.handler.Judger;
import com.dwarfeng.judge.stack.handler.RepositoryHandler;
import com.dwarfeng.judge.stack.handler.SinkHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class JudgeWorkHandlerImpl implements JudgeWorkHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(JudgeWorkHandlerImpl.class);

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;
    @Autowired
    private RepositoryHandler repositoryHandler;
    @Autowired
    private SinkHandler sinkHandler;

    private final List<WorkTask> processingWorkTasks = new ArrayList<>();
    private final List<WorkTask> endingWorkTasks = new ArrayList<>();
    private int thread;

    private final Lock lock = new ReentrantLock();
    private final WorkBuffer workBuffer = new WorkBuffer();
    private boolean startFlag = false;

    public JudgeWorkHandlerImpl(int thread) {
        this.thread = Math.max(thread, 1);
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
                LOGGER.info("Judge worker handler 开启消费线程...");
                workBuffer.block();
                for (int i = 0; i < thread; i++) {
                    WorkTask workTask = new WorkTask(workBuffer, repositoryHandler, sinkHandler);
                    threadPoolTaskExecutor.execute(workTask);
                    processingWorkTasks.add(workTask);
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
                LOGGER.info("Judge work handler 结束消费线程...");
                processingWorkTasks.forEach(WorkTask::shutdown);
                endingWorkTasks.addAll(processingWorkTasks);
                processingWorkTasks.clear();
                workBuffer.unblock();
                Judger judger;
                while (Objects.nonNull(judger = workBuffer.poll())) {
                    try {
                        LOGGER.info("判断 judge work handler 中剩余的元素 1 个...");
                        work(judger);
                    } catch (Exception e) {
                        LOGGER.warn("进行判断工作时发生异常, 抛弃 1 次判断", e);
                    }
                }
                endingWorkTasks.removeIf(AbstractTask::isFinished);
                if (!endingWorkTasks.isEmpty()) {
                    LOGGER.info("Work handler 中的线程还未完全结束, 等待线程结束...");
                    endingWorkTasks.forEach(
                            task -> {
                                try {
                                    task.awaitFinish();
                                } catch (InterruptedException ignored) {
                                }
                            }
                    );
                }
                processingWorkTasks.clear();
                endingWorkTasks.clear();
                LOGGER.info("Work handler 已经妥善处理数据, 消费线程结束");
                startFlag = false;
            }
        } finally {
            lock.unlock();
        }
    }

    private void work(Judger judger) throws Exception {
        DataInfo dataInfo = judger.judge(repositoryHandler);
        sinkHandler.sinkData(dataInfo);
    }

    @Override
    public void accept(Judger judger) {
        workBuffer.accept(judger);
    }

    @Override
    public int getBufferSize() {
        return workBuffer.getBufferSize();
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
                        WorkTask workTask = new WorkTask(workBuffer, repositoryHandler, sinkHandler);
                        threadPoolTaskExecutor.execute(workTask);
                        processingWorkTasks.add(workTask);
                    }
                } else if (delta < 0) {
                    endingWorkTasks.removeIf(AbstractTask::isFinished);
                    for (int i = 0; i < -delta; i++) {
                        WorkTask workTask = processingWorkTasks.remove(0);
                        workTask.shutdown();
                        endingWorkTasks.add(workTask);
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
            if (workBuffer.bufferedSize() > 0) {
                return false;
            }
            if (!processingWorkTasks.isEmpty()) {
                return false;
            }
            endingWorkTasks.removeIf(AbstractTask::isFinished);
            return endingWorkTasks.isEmpty();
        } finally {
            lock.unlock();
        }
    }

    private static final class WorkTask extends AbstractTask {

        private static final Logger LOGGER = LoggerFactory.getLogger(WorkTask.class);

        private final WorkBuffer workBuffer;
        private final RepositoryHandler repositoryHandler;
        private final SinkHandler sinkHandler;
        private final AtomicBoolean runningFlag = new AtomicBoolean(true);

        public WorkTask(WorkBuffer workBuffer, RepositoryHandler repositoryHandler, SinkHandler sinkHandler) {
            this.workBuffer = workBuffer;
            this.repositoryHandler = repositoryHandler;
            this.sinkHandler = sinkHandler;
        }

        @Override
        protected void todo() {
            while (runningFlag.get()) {
                Judger judger = null;
                try {
                    judger = workBuffer.poll();
                    if (Objects.nonNull(judger)) {
                        work(judger);
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

        private void work(Judger judger) throws Exception {
            DataInfo dataInfo = judger.judge(repositoryHandler);
            sinkHandler.sinkData(dataInfo);
        }
    }

    private static class WorkBuffer {

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
                Judger judger = buffer.remove(0);
                provideCondition.signalAll();
                return judger;
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
            return bufferSize;
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
