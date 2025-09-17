package com.dwarfeng.judge.stack.exception;

/**
 * 下沉器器执行异常。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerExecutionException extends SinkerException {

    private static final long serialVersionUID = 2277677596076636026L;

    public SinkerExecutionException() {
    }

    public SinkerExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public SinkerExecutionException(String message) {
        super(message);
    }

    public SinkerExecutionException(Throwable cause) {
        super(cause);
    }
}
