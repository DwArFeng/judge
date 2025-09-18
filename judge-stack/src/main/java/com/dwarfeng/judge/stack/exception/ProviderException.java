package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 提供器异常。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class ProviderException extends HandlerException {

    private static final long serialVersionUID = -5578063259367125581L;

    public ProviderException() {
    }

    public ProviderException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProviderException(String message) {
        super(message);
    }

    public ProviderException(Throwable cause) {
        super(cause);
    }
}
