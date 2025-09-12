package com.dwarfeng.judge.stack.exception;

/**
 * 判断器器判断异常。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class JudgerExecutionException extends JudgerException {

    private static final long serialVersionUID = -6124830854832840907L;

    public JudgerExecutionException() {
    }

    public JudgerExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public JudgerExecutionException(String message) {
        super(message);
    }

    public JudgerExecutionException(Throwable cause) {
        super(cause);
    }
}
