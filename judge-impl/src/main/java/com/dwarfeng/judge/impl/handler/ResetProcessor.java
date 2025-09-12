package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.handler.JobLocalCacheHandler;
import com.dwarfeng.judge.stack.handler.PushHandler;
import com.dwarfeng.judge.stack.handler.ReceiveHandler;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since 1.8.0
 */
@Component
public class ResetProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResetProcessor.class);

    private final ReceiveHandler receiveHandler;
    private final JobLocalCacheHandler jobLocalCacheHandler;

    private final PushHandler pushHandler;

    private final Lock lock = new ReentrantLock();

    public ResetProcessor(
            ReceiveHandler receiveHandler,
            JobLocalCacheHandler jobLocalCacheHandler,
            PushHandler pushHandler
    ) {
        this.receiveHandler = receiveHandler;
        this.jobLocalCacheHandler = jobLocalCacheHandler;
        this.pushHandler = pushHandler;
    }

    public void resetJob() throws HandlerException {
        lock.lock();
        try {
            doResetJob();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        } finally {
            lock.unlock();
        }
    }

    private void doResetJob() throws Exception {
        // 获取当前的接收处理器的状态。
        boolean receiveHandlerStarted = receiveHandler.isStarted();

        // 接收处理器停止，且清空本地缓存。
        receiveHandler.stop();
        jobLocalCacheHandler.clear();

        // 如果接收处理器之前是启动的，则重新启动。
        if (receiveHandlerStarted) {
            receiveHandler.start();
        }

        // 消息推送。
        try {
            pushHandler.jobReset();
        } catch (Exception e) {
            LOGGER.warn("推送作业功能重置消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
        }
    }
}
