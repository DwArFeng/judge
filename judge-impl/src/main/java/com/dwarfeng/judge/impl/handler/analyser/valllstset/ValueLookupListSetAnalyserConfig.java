package com.dwarfeng.judge.impl.handler.analyser.valllstset;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.List;

/**
 * 值查询列表设置分析器配置。
 *
 * <p>
 * 该配置描述一次 lookup 查询参数以及多个字段列表输出 <code>Analysis</code> 的写入规则。
 * 分析器执行单次 <code>Context.lookup(...)</code>，从 <code>LookupResult.datas</code> 的所有行中提取字段列表，
 * 按指定格式序列化后写入字符串或文件类型分析结果。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
public class ValueLookupListSetAnalyserConfig implements Bean {

    private static final long serialVersionUID = 7785041942753597157L;

    @JSONField(name = "#lookup", ordinal = 1)
    private String lookupRem = "lookup 查询参数，必填，用于构造 LookupInfo。";

    @JSONField(name = "lookup", ordinal = 2)
    private LookupConfig lookup;

    @JSONField(name = "#outputs", ordinal = 3)
    private String outputsRem = "字段列表输出 Analysis 写入规则数组，必填且不能为空。";

    @JSONField(name = "outputs", ordinal = 4)
    private List<OutputConfig> outputs;

    public ValueLookupListSetAnalyserConfig() {
    }

    public ValueLookupListSetAnalyserConfig(LookupConfig lookup, List<OutputConfig> outputs) {
        this.lookup = lookup;
        this.outputs = outputs;
    }

    public String getLookupRem() {
        return lookupRem;
    }

    public void setLookupRem(String lookupRem) {
        this.lookupRem = lookupRem;
    }

    public LookupConfig getLookup() {
        return lookup;
    }

    public void setLookup(LookupConfig lookup) {
        this.lookup = lookup;
    }

    public String getOutputsRem() {
        return outputsRem;
    }

    public void setOutputsRem(String outputsRem) {
        this.outputsRem = outputsRem;
    }

    public List<OutputConfig> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<OutputConfig> outputs) {
        this.outputs = outputs;
    }

    @Override
    public String toString() {
        return "ValueLookupListSetAnalyserConfig{" +
                "lookupRem='" + lookupRem + '\'' +
                ", lookup=" + lookup +
                ", outputsRem='" + outputsRem + '\'' +
                ", outputs=" + outputs +
                '}';
    }

    /**
     * lookup 查询配置。
     *
     * <p>
     * 该配置用于描述 <code>Context.lookup(...)</code> 所需的五参 <code>LookupInfo</code>，
     * 字段语义与值设置分析器中的 <code>LookupConfig</code> 保持一致。
     *
     * @author DwArFeng
     * @since 2.6.1
     */
    public static class LookupConfig implements Bean {

        private static final long serialVersionUID = 1936800882248880550L;

        @JSONField(name = "#provider_info_id", ordinal = 1)
        private String providerInfoIdRem = "提供器信息键，必填。";

        @JSONField(name = "provider_info_id", ordinal = 2)
        private Long providerInfoId;

        @JSONField(name = "#adapter_info_id", ordinal = 3)
        private String adapterInfoIdRem = "适配器信息键，可空。";

        @JSONField(name = "adapter_info_id", ordinal = 4)
        private Long adapterInfoId;

        @JSONField(name = "#filter_info_id", ordinal = 5)
        private String filterInfoIdRem = "过滤器信息键，可空。";

        @JSONField(name = "filter_info_id", ordinal = 6)
        private Long filterInfoId;

        @JSONField(name = "#preset", ordinal = 7)
        private String presetRem = "查询预设，必填。";

        @JSONField(name = "preset", ordinal = 8)
        private String preset;

        @JSONField(name = "#objs", ordinal = 9)
        private String objsRem = "预设对象参数数组，必填但允许为空数组。";

        @JSONField(name = "objs", ordinal = 10)
        private List<LookupObjectConfig> objs;

        public LookupConfig() {
        }

        public LookupConfig(
                Long providerInfoId, Long adapterInfoId, Long filterInfoId, String preset,
                List<LookupObjectConfig> objs
        ) {
            this.providerInfoId = providerInfoId;
            this.adapterInfoId = adapterInfoId;
            this.filterInfoId = filterInfoId;
            this.preset = preset;
            this.objs = objs;
        }

        public String getProviderInfoIdRem() {
            return providerInfoIdRem;
        }

        public void setProviderInfoIdRem(String providerInfoIdRem) {
            this.providerInfoIdRem = providerInfoIdRem;
        }

        public Long getProviderInfoId() {
            return providerInfoId;
        }

        public void setProviderInfoId(Long providerInfoId) {
            this.providerInfoId = providerInfoId;
        }

        public String getAdapterInfoIdRem() {
            return adapterInfoIdRem;
        }

        public void setAdapterInfoIdRem(String adapterInfoIdRem) {
            this.adapterInfoIdRem = adapterInfoIdRem;
        }

        public Long getAdapterInfoId() {
            return adapterInfoId;
        }

        public void setAdapterInfoId(Long adapterInfoId) {
            this.adapterInfoId = adapterInfoId;
        }

        public String getFilterInfoIdRem() {
            return filterInfoIdRem;
        }

        public void setFilterInfoIdRem(String filterInfoIdRem) {
            this.filterInfoIdRem = filterInfoIdRem;
        }

        public Long getFilterInfoId() {
            return filterInfoId;
        }

        public void setFilterInfoId(Long filterInfoId) {
            this.filterInfoId = filterInfoId;
        }

        public String getPresetRem() {
            return presetRem;
        }

        public void setPresetRem(String presetRem) {
            this.presetRem = presetRem;
        }

        public String getPreset() {
            return preset;
        }

        public void setPreset(String preset) {
            this.preset = preset;
        }

        public String getObjsRem() {
            return objsRem;
        }

        public void setObjsRem(String objsRem) {
            this.objsRem = objsRem;
        }

        public List<LookupObjectConfig> getObjs() {
            return objs;
        }

        public void setObjs(List<LookupObjectConfig> objs) {
            this.objs = objs;
        }

        @Override
        public String toString() {
            return "LookupConfig{" +
                    "providerInfoIdRem='" + providerInfoIdRem + '\'' +
                    ", providerInfoId=" + providerInfoId +
                    ", adapterInfoIdRem='" + adapterInfoIdRem + '\'' +
                    ", adapterInfoId=" + adapterInfoId +
                    ", filterInfoIdRem='" + filterInfoIdRem + '\'' +
                    ", filterInfoId=" + filterInfoId +
                    ", presetRem='" + presetRem + '\'' +
                    ", preset='" + preset + '\'' +
                    ", objsRem='" + objsRem + '\'' +
                    ", objs=" + objs +
                    '}';
        }
    }

    /**
     * lookup 对象参数配置。
     *
     * <p>
     * 该配置用于描述 <code>lookup.objs</code> 数组中的单个元素，支持从常量或已有分析结果解析参数值。
     *
     * @author DwArFeng
     * @since 2.6.1
     */
    public static class LookupObjectConfig implements Bean {

        private static final long serialVersionUID = 7048750745127272390L;

        @JSONField(name = "#object_source_type", ordinal = 1)
        private String objectSourceTypeRem = "对象来源类型，合法值为 constant、analysis。";

        @JSONField(name = "object_source_type", ordinal = 2)
        private String objectSourceType;

        @JSONField(name = "#constant_value_type", ordinal = 3)
        private String constantValueTypeRem = "当 object_source_type=constant 时必填，合法值为 string、long、double、boolean、date。";

        @JSONField(name = "constant_value_type", ordinal = 4)
        private String constantValueType;

        @JSONField(name = "#constant_value", ordinal = 5)
        private String constantValueRem = "当 object_source_type=constant 时必填，按 constant_value_type 解析。";

        @JSONField(name = "constant_value", ordinal = 6)
        private String constantValue;

        @JSONField(name = "#analysis_data_id", ordinal = 7)
        private String analysisDataIdRem = "当 object_source_type=analysis 时必填，用于读取已有分析结果。";

        @JSONField(name = "analysis_data_id", ordinal = 8)
        private String analysisDataId;

        public LookupObjectConfig() {
        }

        public LookupObjectConfig(
                String objectSourceType, String constantValueType, String constantValue, String analysisDataId
        ) {
            this.objectSourceType = objectSourceType;
            this.constantValueType = constantValueType;
            this.constantValue = constantValue;
            this.analysisDataId = analysisDataId;
        }

        public String getObjectSourceTypeRem() {
            return objectSourceTypeRem;
        }

        public void setObjectSourceTypeRem(String objectSourceTypeRem) {
            this.objectSourceTypeRem = objectSourceTypeRem;
        }

        public String getObjectSourceType() {
            return objectSourceType;
        }

        public void setObjectSourceType(String objectSourceType) {
            this.objectSourceType = objectSourceType;
        }

        public String getConstantValueTypeRem() {
            return constantValueTypeRem;
        }

        public void setConstantValueTypeRem(String constantValueTypeRem) {
            this.constantValueTypeRem = constantValueTypeRem;
        }

        public String getConstantValueType() {
            return constantValueType;
        }

        public void setConstantValueType(String constantValueType) {
            this.constantValueType = constantValueType;
        }

        public String getConstantValueRem() {
            return constantValueRem;
        }

        public void setConstantValueRem(String constantValueRem) {
            this.constantValueRem = constantValueRem;
        }

        public String getConstantValue() {
            return constantValue;
        }

        public void setConstantValue(String constantValue) {
            this.constantValue = constantValue;
        }

        public String getAnalysisDataIdRem() {
            return analysisDataIdRem;
        }

        public void setAnalysisDataIdRem(String analysisDataIdRem) {
            this.analysisDataIdRem = analysisDataIdRem;
        }

        public String getAnalysisDataId() {
            return analysisDataId;
        }

        public void setAnalysisDataId(String analysisDataId) {
            this.analysisDataId = analysisDataId;
        }

        @Override
        public String toString() {
            return "LookupObjectConfig{" +
                    "objectSourceTypeRem='" + objectSourceTypeRem + '\'' +
                    ", objectSourceType='" + objectSourceType + '\'' +
                    ", constantValueTypeRem='" + constantValueTypeRem + '\'' +
                    ", constantValueType='" + constantValueType + '\'' +
                    ", constantValueRem='" + constantValueRem + '\'' +
                    ", constantValue='" + constantValue + '\'' +
                    ", analysisDataIdRem='" + analysisDataIdRem + '\'' +
                    ", analysisDataId='" + analysisDataId + '\'' +
                    '}';
        }
    }

    /**
     * 单个字段列表输出 Analysis 写入配置。
     *
     * <p>
     * 该配置描述一个目标 <code>Analysis</code> 的输出键、值类型、字段名、序列化格式以及可选 CSV 子配置。
     *
     * @author DwArFeng
     * @since 2.6.1
     */
    public static class OutputConfig implements Bean {

        private static final long serialVersionUID = -785337290684859337L;

        @JSONField(name = "#output_analysis_data_id", ordinal = 1)
        private String outputAnalysisDataIdRem = "输出 Analysis 的 dataStringId，必填。";

        @JSONField(name = "output_analysis_data_id", ordinal = 2)
        private String outputAnalysisDataId;

        @JSONField(name = "#output_analysis_value_type", ordinal = 3)
        private String outputAnalysisValueTypeRem = "输出分析结果值类型，合法值为 string、file。";

        @JSONField(name = "output_analysis_value_type", ordinal = 4)
        private String outputAnalysisValueType;

        @JSONField(name = "#field_name", ordinal = 5)
        private String fieldNameRem = "从 LookupResult.datas 每一行提取的字段名，必填。";

        @JSONField(name = "field_name", ordinal = 6)
        private String fieldName;

        @JSONField(name = "#format", ordinal = 7)
        private String formatRem = "字段列表序列化格式，合法值为 json_array、csv。";

        @JSONField(name = "format", ordinal = 8)
        private String format;

        @JSONField(name = "#file_origin_name", ordinal = 9)
        private String fileOriginNameRem = "当 output_analysis_value_type=file 时必填，用于构造 AnalysisFileUpsertInfo.originName。";

        @JSONField(name = "file_origin_name", ordinal = 10)
        private String fileOriginName;

        @JSONField(name = "#csv", ordinal = 11)
        private String csvRem = "当 format=csv 时的可选子配置。";

        @JSONField(name = "csv", ordinal = 12)
        private CsvConfig csv;

        public OutputConfig() {
        }

        public OutputConfig(
                String outputAnalysisDataId, String outputAnalysisValueType, String fieldName, String format,
                String fileOriginName, CsvConfig csv
        ) {
            this.outputAnalysisDataId = outputAnalysisDataId;
            this.outputAnalysisValueType = outputAnalysisValueType;
            this.fieldName = fieldName;
            this.format = format;
            this.fileOriginName = fileOriginName;
            this.csv = csv;
        }

        public String getOutputAnalysisDataIdRem() {
            return outputAnalysisDataIdRem;
        }

        public void setOutputAnalysisDataIdRem(String outputAnalysisDataIdRem) {
            this.outputAnalysisDataIdRem = outputAnalysisDataIdRem;
        }

        public String getOutputAnalysisDataId() {
            return outputAnalysisDataId;
        }

        public void setOutputAnalysisDataId(String outputAnalysisDataId) {
            this.outputAnalysisDataId = outputAnalysisDataId;
        }

        public String getOutputAnalysisValueTypeRem() {
            return outputAnalysisValueTypeRem;
        }

        public void setOutputAnalysisValueTypeRem(String outputAnalysisValueTypeRem) {
            this.outputAnalysisValueTypeRem = outputAnalysisValueTypeRem;
        }

        public String getOutputAnalysisValueType() {
            return outputAnalysisValueType;
        }

        public void setOutputAnalysisValueType(String outputAnalysisValueType) {
            this.outputAnalysisValueType = outputAnalysisValueType;
        }

        public String getFieldNameRem() {
            return fieldNameRem;
        }

        public void setFieldNameRem(String fieldNameRem) {
            this.fieldNameRem = fieldNameRem;
        }

        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public String getFormatRem() {
            return formatRem;
        }

        public void setFormatRem(String formatRem) {
            this.formatRem = formatRem;
        }

        public String getFormat() {
            return format;
        }

        public void setFormat(String format) {
            this.format = format;
        }

        public String getFileOriginNameRem() {
            return fileOriginNameRem;
        }

        public void setFileOriginNameRem(String fileOriginNameRem) {
            this.fileOriginNameRem = fileOriginNameRem;
        }

        public String getFileOriginName() {
            return fileOriginName;
        }

        public void setFileOriginName(String fileOriginName) {
            this.fileOriginName = fileOriginName;
        }

        public String getCsvRem() {
            return csvRem;
        }

        public void setCsvRem(String csvRem) {
            this.csvRem = csvRem;
        }

        public CsvConfig getCsv() {
            return csv;
        }

        public void setCsv(CsvConfig csv) {
            this.csv = csv;
        }

        @Override
        public String toString() {
            return "OutputConfig{" +
                    "outputAnalysisDataIdRem='" + outputAnalysisDataIdRem + '\'' +
                    ", outputAnalysisDataId='" + outputAnalysisDataId + '\'' +
                    ", outputAnalysisValueTypeRem='" + outputAnalysisValueTypeRem + '\'' +
                    ", outputAnalysisValueType='" + outputAnalysisValueType + '\'' +
                    ", fieldNameRem='" + fieldNameRem + '\'' +
                    ", fieldName='" + fieldName + '\'' +
                    ", formatRem='" + formatRem + '\'' +
                    ", format='" + format + '\'' +
                    ", fileOriginNameRem='" + fileOriginNameRem + '\'' +
                    ", fileOriginName='" + fileOriginName + '\'' +
                    ", csvRem='" + csvRem + '\'' +
                    ", csv=" + csv +
                    '}';
        }
    }

    /**
     * CSV 序列化子配置。
     *
     * <p>
     * 该配置用于描述字段列表按 CSV 格式序列化时的表头、字符集和换行符等行为。
     *
     * @author DwArFeng
     * @since 2.6.1
     */
    public static class CsvConfig implements Bean {

        private static final long serialVersionUID = 8877134116783754837L;

        @JSONField(name = "#include_header", ordinal = 1)
        private String includeHeaderRem = "是否输出表头，默认 false。";

        @JSONField(name = "include_header", ordinal = 2)
        private Boolean includeHeader;

        @JSONField(name = "#header_name", ordinal = 3)
        private String headerNameRem = "表头名称，未配置时可使用 field_name。";

        @JSONField(name = "header_name", ordinal = 4)
        private String headerName;

        @JSONField(name = "#charset", ordinal = 5)
        private String charsetRem = "文件或字符串编码，默认 UTF-8。";

        @JSONField(name = "charset", ordinal = 6)
        private String charset;

        @JSONField(name = "#line_separator", ordinal = 7)
        private String lineSeparatorRem = "换行符，支持 LF、CRLF，默认 LF。";

        @JSONField(name = "line_separator", ordinal = 8)
        private String lineSeparator;

        public CsvConfig() {
        }

        public CsvConfig(Boolean includeHeader, String headerName, String charset, String lineSeparator) {
            this.includeHeader = includeHeader;
            this.headerName = headerName;
            this.charset = charset;
            this.lineSeparator = lineSeparator;
        }

        public String getIncludeHeaderRem() {
            return includeHeaderRem;
        }

        public void setIncludeHeaderRem(String includeHeaderRem) {
            this.includeHeaderRem = includeHeaderRem;
        }

        public Boolean getIncludeHeader() {
            return includeHeader;
        }

        public void setIncludeHeader(Boolean includeHeader) {
            this.includeHeader = includeHeader;
        }

        public String getHeaderNameRem() {
            return headerNameRem;
        }

        public void setHeaderNameRem(String headerNameRem) {
            this.headerNameRem = headerNameRem;
        }

        public String getHeaderName() {
            return headerName;
        }

        public void setHeaderName(String headerName) {
            this.headerName = headerName;
        }

        public String getCharsetRem() {
            return charsetRem;
        }

        public void setCharsetRem(String charsetRem) {
            this.charsetRem = charsetRem;
        }

        public String getCharset() {
            return charset;
        }

        public void setCharset(String charset) {
            this.charset = charset;
        }

        public String getLineSeparatorRem() {
            return lineSeparatorRem;
        }

        public void setLineSeparatorRem(String lineSeparatorRem) {
            this.lineSeparatorRem = lineSeparatorRem;
        }

        public String getLineSeparator() {
            return lineSeparator;
        }

        public void setLineSeparator(String lineSeparator) {
            this.lineSeparator = lineSeparator;
        }

        @Override
        public String toString() {
            return "CsvConfig{" +
                    "includeHeaderRem='" + includeHeaderRem + '\'' +
                    ", includeHeader=" + includeHeader +
                    ", headerNameRem='" + headerNameRem + '\'' +
                    ", headerName='" + headerName + '\'' +
                    ", charsetRem='" + charsetRem + '\'' +
                    ", charset='" + charset + '\'' +
                    ", lineSeparatorRem='" + lineSeparatorRem + '\'' +
                    ", lineSeparator='" + lineSeparator + '\'' +
                    '}';
        }
    }
}
