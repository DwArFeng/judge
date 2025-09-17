package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 无效的分析结果数据类型异常。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class InvalidAnalysisDataTypeException extends HandlerException {

    private static final long serialVersionUID = -5166246695253848996L;

    private final int dataType;

    public InvalidAnalysisDataTypeException(int dataType) {
        this.dataType = dataType;
    }

    public InvalidAnalysisDataTypeException(Throwable cause, int dataType) {
        super(cause);
        this.dataType = dataType;
    }

    @Override
    public String getMessage() {
        return "分析结果数据类型 " + dataType + " 无效";
    }
}
