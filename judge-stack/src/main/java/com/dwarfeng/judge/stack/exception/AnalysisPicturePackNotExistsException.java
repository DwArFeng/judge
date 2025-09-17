package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 分析结果图片包不存在异常。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisPicturePackNotExistsException extends HandlerException {

    private static final long serialVersionUID = -2574723173591612740L;

    private final LongIdKey key;

    public AnalysisPicturePackNotExistsException(LongIdKey key) {
        this.key = key;
    }

    public AnalysisPicturePackNotExistsException(Throwable cause, LongIdKey key) {
        super(cause);
        this.key = key;
    }

    @Override
    public String getMessage() {
        return "分析结果图片包 " + key + " 不存在";
    }
}
