package com.dwarfeng.judge.impl.handler.judger.threshold;

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
 * 阈值判断器执行器。
 *
 * <p>
 * 执行器分别解析输入值、低阈值、高阈值三个数值源，在阈值区间内按线性插值计算标准化输出，
 * 并通过判断器上下文写入判断结果。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component("thresholdJudgerRegistry.thresholdExecutor")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ThresholdExecutor extends AbstractExecutor {

    private static final String SOURCE_TYPE_CONSTANT = "constant";
    private static final String SOURCE_TYPE_ANALYSIS = "analysis";

    private static final String INTERPOLATION_FORWARD = "正向";
    private static final String INTERPOLATION_REVERSE = "反向";

    private final ThresholdJudgerConfig config;

    public ThresholdExecutor(ThresholdJudgerConfig config) {
        this.config = config;
    }

    @Override
    public void judge() throws Exception {
        checkRequired("output_judgement_data_id", config.getOutputJudgementDataId());
        checkRequired("input_source_type", config.getInputSourceType());
        checkRequired("low_threshold_source_type", config.getLowThresholdSourceType());
        checkRequired("high_threshold_source_type", config.getHighThresholdSourceType());

        ValueInfo inputInfo = resolveNumericSource(
                "input_source_type", config.getInputSourceType(),
                "input_constant_value", config.getInputConstantValue(),
                "input_analysis_data_id", config.getInputAnalysisDataId(),
                "input_false_value", config.getInputFalseValue(),
                "input_true_value", config.getInputTrueValue()
        );
        ValueInfo lowThresholdInfo = resolveNumericSource(
                "low_threshold_source_type", config.getLowThresholdSourceType(),
                "low_threshold_constant_value", config.getLowThresholdConstantValue(),
                "low_threshold_analysis_data_id", config.getLowThresholdAnalysisDataId(),
                "low_threshold_false_value", config.getLowThresholdFalseValue(),
                "low_threshold_true_value", config.getLowThresholdTrueValue()
        );
        ValueInfo highThresholdInfo = resolveNumericSource(
                "high_threshold_source_type", config.getHighThresholdSourceType(),
                "high_threshold_constant_value", config.getHighThresholdConstantValue(),
                "high_threshold_analysis_data_id", config.getHighThresholdAnalysisDataId(),
                "high_threshold_false_value", config.getHighThresholdFalseValue(),
                "high_threshold_true_value", config.getHighThresholdTrueValue()
        );

        double lowThreshold = lowThresholdInfo.getConvertedValue();
        double highThreshold = highThresholdInfo.getConvertedValue();
        double inputValue = inputInfo.getConvertedValue();
        InterpolationResult interpolationResult = interpolate(inputValue, lowThreshold, highThreshold);
        context.upsertJudgement(new JudgementUpsertInfo(
                context.getTaskKey(), config.getOutputJudgementDataId(), interpolationResult.getOutputValue(),
                makeMessage(inputInfo, lowThresholdInfo, highThresholdInfo, interpolationResult)
        ));
    }

    private InterpolationResult interpolate(double input, double lowThreshold, double highThreshold)
            throws JudgerExecutionException {
        if (Double.compare(lowThreshold, highThreshold) == 0) {
            throw new JudgerExecutionException(
                    "阈值判断器低阈值与高阈值相等, low_threshold=" + lowThreshold + ", high_threshold=" + highThreshold
            );
        }
        if (lowThreshold < highThreshold) {
            if (input <= lowThreshold) {
                return new InterpolationResult(INTERPOLATION_FORWARD, 0.0);
            }
            if (input >= highThreshold) {
                return new InterpolationResult(INTERPOLATION_FORWARD, 1.0);
            }
            return new InterpolationResult(
                    INTERPOLATION_FORWARD, (input - lowThreshold) / (highThreshold - lowThreshold)
            );
        }
        if (input >= lowThreshold) {
            return new InterpolationResult(INTERPOLATION_REVERSE, 0.0);
        }
        if (input <= highThreshold) {
            return new InterpolationResult(INTERPOLATION_REVERSE, 1.0);
        }
        return new InterpolationResult(
                INTERPOLATION_REVERSE, (lowThreshold - input) / (lowThreshold - highThreshold)
        );
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
                throw new JudgerExecutionException("阈值判断器配置项 " + sourceTypeField + " 非法: " + sourceType);
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
            throw new JudgerExecutionException("阈值判断器分析结果不存在, " + analysisDataIdField + ": " + analysisDataId);
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
                        "阈值判断器不支持的分析结果类型, " + analysisDataIdField + ": " + analysisDataId +
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

    private void checkRequired(String dataId, String value) throws JudgerExecutionException {
        if (Objects.isNull(value) || value.trim().isEmpty()) {
            throw new JudgerExecutionException("阈值判断器配置项 " + dataId + " 不能为空");
        }
    }

    private double parseDouble(String dataId, String value) throws JudgerExecutionException {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            throw new JudgerExecutionException("阈值判断器配置项 " + dataId + " 无法按 double 解析: " + value, e);
        }
    }

    private String makeMessage(
            ValueInfo inputInfo, ValueInfo lowThresholdInfo, ValueInfo highThresholdInfo,
            InterpolationResult interpolationResult
    ) {
        return "阈值判断器: 输入来源=" + inputInfo.getSourceLabel() +
                ", 输入原始值=" + inputInfo.getRawValue() +
                ", 输入值=" + inputInfo.getConvertedValue() +
                ", 低阈值来源=" + lowThresholdInfo.getSourceLabel() +
                ", 低阈值原始值=" + lowThresholdInfo.getRawValue() +
                ", 低阈值=" + lowThresholdInfo.getConvertedValue() +
                ", 高阈值来源=" + highThresholdInfo.getSourceLabel() +
                ", 高阈值原始值=" + highThresholdInfo.getRawValue() +
                ", 高阈值=" + highThresholdInfo.getConvertedValue() +
                ", 插值方向=" + interpolationResult.getDirectionLabel() +
                ", 输出值=" + interpolationResult.getOutputValue();
    }

    @Override
    public String toString() {
        return "ThresholdExecutor{" +
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

    private static final class InterpolationResult {

        private final String directionLabel;
        private final double outputValue;

        private InterpolationResult(String directionLabel, double outputValue) {
            this.directionLabel = directionLabel;
            this.outputValue = outputValue;
        }

        public String getDirectionLabel() {
            return directionLabel;
        }

        public double getOutputValue() {
            return outputValue;
        }

        @Override
        public String toString() {
            return "InterpolationResult{" +
                    "directionLabel='" + directionLabel + '\'' +
                    ", outputValue=" + outputValue +
                    '}';
        }
    }
}
