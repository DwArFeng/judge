package com.dwarfeng.judge.impl.handler.judger.linear;

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
 * 线性映射判断器执行器。
 *
 * <p>
 * 执行器分别解析输入值 <code>x</code>、系数 <code>k</code>、偏移 <code>b</code> 三个数值源，
 * 按 <code>k * x + b</code> 计算映射值，裁剪到 <code>[0.0, 1.0]</code> 后通过判断器上下文写入判断结果。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component("linearMappingJudgerRegistry.linearMappingExecutor")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LinearMappingExecutor extends AbstractExecutor {

    private static final String SOURCE_TYPE_CONSTANT = "constant";
    private static final String SOURCE_TYPE_ANALYSIS = "analysis";

    private final LinearMappingJudgerConfig config;

    public LinearMappingExecutor(LinearMappingJudgerConfig config) {
        this.config = config;
    }

    @Override
    public void judge() throws Exception {
        checkRequired("output_judgement_data_id", config.getOutputJudgementDataId());
        checkRequired("input_source_type", config.getInputSourceType());
        checkRequired("coefficient_source_type", config.getCoefficientSourceType());
        checkRequired("offset_source_type", config.getOffsetSourceType());

        ValueInfo xInfo = resolveNumericSource(
                "input_source_type", config.getInputSourceType(),
                "input_constant_value", config.getInputConstantValue(),
                "input_analysis_data_id", config.getInputAnalysisDataId(),
                "input_false_value", config.getInputFalseValue(),
                "input_true_value", config.getInputTrueValue()
        );
        ValueInfo kInfo = resolveNumericSource(
                "coefficient_source_type", config.getCoefficientSourceType(),
                "coefficient_constant_value", config.getCoefficientConstantValue(),
                "coefficient_analysis_data_id", config.getCoefficientAnalysisDataId(),
                "coefficient_false_value", config.getCoefficientFalseValue(),
                "coefficient_true_value", config.getCoefficientTrueValue()
        );
        ValueInfo bInfo = resolveNumericSource(
                "offset_source_type", config.getOffsetSourceType(),
                "offset_constant_value", config.getOffsetConstantValue(),
                "offset_analysis_data_id", config.getOffsetAnalysisDataId(),
                "offset_false_value", config.getOffsetFalseValue(),
                "offset_true_value", config.getOffsetTrueValue()
        );

        double mapped = kInfo.getConvertedValue() * xInfo.getConvertedValue() + bInfo.getConvertedValue();
        double outputValue = clip(mapped);
        context.upsertJudgement(new JudgementUpsertInfo(
                context.getTaskKey(), config.getOutputJudgementDataId(), outputValue,
                makeMessage(xInfo, kInfo, bInfo, mapped, outputValue)
        ));
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
                throw new JudgerExecutionException(
                        "线性映射判断器配置项 " + sourceTypeField + " 非法: " + sourceType
                );
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
            throw new JudgerExecutionException(
                    "线性映射判断器分析结果不存在, " + analysisDataIdField + ": " + analysisDataId
            );
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
                        "线性映射判断器不支持的分析结果类型, " + analysisDataIdField + ": " + analysisDataId +
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
            throw new JudgerExecutionException("线性映射判断器配置项 " + dataId + " 不能为空");
        }
    }

    private double parseDouble(String dataId, String value) throws JudgerExecutionException {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            throw new JudgerExecutionException(
                    "线性映射判断器配置项 " + dataId + " 无法按 double 解析: " + value, e
            );
        }
    }

    // 为了保证代码的可读性，此处代码不做简化。
    @SuppressWarnings("ManualMinMaxCalculation")
    private double clip(double value) {
        if (value < 0.0) {
            return 0.0;
        }
        if (value > 1.0) {
            return 1.0;
        }
        return value;
    }

    private String makeMessage(
            ValueInfo xInfo, ValueInfo kInfo, ValueInfo bInfo, double mapped, double outputValue
    ) {
        return "线性映射判断器: x来源=" + xInfo.getSourceLabel() +
                ", x原始值=" + xInfo.getRawValue() +
                ", x值=" + xInfo.getConvertedValue() +
                ", k来源=" + kInfo.getSourceLabel() +
                ", k原始值=" + kInfo.getRawValue() +
                ", k值=" + kInfo.getConvertedValue() +
                ", b来源=" + bInfo.getSourceLabel() +
                ", b原始值=" + bInfo.getRawValue() +
                ", b值=" + bInfo.getConvertedValue() +
                ", 映射值=" + mapped +
                ", 输出值=" + outputValue;
    }

    @Override
    public String toString() {
        return "LinearMappingExecutor{" +
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
