package com.dwarfeng.judge.stack.exception;

/**
 * 判断工作禁止异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JudgeWorkDisabledException extends JudgeWorkException {

    private static final long serialVersionUID = 8917421521292213764L;

    public JudgeWorkDisabledException() {
    }

    public JudgeWorkDisabledException(String message, Throwable cause) {
        super(message, cause);
    }

    public JudgeWorkDisabledException(String message) {
        super(message);
    }

    public JudgeWorkDisabledException(Throwable cause) {
        super(cause);
    }
}
