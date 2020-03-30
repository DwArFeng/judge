package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 判断工作异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JudgeWorkException extends HandlerException {

    private static final long serialVersionUID = -9202677786055549724L;

    public JudgeWorkException() {
    }

    public JudgeWorkException(String message, Throwable cause) {
        super(message, cause);
    }

    public JudgeWorkException(String message) {
        super(message);
    }

    public JudgeWorkException(Throwable cause) {
        super(cause);
    }
}
