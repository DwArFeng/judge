package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.JSFixedFastJsonJudgerVariableKey;
import com.dwarfeng.judge.stack.bean.entity.JudgerVariable;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 判断器变量。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class JSFixedFastJsonJudgerVariable implements Bean {

    private static final long serialVersionUID = 734967645177744733L;

    public static JSFixedFastJsonJudgerVariable of(JudgerVariable judgerVariable) {
        if (Objects.isNull(judgerVariable)) {
            return null;
        } else {
            return new JSFixedFastJsonJudgerVariable(
                    JSFixedFastJsonJudgerVariableKey.of(judgerVariable.getKey()),
                    judgerVariable.getValue()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonJudgerVariableKey key;

    @JSONField(name = "value", ordinal = 2)
    private String value;

    public JSFixedFastJsonJudgerVariable() {
    }

    public JSFixedFastJsonJudgerVariable(JSFixedFastJsonJudgerVariableKey key, String value) {
        this.key = key;
        this.value = value;
    }

    public JSFixedFastJsonJudgerVariableKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonJudgerVariableKey key) {
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
        return "JSFixedFastJsonJudgerVariable{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
