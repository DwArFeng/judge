package com.dwarfeng.judge.impl.handler.judger.binarization;

import com.dwarfeng.judge.sdk.handler.judger.AbstractExecutor;
import com.dwarfeng.judge.sdk.util.Constants;
import com.dwarfeng.judge.stack.bean.dto.AnalysisInspectInfo;
import com.dwarfeng.judge.stack.bean.dto.AnalysisInspectResult;
import com.dwarfeng.judge.stack.bean.dto.JudgementUpsertInfo;
import com.dwarfeng.judge.stack.exception.JudgerExecutionException;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 二值化判断器执行器。
 *
 * <p>
 * 执行器分别解析输入值与基准值两个数值源，按配置的比较方式得到布尔判断结果，
 * 并输出固定二值 <code>1.0</code> 或 <code>0.0</code>，最后通过判断器上下文写入判断结果。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component("binarizationJudgerRegistry.binarizationExecutor")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class BinarizationExecutor extends AbstractExecutor {

    private static final String SOURCE_TYPE_CONSTANT = "constant";
    private static final String SOURCE_TYPE_ANALYSIS = "analysis";

    private static final String COMPARATOR_GE = "ge";
    private static final String COMPARATOR_LE = "le";
    private static final String COMPARATOR_GT = "gt";
    private static final String COMPARATOR_LT = "lt";

    private final BinarizationJudgerConfig config;

    public BinarizationExecutor(BinarizationJudgerConfig config) {
        this.config = config;
    }

    @Override
    public void judge() throws Exception {
        checkRequired("output_judgement_data_id", config.getOutputJudgementDataId());
        checkRequired("input_source_type", config.getInputSourceType());
        checkRequired("baseline_source_type", config.getBaselineSourceType());
        checkRequired("comparator", config.getComparator());

        ValueInfo inputInfo = resolveNumericSource(
                "input_source_type", config.getInputSourceType(),
                "input_constant_value", config.getInputConstantValue(),
                "input_analysis_data_id", config.getInputAnalysisDataId(),
                "input_false_value", config.getInputFalseValue(),
                "input_true_value", config.getInputTrueValue()
        );
        ValueInfo baselineInfo = resolveNumericSource(
                "baseline_source_type", config.getBaselineSourceType(),
                "baseline_constant_value", config.getBaselineConstantValue(),
                "baseline_analysis_data_id", config.getBaselineAnalysisDataId(),
                "baseline_false_value", config.getBaselineFalseValue(),
                "baseline_true_value", config.getBaselineTrueValue()
        );

        double outputValue = compare(
                config.getComparator(), inputInfo.getConvertedValue(), baselineInfo.getConvertedValue()
        );
        context.upsertJudgement(new JudgementUpsertInfo(
                context.getTaskKey(), config.getOutputJudgementDataId(), outputValue,
                makeMessage(inputInfo, baselineInfo, config.getComparator(), outputValue)
        ));
    }

    private double compare(String comparator, double inputValue, double baselineValue)
            throws JudgerExecutionException {
        switch (comparator) {
            case COMPARATOR_GE:
                return inputValue >= baselineValue ? 1.0 : 0.0;
            case COMPARATOR_LE:
                return inputValue <= baselineValue ? 1.0 : 0.0;
            case COMPARATOR_GT:
                return inputValue > baselineValue ? 1.0 : 0.0;
            case COMPARATOR_LT:
                return inputValue < baselineValue ? 1.0 : 0.0;
            default:
                throw new JudgerExecutionException("二值化判断器配置项 comparator 非法: " + comparator);
        }
    }

    private ValueInfo resolveNumericSource(
            String sourceTypeField, String sourceType,
            String constantField, String constantValue,
            String analysisDataIdField, String analysisDataId,
            String falseValueField, String falseValue,
            String trueValueField, String trueValue
    ) throws Exception {
        switch (sourceType) {
            case SOURCE_TYPE_CONSTANT:
                return resolveConstantValueInfo(constantField, constantValue);
            case SOURCE_TYPE_ANALYSIS:
                return resolveAnalysisValueInfo(
                        analysisDataIdField, analysisDataId, falseValueField, falseValue, trueValueField, trueValue
                );
            default:
                throw new JudgerExecutionException("二值化判断器配置项 " + sourceTypeField + " 非法: " + sourceType);
        }
    }

    private ValueInfo resolveConstantValueInfo(String constantField, String constantValue)
            throws JudgerExecutionException {
        checkRequired(constantField, constantValue);
        double value = parseDouble(constantField, constantValue);
        return new ValueInfo("常量", constantValue, value);
    }

    private ValueInfo resolveAnalysisValueInfo(
            String analysisDataIdField, String analysisDataId,
            String falseValueField, String falseValue,
            String trueValueField, String trueValue
    ) throws Exception {
        checkRequired(analysisDataIdField, analysisDataId);
        AnalysisInspectResult result = context.inspectAnalysis(
                new AnalysisInspectInfo(context.getTaskKey(), analysisDataId)
        );
        if (Objects.isNull(result)) {
            throw new JudgerExecutionException("二值化判断器分析结果不存在, " + analysisDataIdField + ": " + analysisDataId);
        }
        Object rawValue = result.getValue();
        double convertedValue;
        switch (result.getDataType()) {
            case Constants.ANALYSIS_TYPE_LONG:
                convertedValue = ((Long) rawValue).doubleValue();
                break;
            case Constants.ANALYSIS_TYPE_DOUBLE:
                convertedValue = (Double) rawValue;
                break;
            case Constants.ANALYSIS_TYPE_STRING:
                convertedValue = parseDouble(analysisDataIdField + "=" + analysisDataId, (String) rawValue);
                break;
            case Constants.ANALYSIS_TYPE_BOOLEAN:
                convertedValue = parseBooleanValue(
                        falseValueField, falseValue, trueValueField, trueValue, (Boolean) rawValue
                );
                break;
            default:
                throw new JudgerExecutionException(
                        "二值化判断器不支持的分析结果类型, " + analysisDataIdField + ": " + analysisDataId +
                                ", data_type: " + result.getDataType()
                );
        }
        return new ValueInfo("分析结果", String.valueOf(rawValue), convertedValue);
    }

    private double parseBooleanValue(
            String falseValueField, String falseValue,
            String trueValueField, String trueValue,
            Boolean rawValue
    ) throws JudgerExecutionException {
        checkRequired(falseValueField, falseValue);
        checkRequired(trueValueField, trueValue);
        if (rawValue) {
            return parseDouble(trueValueField, trueValue);
        } else {
            return parseDouble(falseValueField, falseValue);
        }
    }

    private void checkRequired(String fieldName, String value) throws JudgerExecutionException {
        if (Objects.isNull(value) || value.trim().isEmpty()) {
            throw new JudgerExecutionException("二值化判断器配置项 " + fieldName + " 不能为空");
        }
    }

    private double parseDouble(String fieldName, String value) throws JudgerExecutionException {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            throw new JudgerExecutionException("二值化判断器配置项 " + fieldName + " 无法按 double 解析: " + value, e);
        }
    }

    private String makeMessage(
            ValueInfo inputInfo, ValueInfo baselineInfo, String comparator, double outputValue
    ) {
        return "二值化判断器: 输入来源=" + inputInfo.getSourceLabel() +
                ", 输入原始值=" + inputInfo.getRawValue() +
                ", 输入值=" + inputInfo.getConvertedValue() +
                ", 基准来源=" + baselineInfo.getSourceLabel() +
                ", 基准原始值=" + baselineInfo.getRawValue() +
                ", 基准值=" + baselineInfo.getConvertedValue() +
                ", 比较方式=" + comparator +
                ", 输出值=" + outputValue;
    }

    @Override
    public String toString() {
        return "BinarizationExecutor{" +
                "config=" + config +
                ", context=" + context +
                '}';
    }

    private static final class ValueInfo {

        private final String sourceLabel;
        private final String rawValue;
        private final double convertedValue;

        private ValueInfo(String sourceLabel, String rawValue, double convertedValue) {
            this.sourceLabel = sourceLabel;
            this.rawValue = rawValue;
            this.convertedValue = convertedValue;
        }

        public String getSourceLabel() {
            return sourceLabel;
        }

        public String getRawValue() {
            return rawValue;
        }

        public double getConvertedValue() {
            return convertedValue;
        }

        @Override
        public String toString() {
            return "ValueInfo{" +
                    "sourceLabel='" + sourceLabel + '\'' +
                    ", rawValue='" + rawValue + '\'' +
                    ", convertedValue=" + convertedValue +
                    '}';
        }
    }
}
