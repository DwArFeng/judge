package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 下沉器信息不存在异常。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerInfoNotExistsException extends HandlerException {

    private static final long serialVersionUID = 5228180433870031009L;

    private final LongIdKey sinkerInfoKey;

    public SinkerInfoNotExistsException(LongIdKey sinkerInfoKey) {
        this.sinkerInfoKey = sinkerInfoKey;
    }

    public SinkerInfoNotExistsException(Throwable cause, LongIdKey sinkerInfoKey) {
        super(cause);
        this.sinkerInfoKey = sinkerInfoKey;
    }

    @Override
    public String getMessage() {
        return "下沉器信息 " + sinkerInfoKey + " 不存在";
    }
}
