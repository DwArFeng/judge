package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableInspectResult;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Objects;

/**
 * FastJson 判断器变量查看结果。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class FastJsonJudgerVariableInspectResult implements Dto {

    private static final long serialVersionUID = -7183143046051318279L;

    public static FastJsonJudgerVariableInspectResult of(JudgerVariableInspectResult judgerVariableInspectResult) {
        if (Objects.isNull(judgerVariableInspectResult)) {
            return null;
        } else {
            return new FastJsonJudgerVariableInspectResult(
                    judgerVariableInspectResult.getValue()
            );
        }
    }

    @JSONField(name = "value", ordinal = 1)
    private String value;

    public FastJsonJudgerVariableInspectResult() {
    }

    public FastJsonJudgerVariableInspectResult(String value) {
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
        return "FastJsonJudgerVariableInspectResult{" +
                "value='" + value + '\'' +
                '}';
    }
}
