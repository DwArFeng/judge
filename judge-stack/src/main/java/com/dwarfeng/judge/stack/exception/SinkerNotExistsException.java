package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 下沉器不存在异常。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerNotExistsException extends HandlerException {

    private static final long serialVersionUID = 5783366108183547291L;

    private final LongIdKey sinkerKey;

    public SinkerNotExistsException(LongIdKey sinkerKey) {
        this.sinkerKey = sinkerKey;
    }

    public SinkerNotExistsException(Throwable cause, LongIdKey sinkerKey) {
        super(cause);
        this.sinkerKey = sinkerKey;
    }

    @Override
    public String getMessage() {
        return "下沉器 " + sinkerKey + " 不存在";
    }
}
