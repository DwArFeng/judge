package com.dwarfeng.judge.stack.exception;

/**
 * 不支持的判断类型异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class UnsupportedDriverTypeException extends DriverException {

    private static final long serialVersionUID = -5375806453349262055L;

    private final String type;

    public UnsupportedDriverTypeException(String type) {
        this.type = type;
    }

    public UnsupportedDriverTypeException(String message, Throwable cause, String type) {
        super(message, cause);
        this.type = type;
    }

    public UnsupportedDriverTypeException(String message, String type) {
        super(message);
        this.type = type;
    }

    public UnsupportedDriverTypeException(Throwable cause, String type) {
        super(cause);
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "不支持的驱动器类型: " + type;
    }
}
