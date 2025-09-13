package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 驱动器异常。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class DriverException extends HandlerException {

    private static final long serialVersionUID = -2583806257646273238L;

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
