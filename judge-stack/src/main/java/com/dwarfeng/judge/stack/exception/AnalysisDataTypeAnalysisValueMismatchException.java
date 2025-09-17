package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 分析结果数据类型与分析结果值不匹配异常。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisDataTypeAnalysisValueMismatchException extends HandlerException {

    private static final long serialVersionUID = 6569051221115377781L;

    private final int dataType;
    private final Class<?> expectedValueClazz;
    private final Class<?> actualValueClazz;

    public AnalysisDataTypeAnalysisValueMismatchException(
            int dataType, Class<?> expectedValueClazz, Class<?> actualValueClazz
    ) {
        this.dataType = dataType;
        this.expectedValueClazz = expectedValueClazz;
        this.actualValueClazz = actualValueClazz;
    }

    public AnalysisDataTypeAnalysisValueMismatchException(
            Throwable cause, int dataType, Class<?> expectedValueClazz, Class<?> actualValueClazz
    ) {
        super(cause);
        this.dataType = dataType;
        this.expectedValueClazz = expectedValueClazz;
        this.actualValueClazz = actualValueClazz;
    }

    @Override
    public String getMessage() {
        return "分析结果数据类型与分析结果值不匹配, 数据类型: " + dataType +
                ", 期望的分析结果值类型: " + expectedValueClazz + ", 实际的分析结果值类型: " + actualValueClazz;
    }
}
