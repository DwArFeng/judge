package com.dwarfeng.judge.stack.exception;

/**
 * 分析器构造异常。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalyserMakeException extends AnalyserException {

    private static final long serialVersionUID = -6713800227758561136L;

    public AnalyserMakeException() {
    }

    public AnalyserMakeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AnalyserMakeException(String message) {
        super(message);
    }

    public AnalyserMakeException(Throwable cause) {
        super(cause);
    }
}
