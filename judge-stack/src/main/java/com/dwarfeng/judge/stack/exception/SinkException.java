package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 水槽异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class SinkException extends HandlerException {

    private static final long serialVersionUID = -4506514101596144767L;

    public SinkException() {
    }

    public SinkException(String message, Throwable cause) {
        super(message, cause);
    }

    public SinkException(String message) {
        super(message);
    }

    public SinkException(Throwable cause) {
        super(cause);
    }
}
