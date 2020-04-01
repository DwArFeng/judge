package com.dwarfeng.judge.stack.exception;

/**
 * 不支持的判断类型异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class UnsupportedJudgerTypeException extends JudgerException {

    private static final long serialVersionUID = -1519636454442356727L;

    private final String type;

    public UnsupportedJudgerTypeException(String type) {
        this.type = type;
    }

    public UnsupportedJudgerTypeException(String message, Throwable cause, String type) {
        super(message, cause);
        this.type = type;
    }

    public UnsupportedJudgerTypeException(String message, String type) {
        super(message);
        this.type = type;
    }

    public UnsupportedJudgerTypeException(Throwable cause, String type) {
        super(cause);
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "不支持的判断器类型: " + type;
    }
}