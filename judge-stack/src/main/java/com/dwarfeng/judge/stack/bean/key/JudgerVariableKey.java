package com.dwarfeng.judge.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 判断器变量键。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class JudgerVariableKey implements Key {

    private static final long serialVersionUID = -8444963091244031112L;

    private Long judgerInfoLongId;
    private String variableStringId;

    public JudgerVariableKey() {
    }

    public JudgerVariableKey(Long judgerInfoLongId, String variableStringId) {
        this.judgerInfoLongId = judgerInfoLongId;
        this.variableStringId = variableStringId;
    }

    public Long getJudgerInfoLongId() {
        return judgerInfoLongId;
    }

    public void setJudgerInfoLongId(Long judgerInfoLongId) {
        this.judgerInfoLongId = judgerInfoLongId;
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

        JudgerVariableKey that = (JudgerVariableKey) o;
        return Objects.equals(judgerInfoLongId, that.judgerInfoLongId) &&
                Objects.equals(variableStringId, that.variableStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(judgerInfoLongId);
        result = 31 * result + Objects.hashCode(variableStringId);
        return result;
    }

    @Override
    public String toString() {
        return "JudgerVariableKey{" +
                "judgerInfoLongId=" + judgerInfoLongId +
                ", variableStringId='" + variableStringId + '\'' +
                '}';
    }
}
