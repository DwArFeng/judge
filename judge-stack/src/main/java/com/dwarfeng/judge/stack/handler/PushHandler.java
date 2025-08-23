package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 推送器处理器。
 *
 * @author DwArFeng
 * @since 1.8.0
 */
public interface PushHandler extends Handler {

    /**
     * 指派功能重置时评估的广播操作。
     *
     * @throws HandlerException 处理器异常。
     */
    void assignReset() throws HandlerException;

    /**
     * 评估功能重置时评估的广播操作。
     *
     * @throws HandlerException 处理器异常。
     */
    void evaluateReset() throws HandlerException;
}
