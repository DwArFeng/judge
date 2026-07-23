package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 报警模态更新信息非法异常。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class InvalidAlarmModalUpdateInfoException extends HandlerException {

    private static final long serialVersionUID = 6939203207819732827L;

    private final String reason;

    public InvalidAlarmModalUpdateInfoException(String reason) {
        this.reason = reason;
    }

    public InvalidAlarmModalUpdateInfoException(Throwable cause, String reason) {
        super(cause);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String getMessage() {
        return "报警模态更新信息非法: " + reason;
    }
}
