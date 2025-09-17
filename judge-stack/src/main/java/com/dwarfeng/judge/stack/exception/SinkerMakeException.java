package com.dwarfeng.judge.stack.exception;

/**
 * 下沉器构造异常。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerMakeException extends SinkerException {

    private static final long serialVersionUID = -7301083080686928450L;

    public SinkerMakeException() {
    }

    public SinkerMakeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SinkerMakeException(String message) {
        super(message);
    }

    public SinkerMakeException(Throwable cause) {
        super(cause);
    }
}
