package com.dwarfeng.judge.sdk.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 事件推送器。
 *
 * @author DwArFeng
 * @since 1.8.0
 */
public interface Pusher {

    /**
     * 返回制造器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 制造器是否支持指定的类型。
     */
    boolean supportType(String type);

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
