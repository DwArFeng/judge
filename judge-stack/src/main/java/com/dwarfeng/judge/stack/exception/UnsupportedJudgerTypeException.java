package com.dwarfeng.judge.stack.exception;

/**
 * 不支持的判断类型异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class UnsupportedJudgerTypeException extends JudgerException {

    private static final long serialVersionUID = -4562245164963855692L;

    private final String type;

    public UnsupportedJudgerTypeException(String type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "不支持的判断器类型: " + type;
    }
}
