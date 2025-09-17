package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.judge.stack.bean.key.JudgementKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 判断结果不存在异常。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class JudgementNotExistsException extends HandlerException {

    private static final long serialVersionUID = -6569097581945055319L;

    private final JudgementKey judgementKey;

    public JudgementNotExistsException(JudgementKey judgementKey) {
        this.judgementKey = judgementKey;
    }

    public JudgementNotExistsException(Throwable cause, JudgementKey judgementKey) {
        super(cause);
        this.judgementKey = judgementKey;
    }

    @Override
    public String getMessage() {
        return "判断结果 " + judgementKey + " 不存在";
    }
}
