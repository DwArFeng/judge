package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.EvaluateInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 评估理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface EvaluateHandler extends Handler {

    /**
     * 评估指派处理器是否被启用。
     *
     * @return 评估指派处理器是否被启用。
     * @throws HandlerException 处理器异常。
     */
    boolean isEnabled() throws HandlerException;

    /**
     * 启用评估指派处理器。
     *
     * @throws HandlerException 处理器异常。
     */
    void enable() throws HandlerException;

    /**
     * 禁用评估指派处理器。
     *
     * @throws HandlerException 处理器异常。
     */
    void disable() throws HandlerException;

    /**
     * 对指定的评估信息进行评估。
     *
     * @param evaluateInfo 指定的评估信息。
     * @throws HandlerException 处理器异常。
     */
    void evaluate(EvaluateInfo evaluateInfo) throws HandlerException;
}
