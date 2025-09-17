package com.dwarfeng.judge.stack.exception;

/**
 * 接收器执行异常。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class ReceiverExecutionException extends ReceiverException {

    private static final long serialVersionUID = -989760942447561253L;

    public ReceiverExecutionException() {
    }

    public ReceiverExecutionException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "接收器执行异常";
    }
}
