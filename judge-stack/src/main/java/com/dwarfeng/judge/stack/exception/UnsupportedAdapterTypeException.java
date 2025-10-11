package com.dwarfeng.judge.stack.exception;

/**
 * 不支持的适配器类型异常。
 *
 * @author wangyc
 * @since 2.3.0
 */
public class UnsupportedAdapterTypeException extends AdapterException {

    private static final long serialVersionUID = -1001944088761807689L;

    private final String type;

    public UnsupportedAdapterTypeException(String type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "不支持的适配器类型: " + type;
    }
}
