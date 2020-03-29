package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 判断异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class RepositoryException extends HandlerException {

    private static final long serialVersionUID = 630711358579611383L;

    public RepositoryException() {
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(Throwable cause) {
        super(cause);
    }
}
