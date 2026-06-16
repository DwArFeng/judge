package com.dwarfeng.judge.impl.handler.analyser.vallbatset;

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
 * 值查询批量设置分析器执行器。
 *
 * <p>
 * 执行器只调用一次 <code>Context.lookup(...)</code>，
 * 再按 <code>outputs</code> 配置逐项从 lookup 结果取值、转换类型并通过分析器上下文写入目标 <code>Analysis</code>。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component("valueLookupBatchSetAnalyserRegistry.valueLookupBatchSetExecutor")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ValueLookupBatchSetExecutor extends AbstractExecutor {

    private static final String SOURCE_TYPE_CONSTANT = "constant";
    private static final String SOURCE_TYPE_ANALYSIS = "analysis";

    private static final String RESULT_SCOPE_DATAS = "datas";
    private static final String RESULT_SCOPE_META = "meta";

    private final ValueLookupBatchSetAnalyserConfig config;

    public ValueLookupBatchSetExecutor(ValueLookupBatchSetAnalyserConfig config) {
        this.config = config;
    }

    @Override
    public void analyse() throws Exception {
        checkRequired("lookup", config.getLookup());
        List<ValueLookupBatchSetAnalyserConfig.OutputConfig> outputs = config.getOutputs();
        if (Objects.isNull(outputs) || outputs.isEmpty()) {
            throw new AnalyserExecutionException("值查询批量设置分析器配置项 outputs 不能为空");
        }

        LookupInfo lookupInfo = buildLookupInfo(config.getLookup());
        LookupResult lookupResult;
        try {
            lookupResult = context.lookup(lookupInfo);
        } catch (HandlerException e) {
            throw new AnalyserExecutionException("值查询批量设置分析器 lookup 查询失败", e);
        }

        for (int index = 0; index < outputs.size(); index++) {
            processOutput(outputs.get(index), index, lookupResult);
        }
    }

    private void processOutput(
            ValueLookupBatchSetAnalyserConfig.OutputConfig outputConfig, int index, LookupResult lookupResult
    ) throws Exception {
        String fieldPrefix = "outputs[" + index + "]";
        if (Objects.isNull(outputConfig)) {
            throw new AnalyserExecutionException("值查询批量设置分析器配置项 " + fieldPrefix + " 不能为空");
        }
        checkRequired(fieldPrefix + ".output_analysis_data_id", outputConfig.getOutputAnalysisDataId());
        checkRequired(fieldPrefix + ".output_analysis_value_type", outputConfig.getOutputAnalysisValueType());
        checkRequired(fieldPrefix + ".lookup_result", outputConfig.getLookupResult());

        Object sourceValue = extractLookupResultValue(
                lookupResult, outputConfig.getLookupResult(), fieldPrefix + ".lookup_result"
        );
        Object outputValue = ValueLookupBatchSetValueConverter.convertToOutputValue(
                fieldPrefix + ".lookup_result", outputConfig.getOutputAnalysisValueType(), sourceValue
        );
        int dataType = ValueLookupBatchSetValueConverter.resolveAnalysisDataType(
                outputConfig.getOutputAnalysisValueType()
        );
        context.upsertAnalysis(new AnalysisUpsertInfo(
                context.getTaskKey(), outputConfig.getOutputAnalysisDataId(), dataType, outputValue
        ));
    }

    private LookupInfo buildLookupInfo(ValueLookupBatchSetAnalyserConfig.LookupConfig lookupConfig)
            throws Exception {
        checkRequired("lookup.provider_info_id", lookupConfig.getProviderInfoId());
        checkRequired("lookup.preset", lookupConfig.getPreset());
        if (Objects.isNull(lookupConfig.getObjs())) {
            throw new AnalyserExecutionException("值查询批量设置分析器配置项 lookup.objs 不能为空");
        }

        Object[] objs = new Object[lookupConfig.getObjs().size()];
        for (int index = 0; index < lookupConfig.getObjs().size(); index++) {
            ValueLookupBatchSetAnalyserConfig.LookupObjectConfig objectConfig = lookupConfig.getObjs().get(index);
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

    private Object resolveLookupObjectValue(
            ValueLookupBatchSetAnalyserConfig.LookupObjectConfig objectConfig, int index
    ) throws Exception {
        String fieldPrefix = "lookup.objs[" + index + "]";
        if (Objects.isNull(objectConfig)) {
            throw new AnalyserExecutionException("值查询批量设置分析器配置项 " + fieldPrefix + " 不能为空");
        }
        checkRequired(fieldPrefix + ".object_source_type", objectConfig.getObjectSourceType());

        switch (objectConfig.getObjectSourceType().trim().toLowerCase()) {
            case SOURCE_TYPE_CONSTANT:
                checkRequired(fieldPrefix + ".constant_value_type", objectConfig.getConstantValueType());
                return ValueLookupBatchSetValueConverter.parseConstantValue(
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
                        "值查询批量设置分析器配置项 " + fieldPrefix + ".object_source_type 非法: " +
                                objectConfig.getObjectSourceType()
                );
        }
    }

    private Object resolveAnalysisSourceValue(String analysisDataId, String fieldName) throws Exception {
        checkRequired(fieldName, analysisDataId);
        AnalysisInspectResult result = context.inspectAnalysis(
                new AnalysisInspectInfo(context.getTaskKey(), analysisDataId)
        );
        if (Objects.isNull(result)) {
            throw new AnalyserExecutionException(
                    "值查询批量设置分析器分析结果不存在, " + fieldName + ": " + analysisDataId
            );
        }
        return result.getValue();
    }

    private Object extractLookupResultValue(
            LookupResult lookupResult,
            ValueLookupBatchSetAnalyserConfig.LookupResultConfig lookupResultConfig,
            String fieldPrefix
    ) throws AnalyserExecutionException {
        checkRequired(fieldPrefix + ".result_scope", lookupResultConfig.getResultScope());
        checkRequired(fieldPrefix + ".field_name", lookupResultConfig.getFieldName());

        switch (lookupResultConfig.getResultScope().trim().toLowerCase()) {
            case RESULT_SCOPE_DATAS:
                return extractLookupDatasValue(lookupResult, lookupResultConfig, fieldPrefix);
            case RESULT_SCOPE_META:
                return extractLookupMetaValue(lookupResult, lookupResultConfig, fieldPrefix);
            default:
                throw new AnalyserExecutionException(
                        "值查询批量设置分析器配置项 " + fieldPrefix + ".result_scope 非法: " +
                                lookupResultConfig.getResultScope()
                );
        }
    }

    // 为了保证代码的可读性，此处代码不做简化。
    @SuppressWarnings("ExtractMethodRecommender")
    private Object extractLookupDatasValue(
            LookupResult lookupResult,
            ValueLookupBatchSetAnalyserConfig.LookupResultConfig lookupResultConfig,
            String fieldPrefix
    ) throws AnalyserExecutionException {
        checkRequired(fieldPrefix + ".row_index", lookupResultConfig.getRowIndex());
        if (lookupResultConfig.getRowIndex() < 0) {
            throw new AnalyserExecutionException(
                    "值查询批量设置分析器配置项 " + fieldPrefix + ".row_index 必须大于等于 0: " +
                            lookupResultConfig.getRowIndex()
            );
        }

        List<Map<String, Object>> datas = lookupResult.getDatas();
        if (Objects.isNull(datas) || datas.isEmpty()) {
            throw new AnalyserExecutionException("值查询批量设置分析器 lookup 结果 datas 为空");
        }
        if (lookupResultConfig.getRowIndex() >= datas.size()) {
            throw new AnalyserExecutionException(
                    "值查询批量设置分析器 lookup 结果 row_index 越界, " + fieldPrefix + ".row_index=" +
                            lookupResultConfig.getRowIndex() + ", datas_size=" + datas.size()
            );
        }

        Map<String, Object> row = datas.get(lookupResultConfig.getRowIndex());
        if (Objects.isNull(row)) {
            throw new AnalyserExecutionException(
                    "值查询批量设置分析器 lookup 结果 datas[" + lookupResultConfig.getRowIndex() + "] 为 null"
            );
        }
        return extractFieldValue(row, lookupResultConfig.getFieldName(), fieldPrefix + ".field_name");
    }

    private Object extractLookupMetaValue(
            LookupResult lookupResult,
            ValueLookupBatchSetAnalyserConfig.LookupResultConfig lookupResultConfig,
            String fieldPrefix
    ) throws AnalyserExecutionException {
        Map<String, Object> meta = lookupResult.getMeta();
        if (Objects.isNull(meta) || meta.isEmpty()) {
            throw new AnalyserExecutionException("值查询批量设置分析器 lookup 结果 meta 为空");
        }
        return extractFieldValue(meta, lookupResultConfig.getFieldName(), fieldPrefix + ".field_name");
    }

    // 为了保证代码的可读性，此处代码不做简化。
    @SuppressWarnings("SameParameterValue")
    private Object extractFieldValue(Map<String, Object> container, String fieldName, String fieldLabel)
            throws AnalyserExecutionException {
        if (!container.containsKey(fieldName)) {
            throw new AnalyserExecutionException(
                    "值查询批量设置分析器 lookup 结果字段不存在, " + fieldLabel + ": " + fieldName
            );
        }
        Object value = container.get(fieldName);
        if (Objects.isNull(value)) {
            throw new AnalyserExecutionException(
                    "值查询批量设置分析器 lookup 结果字段值为 null, " + fieldLabel + ": " + fieldName
            );
        }
        return value;
    }

    // 为了保证代码的可读性，此处代码不做简化。
    @SuppressWarnings("SameParameterValue")
    private LongIdKey toLongIdKey(String fieldName, Long keyValue) throws AnalyserExecutionException {
        checkRequired(fieldName, keyValue);
        return new LongIdKey(keyValue);
    }

    private LongIdKey toNullableLongIdKey(Long keyValue) {
        if (Objects.isNull(keyValue)) {
            return null;
        }
        return new LongIdKey(keyValue);
    }

    /**
     * 校验必填字符串配置项。
     *
     * @param fieldName 配置项名称，用于异常定位。
     * @param value     配置值。
     * @throws AnalyserExecutionException 配置缺失、空字符串或全空白字符串时抛出。
     */
    private static void checkRequired(String fieldName, String value) throws AnalyserExecutionException {
        if (Objects.isNull(value) || value.trim().isEmpty()) {
            throw new AnalyserExecutionException("值查询批量设置分析器配置项 " + fieldName + " 不能为空");
        }
    }

    /**
     * 校验必填对象配置项。
     *
     * @param fieldName 配置项名称，用于异常定位。
     * @param value     配置值。
     * @throws AnalyserExecutionException 配置缺失时抛出。
     */
    private static void checkRequired(String fieldName, Object value) throws AnalyserExecutionException {
        if (Objects.isNull(value)) {
            throw new AnalyserExecutionException("值查询批量设置分析器配置项 " + fieldName + " 不能为空");
        }
    }

    @Override
    public String toString() {
        return "ValueLookupBatchSetExecutor{" +
                "config=" + config +
                ", context=" + context +
                '}';
    }
}
