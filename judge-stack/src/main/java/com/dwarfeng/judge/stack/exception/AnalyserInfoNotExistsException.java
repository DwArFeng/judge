package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 分析器信息不存在异常。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class AnalyserInfoNotExistsException extends HandlerException {

    private static final long serialVersionUID = -3072213253563097958L;

    private final LongIdKey analyserInfoKey;

    public AnalyserInfoNotExistsException(LongIdKey analyserInfoKey) {
        this.analyserInfoKey = analyserInfoKey;
    }

    public AnalyserInfoNotExistsException(Throwable cause, LongIdKey analyserInfoKey) {
        super(cause);
        this.analyserInfoKey = analyserInfoKey;
    }

    @Override
    public String getMessage() {
        return "分析器信息 " + analyserInfoKey + " 不存在";
    }
}
