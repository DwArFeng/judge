package com.dwarfeng.judge.stack.exception;

/**
 * 调度器执行异常。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class DispatcherExecutionException extends DispatcherException {

    private static final long serialVersionUID = 192793079365234562L;

    public DispatcherExecutionException() {
    }

    public DispatcherExecutionException(Throwable cause) {
        super(cause);
    }

    @Override
    public String getMessage() {
        return "调度器执行异常";
    }
}
