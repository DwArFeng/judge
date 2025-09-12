package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.FastJsonAnalyserVariableKey;
import com.dwarfeng.judge.stack.bean.entity.AnalyserVariable;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 分析器变量。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class FastJsonAnalyserVariable implements Bean {

    private static final long serialVersionUID = 8616026472329997759L;

    public static FastJsonAnalyserVariable of(AnalyserVariable analyserVariable) {
        if (Objects.isNull(analyserVariable)) {
            return null;
        } else {
            return new FastJsonAnalyserVariable(
                    FastJsonAnalyserVariableKey.of(analyserVariable.getKey()),
                    analyserVariable.getValue()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonAnalyserVariableKey key;

    @JSONField(name = "value", ordinal = 2)
    private String value;

    public FastJsonAnalyserVariable() {
    }

    public FastJsonAnalyserVariable(FastJsonAnalyserVariableKey key, String value) {
        this.key = key;
        this.value = value;
    }

    public FastJsonAnalyserVariableKey getKey() {
        return key;
    }

    public void setKey(FastJsonAnalyserVariableKey key) {
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
        return "FastJsonAnalyserVariable{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
