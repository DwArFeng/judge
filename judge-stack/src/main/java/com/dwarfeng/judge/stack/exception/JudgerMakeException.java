package com.dwarfeng.judge.stack.exception;

/**
 * 判断器构造异常。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class JudgerMakeException extends JudgerException {

    private static final long serialVersionUID = 2457878046521313646L;

    public JudgerMakeException() {
    }

    public JudgerMakeException(String message, Throwable cause) {
        super(message, cause);
    }

    public JudgerMakeException(String message) {
        super(message);
    }

    public JudgerMakeException(Throwable cause) {
        super(cause);
    }
}
