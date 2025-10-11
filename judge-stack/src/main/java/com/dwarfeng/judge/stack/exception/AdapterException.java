package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 适配器异常。
 *
 * @author wangyc
 * @since 2.3.0
 */
public class AdapterException extends HandlerException {

    private static final long serialVersionUID = 8356919260848946559L;

    public AdapterException() {
    }

    public AdapterException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdapterException(String message) {
        super(message);
    }

    public AdapterException(Throwable cause) {
        super(cause);
    }
}
