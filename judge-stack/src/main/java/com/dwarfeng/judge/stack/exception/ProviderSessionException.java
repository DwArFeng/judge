package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 提供器会话异常。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class ProviderSessionException extends HandlerException {

    private static final long serialVersionUID = -3747394012280447854L;

    public ProviderSessionException() {
    }

    public ProviderSessionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProviderSessionException(String message) {
        super(message);
    }

    public ProviderSessionException(Throwable cause) {
        super(cause);
    }
}
