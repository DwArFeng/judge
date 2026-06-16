package com.dwarfeng.judge.impl.handler.analyser.valllstset;

import com.dwarfeng.judge.stack.exception.AnalyserExecutionException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * 值查询列表设置分析器值类型转换工具。
 *
 * <p>
 * 该工具负责将 lookup 对象参数中的常量配置解析为 Java 对象，
 * 供 <code>lookup.objs</code> 构造 <code>Object[]</code> 时使用。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
final class ValueLookupListSetValueConverter {

    static final String VALUE_TYPE_STRING = "string";
    static final String VALUE_TYPE_LONG = "long";
    static final String VALUE_TYPE_DOUBLE = "double";
    static final String VALUE_TYPE_BOOLEAN = "boolean";
    static final String VALUE_TYPE_DATE = "date";

    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private ValueLookupListSetValueConverter() {
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
                        "值查询列表设置分析器配置项 " + fieldName + " 的值类型非法: " + valueType
                );
        }
    }

    private static String normalizeValueType(String fieldName, String valueType)
            throws AnalyserExecutionException {
        checkRequired(fieldName, valueType);
        return valueType.trim().toLowerCase();
    }

    private static Long parseLong(String fieldName, String value) throws AnalyserExecutionException {
        try {
            return Long.parseLong(value.trim());
        } catch (Exception e) {
            throw new AnalyserExecutionException(
                    "值查询列表设置分析器配置项 " + fieldName + " 无法按 long 解析: " + value, e
            );
        }
    }

    private static Double parseDouble(String fieldName, String value) throws AnalyserExecutionException {
        try {
            return Double.parseDouble(value.trim());
        } catch (Exception e) {
            throw new AnalyserExecutionException(
                    "值查询列表设置分析器配置项 " + fieldName + " 无法按 double 解析: " + value, e
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
                "值查询列表设置分析器配置项 " + fieldName + " 无法按 boolean 解析: " + value
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
                    "值查询列表设置分析器配置项 " + fieldName + " 无法按 date 解析: " + value, e
            );
        }
    }

    private static void checkRequired(String fieldName, String value) throws AnalyserExecutionException {
        if (Objects.isNull(value) || value.trim().isEmpty()) {
            throw new AnalyserExecutionException("值查询列表设置分析器配置项 " + fieldName + " 不能为空");
        }
    }
}
