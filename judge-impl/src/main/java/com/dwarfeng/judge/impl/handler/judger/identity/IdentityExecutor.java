package com.dwarfeng.judge.impl.handler.judger.identity;

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
 * 本征判断器执行器。
 *
 * <p>
 * 执行器从配置指定的常量或分析结果中取得原始值，将其转换为 double 后裁剪到 <code>[0.0, 1.0]</code>，
 * 最后通过判断器上下文写入判断结果。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component("identityJudgerRegistry.identityExecutor")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class IdentityExecutor extends AbstractExecutor {

    private static final String INPUT_SOURCE_TYPE_CONSTANT = "constant";
    private static final String INPUT_SOURCE_TYPE_ANALYSIS = "analysis";

    private final IdentityJudgerConfig config;

    public IdentityExecutor(IdentityJudgerConfig config) {
        this.config = config;
    }

    @Override
    public void judge() throws Exception {
        checkRequired("output_judgement_data_id", config.getOutputJudgementDataId());
        checkRequired("input_source_type", config.getInputSourceType());

        ValueInfo valueInfo = inspectValueInfo();
        double outputValue = clip(valueInfo.getConvertedValue());
        context.upsertJudgement(new JudgementUpsertInfo(
                context.getTaskKey(), config.getOutputJudgementDataId(), outputValue,
                makeMessage(valueInfo, outputValue)
        ));
    }

    private ValueInfo inspectValueInfo() throws Exception {
        switch (config.getInputSourceType()) {
            case INPUT_SOURCE_TYPE_CONSTANT:
                return inspectConstantValueInfo();
            case INPUT_SOURCE_TYPE_ANALYSIS:
                return inspectAnalysisValueInfo();
            default:
                throw new JudgerExecutionException(
                        "本征判断器配置项 input_source_type 非法: " + config.getInputSourceType()
                );
        }
    }

    private ValueInfo inspectConstantValueInfo() throws JudgerExecutionException {
        checkRequired("input_constant_value", config.getInputConstantValue());
        double value = parseDouble("input_constant_value", config.getInputConstantValue());
        return new ValueInfo("常量", config.getInputConstantValue(), value);
    }

    private ValueInfo inspectAnalysisValueInfo() throws Exception {
        checkRequired("input_analysis_data_id", config.getInputAnalysisDataId());
        AnalysisInspectResult result = context.inspectAnalysis(
                new AnalysisInspectInfo(context.getTaskKey(), config.getInputAnalysisDataId())
        );
        if (Objects.isNull(result)) {
            throw new JudgerExecutionException(
                    "本征判断器分析结果不存在, input_analysis_data_id: " + config.getInputAnalysisDataId()
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
                convertedValue = parseDouble("input_analysis_data_id=" + config.getInputAnalysisDataId(), (String) rawValue);
                break;
            case Constants.ANALYSIS_TYPE_BOOLEAN:
                convertedValue = parseBooleanValue((Boolean) rawValue);
                break;
            default:
                throw new JudgerExecutionException(
                        "本征判断器不支持的分析结果类型, input_analysis_data_id: " + config.getInputAnalysisDataId() +
                                ", data_type: " + result.getDataType()
                );
        }
        return new ValueInfo("分析结果", String.valueOf(rawValue), convertedValue);
    }

    private double parseBooleanValue(Boolean rawValue) throws JudgerExecutionException {
        checkRequired("input_false_value", config.getInputFalseValue());
        checkRequired("input_true_value", config.getInputTrueValue());
        if (rawValue) {
            return parseDouble("input_true_value", config.getInputTrueValue());
        } else {
            return parseDouble("input_false_value", config.getInputFalseValue());
        }
    }

    private void checkRequired(String fieldName, String value) throws JudgerExecutionException {
        if (Objects.isNull(value) || value.trim().isEmpty()) {
            throw new JudgerExecutionException("本征判断器配置项 " + fieldName + " 不能为空");
        }
    }

    private double parseDouble(String fieldName, String value) throws JudgerExecutionException {
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            throw new JudgerExecutionException("本征判断器配置项 " + fieldName + " 无法按 double 解析: " + value, e);
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

    private String makeMessage(ValueInfo valueInfo, double outputValue) {
        return "本征判断器: 来源=" + valueInfo.getSourceLabel() +
                ", 原始值=" + valueInfo.getRawValue() +
                ", 转换值=" + valueInfo.getConvertedValue() +
                ", 输出值=" + outputValue;
    }

    @Override
    public String toString() {
        return "IdentityExecutor{" +
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
