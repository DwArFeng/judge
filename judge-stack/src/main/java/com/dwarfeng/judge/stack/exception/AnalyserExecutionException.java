package com.dwarfeng.judge.stack.exception;

/**
 * 分析器器分析异常。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalyserExecutionException extends AnalyserException {

    private static final long serialVersionUID = -3719484033763246676L;

    public AnalyserExecutionException() {
    }

    public AnalyserExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public AnalyserExecutionException(String message) {
        super(message);
    }

    public AnalyserExecutionException(Throwable cause) {
        super(cause);
    }
}
