package com.dwarfeng.judge.stack.exception;

/**
 * 过滤器器执行异常。
 *
 * @author wangyc
 * @since 2.3.0
 */
public class FilterExecutionException extends FilterException {

    private static final long serialVersionUID = 3587153782100766607L;

    public FilterExecutionException() {
    }

    public FilterExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public FilterExecutionException(String message) {
        super(message);
    }

    public FilterExecutionException(Throwable cause) {
        super(cause);
    }
}
