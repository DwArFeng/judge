package com.dwarfeng.judge.stack.exception;

/**
 * 提供器器执行异常。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class ProviderExecutionException extends ProviderException {

    private static final long serialVersionUID = -1043321742180330101L;

    public ProviderExecutionException() {
    }

    public ProviderExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProviderExecutionException(String message) {
        super(message);
    }

    public ProviderExecutionException(Throwable cause) {
        super(cause);
    }
}
