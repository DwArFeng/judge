package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 判断结果模态更新值无效异常。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class InvalidJudgementModalUpdateValueException extends HandlerException {

    private static final long serialVersionUID = 5611153321137233816L;

    private final double value;

    public InvalidJudgementModalUpdateValueException(double value) {
        this.value = value;
    }

    public InvalidJudgementModalUpdateValueException(Throwable cause, double value) {
        super(cause);
        this.value = value;
    }

    @Override
    public String getMessage() {
        return "判断结果模态更新值无效, 取值范围: [0.0, 1.0], 实际值: " + value;
    }
}
