package com.dwarfeng.judge.stack.exception;

/**
 * 接收器未启动异常。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class ReceiverNotStartException extends ReceiverException {

    private static final long serialVersionUID = -9070813053140330237L;

    public ReceiverNotStartException() {
    }

    public ReceiverNotStartException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "接收器未启动";
    }
}
