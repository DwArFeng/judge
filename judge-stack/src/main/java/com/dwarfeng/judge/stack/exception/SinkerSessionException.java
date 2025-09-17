package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 下沉器会话异常。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerSessionException extends HandlerException {

    private static final long serialVersionUID = 2700157018200501110L;

    public SinkerSessionException() {
    }

    public SinkerSessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public SinkerSessionException(String message) {
        super(message);
    }

    public SinkerSessionException(Throwable cause) {
        super(cause);
    }
}
