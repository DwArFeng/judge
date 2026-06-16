package com.dwarfeng.judge.impl.handler.analyser.valset;

import com.dwarfeng.judge.sdk.handler.analyser.AbstractExecutor;
import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.judge.stack.exception.AnalyserExecutionException;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 值设置分析器执行器。
 *
 * <p>
 * 执行器按配置解析常量、已有分析结果或 lookup 查询结果，统一转换为指定分析值类型，
 * 并通过分析器上下文写入目标 <code>Analysis</code>。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component("valueSetAnalyserRegistry.valueSetExecutor")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ValueSetExecutor extends AbstractExecutor {

    private static final String SOURCE_TYPE_CONSTANT = "constant";
    private static final String SOURCE_TYPE_ANALYSIS = "analysis";
    private static final String SOURCE_TYPE_LOOKUP = "lookup";

    private static final String RESULT_SCOPE_DATAS = "datas";
    private static final String RESULT_SCOPE_META = "meta";

    private final ValueSetAnalyserConfig config;

    public ValueSetExecutor(ValueSetAnalyserConfig config) {
        this.config = config;
    }

    @Override
    public void analyse() throws Exception {
        ValueSetValueConverter.checkRequired("output_analysis_data_id", config.getOutputAnalysisDataId());
        ValueSetValueConverter.checkRequired("output_analysis_value_type", config.getOutputAnalysisValueType());
        ValueSetValueConverter.checkRequired("source_type", config.getSourceType());

        Object sourceValue = resolveSourceValue();
        Object outputValue = ValueSetValueConverter.convertToOutputValue(
                "source_value", config.getOutputAnalysisValueType(), sourceValue
        );
        int dataType = ValueSetValueConverter.resolveAnalysisDataType(config.getOutputAnalysisValueType());
        context.upsertAnalysis(new AnalysisUpsertInfo(
                context.getTaskKey(), config.getOutputAnalysisDataId(), dataType, outputValue
        ));
    }

    private Object resolveSourceValue() throws Exception {
        switch (config.getSourceType().trim().toLowerCase()) {
            case SOURCE_TYPE_CONSTANT:
                return resolveConstantSourceValue();
            case SOURCE_TYPE_ANALYSIS:
                return resolveAnalysisSourceValue(config.getAnalysisDataId(), "analysis_data_id");
            case SOURCE_TYPE_LOOKUP:
                return resolveLookupSourceValue();
            default:
                throw new AnalyserExecutionException(
                        "值设置分析器配置项 source_type 非法: " + config.getSourceType()
                );
        }
    }

    private Object resolveConstantSourceValue() throws AnalyserExecutionException {
        ValueSetValueConverter.checkRequired("constant_value_type", config.getConstantValueType());
        return ValueSetValueConverter.parseConstantValue(
                "constant_value", config.getConstantValueType(), config.getConstantValue()
        );
    }

    private Object resolveAnalysisSourceValue(String analysisDataId, String fieldName) throws Exception {
        ValueSetValueConverter.checkRequired(fieldName, analysisDataId);
        AnalysisInspectResult result = context.inspectAnalysis(
                new AnalysisInspectInfo(context.getTaskKey(), analysisDataId)
        );
        if (Objects.isNull(result)) {
            throw new AnalyserExecutionException(
                    "值设置分析器分析结果不存在, " + fieldName + ": " + analysisDataId
            );
        }
        return result.getValue();
    }

    private Object resolveLookupSourceValue() throws Exception {
        ValueSetAnalyserConfig.LookupConfig lookupConfig = config.getLookup();
        ValueSetAnalyserConfig.LookupResultConfig lookupResultConfig = config.getLookupResult();
        ValueSetValueConverter.checkRequired("lookup", lookupConfig);
        ValueSetValueConverter.checkRequired("lookup_result", lookupResultConfig);

        LookupInfo lookupInfo = buildLookupInfo(lookupConfig);
        LookupResult lookupResult;
        try {
            lookupResult = context.lookup(lookupInfo);
        } catch (HandlerException e) {
            throw new AnalyserExecutionException("值设置分析器 lookup 查询失败", e);
        }
        return extractLookupResultValue(lookupResult, lookupResultConfig);
    }

    private LookupInfo buildLookupInfo(ValueSetAnalyserConfig.LookupConfig lookupConfig) throws Exception {
        ValueSetValueConverter.checkRequired("lookup.provider_info_id", lookupConfig.getProviderInfoId());
        ValueSetValueConverter.checkRequired("lookup.preset", lookupConfig.getPreset());
        if (Objects.isNull(lookupConfig.getObjs())) {
            throw new AnalyserExecutionException("值设置分析器配置项 lookup.objs 不能为空");
        }

        Object[] objs = new Object[lookupConfig.getObjs().size()];
        for (int index = 0; index < lookupConfig.getObjs().size(); index++) {
            ValueSetAnalyserConfig.LookupObjectConfig objectConfig = lookupConfig.getObjs().get(index);
            objs[index] = resolveLookupObjectValue(objectConfig, index);
        }

        return new LookupInfo(
                toLongIdKey("lookup.provider_info_id", lookupConfig.getProviderInfoId()),
                toNullableLongIdKey(lookupConfig.getAdapterInfoId()),
                toNullableLongIdKey(lookupConfig.getFilterInfoId()),
                lookupConfig.getPreset(),
                objs
        );
    }

    private Object resolveLookupObjectValue(ValueSetAnalyserConfig.LookupObjectConfig objectConfig, int index)
            throws Exception {
        String fieldPrefix = "lookup.objs[" + index + "]";
        if (Objects.isNull(objectConfig)) {
            throw new AnalyserExecutionException("值设置分析器配置项 " + fieldPrefix + " 不能为空");
        }
        ValueSetValueConverter.checkRequired(fieldPrefix + ".object_source_type", objectConfig.getObjectSourceType());

        switch (objectConfig.getObjectSourceType().trim().toLowerCase()) {
            case SOURCE_TYPE_CONSTANT:
                ValueSetValueConverter.checkRequired(
                        fieldPrefix + ".constant_value_type", objectConfig.getConstantValueType()
                );
                return ValueSetValueConverter.parseConstantValue(
                        fieldPrefix + ".constant_value",
                        objectConfig.getConstantValueType(),
                        objectConfig.getConstantValue()
                );
            case SOURCE_TYPE_ANALYSIS:
                return resolveAnalysisSourceValue(
                        objectConfig.getAnalysisDataId(), fieldPrefix + ".analysis_data_id"
                );
            default:
                throw new AnalyserExecutionException(
                        "值设置分析器配置项 " + fieldPrefix + ".object_source_type 非法: " +
                                objectConfig.getObjectSourceType()
                );
        }
    }

    private Object extractLookupResultValue(
            LookupResult lookupResult, ValueSetAnalyserConfig.LookupResultConfig lookupResultConfig
    ) throws AnalyserExecutionException {
        ValueSetValueConverter.checkRequired("lookup_result.result_scope", lookupResultConfig.getResultScope());
        ValueSetValueConverter.checkRequired("lookup_result.field_name", lookupResultConfig.getFieldName());

        switch (lookupResultConfig.getResultScope().trim().toLowerCase()) {
            case RESULT_SCOPE_DATAS:
                return extractLookupDatasValue(lookupResult, lookupResultConfig);
            case RESULT_SCOPE_META:
                return extractLookupMetaValue(lookupResult, lookupResultConfig);
            default:
                throw new AnalyserExecutionException(
                        "值设置分析器配置项 lookup_result.result_scope 非法: " +
                                lookupResultConfig.getResultScope()
                );
        }
    }

    // 为了保证代码的可读性，此处代码不做简化。
    @SuppressWarnings("ExtractMethodRecommender")
    private Object extractLookupDatasValue(
            LookupResult lookupResult, ValueSetAnalyserConfig.LookupResultConfig lookupResultConfig
    ) throws AnalyserExecutionException {
        ValueSetValueConverter.checkRequired("lookup_result.row_index", lookupResultConfig.getRowIndex());
        if (lookupResultConfig.getRowIndex() < 0) {
            throw new AnalyserExecutionException(
                    "值设置分析器配置项 lookup_result.row_index 必须大于等于 0: " +
                            lookupResultConfig.getRowIndex()
            );
        }

        List<Map<String, Object>> datas = lookupResult.getDatas();
        if (Objects.isNull(datas) || datas.isEmpty()) {
            throw new AnalyserExecutionException("值设置分析器 lookup 结果 datas 为空");
        }
        if (lookupResultConfig.getRowIndex() >= datas.size()) {
            throw new AnalyserExecutionException(
                    "值设置分析器 lookup 结果 row_index 越界, row_index=" + lookupResultConfig.getRowIndex() +
                            ", datas_size=" + datas.size()
            );
        }

        Map<String, Object> row = datas.get(lookupResultConfig.getRowIndex());
        if (Objects.isNull(row)) {
            throw new AnalyserExecutionException(
                    "值设置分析器 lookup 结果 datas[" + lookupResultConfig.getRowIndex() + "] 为 null"
            );
        }
        return extractFieldValue(
                row, lookupResultConfig.getFieldName(), "lookup_result.field_name"
        );
    }

    private Object extractLookupMetaValue(
            LookupResult lookupResult, ValueSetAnalyserConfig.LookupResultConfig lookupResultConfig
    ) throws AnalyserExecutionException {
        Map<String, Object> meta = lookupResult.getMeta();
        if (Objects.isNull(meta) || meta.isEmpty()) {
            throw new AnalyserExecutionException("值设置分析器 lookup 结果 meta 为空");
        }
        return extractFieldValue(meta, lookupResultConfig.getFieldName(), "lookup_result.field_name");
    }

    // 为了保证代码的可读性，此处代码不做简化。
    @SuppressWarnings("SameParameterValue")
    private Object extractFieldValue(Map<String, Object> container, String fieldName, String fieldLabel)
            throws AnalyserExecutionException {
        if (!container.containsKey(fieldName)) {
            throw new AnalyserExecutionException(
                    "值设置分析器 lookup 结果字段不存在, " + fieldLabel + ": " + fieldName
            );
        }
        Object value = container.get(fieldName);
        if (Objects.isNull(value)) {
            throw new AnalyserExecutionException(
                    "值设置分析器 lookup 结果字段值为 null, " + fieldLabel + ": " + fieldName
            );
        }
        return value;
    }

    // 为了保证代码的可读性，此处代码不做简化。
    @SuppressWarnings("SameParameterValue")
    private LongIdKey toLongIdKey(String fieldName, Long keyValue) throws AnalyserExecutionException {
        ValueSetValueConverter.checkRequired(fieldName, keyValue);
        return new LongIdKey(keyValue);
    }

    private LongIdKey toNullableLongIdKey(Long keyValue) {
        if (Objects.isNull(keyValue)) {
            return null;
        }
        return new LongIdKey(keyValue);
    }

    @Override
    public String toString() {
        return "ValueSetExecutor{" +
                "config=" + config +
                ", context=" + context +
                '}';
    }
}
