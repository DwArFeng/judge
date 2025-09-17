package com.dwarfeng.judge.stack.exception;

/**
 * 不支持的分析器类型异常。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class UnsupportedAnalyserTypeException extends AnalyserException {

    private static final long serialVersionUID = 7076934104195198178L;

    private final String type;

    public UnsupportedAnalyserTypeException(String type) {
        this.type = type;
    }

    public UnsupportedAnalyserTypeException(Throwable cause, String type) {
        super(cause);
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "不支持的分析器类型: " + type;
    }
}
