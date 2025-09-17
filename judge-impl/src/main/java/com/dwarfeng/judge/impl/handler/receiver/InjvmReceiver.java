package com.dwarfeng.judge.impl.handler.receiver;

import com.dwarfeng.judge.sdk.handler.receiver.AbstractReceiver;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Injvm 接收器。
 *
 * <p>
 * 该接收器适用于单节点服务，提供了虚拟机内部的直接调用方式。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
@Component
public class InjvmReceiver extends AbstractReceiver {

    public static final String RECEIVER_TYPE = "injvm";

    private static final Logger LOGGER = LoggerFactory.getLogger(InjvmReceiver.class);

    private final InjvmDispatcherCaller dispatcherCaller = new InjvmDispatcherCaller();

    public InjvmReceiver() {
        super(RECEIVER_TYPE);
    }

    @Override
    protected void doStart() {
        LOGGER.info("injvm receiver 上线...");
    }

    @Override
    protected void doStop() {
        LOGGER.info("injvm receiver 下线...");
    }

    public InjvmDispatcherCaller getDispatcherCaller() {
        return dispatcherCaller;
    }

    @Override
    public String toString() {
        return "InjvmReceiver{" +
                "dispatcherCaller=" + dispatcherCaller +
                ", receiverType='" + receiverType + '\'' +
                ", context=" + context +
                '}';
    }

    /**
     * Injvm 调度器调用器。
     *
     * <p>
     * 供 Injvm 调度器调用的调用器。
     *
     * @author DwArFeng
     * @since 2.0.0-beta
     */
    public class InjvmDispatcherCaller {

        /**
         * 返回接收器是否已经启动。
         *
         * @return 接收器是否已经启动。
         */
        public boolean isStarted() {
            return InjvmReceiver.this.isStarted();
        }

        /**
         * 执行指定的部件键。
         *
         * <p>
         * 调用该方法之前，需要先调用 {@link #isStarted()} 方法，确保接收器已经启动。<br>
         * 如果接收器没有启动，则应该抛出调度器侧对应的异常。
         *
         * @param sectionKey 指定的部件键。
         */
        public void execute(LongIdKey sectionKey) {
            // 调用上下文的执行方法。
            try {
                InjvmReceiver.this.context.execute(sectionKey);
            } catch (Exception e) {
                String message = "接收器调用执行动作时发生异常, 将忽略统计任务执行 1 次, " +
                        "相关 sectionKey 为 " + sectionKey + ", 异常信息如下: ";
                LOGGER.warn(message, e);
            }
        }

        @Override
        public String toString() {
            return "InjvmDispatcherCaller{}";
        }
    }
}
