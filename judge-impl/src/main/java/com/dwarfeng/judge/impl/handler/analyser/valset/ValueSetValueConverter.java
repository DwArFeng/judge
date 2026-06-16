package com.dwarfeng.judge.impl.handler.analyser.valset;

import com.dwarfeng.judge.stack.exception.AnalyserExecutionException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * 值设置分析器值类型转换工具。
 *
 * <p>
 * 该工具负责将常量字符串、已有分析结果值或 lookup 查询结果统一转换为
 * <code>string</code>、<code>long</code>、<code>double</code>、<code>boolean</code>、
 * <code>date</code> 五类输出值。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
final class ValueSetValueConverter {

    static final String VALUE_TYPE_STRING = "string";
    static final String VALUE_TYPE_LONG = "long";
    static final String VALUE_TYPE_DOUBLE = "double";
    static final String VALUE_TYPE_BOOLEAN = "boolean";
    static final String VALUE_TYPE_DATE = "date";

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private ValueSetValueConverter() {
        throw new IllegalStateException("禁止实例化");
    }

    /**
     * 按常量值类型解析配置字符串。
     *
     * @param fieldName 配置项名称，用于异常定位。
     * @param valueType 常量值类型。
     * @param rawValue  常量原始字符串。
     * @return 解析后的 Java 对象。
     * @throws AnalyserExecutionException 解析失败时抛出。
     */
    static Object parseConstantValue(String fieldName, String valueType, String rawValue)
            throws AnalyserExecutionException {
        checkRequired(fieldName, rawValue);
        switch (normalizeValueType(fieldName, valueType)) {
            case VALUE_TYPE_STRING:
                return rawValue;
            case VALUE_TYPE_LONG:
                return parseLong(fieldName, rawValue);
            case VALUE_TYPE_DOUBLE:
                return parseDouble(fieldName, rawValue);
            case VALUE_TYPE_BOOLEAN:
                return parseBoolean(fieldName, rawValue);
            case VALUE_TYPE_DATE:
                return parseDate(fieldName, rawValue);
            default:
                throw new AnalyserExecutionException(
                        "值设置分析器配置项 " + fieldName + " 的值类型非法: " + valueType
                );
        }
    }

    /**
     * 将源值转换为指定输出值类型。
     *
     * @param sourceField 源字段名称，用于异常定位。
     * @param targetType  目标值类型。
     * @param sourceValue 源值对象。
     * @return 转换后的输出值。
     * @throws AnalyserExecutionException 转换失败时抛出。
     */
    // 为了保证代码的可读性，此处代码不做简化。
    @SuppressWarnings("SameParameterValue")
    static Object convertToOutputValue(String sourceField, String targetType, Object sourceValue)
            throws AnalyserExecutionException {
        if (Objects.isNull(sourceValue)) {
            throw new AnalyserExecutionException(
                    "值设置分析器源字段 " + sourceField + " 的值为 null, 目标值类型: " + targetType
            );
        }
        switch (normalizeValueType("output_analysis_value_type", targetType)) {
            case VALUE_TYPE_STRING:
                return convertToString(sourceField, targetType, sourceValue);
            case VALUE_TYPE_LONG:
                return convertToLong(sourceField, targetType, sourceValue);
            case VALUE_TYPE_DOUBLE:
                return convertToDouble(sourceField, targetType, sourceValue);
            case VALUE_TYPE_BOOLEAN:
                return convertToBoolean(sourceField, targetType, sourceValue);
            case VALUE_TYPE_DATE:
                return convertToDate(sourceField, targetType, sourceValue);
            default:
                throw new AnalyserExecutionException(
                        "值设置分析器配置项 output_analysis_value_type 非法: " + targetType
                );
        }
    }

    static int resolveAnalysisDataType(String valueType) throws AnalyserExecutionException {
        switch (normalizeValueType("output_analysis_value_type", valueType)) {
            case VALUE_TYPE_STRING:
                return com.dwarfeng.judge.sdk.util.Constants.ANALYSIS_TYPE_STRING;
            case VALUE_TYPE_LONG:
                return com.dwarfeng.judge.sdk.util.Constants.ANALYSIS_TYPE_LONG;
            case VALUE_TYPE_DOUBLE:
                return com.dwarfeng.judge.sdk.util.Constants.ANALYSIS_TYPE_DOUBLE;
            case VALUE_TYPE_BOOLEAN:
                return com.dwarfeng.judge.sdk.util.Constants.ANALYSIS_TYPE_BOOLEAN;
            case VALUE_TYPE_DATE:
                return com.dwarfeng.judge.sdk.util.Constants.ANALYSIS_TYPE_DATE;
            default:
                throw new AnalyserExecutionException(
                        "值设置分析器配置项 output_analysis_value_type 非法: " + valueType
                );
        }
    }

    static void checkRequired(String fieldName, String value) throws AnalyserExecutionException {
        if (Objects.isNull(value) || value.trim().isEmpty()) {
            throw new AnalyserExecutionException("值设置分析器配置项 " + fieldName + " 不能为空");
        }
    }

    static void checkRequired(String fieldName, Object value) throws AnalyserExecutionException {
        if (Objects.isNull(value)) {
            throw new AnalyserExecutionException("值设置分析器配置项 " + fieldName + " 不能为空");
        }
    }

    private static String normalizeValueType(String fieldName, String valueType) throws AnalyserExecutionException {
        checkRequired(fieldName, valueType);
        return valueType.trim().toLowerCase();
    }

