package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.key.SinkerVariableKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 下沉器变量主键。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class FastJsonSinkerVariableKey implements Key {

    private static final long serialVersionUID = 5130091629505247441L;

    public static FastJsonSinkerVariableKey of(SinkerVariableKey sinkerVariableKey) {
        if (Objects.isNull(sinkerVariableKey)) {
            return null;
        } else {
            return new FastJsonSinkerVariableKey(
                    sinkerVariableKey.getSinkerLongId(),
                    sinkerVariableKey.getVariableStringId()
            );
        }
    }

    @JSONField(name = "sinker_long_id", ordinal = 1)
    private Long sinkerLongId;

    @JSONField(name = "variable_string_id", ordinal = 2)
    private String variableStringId;

    public FastJsonSinkerVariableKey() {
    }

    public FastJsonSinkerVariableKey(Long sinkerLongId, String variableStringId) {
        this.sinkerLongId = sinkerLongId;
        this.variableStringId = variableStringId;
    }

    public Long getSinkerLongId() {
        return sinkerLongId;
    }

    public void setSinkerLongId(Long sinkerLongId) {
        this.sinkerLongId = sinkerLongId;
    }

    public String getVariableStringId() {
        return variableStringId;
    }

    public void setVariableStringId(String variableStringId) {
        this.variableStringId = variableStringId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        FastJsonSinkerVariableKey that = (FastJsonSinkerVariableKey) o;
        return Objects.equals(sinkerLongId, that.sinkerLongId) &&
                Objects.equals(variableStringId, that.variableStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(sinkerLongId);
        result = 31 * result + Objects.hashCode(variableStringId);
        return result;
    }

    @Override
    public String toString() {
        return "FastJsonSinkerVariableKey{" +
                "sinkerLongId=" + sinkerLongId +
                ", variableStringId='" + variableStringId + '\'' +
                '}';
    }
}
