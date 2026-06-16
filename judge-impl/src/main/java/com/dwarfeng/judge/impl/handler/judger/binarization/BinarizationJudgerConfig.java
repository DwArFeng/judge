package com.dwarfeng.judge.impl.handler.judger.binarization;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.Bean;

/**
 * 二值化判断器配置。
 *
 * <p>
 * 该配置用于描述二值化判断器的输入值、基准值、比较方式以及输出目标。
 * 判断器将输入值与基准值比较后，输出固定二值 <code>1.0</code> 或 <code>0.0</code>。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
public class BinarizationJudgerConfig implements Bean {

    private static final long serialVersionUID = -2716575824060129727L;

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

    @JSONField(name = "#baseline_source_type", ordinal = 13)
    private String baselineSourceTypeRem = "基准值来源类型，合法值为 constant、analysis。";

    @JSONField(name = "baseline_source_type", ordinal = 14)
    private String baselineSourceType;

    @JSONField(name = "#baseline_constant_value", ordinal = 15)
    private String baselineConstantValueRem = "当 baseline_source_type=constant 时必填，按 double 解析。";

    @JSONField(name = "baseline_constant_value", ordinal = 16)
    private String baselineConstantValue;

    @JSONField(name = "#baseline_analysis_data_id", ordinal = 17)
    private String baselineAnalysisDataIdRem = "当 baseline_source_type=analysis 时必填，用于读取基准值分析结果。";

    @JSONField(name = "baseline_analysis_data_id", ordinal = 18)
    private String baselineAnalysisDataId;

    @JSONField(name = "#baseline_false_value", ordinal = 19)
    private String baselineFalseValueRem = "当基准分析结果类型为 BOOLEAN 时必填，表示 false 转换出的 double 值。";

    @JSONField(name = "baseline_false_value", ordinal = 20)
    private String baselineFalseValue;

    @JSONField(name = "#baseline_true_value", ordinal = 21)
    private String baselineTrueValueRem = "当基准分析结果类型为 BOOLEAN 时必填，表示 true 转换出的 double 值。";

    @JSONField(name = "baseline_true_value", ordinal = 22)
    private String baselineTrueValue;

    @JSONField(name = "#comparator", ordinal = 23)
    private String comparatorRem = "比较方式，合法值为 ge、le、gt、lt。";

    @JSONField(name = "comparator", ordinal = 24)
    private String comparator;

    public BinarizationJudgerConfig() {
    }

    public BinarizationJudgerConfig(
            String outputJudgementDataId, String inputSourceType, String inputConstantValue,
            String inputAnalysisDataId, String inputFalseValue, String inputTrueValue,
            String baselineSourceType, String baselineConstantValue, String baselineAnalysisDataId,
            String baselineFalseValue, String baselineTrueValue, String comparator
    ) {
        this.outputJudgementDataId = outputJudgementDataId;
        this.inputSourceType = inputSourceType;
        this.inputConstantValue = inputConstantValue;
        this.inputAnalysisDataId = inputAnalysisDataId;
        this.inputFalseValue = inputFalseValue;
        this.inputTrueValue = inputTrueValue;
        this.baselineSourceType = baselineSourceType;
        this.baselineConstantValue = baselineConstantValue;
        this.baselineAnalysisDataId = baselineAnalysisDataId;
        this.baselineFalseValue = baselineFalseValue;
        this.baselineTrueValue = baselineTrueValue;
        this.comparator = comparator;
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

    public String getBaselineSourceTypeRem() {
        return baselineSourceTypeRem;
    }

    public void setBaselineSourceTypeRem(String baselineSourceTypeRem) {
        this.baselineSourceTypeRem = baselineSourceTypeRem;
    }

    public String getBaselineSourceType() {
        return baselineSourceType;
    }

    public void setBaselineSourceType(String baselineSourceType) {
        this.baselineSourceType = baselineSourceType;
    }

    public String getBaselineConstantValueRem() {
        return baselineConstantValueRem;
    }

    public void setBaselineConstantValueRem(String baselineConstantValueRem) {
        this.baselineConstantValueRem = baselineConstantValueRem;
    }

    public String getBaselineConstantValue() {
        return baselineConstantValue;
    }

    public void setBaselineConstantValue(String baselineConstantValue) {
        this.baselineConstantValue = baselineConstantValue;
    }

    public String getBaselineAnalysisDataIdRem() {
        return baselineAnalysisDataIdRem;
    }

    public void setBaselineAnalysisDataIdRem(String baselineAnalysisDataIdRem) {
        this.baselineAnalysisDataIdRem = baselineAnalysisDataIdRem;
    }

    public String getBaselineAnalysisDataId() {
        return baselineAnalysisDataId;
    }

    public void setBaselineAnalysisDataId(String baselineAnalysisDataId) {
        this.baselineAnalysisDataId = baselineAnalysisDataId;
    }

    public String getBaselineFalseValueRem() {
        return baselineFalseValueRem;
    }

    public void setBaselineFalseValueRem(String baselineFalseValueRem) {
        this.baselineFalseValueRem = baselineFalseValueRem;
    }

    public String getBaselineFalseValue() {
        return baselineFalseValue;
    }

    public void setBaselineFalseValue(String baselineFalseValue) {
        this.baselineFalseValue = baselineFalseValue;
    }

    public String getBaselineTrueValueRem() {
        return baselineTrueValueRem;
    }

    public void setBaselineTrueValueRem(String baselineTrueValueRem) {
        this.baselineTrueValueRem = baselineTrueValueRem;
    }

    public String getBaselineTrueValue() {
        return baselineTrueValue;
    }

    public void setBaselineTrueValue(String baselineTrueValue) {
        this.baselineTrueValue = baselineTrueValue;
    }

    public String getComparatorRem() {
        return comparatorRem;
    }

    public void setComparatorRem(String comparatorRem) {
        this.comparatorRem = comparatorRem;
    }

    public String getComparator() {
        return comparator;
    }

    public void setComparator(String comparator) {
        this.comparator = comparator;
    }

    @Override
    public String toString() {
        return "BinarizationJudgerConfig{" +
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
                ", baselineSourceTypeRem='" + baselineSourceTypeRem + '\'' +
                ", baselineSourceType='" + baselineSourceType + '\'' +
                ", baselineConstantValueRem='" + baselineConstantValueRem + '\'' +
                ", baselineConstantValue='" + baselineConstantValue + '\'' +
                ", baselineAnalysisDataIdRem='" + baselineAnalysisDataIdRem + '\'' +
                ", baselineAnalysisDataId='" + baselineAnalysisDataId + '\'' +
                ", baselineFalseValueRem='" + baselineFalseValueRem + '\'' +
                ", baselineFalseValue='" + baselineFalseValue + '\'' +
                ", baselineTrueValueRem='" + baselineTrueValueRem + '\'' +
                ", baselineTrueValue='" + baselineTrueValue + '\'' +
                ", comparatorRem='" + comparatorRem + '\'' +
                ", comparator='" + comparator + '\'' +
                '}';
    }
}
