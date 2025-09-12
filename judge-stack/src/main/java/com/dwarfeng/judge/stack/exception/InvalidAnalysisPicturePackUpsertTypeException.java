package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 无效的分析结果图片包插入或更新类型异常。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class InvalidAnalysisPicturePackUpsertTypeException extends HandlerException {

    private static final long serialVersionUID = 1784777883395968962L;

    private final int upsertType;

    public InvalidAnalysisPicturePackUpsertTypeException(int upsertType) {
        this.upsertType = upsertType;
    }

    public InvalidAnalysisPicturePackUpsertTypeException(Throwable cause, int upsertType) {
        super(cause);
        this.upsertType = upsertType;
    }

    @Override
    public String getMessage() {
        return "分析结果图片包插入或更新类型 " + upsertType + " 无效";
    }
}
