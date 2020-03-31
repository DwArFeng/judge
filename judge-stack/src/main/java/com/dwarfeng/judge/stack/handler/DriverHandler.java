package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 驱动处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface DriverHandler extends Handler {

    /**
     * 是否注册。
     *
     * @return 是否注册。
     */
    boolean isRegistered();

    /**
     * 注册。
     *
     * @throws HandlerException 处理器异常。
     */
    void register() throws HandlerException;

    /**
     * 取消注册。
     *
     * @throws HandlerException 处理器异常。
     */
    void unregister() throws HandlerException;
}
