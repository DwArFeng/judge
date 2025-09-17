package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 无效的判断结果判断值异常。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class InvalidJudgementValueException extends HandlerException {

    private static final long serialVersionUID = 8633937195545953888L;

    private final double value;

    public InvalidJudgementValueException(double value) {
        this.value = value;
    }

    public InvalidJudgementValueException(Throwable cause, double value) {
        super(cause);
        this.value = value;
    }

    @Override
    public String getMessage() {
        return "判断结果判断值 " + value + " 无效, 取值范围: [0.0, 1.0]";
    }
}
