package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 适配器不存在异常。
 *
 * @author wangyc
 * @since 2.3.0
 */
public class AdapterNotExistsException extends HandlerException {

    private static final long serialVersionUID = -4028189933180445187L;

    private final LongIdKey AdapterKey;

    public AdapterNotExistsException(LongIdKey AdapterKey) {
        this.AdapterKey = AdapterKey;
    }

    public AdapterNotExistsException(Throwable cause, LongIdKey AdapterKey) {
        super(cause);
        this.AdapterKey = AdapterKey;
    }

    @Override
    public String getMessage() {
        return "适配器 " + AdapterKey + " 不存在";
    }
}
