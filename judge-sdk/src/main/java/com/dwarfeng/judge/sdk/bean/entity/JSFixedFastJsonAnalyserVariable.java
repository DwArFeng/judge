package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.JSFixedFastJsonAnalyserVariableKey;
import com.dwarfeng.judge.stack.bean.entity.AnalyserVariable;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 分析器变量。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class JSFixedFastJsonAnalyserVariable implements Bean {

    private static final long serialVersionUID = -690268431571187911L;

    public static JSFixedFastJsonAnalyserVariable of(AnalyserVariable analyserVariable) {
        if (Objects.isNull(analyserVariable)) {
            return null;
        } else {
            return new JSFixedFastJsonAnalyserVariable(
                    JSFixedFastJsonAnalyserVariableKey.of(analyserVariable.getKey()),
                    analyserVariable.getValue()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonAnalyserVariableKey key;

    @JSONField(name = "value", ordinal = 2)
    private String value;

    public JSFixedFastJsonAnalyserVariable() {
    }

    public JSFixedFastJsonAnalyserVariable(JSFixedFastJsonAnalyserVariableKey key, String value) {
        this.key = key;
        this.value = value;
    }

    public JSFixedFastJsonAnalyserVariableKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonAnalyserVariableKey key) {
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
        return "JSFixedFastJsonAnalyserVariable{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
