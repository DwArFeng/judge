package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 无效的分析结果文件包插入或更新类型异常。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class InvalidAnalysisFilePackUpsertTypeException extends HandlerException {

    private static final long serialVersionUID = 8395319121175367566L;

    private final int upsertType;

    public InvalidAnalysisFilePackUpsertTypeException(int upsertType) {
        this.upsertType = upsertType;
    }

    public InvalidAnalysisFilePackUpsertTypeException(Throwable cause, int upsertType) {
        super(cause);
        this.upsertType = upsertType;
    }

    @Override
    public String getMessage() {
        return "分析结果文件包插入或更新类型 " + upsertType + " 无效";
    }
}
