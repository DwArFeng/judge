package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 判断器异常。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class JudgerException extends HandlerException {

    private static final long serialVersionUID = -2150776185453120376L;

    public JudgerException() {
    }

    public JudgerException(String message, Throwable cause) {
        super(message, cause);
    }

    public JudgerException(String message) {
        super(message);
    }

    public JudgerException(Throwable cause) {
        super(cause);
    }
}
