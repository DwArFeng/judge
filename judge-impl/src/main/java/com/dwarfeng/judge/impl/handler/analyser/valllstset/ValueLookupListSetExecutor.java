package com.dwarfeng.judge.impl.handler.analyser.valllstset;

import com.dwarfeng.judge.sdk.handler.analyser.AbstractExecutor;
import com.dwarfeng.judge.sdk.util.Constants;
import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.judge.stack.exception.AnalyserExecutionException;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 值查询列表设置分析器执行器。
 *
 * <p>
 * 执行器只调用一次 <code>Context.lookup(...)</code>，
 * 再从 <code>LookupResult.datas</code> 的所有行中提取字段列表、序列化后写入字符串或文件类型 <code>Analysis</code>。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component("valueLookupListSetAnalyserRegistry.valueLookupListSetExecutor")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ValueLookupListSetExecutor extends AbstractExecutor {

    private static final String SOURCE_TYPE_CONSTANT = "constant";
    private static final String SOURCE_TYPE_ANALYSIS = "analysis";

    private static final String VALUE_TYPE_STRING = "string";
    private static final String VALUE_TYPE_FILE = "file";

    private final ValueLookupListSetAnalyserConfig config;

    public ValueLookupListSetExecutor(ValueLookupListSetAnalyserConfig config) {
        this.config = config;
    }

    @Override
    public void analyse() throws Exception {
        checkRequired("lookup", config.getLookup());
        List<ValueLookupListSetAnalyserConfig.OutputConfig> outputs = config.getOutputs();
        if (Objects.isNull(outputs) || outputs.isEmpty()) {
            throw new AnalyserExecutionException("值查询列表设置分析器配置项 outputs 不能为空");
        }

        LookupInfo lookupInfo = buildLookupInfo(config.getLookup());
        LookupResult lookupResult;
        try {
            lookupResult = context.lookup(lookupInfo);
        } catch (HandlerException e) {
            throw new AnalyserExecutionException("值查询列表设置分析器 lookup 查询失败", e);
        }

        List<Map<String, Object>> datas = lookupResult.getDatas();
        for (int index = 0; index < outputs.size(); index++) {
            processOutput(outputs.get(index), index, datas);
        }
    }

    private void processOutput(
            ValueLookupListSetAnalyserConfig.OutputConfig outputConfig, int index,
            List<Map<String, Object>> datas
    ) throws Exception {
        String fieldPrefix = "outputs[" + index + "]";
        if (Objects.isNull(outputConfig)) {
            throw new AnalyserExecutionException("值查询列表设置分析器配置项 " + fieldPrefix + " 不能为空");
        }
        checkRequired(fieldPrefix + ".output_analysis_data_id", outputConfig.getOutputAnalysisDataId());
        checkRequired(fieldPrefix + ".output_analysis_value_type", outputConfig.getOutputAnalysisValueType());
        checkRequired(fieldPrefix + ".field_name", outputConfig.getFieldName());
        checkRequired(fieldPrefix + ".format", outputConfig.getFormat());

        String outputValueType = outputConfig.getOutputAnalysisValueType().trim().toLowerCase();
        switch (outputValueType) {
            case VALUE_TYPE_STRING:
                writeStringOutput(outputConfig, fieldPrefix, datas);
                break;
            case VALUE_TYPE_FILE:
                writeFileOutput(outputConfig, fieldPrefix, datas);
                break;
            default:
                throw new AnalyserExecutionException(
                        "值查询列表设置分析器配置项 " + fieldPrefix + ".output_analysis_value_type 非法: " +
                                outputConfig.getOutputAnalysisValueType()
                );
        }
    }

    private void writeStringOutput(
            ValueLookupListSetAnalyserConfig.OutputConfig outputConfig, String fieldPrefix,
            List<Map<String, Object>> datas
    ) throws Exception {
        List<Object> fieldValues = extractFieldList(datas, outputConfig.getFieldName(), fieldPrefix);
        String content = ValueLookupListSetListSerializer.serializeToString(
                fieldPrefix, fieldValues, outputConfig.getFormat(), outputConfig.getFieldName(),
                outputConfig.getCsv()
        );
        context.upsertAnalysis(new AnalysisUpsertInfo(
                context.getTaskKey(), outputConfig.getOutputAnalysisDataId(),
                Constants.ANALYSIS_TYPE_STRING, content
        ));
    }

    private void writeFileOutput(
            ValueLookupListSetAnalyserConfig.OutputConfig outputConfig, String fieldPrefix,
            List<Map<String, Object>> datas
    ) throws Exception {
        checkRequired(fieldPrefix + ".file_origin_name", outputConfig.getFileOriginName());
        List<Object> fieldValues = extractFieldList(datas, outputConfig.getFieldName(), fieldPrefix);
        byte[] content = ValueLookupListSetListSerializer.serializeToBytes(
                fieldPrefix, fieldValues, outputConfig.getFormat(), outputConfig.getFieldName(),
                outputConfig.getCsv()
        );
        AnalysisFileUpsertInfo fileUpsertInfo = new AnalysisFileUpsertInfo(
                outputConfig.getFileOriginName(), content
        );
        context.upsertAnalysis(new AnalysisUpsertInfo(
                context.getTaskKey(), outputConfig.getOutputAnalysisDataId(),
                Constants.ANALYSIS_TYPE_FILE, fileUpsertInfo
        ));
    }

    private List<Object> extractFieldList(
            List<Map<String, Object>> datas, String fieldName, String fieldPrefix
    ) throws AnalyserExecutionException {
        if (Objects.isNull(datas) || datas.isEmpty()) {
            throw new AnalyserExecutionException(
                    "值查询列表设置分析器 lookup 结果 datas 为空, " + fieldPrefix + ".field_name: " + fieldName
            );
        }

        List<Object> fieldValues = new ArrayList<>(datas.size());
        for (int rowIndex = 0; rowIndex < datas.size(); rowIndex++) {
            Map<String, Object> row = datas.get(rowIndex);
            if (Objects.isNull(row)) {
                throw new AnalyserExecutionException(
                        "值查询列表设置分析器 lookup 结果 datas[" + rowIndex + "] 为 null, " +
                                fieldPrefix + ".field_name: " + fieldName
                );
            }
            if (!row.containsKey(fieldName)) {
                throw new AnalyserExecutionException(
                        "值查询列表设置分析器 lookup 结果字段不存在, " + fieldPrefix + ".field_name: " + fieldName
                );
            }
            Object value = row.get(fieldName);
            if (Objects.isNull(value)) {
                throw new AnalyserExecutionException(
                        "值查询列表设置分析器 lookup 结果字段值为 null, " + fieldPrefix + ".field_name: " + fieldName
                );
            }
            fieldValues.add(value);
        }
        return fieldValues;
    }

    private LookupInfo buildLookupInfo(ValueLookupListSetAnalyserConfig.LookupConfig lookupConfig)
            throws Exception {
        checkRequired("lookup.provider_info_id", lookupConfig.getProviderInfoId());
        checkRequired("lookup.preset", lookupConfig.getPreset());
        if (Objects.isNull(lookupConfig.getObjs())) {
            throw new AnalyserExecutionException("值查询列表设置分析器配置项 lookup.objs 不能为空");
        }

        Object[] objs = new Object[lookupConfig.getObjs().size()];
        for (int index = 0; index < lookupConfig.getObjs().size(); index++) {
            ValueLookupListSetAnalyserConfig.LookupObjectConfig objectConfig = lookupConfig.getObjs().get(index);
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
            ValueLookupListSetAnalyserConfig.LookupObjectConfig objectConfig, int index
    ) throws Exception {
        String fieldPrefix = "lookup.objs[" + index + "]";
        if (Objects.isNull(objectConfig)) {
            throw new AnalyserExecutionException("值查询列表设置分析器配置项 " + fieldPrefix + " 不能为空");
        }
        checkRequired(fieldPrefix + ".object_source_type", objectConfig.getObjectSourceType());

        switch (objectConfig.getObjectSourceType().trim().toLowerCase()) {
            case SOURCE_TYPE_CONSTANT:
                checkRequired(fieldPrefix + ".constant_value_type", objectConfig.getConstantValueType());
                return ValueLookupListSetValueConverter.parseConstantValue(
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
                        "值查询列表设置分析器配置项 " + fieldPrefix + ".object_source_type 非法: " +
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
                    "值查询列表设置分析器分析结果不存在, " + fieldName + ": " + analysisDataId
            );
        }
        return result.getValue();
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

    private static void checkRequired(String fieldName, String value) throws AnalyserExecutionException {
        if (Objects.isNull(value) || value.trim().isEmpty()) {
            throw new AnalyserExecutionException("值查询列表设置分析器配置项 " + fieldName + " 不能为空");
        }
    }

    private static void checkRequired(String fieldName, Object value) throws AnalyserExecutionException {
        if (Objects.isNull(value)) {
            throw new AnalyserExecutionException("值查询列表设置分析器配置项 " + fieldName + " 不能为空");
        }
    }

    @Override
    public String toString() {
        return "ValueLookupListSetExecutor{" +
                "config=" + config +
                ", context=" + context +
                '}';
    }
}
