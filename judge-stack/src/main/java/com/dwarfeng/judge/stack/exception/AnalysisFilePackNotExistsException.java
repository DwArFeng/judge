package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 分析结果文件包不存在异常。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisFilePackNotExistsException extends HandlerException {

    private static final long serialVersionUID = -8149181284173913286L;

    private final LongIdKey key;

    public AnalysisFilePackNotExistsException(LongIdKey key) {
        this.key = key;
    }

    public AnalysisFilePackNotExistsException(Throwable cause, LongIdKey key) {
        super(cause);
        this.key = key;
    }

    @Override
    public String getMessage() {
        return "分析结果文件包 " + key + " 不存在";
    }
}
