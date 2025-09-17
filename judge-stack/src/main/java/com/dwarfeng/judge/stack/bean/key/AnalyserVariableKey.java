package com.dwarfeng.judge.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 分析器变量键。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalyserVariableKey implements Key {

    private static final long serialVersionUID = 5838694668873808605L;

    private Long analyserInfoLongId;
    private String variableStringId;

    public AnalyserVariableKey() {
    }

    public AnalyserVariableKey(Long analyserInfoLongId, String variableStringId) {
        this.analyserInfoLongId = analyserInfoLongId;
        this.variableStringId = variableStringId;
    }

    public Long getAnalyserInfoLongId() {
        return analyserInfoLongId;
    }

    public void setAnalyserInfoLongId(Long analyserInfoLongId) {
        this.analyserInfoLongId = analyserInfoLongId;
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

        AnalyserVariableKey that = (AnalyserVariableKey) o;
        return Objects.equals(analyserInfoLongId, that.analyserInfoLongId) &&
                Objects.equals(variableStringId, that.variableStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(analyserInfoLongId);
        result = 31 * result + Objects.hashCode(variableStringId);
        return result;
    }

    @Override
    public String toString() {
        return "AnalyserVariableKey{" +
                "analyserInfoLongId=" + analyserInfoLongId +
                ", variableStringId='" + variableStringId + '\'' +
                '}';
    }
}
