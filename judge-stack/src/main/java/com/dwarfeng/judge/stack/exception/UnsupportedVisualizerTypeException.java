package com.dwarfeng.judge.stack.exception;

/**
 * 不支持的可视化器类型异常。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class UnsupportedVisualizerTypeException extends VisualizerException {

    private static final long serialVersionUID = 1176091536433736111L;

    private final String type;

    public UnsupportedVisualizerTypeException(String type) {
        this.type = type;
    }

    public UnsupportedVisualizerTypeException(Throwable cause, String type) {
        super(cause);
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "不支持的可视化器类型: " + type;
    }
}
