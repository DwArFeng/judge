package com.dwarfeng.judge.stack.exception;

/**
 * 调度器未启动异常。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class DispatcherNotStartException extends DispatcherException {

    private static final long serialVersionUID = 2118898609422459620L;

    public DispatcherNotStartException() {
    }

    public DispatcherNotStartException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "调度器未启动";
    }
}
