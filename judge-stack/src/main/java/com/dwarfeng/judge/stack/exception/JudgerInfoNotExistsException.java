package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 判断器信息不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JudgerInfoNotExistsException extends HandlerException {

    private static final long serialVersionUID = -1737163427677257517L;

    private final LongIdKey judgerInfoKey;

    public JudgerInfoNotExistsException(LongIdKey judgerInfoKey) {
        this.judgerInfoKey = judgerInfoKey;
    }

    public JudgerInfoNotExistsException(Throwable cause, LongIdKey judgerInfoKey) {
        super(cause);
        this.judgerInfoKey = judgerInfoKey;
    }

    @Override
    public String getMessage() {
        return "判断器信息 " + judgerInfoKey + " 不存在";
    }
}
