package com.dwarfeng.judge.impl.handler.analyser.valllstset;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.judge.stack.exception.AnalyserExecutionException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 值查询列表设置分析器列表序列化工具。
 *
 * <p>
 * 该工具负责将 lookup 查询结果中提取的字段值列表按 <code>json_array</code> 或 <code>csv</code>
 * 格式序列化为文本内容，供分析器写入字符串或文件类型 <code>Analysis</code>。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
final class ValueLookupListSetListSerializer {

    static final String FORMAT_JSON_ARRAY = "json_array";
    static final String FORMAT_CSV = "csv";

    static final String LINE_SEPARATOR_LF = "LF";
    static final String LINE_SEPARATOR_CRLF = "CRLF";

    static final String DEFAULT_CHARSET = "UTF-8";

    private ValueLookupListSetListSerializer() {
        throw new IllegalStateException("禁止实例化");
    }

    /**
     * 将字段值列表序列化为字符串内容。
     *
     * @param fieldPrefix 配置项前缀，用于异常定位。
     * @param fieldValues 字段值列表。
     * @param format      序列化格式。
     * @param fieldName   字段名，CSV 表头缺省值。
     * @param csvConfig   CSV 子配置，仅 <code>format=csv</code> 时使用。
     * @return 序列化后的字符串内容。
     * @throws AnalyserExecutionException 序列化失败时抛出。
     */
    static String serializeToString(
            String fieldPrefix, List<Object> fieldValues, String format, String fieldName,
            ValueLookupListSetAnalyserConfig.CsvConfig csvConfig
    ) throws AnalyserExecutionException {
        checkRequired(fieldPrefix + ".format", format);
        switch (format.trim().toLowerCase()) {
            case FORMAT_JSON_ARRAY:
                return serializeJsonArray(fieldPrefix, fieldValues);
            case FORMAT_CSV:
                return serializeCsv(fieldPrefix, fieldValues, fieldName, csvConfig);
            default:
                throw new AnalyserExecutionException(
                        "值查询列表设置分析器配置项 " + fieldPrefix + ".format 非法: " + format
                );
        }
    }

    /**
     * 将字段值列表序列化为字节内容。
     *
     * <p>
     * 当输出类型为文件时，字符串内容会按 CSV 子配置中的字符集编码为字节数组；
     * 若格式为 <code>json_array</code> 且未提供 CSV 字符集，则默认使用 UTF-8。
     *
     * @param fieldPrefix 配置项前缀，用于异常定位。
     * @param fieldValues 字段值列表。
     * @param format      序列化格式。
     * @param fieldName   字段名，CSV 表头缺省值。
     * @param csvConfig   CSV 子配置，仅 <code>format=csv</code> 时使用。
     * @return 序列化后的字节内容。
     * @throws AnalyserExecutionException 序列化失败时抛出。
     */
    static byte[] serializeToBytes(
            String fieldPrefix, List<Object> fieldValues, String format, String fieldName,
            ValueLookupListSetAnalyserConfig.CsvConfig csvConfig
    ) throws AnalyserExecutionException {
        String content = serializeToString(fieldPrefix, fieldValues, format, fieldName, csvConfig);
        String charsetName = resolveCharset(fieldPrefix, format, csvConfig);
        try {
            return content.getBytes(charsetName);
        } catch (UnsupportedEncodingException e) {
            throw new AnalyserExecutionException(
                    "值查询列表设置分析器配置项 " + fieldPrefix + ".csv.charset 非法: " + charsetName, e
            );
        }
    }

    private static String serializeJsonArray(String fieldPrefix, List<Object> fieldValues)
            throws AnalyserExecutionException {
        try {
            return JSON.toJSONString(fieldValues);
        } catch (Exception e) {
            throw new AnalyserExecutionException(
                    "值查询列表设置分析器字段列表 JSON 序列化失败, " + fieldPrefix, e
            );
        }
    }

