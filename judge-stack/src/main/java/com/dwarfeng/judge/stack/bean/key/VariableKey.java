package com.dwarfeng.judge.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 变量主键。
 *
 * @author DwArFeng
 * @since 1.5.0
 */
public class VariableKey implements Key {

    private static final long serialVersionUID = 7548818624614983902L;

    private Long longId;
    private String stringId;

    public VariableKey() {
    }

    public VariableKey(Long longId, String stringId) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VariableKey)) return false;

        VariableKey that = (VariableKey) o;

        if (!Objects.equals(longId, that.longId)) return false;
        return Objects.equals(stringId, that.stringId);
    }

    @Override
    public int hashCode() {
        int result = longId != null ? longId.hashCode() : 0;
        result = 31 * result + (stringId != null ? stringId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "VariableKey{" +
                "longId=" + longId +
                ", stringId='" + stringId + '\'' +
                '}';
    }
}
