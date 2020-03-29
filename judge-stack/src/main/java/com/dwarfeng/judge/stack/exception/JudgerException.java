package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 判断异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JudgerException extends HandlerException {

    private static final long serialVersionUID = 630711358579611383L;

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
