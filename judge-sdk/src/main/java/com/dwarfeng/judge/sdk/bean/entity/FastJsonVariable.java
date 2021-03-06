package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.FastJsonVariableKey;
import com.dwarfeng.judge.stack.bean.entity.Variable;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 变量。
 *
 * @author DwArFeng
 * @since beta-1.5.0
 */
public class FastJsonVariable implements Bean {

    private static final long serialVersionUID = 4633472119254067741L;

    public static FastJsonVariable of(Variable variable) {
        if (Objects.isNull(variable)) {
            return null;
        } else {
            return new FastJsonVariable(
                    FastJsonVariableKey.of(variable.getKey()), variable.getValue(), variable.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonVariableKey key;

    @JSONField(name = "value", ordinal = 2)
    private String value;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public FastJsonVariable() {
    }

    public FastJsonVariable(FastJsonVariableKey key, String value, String remark) {
        this.key = key;
        this.value = value;
        this.remark = remark;
    }

    public FastJsonVariableKey getKey() {
        return key;
    }

    public void setKey(FastJsonVariableKey key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonVariable{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
