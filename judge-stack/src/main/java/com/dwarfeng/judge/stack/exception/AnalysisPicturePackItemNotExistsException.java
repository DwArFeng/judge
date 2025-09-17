package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 分析结果图片包条目不存在异常。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisPicturePackItemNotExistsException extends HandlerException {

    private static final long serialVersionUID = 5771064577383494673L;

    private final LongIdKey key;

    public AnalysisPicturePackItemNotExistsException(LongIdKey key) {
        this.key = key;
    }

    public AnalysisPicturePackItemNotExistsException(Throwable cause, LongIdKey key) {
        super(cause);
        this.key = key;
    }

    @Override
    public String getMessage() {
        return "分析结果图片包条目 " + key + " 不存在";
    }
}
