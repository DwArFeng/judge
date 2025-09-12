package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.FastJsonJudgerVariableKey;
import com.dwarfeng.judge.stack.bean.entity.JudgerVariable;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 判断器变量。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class FastJsonJudgerVariable implements Bean {

    private static final long serialVersionUID = -3729552424363733254L;

    public static FastJsonJudgerVariable of(JudgerVariable judgerVariable) {
        if (Objects.isNull(judgerVariable)) {
            return null;
        } else {
            return new FastJsonJudgerVariable(
                    FastJsonJudgerVariableKey.of(judgerVariable.getKey()),
                    judgerVariable.getValue()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonJudgerVariableKey key;

    @JSONField(name = "value", ordinal = 2)
    private String value;

    public FastJsonJudgerVariable() {
    }

    public FastJsonJudgerVariable(FastJsonJudgerVariableKey key, String value) {
        this.key = key;
        this.value = value;
    }

    public FastJsonJudgerVariableKey getKey() {
        return key;
    }

    public void setKey(FastJsonJudgerVariableKey key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FastJsonJudgerVariable{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
