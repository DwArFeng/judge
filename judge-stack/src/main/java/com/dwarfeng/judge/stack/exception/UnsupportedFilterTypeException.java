package com.dwarfeng.judge.stack.exception;

/**
 * 不支持的过滤器类型异常。
 *
 * @author wangyc
 * @since 2.3.0
 */
public class UnsupportedFilterTypeException extends FilterException {

    private static final long serialVersionUID = -8838069129028574707L;

    private final String type;

    public UnsupportedFilterTypeException(String type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "不支持的过滤器类型: " + type;
    }
}
