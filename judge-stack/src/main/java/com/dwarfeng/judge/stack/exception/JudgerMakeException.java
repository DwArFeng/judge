package com.dwarfeng.judge.stack.exception;

/**
 * 判断构造异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JudgerMakeException extends JudgerException {

    private static final long serialVersionUID = 3676863893525095647L;

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
