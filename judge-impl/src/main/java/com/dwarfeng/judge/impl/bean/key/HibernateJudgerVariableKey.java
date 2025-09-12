package com.dwarfeng.judge.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * Hibernate 判断器变量键。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class HibernateJudgerVariableKey implements Key {

    private static final long serialVersionUID = -2929931360326255447L;

    private Long judgerInfoLongId;
    private String variableStringId;

    public HibernateJudgerVariableKey() {
    }

    public HibernateJudgerVariableKey(Long judgerInfoLongId, String variableStringId) {
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

        HibernateJudgerVariableKey that = (HibernateJudgerVariableKey) o;
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
        return "HibernateJudgerVariableKey{" +
                "judgerInfoLongId=" + judgerInfoLongId +
                ", variableStringId='" + variableStringId + '\'' +
                '}';
    }
}
