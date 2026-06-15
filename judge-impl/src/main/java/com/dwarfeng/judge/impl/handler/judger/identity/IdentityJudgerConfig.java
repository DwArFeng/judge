package com.dwarfeng.judge.impl.handler.judger.identity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.Bean;

/**
 * 本征判断器配置。
 *
 * <p>
 * 该配置用于描述本征判断器的输入来源和输出目标。本征判断器会将常量或分析结果转换为 double，
 * 并裁剪到 <code>[0.0, 1.0]</code> 后写入判断结果。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
public class IdentityJudgerConfig implements Bean {

    private static final long serialVersionUID = 4042492412262331023L;

    @JSONField(name = "#output_judgement_data_id", ordinal = 1)
    private String outputJudgementDataIdRem = "输出判断结果的 dataStringId。";

    @JSONField(name = "output_judgement_data_id", ordinal = 2)
    private String outputJudgementDataId;

    @JSONField(name = "#input_source_type", ordinal = 3)
    private String inputSourceTypeRem = "输入来源类型，合法值为 constant、analysis。";

    @JSONField(name = "input_source_type", ordinal = 4)
    private String inputSourceType;

    @JSONField(name = "#input_constant_value", ordinal = 5)
    private String inputConstantValueRem = "当 input_source_type=constant 时必填，按 double 解析。";

    @JSONField(name = "input_constant_value", ordinal = 6)
    private String inputConstantValue;

    @JSONField(name = "#input_analysis_data_id", ordinal = 7)
    private String inputAnalysisDataIdRem = "当 input_source_type=analysis 时必填，用于读取分析结果。";

    @JSONField(name = "input_analysis_data_id", ordinal = 8)
    private String inputAnalysisDataId;

    @JSONField(name = "#input_false_value", ordinal = 9)
    private String inputFalseValueRem = "当 BOOLEAN 分析结果为 false 时转换出的 double 值。";

    @JSONField(name = "input_false_value", ordinal = 10)
    private String inputFalseValue;

    @JSONField(name = "#input_true_value", ordinal = 11)
    private String inputTrueValueRem = "当 BOOLEAN 分析结果为 true 时转换出的 double 值。";

    @JSONField(name = "input_true_value", ordinal = 12)
    private String inputTrueValue;

    public IdentityJudgerConfig() {
    }

    public IdentityJudgerConfig(
            String outputJudgementDataId, String inputSourceType, String inputConstantValue,
            String inputAnalysisDataId, String inputFalseValue, String inputTrueValue
    ) {
        this.outputJudgementDataId = outputJudgementDataId;
        this.inputSourceType = inputSourceType;
        this.inputConstantValue = inputConstantValue;
        this.inputAnalysisDataId = inputAnalysisDataId;
        this.inputFalseValue = inputFalseValue;
        this.inputTrueValue = inputTrueValue;
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

    @Override
    public String toString() {
        return "IdentityJudgerConfig{" +
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
                '}';
    }
}
