package com.dwarfeng.judge.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

public class HibernateSinkerVariableKey implements Key {

    private static final long serialVersionUID = -1147383140358957194L;

    private Long sinkerLongId;
    private String variableStringId;

    public HibernateSinkerVariableKey() {
    }

    public HibernateSinkerVariableKey(Long sinkerLongId, String variableStringId) {
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

        HibernateSinkerVariableKey that = (HibernateSinkerVariableKey) o;
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
        return "HibernateSinkerVariableKey{" +
                "sinkerLongId=" + sinkerLongId +
                ", variableStringId='" + variableStringId + '\'' +
                '}';
    }
}
