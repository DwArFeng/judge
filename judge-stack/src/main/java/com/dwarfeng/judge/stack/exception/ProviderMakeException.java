package com.dwarfeng.judge.stack.exception;

/**
 * 提供器构造异常。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class ProviderMakeException extends ProviderException {

    private static final long serialVersionUID = -6962503894235411225L;

    public ProviderMakeException() {
    }

    public ProviderMakeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProviderMakeException(String message) {
        super(message);
    }

    public ProviderMakeException(Throwable cause) {
        super(cause);
    }
}
