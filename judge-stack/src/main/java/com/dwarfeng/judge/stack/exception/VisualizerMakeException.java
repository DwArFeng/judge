package com.dwarfeng.judge.stack.exception;

/**
 * 可视化器构造异常。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class VisualizerMakeException extends VisualizerException {

    private static final long serialVersionUID = 1525434974839905L;

    public VisualizerMakeException() {
    }

    public VisualizerMakeException(String message, Throwable cause) {
        super(message, cause);
    }

    public VisualizerMakeException(String message) {
        super(message);
    }

    public VisualizerMakeException(Throwable cause) {
        super(cause);
    }
}
