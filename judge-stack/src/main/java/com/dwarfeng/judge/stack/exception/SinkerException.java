package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 下沉器异常。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerException extends HandlerException {

    private static final long serialVersionUID = -4067892226954378822L;

    public SinkerException() {
    }

    public SinkerException(String message, Throwable cause) {
        super(message, cause);
    }

    public SinkerException(String message) {
        super(message);
    }

    public SinkerException(Throwable cause) {
        super(cause);
    }
}
