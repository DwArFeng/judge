package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.judge.stack.bean.key.AnalyserVariableKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 分析器变量不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class AnalyserVariableNotExistsException extends HandlerException {

    private static final long serialVersionUID = 537511320839504794L;

    private final AnalyserVariableKey analyserVariableKey;

    public AnalyserVariableNotExistsException(AnalyserVariableKey analyserVariableKey) {
        this.analyserVariableKey = analyserVariableKey;
    }

    public AnalyserVariableNotExistsException(Throwable cause, AnalyserVariableKey analyserVariableKey) {
        super(cause);
        this.analyserVariableKey = analyserVariableKey;
    }

    @Override
    public String getMessage() {
        return "分析器变量 " + analyserVariableKey + " 不存在";
    }
}
