package com.dwarfeng.judge.stack.exception;

/**
 * 适配器构造异常。
 *
 * @author wangyc
 * @since 2.3.0
 */
public class AdapterMakeException extends AdapterException {

    private static final long serialVersionUID = -6154954683947563994L;

    public AdapterMakeException() {
    }

    public AdapterMakeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdapterMakeException(String message) {
        super(message);
    }

    public AdapterMakeException(Throwable cause) {
        super(cause);
    }
}