    // 为了保证代码的可读性，此处代码不做简化。
    @SuppressWarnings({"unused", "RedundantThrows"})
    private static String convertToString(String sourceField, String targetType, Object sourceValue)
            throws AnalyserExecutionException {
        if (sourceValue instanceof String) {
            return (String) sourceValue;
        }
        if (sourceValue instanceof Date) {
            return new SimpleDateFormat(DATE_PATTERN).format((Date) sourceValue);
        }
        return String.valueOf(sourceValue);
    }

    private static Long convertToLong(String sourceField, String targetType, Object sourceValue)
            throws AnalyserExecutionException {
        if (sourceValue instanceof Long) {
            return (Long) sourceValue;
        }
        if (sourceValue instanceof Integer) {
            return ((Integer) sourceValue).longValue();
        }
        if (sourceValue instanceof Short) {
            return ((Short) sourceValue).longValue();
        }
        if (sourceValue instanceof Byte) {
            return ((Byte) sourceValue).longValue();
        }
        if (sourceValue instanceof Double) {
            return ((Double) sourceValue).longValue();
        }
        if (sourceValue instanceof Float) {
            return ((Float) sourceValue).longValue();
        }
        if (sourceValue instanceof Number) {
            return ((Number) sourceValue).longValue();
        }
        if (sourceValue instanceof String) {
            return parseLong(sourceField, (String) sourceValue);
        }
        if (sourceValue instanceof Date) {
            return ((Date) sourceValue).getTime();
        }
        throw conversionFailed(sourceField, sourceValue, targetType);
    }

    private static Double convertToDouble(String sourceField, String targetType, Object sourceValue)
            throws AnalyserExecutionException {
        if (sourceValue instanceof Double) {
            return (Double) sourceValue;
        }
        if (sourceValue instanceof Float) {
            return ((Float) sourceValue).doubleValue();
        }
        if (sourceValue instanceof Long) {
            return ((Long) sourceValue).doubleValue();
        }
        if (sourceValue instanceof Integer) {
            return ((Integer) sourceValue).doubleValue();
        }
        if (sourceValue instanceof Short) {
            return ((Short) sourceValue).doubleValue();
        }
        if (sourceValue instanceof Byte) {
            return ((Byte) sourceValue).doubleValue();
        }
        if (sourceValue instanceof Number) {
            return ((Number) sourceValue).doubleValue();
        }
        if (sourceValue instanceof String) {
            return parseDouble(sourceField, (String) sourceValue);
        }
        if (sourceValue instanceof Date) {
            return (double) ((Date) sourceValue).getTime();
        }
        throw conversionFailed(sourceField, sourceValue, targetType);
    }

    private static Boolean convertToBoolean(String sourceField, String targetType, Object sourceValue)
            throws AnalyserExecutionException {
        if (sourceValue instanceof Boolean) {
            return (Boolean) sourceValue;
        }
        if (sourceValue instanceof String) {
            return parseBoolean(sourceField, (String) sourceValue);
        }
        throw conversionFailed(sourceField, sourceValue, targetType);
    }

    private static Date convertToDate(String sourceField, String targetType, Object sourceValue)
            throws AnalyserExecutionException {
        if (sourceValue instanceof Date) {
            return (Date) sourceValue;
        }
        if (sourceValue instanceof Number) {
            return new Date(((Number) sourceValue).longValue());
        }
        if (sourceValue instanceof String) {
            return parseDate(sourceField, (String) sourceValue);
        }
        throw conversionFailed(sourceField, sourceValue, targetType);
    }

    private static Long parseLong(String fieldName, String value) throws AnalyserExecutionException {
        try {
            return Long.parseLong(value.trim());
        } catch (Exception e) {
            throw new AnalyserExecutionException(
                    "值设置分析器配置项 " + fieldName + " 无法按 long 解析: " + value, e
            );
        }
    }

    private static Double parseDouble(String fieldName, String value) throws AnalyserExecutionException {
        try {
            return Double.parseDouble(value.trim());
        } catch (Exception e) {
            throw new AnalyserExecutionException(
                    "值设置分析器配置项 " + fieldName + " 无法按 double 解析: " + value, e
            );
        }
    }

    private static Boolean parseBoolean(String fieldName, String value) throws AnalyserExecutionException {
        String normalized = value.trim().toLowerCase();
        if ("true".equals(normalized)) {
            return Boolean.TRUE;
        }
        if ("false".equals(normalized)) {
            return Boolean.FALSE;
        }
        throw new AnalyserExecutionException(
                "值设置分析器配置项 " + fieldName + " 无法按 boolean 解析: " + value
        );
    }

    private static Date parseDate(String fieldName, String value) throws AnalyserExecutionException {
        String trimmed = value.trim();
        try {
            return new SimpleDateFormat(DATE_PATTERN).parse(trimmed);
        } catch (ParseException ignored) {
            // 继续尝试按毫秒时间戳解析。
        }
        try {
            return new Date(Long.parseLong(trimmed));
        } catch (Exception e) {
            throw new AnalyserExecutionException(
                    "值设置分析器配置项 " + fieldName + " 无法按 date 解析: " + value, e
            );
        }
    }

    private static AnalyserExecutionException conversionFailed(
            String sourceField, Object sourceValue, String targetType
    ) {
        return new AnalyserExecutionException(
                "值设置分析器源字段 " + sourceField + " 无法转换为目标值类型 " + targetType +
                        ", 源值类型: " + sourceValue.getClass().getName() + ", 源值: " + sourceValue
        );
    }
}
