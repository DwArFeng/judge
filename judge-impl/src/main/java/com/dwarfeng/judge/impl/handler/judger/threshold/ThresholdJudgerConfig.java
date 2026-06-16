package com.dwarfeng.judge.impl.handler.judger.threshold;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.Bean;

/**
 * 阈值判断器配置。
 *
 * <p>
 * 该配置用于描述阈值判断器的输入值、低阈值、高阈值三个数值源以及输出目标。
 * 判断器在 low_threshold 与 high_threshold 之间按线性插值输出 <code>[0.0, 1.0]</code> 范围内的标准化结果。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
public class ThresholdJudgerConfig implements Bean {

    private static final long serialVersionUID = -5327271751011027069L;

    @JSONField(name = "#output_judgement_data_id", ordinal = 1)
    private String outputJudgementDataIdRem = "输出判断结果的 dataStringId。";

    @JSONField(name = "output_judgement_data_id", ordinal = 2)
    private String outputJudgementDataId;

    @JSONField(name = "#input_source_type", ordinal = 3)
    private String inputSourceTypeRem = "输入值来源类型，合法值为 constant、analysis。";

    @JSONField(name = "input_source_type", ordinal = 4)
    private String inputSourceType;

    @JSONField(name = "#input_constant_value", ordinal = 5)
    private String inputConstantValueRem = "当 input_source_type=constant 时必填，按 double 解析。";

    @JSONField(name = "input_constant_value", ordinal = 6)
    private String inputConstantValue;

    @JSONField(name = "#input_analysis_data_id", ordinal = 7)
    private String inputAnalysisDataIdRem = "当 input_source_type=analysis 时必填，用于读取输入值分析结果。";

    @JSONField(name = "input_analysis_data_id", ordinal = 8)
    private String inputAnalysisDataId;

    @JSONField(name = "#input_false_value", ordinal = 9)
    private String inputFalseValueRem = "当输入分析结果类型为 BOOLEAN 时必填，表示 false 转换出的 double 值。";

    @JSONField(name = "input_false_value", ordinal = 10)
    private String inputFalseValue;

    @JSONField(name = "#input_true_value", ordinal = 11)
    private String inputTrueValueRem = "当输入分析结果类型为 BOOLEAN 时必填，表示 true 转换出的 double 值。";

    @JSONField(name = "input_true_value", ordinal = 12)
    private String inputTrueValue;

    @JSONField(name = "#low_threshold_source_type", ordinal = 13)
    private String lowThresholdSourceTypeRem = "低阈值来源类型，合法值为 constant、analysis。";

    @JSONField(name = "low_threshold_source_type", ordinal = 14)
    private String lowThresholdSourceType;

    @JSONField(name = "#low_threshold_constant_value", ordinal = 15)
    private String lowThresholdConstantValueRem = "当 low_threshold_source_type=constant 时必填，按 double 解析。";

    @JSONField(name = "low_threshold_constant_value", ordinal = 16)
    private String lowThresholdConstantValue;

    @JSONField(name = "#low_threshold_analysis_data_id", ordinal = 17)
    private String lowThresholdAnalysisDataIdRem = "当 low_threshold_source_type=analysis 时必填，用于读取低阈值分析结果。";

    @JSONField(name = "low_threshold_analysis_data_id", ordinal = 18)
    private String lowThresholdAnalysisDataId;

    @JSONField(name = "#low_threshold_false_value", ordinal = 19)
    private String lowThresholdFalseValueRem = "当低阈值分析结果类型为 BOOLEAN 时必填，表示 false 转换出的 double 值。";

    @JSONField(name = "low_threshold_false_value", ordinal = 20)
    private String lowThresholdFalseValue;

    @JSONField(name = "#low_threshold_true_value", ordinal = 21)
    private String lowThresholdTrueValueRem = "当低阈值分析结果类型为 BOOLEAN 时必填，表示 true 转换出的 double 值。";

    @JSONField(name = "low_threshold_true_value", ordinal = 22)
    private String lowThresholdTrueValue;

    @JSONField(name = "#high_threshold_source_type", ordinal = 23)
    private String highThresholdSourceTypeRem = "高阈值来源类型，合法值为 constant、analysis。";

    @JSONField(name = "high_threshold_source_type", ordinal = 24)
    private String highThresholdSourceType;

    @JSONField(name = "#high_threshold_constant_value", ordinal = 25)
    private String highThresholdConstantValueRem = "当 high_threshold_source_type=constant 时必填，按 double 解析。";

    @JSONField(name = "high_threshold_constant_value", ordinal = 26)
    private String highThresholdConstantValue;

    @JSONField(name = "#high_threshold_analysis_data_id", ordinal = 27)
    private String highThresholdAnalysisDataIdRem = "当 high_threshold_source_type=analysis 时必填，用于读取高阈值分析结果。";

    @JSONField(name = "high_threshold_analysis_data_id", ordinal = 28)
    private String highThresholdAnalysisDataId;

    @JSONField(name = "#high_threshold_false_value", ordinal = 29)
    private String highThresholdFalseValueRem = "当高阈值分析结果类型为 BOOLEAN 时必填，表示 false 转换出的 double 值。";

    @JSONField(name = "high_threshold_false_value", ordinal = 30)
    private String highThresholdFalseValue;

    @JSONField(name = "#high_threshold_true_value", ordinal = 31)
    private String highThresholdTrueValueRem = "当高阈值分析结果类型为 BOOLEAN 时必填，表示 true 转换出的 double 值。";

    @JSONField(name = "high_threshold_true_value", ordinal = 32)
    private String highThresholdTrueValue;

    public ThresholdJudgerConfig() {
    }

    public ThresholdJudgerConfig(
            String outputJudgementDataId,
            String inputSourceType, String inputConstantValue, String inputAnalysisDataId,
            String inputFalseValue, String inputTrueValue,
            String lowThresholdSourceType, String lowThresholdConstantValue, String lowThresholdAnalysisDataId,
            String lowThresholdFalseValue, String lowThresholdTrueValue,
            String highThresholdSourceType, String highThresholdConstantValue, String highThresholdAnalysisDataId,
            String highThresholdFalseValue, String highThresholdTrueValue
    ) {
        this.outputJudgementDataId = outputJudgementDataId;
        this.inputSourceType = inputSourceType;
        this.inputConstantValue = inputConstantValue;
        this.inputAnalysisDataId = inputAnalysisDataId;
        this.inputFalseValue = inputFalseValue;
        this.inputTrueValue = inputTrueValue;
        this.lowThresholdSourceType = lowThresholdSourceType;
        this.lowThresholdConstantValue = lowThresholdConstantValue;
        this.lowThresholdAnalysisDataId = lowThresholdAnalysisDataId;
        this.lowThresholdFalseValue = lowThresholdFalseValue;
        this.lowThresholdTrueValue = lowThresholdTrueValue;
        this.highThresholdSourceType = highThresholdSourceType;
        this.highThresholdConstantValue = highThresholdConstantValue;
        this.highThresholdAnalysisDataId = highThresholdAnalysisDataId;
        this.highThresholdFalseValue = highThresholdFalseValue;
        this.highThresholdTrueValue = highThresholdTrueValue;
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

    public String getLowThresholdSourceTypeRem() {
        return lowThresholdSourceTypeRem;
    }

    public void setLowThresholdSourceTypeRem(String lowThresholdSourceTypeRem) {
        this.lowThresholdSourceTypeRem = lowThresholdSourceTypeRem;
    }

    public String getLowThresholdSourceType() {
        return lowThresholdSourceType;
    }

    public void setLowThresholdSourceType(String lowThresholdSourceType) {
        this.lowThresholdSourceType = lowThresholdSourceType;
    }

    public String getLowThresholdConstantValueRem() {
        return lowThresholdConstantValueRem;
    }

    public void setLowThresholdConstantValueRem(String lowThresholdConstantValueRem) {
        this.lowThresholdConstantValueRem = lowThresholdConstantValueRem;
    }

    public String getLowThresholdConstantValue() {
        return lowThresholdConstantValue;
    }

    public void setLowThresholdConstantValue(String lowThresholdConstantValue) {
        this.lowThresholdConstantValue = lowThresholdConstantValue;
    }

    public String getLowThresholdAnalysisDataIdRem() {
        return lowThresholdAnalysisDataIdRem;
    }

    public void setLowThresholdAnalysisDataIdRem(String lowThresholdAnalysisDataIdRem) {
        this.lowThresholdAnalysisDataIdRem = lowThresholdAnalysisDataIdRem;
    }

    public String getLowThresholdAnalysisDataId() {
        return lowThresholdAnalysisDataId;
    }

    public void setLowThresholdAnalysisDataId(String lowThresholdAnalysisDataId) {
        this.lowThresholdAnalysisDataId = lowThresholdAnalysisDataId;
    }

    public String getLowThresholdFalseValueRem() {
        return lowThresholdFalseValueRem;
    }

    public void setLowThresholdFalseValueRem(String lowThresholdFalseValueRem) {
        this.lowThresholdFalseValueRem = lowThresholdFalseValueRem;
    }

    public String getLowThresholdFalseValue() {
        return lowThresholdFalseValue;
    }

    public void setLowThresholdFalseValue(String lowThresholdFalseValue) {
        this.lowThresholdFalseValue = lowThresholdFalseValue;
    }

    public String getLowThresholdTrueValueRem() {
        return lowThresholdTrueValueRem;
    }

    public void setLowThresholdTrueValueRem(String lowThresholdTrueValueRem) {
        this.lowThresholdTrueValueRem = lowThresholdTrueValueRem;
    }

    public String getLowThresholdTrueValue() {
        return lowThresholdTrueValue;
    }

    public void setLowThresholdTrueValue(String lowThresholdTrueValue) {
        this.lowThresholdTrueValue = lowThresholdTrueValue;
    }

    public String getHighThresholdSourceTypeRem() {
        return highThresholdSourceTypeRem;
    }

    public void setHighThresholdSourceTypeRem(String highThresholdSourceTypeRem) {
        this.highThresholdSourceTypeRem = highThresholdSourceTypeRem;
    }

    public String getHighThresholdSourceType() {
        return highThresholdSourceType;
    }

    public void setHighThresholdSourceType(String highThresholdSourceType) {
        this.highThresholdSourceType = highThresholdSourceType;
    }

    public String getHighThresholdConstantValueRem() {
        return highThresholdConstantValueRem;
    }

    public void setHighThresholdConstantValueRem(String highThresholdConstantValueRem) {
        this.highThresholdConstantValueRem = highThresholdConstantValueRem;
    }

    public String getHighThresholdConstantValue() {
        return highThresholdConstantValue;
    }

    public void setHighThresholdConstantValue(String highThresholdConstantValue) {
        this.highThresholdConstantValue = highThresholdConstantValue;
    }

    public String getHighThresholdAnalysisDataIdRem() {
        return highThresholdAnalysisDataIdRem;
    }

    public void setHighThresholdAnalysisDataIdRem(String highThresholdAnalysisDataIdRem) {
        this.highThresholdAnalysisDataIdRem = highThresholdAnalysisDataIdRem;
    }

    public String getHighThresholdAnalysisDataId() {
        return highThresholdAnalysisDataId;
    }

    public void setHighThresholdAnalysisDataId(String highThresholdAnalysisDataId) {
        this.highThresholdAnalysisDataId = highThresholdAnalysisDataId;
    }

    public String getHighThresholdFalseValueRem() {
        return highThresholdFalseValueRem;
    }

    public void setHighThresholdFalseValueRem(String highThresholdFalseValueRem) {
        this.highThresholdFalseValueRem = highThresholdFalseValueRem;
    }

    public String getHighThresholdFalseValue() {
        return highThresholdFalseValue;
    }

    public void setHighThresholdFalseValue(String highThresholdFalseValue) {
        this.highThresholdFalseValue = highThresholdFalseValue;
    }

    public String getHighThresholdTrueValueRem() {
        return highThresholdTrueValueRem;
    }

    public void setHighThresholdTrueValueRem(String highThresholdTrueValueRem) {
        this.highThresholdTrueValueRem = highThresholdTrueValueRem;
    }

    public String getHighThresholdTrueValue() {
        return highThresholdTrueValue;
    }

    public void setHighThresholdTrueValue(String highThresholdTrueValue) {
        this.highThresholdTrueValue = highThresholdTrueValue;
    }

    @Override
    public String toString() {
        return "ThresholdJudgerConfig{" +
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
                ", lowThresholdSourceTypeRem='" + lowThresholdSourceTypeRem + '\'' +
                ", lowThresholdSourceType='" + lowThresholdSourceType + '\'' +
                ", lowThresholdConstantValueRem='" + lowThresholdConstantValueRem + '\'' +
                ", lowThresholdConstantValue='" + lowThresholdConstantValue + '\'' +
                ", lowThresholdAnalysisDataIdRem='" + lowThresholdAnalysisDataIdRem + '\'' +
                ", lowThresholdAnalysisDataId='" + lowThresholdAnalysisDataId + '\'' +
                ", lowThresholdFalseValueRem='" + lowThresholdFalseValueRem + '\'' +
                ", lowThresholdFalseValue='" + lowThresholdFalseValue + '\'' +
                ", lowThresholdTrueValueRem='" + lowThresholdTrueValueRem + '\'' +
                ", lowThresholdTrueValue='" + lowThresholdTrueValue + '\'' +
                ", highThresholdSourceTypeRem='" + highThresholdSourceTypeRem + '\'' +
                ", highThresholdSourceType='" + highThresholdSourceType + '\'' +
                ", highThresholdConstantValueRem='" + highThresholdConstantValueRem + '\'' +
                ", highThresholdConstantValue='" + highThresholdConstantValue + '\'' +
                ", highThresholdAnalysisDataIdRem='" + highThresholdAnalysisDataIdRem + '\'' +
                ", highThresholdAnalysisDataId='" + highThresholdAnalysisDataId + '\'' +
                ", highThresholdFalseValueRem='" + highThresholdFalseValueRem + '\'' +
                ", highThresholdFalseValue='" + highThresholdFalseValue + '\'' +
                ", highThresholdTrueValueRem='" + highThresholdTrueValueRem + '\'' +
                ", highThresholdTrueValue='" + highThresholdTrueValue + '\'' +
                '}';
    }
}
