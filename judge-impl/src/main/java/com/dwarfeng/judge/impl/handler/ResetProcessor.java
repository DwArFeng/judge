package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.handler.*;
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
    private final SuperviseHandler superviseHandler;
    private final DriveLocalCacheHandler driveLocalCacheHandler;
    private final SinkerSessionHoldHandler sinkerSessionHoldHandler;
    private final SinkerLocalCacheHandler sinkerLocalCacheHandler;
    private final SectionBindingLocalCacheHandler sectionBindingLocalCacheHandler;
    private final SinkerBindingLocalCacheHandler sinkerBindingLocalCacheHandler;
    private final ProviderSessionHoldHandler providerSessionHoldHandler;
    private final ProviderLocalCacheHandler providerLocalCacheHandler;
    private final PushHandler pushHandler;

    private final Lock lock = new ReentrantLock();

    public ResetProcessor(
            ReceiveHandler receiveHandler,
            JobLocalCacheHandler jobLocalCacheHandler,
            SuperviseHandler superviseHandler,
            DriveLocalCacheHandler driveLocalCacheHandler,
            SinkerSessionHoldHandler sinkerSessionHoldHandler,
            SinkerLocalCacheHandler sinkerLocalCacheHandler,
            SectionBindingLocalCacheHandler sectionBindingLocalCacheHandler,
            SinkerBindingLocalCacheHandler sinkerBindingLocalCacheHandler,
            ProviderSessionHoldHandler providerSessionHoldHandler,
            ProviderLocalCacheHandler providerLocalCacheHandler,
            PushHandler pushHandler
    ) {
        this.receiveHandler = receiveHandler;
        this.jobLocalCacheHandler = jobLocalCacheHandler;
        this.superviseHandler = superviseHandler;
        this.driveLocalCacheHandler = driveLocalCacheHandler;
        this.sinkerSessionHoldHandler = sinkerSessionHoldHandler;
        this.sinkerLocalCacheHandler = sinkerLocalCacheHandler;
        this.sectionBindingLocalCacheHandler = sectionBindingLocalCacheHandler;
        this.sinkerBindingLocalCacheHandler = sinkerBindingLocalCacheHandler;
        this.providerSessionHoldHandler = providerSessionHoldHandler;
        this.providerLocalCacheHandler = providerLocalCacheHandler;
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

    public void resetSupervise() throws HandlerException {
        lock.lock();
        try {
            doResetSupervise();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        } finally {
            lock.unlock();
        }
    }

    private void doResetSupervise() throws Exception {
        // 获取当前的主管处理器的状态。
        boolean superviseHandlerStarted = superviseHandler.isStarted();

        // 主管处理器停止，且清空本地缓存。
        superviseHandler.stop();
        driveLocalCacheHandler.clear();

        // 如果主管处理器之前是启动的，则重新启动。
        if (superviseHandlerStarted) {
            superviseHandler.start();
        }

        // 消息推送。
        try {
            pushHandler.superviseReset();
        } catch (Exception e) {
            LOGGER.warn("推送主管功能重置消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
        }
    }

    public void resetSink() throws HandlerException {
        lock.lock();
        try {
            doResetSink();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        } finally {
            lock.unlock();
        }
    }

    private void doResetSink() throws Exception {
        // 获取当前的接收处理器的状态。
        boolean receiveHandlerStarted = receiveHandler.isStarted();

        // 接收处理器停止。
        receiveHandler.stop();

        // 关闭并清空下沉会话持有器。
        sinkerSessionHoldHandler.closeAndClear();
        // 清空下沉本地缓存。
        sinkerLocalCacheHandler.clear();
        // 清空部件绑定本地缓存。
        sectionBindingLocalCacheHandler.clear();
        // 清空下沉器绑定本地缓存。
        sinkerBindingLocalCacheHandler.clear();

        // 如果接收处理器之前是启动的，则重新启动。
        if (receiveHandlerStarted) {
            receiveHandler.start();
        }

        // 消息推送。
        try {
            pushHandler.sinkReset();
        } catch (Exception e) {
            LOGGER.warn("推送下沉功能重置消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
        }
    }

    public void resetProvide() throws HandlerException {
        lock.lock();
        try {
            doResetProvide();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        } finally {
            lock.unlock();
        }
    }

    private void doResetProvide() throws Exception {
        // 获取当前的接收处理器的状态。
        boolean receiveHandlerStarted = receiveHandler.isStarted();

        // 接收处理器停止。
        receiveHandler.stop();

        // 关闭并清空提供会话持有器。
        providerSessionHoldHandler.closeAndClear();
        // 清空提供本地缓存。
        providerLocalCacheHandler.clear();

        // 如果接收处理器之前是启动的，则重新启动。
        if (receiveHandlerStarted) {
            receiveHandler.start();
        }

        // 消息推送。
        try {
            pushHandler.provideReset();
        } catch (Exception e) {
            LOGGER.warn("推送提供功能重置消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
        }
    }
}
