package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.FastJsonSinkerVariableKey;
import com.dwarfeng.judge.stack.bean.entity.SinkerVariable;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 下沉器变量。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class FastJsonSinkerVariable implements Bean {

    private static final long serialVersionUID = -9019264270464722065L;

    public static FastJsonSinkerVariable of(SinkerVariable sinkerVariable) {
        if (Objects.isNull(sinkerVariable)) {
            return null;
        } else {
            return new FastJsonSinkerVariable(
                    FastJsonSinkerVariableKey.of(sinkerVariable.getKey()),
                    sinkerVariable.getValue()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonSinkerVariableKey key;

    @JSONField(name = "value", ordinal = 2)
    private String value;

    public FastJsonSinkerVariable() {
    }

    public FastJsonSinkerVariable(FastJsonSinkerVariableKey key, String value) {
        this.key = key;
        this.value = value;
    }

    public FastJsonSinkerVariableKey getKey() {
        return key;
    }

    public void setKey(FastJsonSinkerVariableKey key) {
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
        return "FastJsonSinkerVariable{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
