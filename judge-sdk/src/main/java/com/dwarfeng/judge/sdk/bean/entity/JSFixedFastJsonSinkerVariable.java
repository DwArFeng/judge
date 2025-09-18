package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.JSFixedFastJsonSinkerVariableKey;
import com.dwarfeng.judge.stack.bean.entity.SinkerVariable;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 下沉器变量。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class JSFixedFastJsonSinkerVariable implements Bean {

    private static final long serialVersionUID = 3250913989046096981L;

    public static JSFixedFastJsonSinkerVariable of(SinkerVariable sinkerVariable) {
        if (Objects.isNull(sinkerVariable)) {
            return null;
        } else {
            return new JSFixedFastJsonSinkerVariable(
                    JSFixedFastJsonSinkerVariableKey.of(sinkerVariable.getKey()),
                    sinkerVariable.getValue()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonSinkerVariableKey key;

    @JSONField(name = "value", ordinal = 2)
    private String value;

    public JSFixedFastJsonSinkerVariable() {
    }

    public JSFixedFastJsonSinkerVariable(JSFixedFastJsonSinkerVariableKey key, String value) {
        this.key = key;
        this.value = value;
    }

    public JSFixedFastJsonSinkerVariableKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonSinkerVariableKey key) {
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
        return "JSFixedFastJsonSinkerVariable{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
