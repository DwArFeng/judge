package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.exception.DriveException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 驱动处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface DriveHandler extends Handler {

    /**
     * 是否注册。
     *
     * @return 是否注册。
     */
    boolean isRegistered();

    /**
     * 注册。
     *
     * @throws DriveException 驱动异常。
     */
    void register() throws DriveException;

    /**
     * 取消注册。
     *
     * @throws DriveException 驱动异常。
     */
    void unregister() throws DriveException;
}
