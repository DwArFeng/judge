package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 过滤器异常。
 *
 * @author wangyc
 * @since 2.3.0
 */
public class FilterException extends HandlerException {

    private static final long serialVersionUID = 9181289585874522276L;

    public FilterException() {
    }

    public FilterException(String message, Throwable cause) {
        super(message, cause);
    }

    public FilterException(String message) {
        super(message);
    }

    public FilterException(Throwable cause) {
        super(cause);
    }
}
