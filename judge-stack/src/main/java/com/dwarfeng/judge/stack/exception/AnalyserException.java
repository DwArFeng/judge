package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 分析器异常。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalyserException extends HandlerException {

    private static final long serialVersionUID = 1483193889767361441L;

    public AnalyserException() {
    }

    public AnalyserException(String message, Throwable cause) {
        super(message, cause);
    }

    public AnalyserException(String message) {
        super(message);
    }

    public AnalyserException(Throwable cause) {
        super(cause);
    }
}
