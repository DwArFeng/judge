package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.SinkerVariableInspectResult;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Objects;

/**
 * FastJson 下沉器变量查看结果。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class FastJsonSinkerVariableInspectResult implements Dto {

    private static final long serialVersionUID = -7870227287018871272L;

    public static FastJsonSinkerVariableInspectResult of(SinkerVariableInspectResult sinkerVariableInspectResult) {
        if (Objects.isNull(sinkerVariableInspectResult)) {
            return null;
        } else {
            return new FastJsonSinkerVariableInspectResult(
                    sinkerVariableInspectResult.getValue()
            );
        }
    }

    @JSONField(name = "value", ordinal = 1)
    private String value;

    public FastJsonSinkerVariableInspectResult() {
    }

    public FastJsonSinkerVariableInspectResult(String value) {
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
        return "FastJsonSinkerVariableInspectResult{" +
                "value='" + value + '\'' +
                '}';
    }
}
