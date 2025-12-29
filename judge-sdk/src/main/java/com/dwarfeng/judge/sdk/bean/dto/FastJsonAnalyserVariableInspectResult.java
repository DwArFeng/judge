package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.AnalyserVariableInspectResult;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Objects;

/**
 * FastJson 分析器变量查看结果。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class FastJsonAnalyserVariableInspectResult implements Dto {

    private static final long serialVersionUID = -7350469822566114361L;

    public static FastJsonAnalyserVariableInspectResult of(
            AnalyserVariableInspectResult analyserVariableInspectResult
    ) {
        if (Objects.isNull(analyserVariableInspectResult)) {
            return null;
        } else {
            return new FastJsonAnalyserVariableInspectResult(
                    analyserVariableInspectResult.getValue()
            );
        }
    }

    @JSONField(name = "value", ordinal = 1)
    private String value;

    public FastJsonAnalyserVariableInspectResult() {
    }

    public FastJsonAnalyserVariableInspectResult(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FastJsonAnalyserVariableInspectResult{" +
                "value='" + value + '\'' +
                '}';
    }
}
