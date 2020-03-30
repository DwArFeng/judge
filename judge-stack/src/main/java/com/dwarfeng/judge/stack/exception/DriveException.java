package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 驱动异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class DriveException extends HandlerException {

    private static final long serialVersionUID = 628446198025045030L;

    public DriveException() {
    }

    public DriveException(String message, Throwable cause) {
        super(message, cause);
    }

    public DriveException(String message) {
        super(message);
    }

    public DriveException(Throwable cause) {
        super(cause);
    }
}
