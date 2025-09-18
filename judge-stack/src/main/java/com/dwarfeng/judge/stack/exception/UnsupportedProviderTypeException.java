package com.dwarfeng.judge.stack.exception;

/**
 * 不支持的提供器类型异常。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class UnsupportedProviderTypeException extends ProviderException {

    private static final long serialVersionUID = -7146152799558560700L;

    private final String type;

    public UnsupportedProviderTypeException(String type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "不支持的提供器类型: " + type;
    }
}
