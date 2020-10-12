package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 指派处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface AssignHandler extends Handler {

    /**
     * 指派处理器是否上线。
     *
     * @return 是否上线。
     * @throws HandlerException 处理器异常。
     */
    boolean isOnline() throws HandlerException;

    /**
     * 上线指派处理器。
     *
     * @throws HandlerException 处理器异常。
     */
    void online() throws HandlerException;

    /**
     * 下线指派处理器。
     *
     * @throws HandlerException 处理器异常。
     */
    void offline() throws HandlerException;
}
