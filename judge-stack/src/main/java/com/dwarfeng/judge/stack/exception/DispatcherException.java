package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 调度器异常。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class DispatcherException extends HandlerException {

    private static final long serialVersionUID = -426261132514891965L;

    public DispatcherException() {
    }

    public DispatcherException(String message, Throwable cause) {
        super(message, cause);
    }

    public DispatcherException(String message) {
        super(message);
    }

    public DispatcherException(Throwable cause) {
        super(cause);
    }
}
