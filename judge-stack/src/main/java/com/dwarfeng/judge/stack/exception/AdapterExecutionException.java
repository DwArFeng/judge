package com.dwarfeng.judge.stack.exception;

/**
 * 适配器器执行异常。
 *
 * @author wangyc
 * @since 2.3.0
 */
public class AdapterExecutionException extends AdapterException {

    private static final long serialVersionUID = -4689493911740890726L;

    public AdapterExecutionException() {
    }

    public AdapterExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdapterExecutionException(String message) {
        super(message);
    }

    public AdapterExecutionException(Throwable cause) {
        super(cause);
    }
}
