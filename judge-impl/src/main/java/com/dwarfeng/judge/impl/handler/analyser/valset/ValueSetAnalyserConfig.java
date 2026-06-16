package com.dwarfeng.judge.impl.handler.analyser.valset;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.List;

/**
 * 值设置分析器配置。
 *
 * <p>
 * 该配置用于描述值设置分析器的输出目标、源值来源以及 lookup 相关参数。
 * 分析器按配置取得源值，转换为指定分析值类型后写入目标 <code>Analysis</code>。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
public class ValueSetAnalyserConfig implements Bean {

    private static final long serialVersionUID = 4771966927509161714L;

    @JSONField(name = "#output_analysis_data_id", ordinal = 1)
    private String outputAnalysisDataIdRem = "输出 Analysis 的 dataStringId，必填。";

    @JSONField(name = "output_analysis_data_id", ordinal = 2)
    private String outputAnalysisDataId;

    @JSONField(name = "#output_analysis_value_type", ordinal = 3)
    private String outputAnalysisValueTypeRem = "输出分析结果值类型，合法值为 string、long、double、boolean、date。";

    @JSONField(name = "output_analysis_value_type", ordinal = 4)
    private String outputAnalysisValueType;

    @JSONField(name = "#source_type", ordinal = 5)
    private String sourceTypeRem = "源值来源类型，合法值为 constant、analysis、lookup。";

    @JSONField(name = "source_type", ordinal = 6)
    private String sourceType;

    @JSONField(name = "#constant_value_type", ordinal = 7)
    private String constantValueTypeRem = "当 source_type=constant 时必填，合法值为 string、long、double、boolean、date。";

    @JSONField(name = "constant_value_type", ordinal = 8)
    private String constantValueType;

    @JSONField(name = "#constant_value", ordinal = 9)
    private String constantValueRem = "当 source_type=constant 时必填，按 constant_value_type 解析。";

    @JSONField(name = "constant_value", ordinal = 10)
    private String constantValue;

    @JSONField(name = "#analysis_data_id", ordinal = 11)
    private String analysisDataIdRem = "当 source_type=analysis 时必填，用于读取已有分析结果。";

    @JSONField(name = "analysis_data_id", ordinal = 12)
    private String analysisDataId;

    @JSONField(name = "#lookup", ordinal = 13)
    private String lookupRem = "当 source_type=lookup 时必填，描述 LookupInfo 构造参数。";

    @JSONField(name = "lookup", ordinal = 14)
    private LookupConfig lookup;

    @JSONField(name = "#lookup_result", ordinal = 15)
    private String lookupResultRem = "当 source_type=lookup 时必填，描述 LookupResult 取值规则。";

    @JSONField(name = "lookup_result", ordinal = 16)
    private LookupResultConfig lookupResult;

    public ValueSetAnalyserConfig() {
    }

    public ValueSetAnalyserConfig(
            String outputAnalysisDataId, String outputAnalysisValueType, String sourceType,
            String constantValueType, String constantValue, String analysisDataId,
            LookupConfig lookup, LookupResultConfig lookupResult
    ) {
        this.outputAnalysisDataId = outputAnalysisDataId;
        this.outputAnalysisValueType = outputAnalysisValueType;
        this.sourceType = sourceType;
        this.constantValueType = constantValueType;
        this.constantValue = constantValue;
        this.analysisDataId = analysisDataId;
        this.lookup = lookup;
        this.lookupResult = lookupResult;
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

    public String getSourceTypeRem() {
        return sourceTypeRem;
    }

    public void setSourceTypeRem(String sourceTypeRem) {
        this.sourceTypeRem = sourceTypeRem;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
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

    public String getLookupResultRem() {
        return lookupResultRem;
    }

    public void setLookupResultRem(String lookupResultRem) {
        this.lookupResultRem = lookupResultRem;
    }

    public LookupResultConfig getLookupResult() {
        return lookupResult;
    }

    public void setLookupResult(LookupResultConfig lookupResult) {
        this.lookupResult = lookupResult;
    }

    @Override
    public String toString() {
        return "ValueSetAnalyserConfig{" +
                "outputAnalysisDataIdRem='" + outputAnalysisDataIdRem + '\'' +
                ", outputAnalysisDataId='" + outputAnalysisDataId + '\'' +
                ", outputAnalysisValueTypeRem='" + outputAnalysisValueTypeRem + '\'' +
                ", outputAnalysisValueType='" + outputAnalysisValueType + '\'' +
                ", sourceTypeRem='" + sourceTypeRem + '\'' +
                ", sourceType='" + sourceType + '\'' +
                ", constantValueTypeRem='" + constantValueTypeRem + '\'' +
                ", constantValueType='" + constantValueType + '\'' +
                ", constantValueRem='" + constantValueRem + '\'' +
                ", constantValue='" + constantValue + '\'' +
                ", analysisDataIdRem='" + analysisDataIdRem + '\'' +
                ", analysisDataId='" + analysisDataId + '\'' +
                ", lookupRem='" + lookupRem + '\'' +
                ", lookup=" + lookup +
                ", lookupResultRem='" + lookupResultRem + '\'' +
                ", lookupResult=" + lookupResult +
                '}';
    }

    /**
     * lookup 查询配置。
     *
     * <p>
     * 该配置用于描述 <code>Context.lookup(...)</code> 所需的五参 <code>LookupInfo</code>，
     * 包括提供器、适配器、过滤器、预设以及对象参数数组。
     *
     * @author DwArFeng
     * @since 2.6.1
     */
    public static class LookupConfig implements Bean {

        private static final long serialVersionUID = -2952289263867286532L;

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

        private static final long serialVersionUID = 978684151638316777L;

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
     * lookup 结果取值配置。
     *
     * <p>
     * 该配置用于描述从 <code>LookupResult</code> 中提取字段值的规则，
     * 支持从 <code>datas</code> 或 <code>meta</code> 中按字段名取值。
     *
     * @author DwArFeng
     * @since 2.6.1
     */
    public static class LookupResultConfig implements Bean {

        private static final long serialVersionUID = -4285041398701724975L;

        @JSONField(name = "#result_scope", ordinal = 1)
        private String resultScopeRem = "结果取值范围，合法值为 datas、meta。";

        @JSONField(name = "result_scope", ordinal = 2)
        private String resultScope;

        @JSONField(name = "#row_index", ordinal = 3)
        private String rowIndexRem = "当 result_scope=datas 时必填，且必须大于等于 0。";

        @JSONField(name = "row_index", ordinal = 4)
        private Integer rowIndex;

        @JSONField(name = "#field_name", ordinal = 5)
        private String fieldNameRem = "字段名，必填。";

        @JSONField(name = "field_name", ordinal = 6)
        private String fieldName;

        public LookupResultConfig() {
        }

        public LookupResultConfig(String resultScope, Integer rowIndex, String fieldName) {
            this.resultScope = resultScope;
            this.rowIndex = rowIndex;
            this.fieldName = fieldName;
        }

        public String getResultScopeRem() {
            return resultScopeRem;
        }

        public void setResultScopeRem(String resultScopeRem) {
            this.resultScopeRem = resultScopeRem;
        }

        public String getResultScope() {
            return resultScope;
        }

        public void setResultScope(String resultScope) {
            this.resultScope = resultScope;
        }

        public String getRowIndexRem() {
            return rowIndexRem;
        }

        public void setRowIndexRem(String rowIndexRem) {
            this.rowIndexRem = rowIndexRem;
        }

        public Integer getRowIndex() {
            return rowIndex;
        }

        public void setRowIndex(Integer rowIndex) {
            this.rowIndex = rowIndex;
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

        @Override
        public String toString() {
            return "LookupResultConfig{" +
                    "resultScopeRem='" + resultScopeRem + '\'' +
                    ", resultScope='" + resultScope + '\'' +
                    ", rowIndexRem='" + rowIndexRem + '\'' +
                    ", rowIndex=" + rowIndex +
                    ", fieldNameRem='" + fieldNameRem + '\'' +
                    ", fieldName='" + fieldName + '\'' +
                    '}';
        }
    }
}
