package com.dwarfeng.judge.stack.exception;

/**
 * 不支持的下沉器类型异常。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class UnsupportedSinkerTypeException extends SinkerException {

    private static final long serialVersionUID = 15061033354764854L;

    private final String type;

    public UnsupportedSinkerTypeException(String type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "不支持的下沉器类型: " + type;
    }
}
