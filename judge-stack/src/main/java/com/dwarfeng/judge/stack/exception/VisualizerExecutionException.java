package com.dwarfeng.judge.stack.exception;

/**
 * 可视化器器可视化异常。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class VisualizerExecutionException extends VisualizerException {

    private static final long serialVersionUID = -6699628602237759218L;

    public VisualizerExecutionException() {
    }

    public VisualizerExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public VisualizerExecutionException(String message) {
        super(message);
    }

    public VisualizerExecutionException(Throwable cause) {
        super(cause);
    }
}