    private static String serializeCsv(
            String fieldPrefix, List<Object> fieldValues, String fieldName,
            ValueLookupListSetAnalyserConfig.CsvConfig csvConfig
    ) throws AnalyserExecutionException {
        String lineSeparator = resolveLineSeparator(fieldPrefix, csvConfig);
        boolean includeHeader = resolveIncludeHeader(csvConfig);
        String headerName = resolveHeaderName(fieldPrefix, fieldName, csvConfig);

        List<String> lines = new ArrayList<>();
        if (includeHeader) {
            lines.add(escapeCsvValue(headerName));
        }
        for (Object fieldValue : fieldValues) {
            lines.add(escapeCsvValue(formatCsvCellValue(fieldValue)));
        }
        return String.join(lineSeparator, lines);
    }

    /**
     * 按 RFC 4180 规则转义 CSV 单元格值。
     *
     * <p>
     * 当值包含英文逗号、双引号、回车或换行时，使用双引号包裹并将内部双引号转义为两个双引号。
     *
     * @param value 原始单元格文本。
     * @return 转义后的单元格文本。
     */
    static String escapeCsvValue(String value) {
        if (Objects.isNull(value)) {
            return "";
        }
        boolean needQuote = value.indexOf(',') >= 0
                || value.indexOf('"') >= 0
                || value.indexOf('\r') >= 0
                || value.indexOf('\n') >= 0;
        if (!needQuote) {
            return value;
        }
        return '"' + value.replace("\"", "\"\"") + '"';
    }

    private static String formatCsvCellValue(Object value) {
        if (Objects.isNull(value)) {
            return "";
        }
        return String.valueOf(value);
    }

    private static boolean resolveIncludeHeader(ValueLookupListSetAnalyserConfig.CsvConfig csvConfig) {
        if (Objects.isNull(csvConfig) || Objects.isNull(csvConfig.getIncludeHeader())) {
            return false;
        }
        return csvConfig.getIncludeHeader();
    }

    private static String resolveHeaderName(
            String fieldPrefix, String fieldName, ValueLookupListSetAnalyserConfig.CsvConfig csvConfig
    ) throws AnalyserExecutionException {
        if (Objects.nonNull(csvConfig) && Objects.nonNull(csvConfig.getHeaderName())
                && !csvConfig.getHeaderName().trim().isEmpty()) {
            return csvConfig.getHeaderName();
        }
        checkRequired(fieldPrefix + ".field_name", fieldName);
        return fieldName;
    }

    private static String resolveCharset(
            String fieldPrefix, String format, ValueLookupListSetAnalyserConfig.CsvConfig csvConfig
    ) throws AnalyserExecutionException {
        String charsetName = DEFAULT_CHARSET;
        if (Objects.nonNull(csvConfig) && Objects.nonNull(csvConfig.getCharset())
                && !csvConfig.getCharset().trim().isEmpty()) {
            charsetName = csvConfig.getCharset().trim();
        }
        if (!Charset.isSupported(charsetName)) {
            String configField = FORMAT_CSV.equalsIgnoreCase(format.trim())
                    ? fieldPrefix + ".csv.charset"
                    : fieldPrefix + ".format";
            throw new AnalyserExecutionException(
                    "值查询列表设置分析器配置项 " + configField + " 非法: " + charsetName
            );
        }
        return charsetName;
    }

    private static String resolveLineSeparator(
            String fieldPrefix, ValueLookupListSetAnalyserConfig.CsvConfig csvConfig
    ) throws AnalyserExecutionException {
        if (Objects.isNull(csvConfig) || Objects.isNull(csvConfig.getLineSeparator())
                || csvConfig.getLineSeparator().trim().isEmpty()) {
            return "\n";
        }
        switch (csvConfig.getLineSeparator().trim().toUpperCase()) {
            case LINE_SEPARATOR_LF:
                return "\n";
            case LINE_SEPARATOR_CRLF:
                return "\r\n";
            default:
                throw new AnalyserExecutionException(
                        "值查询列表设置分析器配置项 " + fieldPrefix + ".csv.line_separator 非法: " +
                                csvConfig.getLineSeparator()
                );
        }
    }

    private static void checkRequired(String fieldName, String value) throws AnalyserExecutionException {
        if (Objects.isNull(value) || value.trim().isEmpty()) {
            throw new AnalyserExecutionException("值查询列表设置分析器配置项 " + fieldName + " 不能为空");
        }
    }
}
