package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 分析结果文件不存在异常。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisFileNotExistsException extends HandlerException {

    private static final long serialVersionUID = -6152295820660035169L;

    private final LongIdKey key;

    public AnalysisFileNotExistsException(LongIdKey key) {
        this.key = key;
    }

    public AnalysisFileNotExistsException(Throwable cause, LongIdKey key) {
        super(cause);
        this.key = key;
    }

    @Override
    public String getMessage() {
        return "分析结果文件 " + key + " 不存在";
    }
}
