package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.StartableHandler;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since 1.8.0
 */
public interface ResetHandler extends StartableHandler {

    /**
     * 重置指派。
     *
     * @throws HandlerException 处理器异常。
     */
    void resetAssign() throws HandlerException;

    /**
     * 重置评估。
     *
     * @throws HandlerException 处理器异常。
     */
    void resetEvaluate() throws HandlerException;
}
