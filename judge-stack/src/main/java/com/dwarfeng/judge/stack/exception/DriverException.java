package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 驱动异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class DriverException extends HandlerException {

    private static final long serialVersionUID = 628446198025045030L;

    public DriverException() {
    }

    public DriverException(String message, Throwable cause) {
        super(message, cause);
    }

    public DriverException(String message) {
        super(message);
    }

    public DriverException(Throwable cause) {
        super(cause);
    }
}
