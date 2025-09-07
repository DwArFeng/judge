package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.judge.stack.bean.key.JudgerVariableKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 判断器变量不存在异常。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class JudgerVariableNotExistsException extends HandlerException {

    private static final long serialVersionUID = 5440042852867740776L;

    private final JudgerVariableKey judgerVariableKey;

    public JudgerVariableNotExistsException(JudgerVariableKey judgerVariableKey) {
        this.judgerVariableKey = judgerVariableKey;
    }

    public JudgerVariableNotExistsException(Throwable cause, JudgerVariableKey judgerVariableKey) {
        super(cause);
        this.judgerVariableKey = judgerVariableKey;
    }

    @Override
    public String getMessage() {
        return "判断器变量 " + judgerVariableKey + " 不存在";
    }
}
