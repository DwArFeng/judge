package com.dwarfeng.judge.stack.exception;

/**
 * 过滤器构造异常。
 *
 * @author wangyc
 * @since 2.3.0
 */
public class FilterMakeException extends FilterException {

    private static final long serialVersionUID = 6432842985936576590L;

    public FilterMakeException() {
    }

    public FilterMakeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FilterMakeException(String message) {
        super(message);
    }

    public FilterMakeException(Throwable cause) {
        super(cause);
    }
}
