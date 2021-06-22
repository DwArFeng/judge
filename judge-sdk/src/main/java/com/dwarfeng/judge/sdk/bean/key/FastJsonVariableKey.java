package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.key.VariableKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 变量主键。
 *
 * @author DwArFeng
 * @since 1.5.0
 */
public class FastJsonVariableKey implements Key {

    private static final long serialVersionUID = -5713934330542320871L;

    public static FastJsonVariableKey of(VariableKey key) {
        if (Objects.isNull(key)) {
            return null;
        } else {
            return new FastJsonVariableKey(key.getLongId(), key.getStringId());
        }
    }

    @JSONField(name = "long_id", ordinal = 1)
    private Long longId;

    @JSONField(name = "string_id", ordinal = 2)
    private String stringId;

    public FastJsonVariableKey() {
    }

    public FastJsonVariableKey(Long longId, String stringId) {
        this.longId = longId;
        this.stringId = stringId;
    }

    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
    }

    public String getStringId() {
        return stringId;
    }

    public void setStringId(String stringId) {
        this.stringId = stringId;
    }

    @Override
    public String toString() {
        return "FastJsonVariableKey{" +
                "longId=" + longId +
                ", stringId='" + stringId + '\'' +
                '}';
    }
}
