package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.handler.AssignHandler;
import com.dwarfeng.judge.stack.handler.AssignLocalCacheHandler;
import com.dwarfeng.judge.stack.handler.EvaluateHandler;
import com.dwarfeng.judge.stack.handler.EvaluateLocalCacheHandler;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since 1.8.0
 */
@Component
public class ResetProcessor {

    private final AssignHandler assignHandler;
    private final AssignLocalCacheHandler assignLocalCacheHandler;

    private final EvaluateHandler evaluateHandler;
    private final EvaluateLocalCacheHandler evaluateLocalCacheHandler;

    public ResetProcessor(
            AssignHandler assignHandler,
            AssignLocalCacheHandler assignLocalCacheHandler,
            EvaluateHandler evaluateHandler,
            EvaluateLocalCacheHandler evaluateLocalCacheHandler
    ) {
        this.assignHandler = assignHandler;
        this.assignLocalCacheHandler = assignLocalCacheHandler;
        this.evaluateHandler = evaluateHandler;
        this.evaluateLocalCacheHandler = evaluateLocalCacheHandler;
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
    }
}
