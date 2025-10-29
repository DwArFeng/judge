package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 过滤器不存在异常。
 *
 * @author wangyc
 * @since 2.3.0
 */
public class FilterNotExistsException extends HandlerException {

    private static final long serialVersionUID = -5376284717927480167L;

    private final LongIdKey FilterKey;

    public FilterNotExistsException(LongIdKey FilterKey) {
        this.FilterKey = FilterKey;
    }

    public FilterNotExistsException(Throwable cause, LongIdKey FilterKey) {
        super(cause);
        this.FilterKey = FilterKey;
    }

    @Override
    public String getMessage() {
        return "过滤器 " + FilterKey + " 不存在";
    }
}
