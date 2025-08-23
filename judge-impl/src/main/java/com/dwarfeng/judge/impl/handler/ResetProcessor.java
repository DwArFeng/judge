package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.handler.*;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since 1.8.0
 */
@Component
public class ResetProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResetProcessor.class);

    private final AssignHandler assignHandler;
    private final AssignLocalCacheHandler assignLocalCacheHandler;

    private final EvaluateHandler evaluateHandler;
    private final EvaluateLocalCacheHandler evaluateLocalCacheHandler;

    private final PushHandler pushHandler;

    public ResetProcessor(
            AssignHandler assignHandler,
            AssignLocalCacheHandler assignLocalCacheHandler,
            EvaluateHandler evaluateHandler,
            EvaluateLocalCacheHandler evaluateLocalCacheHandler,
            PushHandler pushHandler
    ) {
        this.assignHandler = assignHandler;
        this.assignLocalCacheHandler = assignLocalCacheHandler;
        this.evaluateHandler = evaluateHandler;
        this.evaluateLocalCacheHandler = evaluateLocalCacheHandler;
        this.pushHandler = pushHandler;
    }

    public void resetAssign() throws HandlerException {
        // 获取当前指派处理器的在线状态。
        boolean onlineFlag = assignHandler.isOnline();

        // 指派处理器下线，且清空本地缓存。
        assignHandler.offline();
        assignLocalCacheHandler.clear();

        // 如果指派处理器之前是上线的状态，则重新上线。
        if (onlineFlag) {
            assignHandler.online();
        }

        // 消息推送。
        try {
            pushHandler.assignReset();
        } catch (Exception e) {
            LOGGER.warn("推送指派功能重置消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
        }
    }

    public void resetEvaluate() throws HandlerException {
        // 获取当前评估处理器的启用状态。
        boolean enabledFlag = evaluateHandler.isEnabled();

        // 禁用评估处理器，且清空本地缓存。
        evaluateHandler.disable();
        evaluateLocalCacheHandler.clear();

        // 如果评估处理器之前是启用的状态，则重新启用。
        if (enabledFlag) {
            evaluateHandler.enable();
        }

        // 消息推送。
        try {
            pushHandler.evaluateReset();
        } catch (Exception e) {
            LOGGER.warn("推送评估功能重置消息时发生异常, 本次消息将不会被推送, 异常信息如下: ", e);
        }
    }
}
