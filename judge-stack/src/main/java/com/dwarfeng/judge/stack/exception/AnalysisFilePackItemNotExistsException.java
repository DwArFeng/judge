package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 分析结果文件包条目不存在异常。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisFilePackItemNotExistsException extends HandlerException {

    private static final long serialVersionUID = 1694386418604214455L;

    private final LongIdKey key;

    public AnalysisFilePackItemNotExistsException(LongIdKey key) {
        this.key = key;
    }

    public AnalysisFilePackItemNotExistsException(Throwable cause, LongIdKey key) {
        super(cause);
        this.key = key;
    }

    @Override
    public String getMessage() {
        return "分析结果文件包条目 " + key + " 不存在";
    }
}
