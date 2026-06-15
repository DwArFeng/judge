package com.dwarfeng.judge.impl.handler.judger.linear;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.Bean;

/**
 * 线性映射判断器配置。
 *
 * <p>
 * 该配置用于描述线性映射判断器的输入值 <code>x</code>、系数 <code>k</code>、偏移 <code>b</code> 三个数值源，
 * 以及输出目标。判断器按 <code>k * x + b</code> 计算映射值，并裁剪到 <code>[0.0, 1.0]</code> 后写入判断结果。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
public class LinearMappingJudgerConfig implements Bean {

    private static final long serialVersionUID = -3703738576390725349L;

    @JSONField(name = "#output_judgement_data_id", ordinal = 1)
    private String outputJudgementDataIdRem = "输出判断结果的 dataStringId。";

    @JSONField(name = "output_judgement_data_id", ordinal = 2)
    private String outputJudgementDataId;

    @JSONField(name = "#input_source_type", ordinal = 3)
    private String inputSourceTypeRem = "输入值 x 的来源类型，合法值为 constant、analysis。";

    @JSONField(name = "input_source_type", ordinal = 4)
    private String inputSourceType;

    @JSONField(name = "#input_constant_value", ordinal = 5)
    private String inputConstantValueRem = "当 input_source_type=constant 时必填，表示输入值 x，按 double 解析。";

    @JSONField(name = "input_constant_value", ordinal = 6)
    private String inputConstantValue;

    @JSONField(name = "#input_analysis_data_id", ordinal = 7)
    private String inputAnalysisDataIdRem = "当 input_source_type=analysis 时必填，用于读取分析结果作为输入值 x。";

    @JSONField(name = "input_analysis_data_id", ordinal = 8)
    private String inputAnalysisDataId;

    @JSONField(name = "#input_false_value", ordinal = 9)
    private String inputFalseValueRem = "当输入值 x 的分析结果类型为 BOOLEAN 时必填，表示 false 转换出的 double 值。";

    @JSONField(name = "input_false_value", ordinal = 10)
    private String inputFalseValue;

    @JSONField(name = "#input_true_value", ordinal = 11)
    private String inputTrueValueRem = "当输入值 x 的分析结果类型为 BOOLEAN 时必填，表示 true 转换出的 double 值。";

    @JSONField(name = "input_true_value", ordinal = 12)
    private String inputTrueValue;

    @JSONField(name = "#coefficient_source_type", ordinal = 13)
    private String coefficientSourceTypeRem = "系数 k 的来源类型，合法值为 constant、analysis。";

    @JSONField(name = "coefficient_source_type", ordinal = 14)
    private String coefficientSourceType;

    @JSONField(name = "#coefficient_constant_value", ordinal = 15)
    private String coefficientConstantValueRem =
            "当 coefficient_source_type=constant 时必填，表示系数 k，按 double 解析，允许负数。";

    @JSONField(name = "coefficient_constant_value", ordinal = 16)
    private String coefficientConstantValue;

    @JSONField(name = "#coefficient_analysis_data_id", ordinal = 17)
    private String coefficientAnalysisDataIdRem = "当 coefficient_source_type=analysis 时必填，用于读取分析结果作为系数 k。";

    @JSONField(name = "coefficient_analysis_data_id", ordinal = 18)
    private String coefficientAnalysisDataId;

    @JSONField(name = "#coefficient_false_value", ordinal = 19)
    private String coefficientFalseValueRem = "当系数 k 的分析结果类型为 BOOLEAN 时必填，表示 false 转换出的 double 值。";

    @JSONField(name = "coefficient_false_value", ordinal = 20)
    private String coefficientFalseValue;

    @JSONField(name = "#coefficient_true_value", ordinal = 21)
    private String coefficientTrueValueRem = "当系数 k 的分析结果类型为 BOOLEAN 时必填，表示 true 转换出的 double 值。";

    @JSONField(name = "coefficient_true_value", ordinal = 22)
    private String coefficientTrueValue;

    @JSONField(name = "#offset_source_type", ordinal = 23)
    private String offsetSourceTypeRem = "偏移 b 的来源类型，合法值为 constant、analysis。";

    @JSONField(name = "offset_source_type", ordinal = 24)
    private String offsetSourceType;

    @JSONField(name = "#offset_constant_value", ordinal = 25)
    private String offsetConstantValueRem = "当 offset_source_type=constant 时必填，表示偏移 b，按 double 解析，允许负数。";

    @JSONField(name = "offset_constant_value", ordinal = 26)
    private String offsetConstantValue;

    @JSONField(name = "#offset_analysis_data_id", ordinal = 27)
    private String offsetAnalysisDataIdRem = "当 offset_source_type=analysis 时必填，用于读取分析结果作为偏移 b。";

    @JSONField(name = "offset_analysis_data_id", ordinal = 28)
    private String offsetAnalysisDataId;

    @JSONField(name = "#offset_false_value", ordinal = 29)
    private String offsetFalseValueRem = "当偏移 b 的分析结果类型为 BOOLEAN 时必填，表示 false 转换出的 double 值。";

    @JSONField(name = "offset_false_value", ordinal = 30)
    private String offsetFalseValue;

    @JSONField(name = "#offset_true_value", ordinal = 31)
    private String offsetTrueValueRem = "当偏移 b 的分析结果类型为 BOOLEAN 时必填，表示 true 转换出的 double 值。";

    @JSONField(name = "offset_true_value", ordinal = 32)
    private String offsetTrueValue;

    public LinearMappingJudgerConfig() {
    }

    public LinearMappingJudgerConfig(
            String outputJudgementDataId, String inputSourceType, String inputConstantValue,
            String inputAnalysisDataId, String inputFalseValue, String inputTrueValue,
            String coefficientSourceType, String coefficientConstantValue, String coefficientAnalysisDataId,
            String coefficientFalseValue, String coefficientTrueValue,
            String offsetSourceType, String offsetConstantValue, String offsetAnalysisDataId,
            String offsetFalseValue, String offsetTrueValue
    ) {
        this.outputJudgementDataId = outputJudgementDataId;
        this.inputSourceType = inputSourceType;
        this.inputConstantValue = inputConstantValue;
        this.inputAnalysisDataId = inputAnalysisDataId;
        this.inputFalseValue = inputFalseValue;
        this.inputTrueValue = inputTrueValue;
        this.coefficientSourceType = coefficientSourceType;
        this.coefficientConstantValue = coefficientConstantValue;
        this.coefficientAnalysisDataId = coefficientAnalysisDataId;
        this.coefficientFalseValue = coefficientFalseValue;
        this.coefficientTrueValue = coefficientTrueValue;
        this.offsetSourceType = offsetSourceType;
        this.offsetConstantValue = offsetConstantValue;
        this.offsetAnalysisDataId = offsetAnalysisDataId;
        this.offsetFalseValue = offsetFalseValue;
        this.offsetTrueValue = offsetTrueValue;
    }

    public String getOutputJudgementDataIdRem() {
        return outputJudgementDataIdRem;
    }

    public void setOutputJudgementDataIdRem(String outputJudgementDataIdRem) {
        this.outputJudgementDataIdRem = outputJudgementDataIdRem;
    }

    public String getOutputJudgementDataId() {
        return outputJudgementDataId;
    }

    public void setOutputJudgementDataId(String outputJudgementDataId) {
        this.outputJudgementDataId = outputJudgementDataId;
    }

    public String getInputSourceTypeRem() {
        return inputSourceTypeRem;
    }

    public void setInputSourceTypeRem(String inputSourceTypeRem) {
        this.inputSourceTypeRem = inputSourceTypeRem;
    }

    public String getInputSourceType() {
        return inputSourceType;
    }

    public void setInputSourceType(String inputSourceType) {
        this.inputSourceType = inputSourceType;
    }

    public String getInputConstantValueRem() {
        return inputConstantValueRem;
    }

    public void setInputConstantValueRem(String inputConstantValueRem) {
        this.inputConstantValueRem = inputConstantValueRem;
    }

    public String getInputConstantValue() {
        return inputConstantValue;
    }

    public void setInputConstantValue(String inputConstantValue) {
        this.inputConstantValue = inputConstantValue;
    }

    public String getInputAnalysisDataIdRem() {
        return inputAnalysisDataIdRem;
    }

    public void setInputAnalysisDataIdRem(String inputAnalysisDataIdRem) {
        this.inputAnalysisDataIdRem = inputAnalysisDataIdRem;
    }

    public String getInputAnalysisDataId() {
        return inputAnalysisDataId;
    }

    public void setInputAnalysisDataId(String inputAnalysisDataId) {
        this.inputAnalysisDataId = inputAnalysisDataId;
    }

    public String getInputFalseValueRem() {
        return inputFalseValueRem;
    }

    public void setInputFalseValueRem(String inputFalseValueRem) {
        this.inputFalseValueRem = inputFalseValueRem;
    }

    public String getInputFalseValue() {
        return inputFalseValue;
    }

    public void setInputFalseValue(String inputFalseValue) {
        this.inputFalseValue = inputFalseValue;
    }

    public String getInputTrueValueRem() {
        return inputTrueValueRem;
    }

    public void setInputTrueValueRem(String inputTrueValueRem) {
        this.inputTrueValueRem = inputTrueValueRem;
    }

    public String getInputTrueValue() {
        return inputTrueValue;
    }

    public void setInputTrueValue(String inputTrueValue) {
        this.inputTrueValue = inputTrueValue;
    }

    public String getCoefficientSourceTypeRem() {
        return coefficientSourceTypeRem;
    }

    public void setCoefficientSourceTypeRem(String coefficientSourceTypeRem) {
        this.coefficientSourceTypeRem = coefficientSourceTypeRem;
    }

    public String getCoefficientSourceType() {
        return coefficientSourceType;
    }

    public void setCoefficientSourceType(String coefficientSourceType) {
        this.coefficientSourceType = coefficientSourceType;
    }

    public String getCoefficientConstantValueRem() {
        return coefficientConstantValueRem;
    }

    public void setCoefficientConstantValueRem(String coefficientConstantValueRem) {
        this.coefficientConstantValueRem = coefficientConstantValueRem;
    }

    public String getCoefficientConstantValue() {
        return coefficientConstantValue;
    }

    public void setCoefficientConstantValue(String coefficientConstantValue) {
        this.coefficientConstantValue = coefficientConstantValue;
    }

    public String getCoefficientAnalysisDataIdRem() {
        return coefficientAnalysisDataIdRem;
    }

    public void setCoefficientAnalysisDataIdRem(String coefficientAnalysisDataIdRem) {
        this.coefficientAnalysisDataIdRem = coefficientAnalysisDataIdRem;
    }

    public String getCoefficientAnalysisDataId() {
        return coefficientAnalysisDataId;
    }

    public void setCoefficientAnalysisDataId(String coefficientAnalysisDataId) {
        this.coefficientAnalysisDataId = coefficientAnalysisDataId;
    }

    public String getCoefficientFalseValueRem() {
        return coefficientFalseValueRem;
    }

    public void setCoefficientFalseValueRem(String coefficientFalseValueRem) {
        this.coefficientFalseValueRem = coefficientFalseValueRem;
    }

    public String getCoefficientFalseValue() {
        return coefficientFalseValue;
    }

    public void setCoefficientFalseValue(String coefficientFalseValue) {
        this.coefficientFalseValue = coefficientFalseValue;
    }

    public String getCoefficientTrueValueRem() {
        return coefficientTrueValueRem;
    }

    public void setCoefficientTrueValueRem(String coefficientTrueValueRem) {
        this.coefficientTrueValueRem = coefficientTrueValueRem;
    }

    public String getCoefficientTrueValue() {
        return coefficientTrueValue;
    }

    public void setCoefficientTrueValue(String coefficientTrueValue) {
        this.coefficientTrueValue = coefficientTrueValue;
    }

    public String getOffsetSourceTypeRem() {
        return offsetSourceTypeRem;
    }

    public void setOffsetSourceTypeRem(String offsetSourceTypeRem) {
        this.offsetSourceTypeRem = offsetSourceTypeRem;
    }

    public String getOffsetSourceType() {
        return offsetSourceType;
    }

    public void setOffsetSourceType(String offsetSourceType) {
        this.offsetSourceType = offsetSourceType;
    }

    public String getOffsetConstantValueRem() {
        return offsetConstantValueRem;
    }

    public void setOffsetConstantValueRem(String offsetConstantValueRem) {
        this.offsetConstantValueRem = offsetConstantValueRem;
    }

    public String getOffsetConstantValue() {
        return offsetConstantValue;
    }

    public void setOffsetConstantValue(String offsetConstantValue) {
        this.offsetConstantValue = offsetConstantValue;
    }

    public String getOffsetAnalysisDataIdRem() {
        return offsetAnalysisDataIdRem;
    }

    public void setOffsetAnalysisDataIdRem(String offsetAnalysisDataIdRem) {
        this.offsetAnalysisDataIdRem = offsetAnalysisDataIdRem;
    }

    public String getOffsetAnalysisDataId() {
        return offsetAnalysisDataId;
    }

    public void setOffsetAnalysisDataId(String offsetAnalysisDataId) {
        this.offsetAnalysisDataId = offsetAnalysisDataId;
    }

    public String getOffsetFalseValueRem() {
        return offsetFalseValueRem;
    }

    public void setOffsetFalseValueRem(String offsetFalseValueRem) {
        this.offsetFalseValueRem = offsetFalseValueRem;
    }

    public String getOffsetFalseValue() {
        return offsetFalseValue;
    }

    public void setOffsetFalseValue(String offsetFalseValue) {
        this.offsetFalseValue = offsetFalseValue;
    }

    public String getOffsetTrueValueRem() {
        return offsetTrueValueRem;
    }

    public void setOffsetTrueValueRem(String offsetTrueValueRem) {
        this.offsetTrueValueRem = offsetTrueValueRem;
    }

    public String getOffsetTrueValue() {
        return offsetTrueValue;
    }

    public void setOffsetTrueValue(String offsetTrueValue) {
        this.offsetTrueValue = offsetTrueValue;
    }

    @Override
    public String toString() {
        return "LinearMappingJudgerConfig{" +
                "outputJudgementDataIdRem='" + outputJudgementDataIdRem + '\'' +
                ", outputJudgementDataId='" + outputJudgementDataId + '\'' +
                ", inputSourceTypeRem='" + inputSourceTypeRem + '\'' +
                ", inputSourceType='" + inputSourceType + '\'' +
                ", inputConstantValueRem='" + inputConstantValueRem + '\'' +
                ", inputConstantValue='" + inputConstantValue + '\'' +
                ", inputAnalysisDataIdRem='" + inputAnalysisDataIdRem + '\'' +
                ", inputAnalysisDataId='" + inputAnalysisDataId + '\'' +
                ", inputFalseValueRem='" + inputFalseValueRem + '\'' +
                ", inputFalseValue='" + inputFalseValue + '\'' +
                ", inputTrueValueRem='" + inputTrueValueRem + '\'' +
                ", inputTrueValue='" + inputTrueValue + '\'' +
                ", coefficientSourceTypeRem='" + coefficientSourceTypeRem + '\'' +
                ", coefficientSourceType='" + coefficientSourceType + '\'' +
                ", coefficientConstantValueRem='" + coefficientConstantValueRem + '\'' +
                ", coefficientConstantValue='" + coefficientConstantValue + '\'' +
                ", coefficientAnalysisDataIdRem='" + coefficientAnalysisDataIdRem + '\'' +
                ", coefficientAnalysisDataId='" + coefficientAnalysisDataId + '\'' +
                ", coefficientFalseValueRem='" + coefficientFalseValueRem + '\'' +
                ", coefficientFalseValue='" + coefficientFalseValue + '\'' +
                ", coefficientTrueValueRem='" + coefficientTrueValueRem + '\'' +
                ", coefficientTrueValue='" + coefficientTrueValue + '\'' +
                ", offsetSourceTypeRem='" + offsetSourceTypeRem + '\'' +
                ", offsetSourceType='" + offsetSourceType + '\'' +
                ", offsetConstantValueRem='" + offsetConstantValueRem + '\'' +
                ", offsetConstantValue='" + offsetConstantValue + '\'' +
                ", offsetAnalysisDataIdRem='" + offsetAnalysisDataIdRem + '\'' +
                ", offsetAnalysisDataId='" + offsetAnalysisDataId + '\'' +
                ", offsetFalseValueRem='" + offsetFalseValueRem + '\'' +
                ", offsetFalseValue='" + offsetFalseValue + '\'' +
                ", offsetTrueValueRem='" + offsetTrueValueRem + '\'' +
                ", offsetTrueValue='" + offsetTrueValue + '\'' +
                '}';
    }
}
