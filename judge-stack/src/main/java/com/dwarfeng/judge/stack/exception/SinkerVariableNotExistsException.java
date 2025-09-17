package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.judge.stack.bean.key.SinkerVariableKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 下沉器变量不存在异常。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerVariableNotExistsException extends HandlerException {

    private static final long serialVersionUID = -5332361876870862472L;

    private final SinkerVariableKey sinkerVariableKey;

    public SinkerVariableNotExistsException(SinkerVariableKey sinkerVariableKey) {
        this.sinkerVariableKey = sinkerVariableKey;
    }

    public SinkerVariableNotExistsException(Throwable cause, SinkerVariableKey sinkerVariableKey) {
        super(cause);
        this.sinkerVariableKey = sinkerVariableKey;
    }

    @Override
    public String getMessage() {
        return "下沉器变量 " + sinkerVariableKey + " 不存在";
    }
}
