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
     * 寻找指定的驱动。
     *
     * @param type 驱动的类型。
     * @return 符合驱动类型的指定的驱动。
     * @throws HandlerException 驱动异常。
     */
    Driver find(String type) throws HandlerException;
}
