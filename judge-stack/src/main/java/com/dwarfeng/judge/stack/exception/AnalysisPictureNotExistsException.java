package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 分析结果图片不存在异常。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalysisPictureNotExistsException extends HandlerException {

    private static final long serialVersionUID = -924672747963470614L;

    private final LongIdKey key;

    public AnalysisPictureNotExistsException(LongIdKey key) {
        this.key = key;
    }

    public AnalysisPictureNotExistsException(Throwable cause, LongIdKey key) {
        super(cause);
        this.key = key;
    }

    @Override
    public String getMessage() {
        return "分析结果图片 " + key + " 不存在";
    }
}
