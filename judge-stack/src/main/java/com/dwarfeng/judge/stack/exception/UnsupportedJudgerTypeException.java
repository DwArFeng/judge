package com.dwarfeng.judge.stack.exception;

/**
 * 不支持的判断器类型异常。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class UnsupportedJudgerTypeException extends JudgerException {

    private static final long serialVersionUID = 953770917346868689L;

    private final String type;

    public UnsupportedJudgerTypeException(String type) {
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
