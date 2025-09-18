package com.dwarfeng.judge.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 下沉器变量主键。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerVariableKey implements Key {

    private static final long serialVersionUID = -2335427891984115678L;

    private Long sinkerLongId;
    private String variableStringId;

    public SinkerVariableKey() {
    }

    public SinkerVariableKey(Long sinkerLongId, String variableStringId) {
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

        SinkerVariableKey that = (SinkerVariableKey) o;
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
        return "SinkerVariableKey{" +
                "sinkerLongId=" + sinkerLongId +
                ", variableStringId='" + variableStringId + '\'' +
                '}';
    }
}
