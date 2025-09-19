package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 可视化器异常。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class VisualizerException extends HandlerException {

    private static final long serialVersionUID = -4807932573361260487L;

    public VisualizerException() {
    }

    public VisualizerException(String message, Throwable cause) {
        super(message, cause);
    }

    public VisualizerException(String message) {
        super(message);
    }

    public VisualizerException(Throwable cause) {
        super(cause);
    }
}
