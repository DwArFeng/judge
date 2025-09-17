package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.key.AnalyserVariableKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 分析器变量键。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class FastJsonAnalyserVariableKey implements Key {

    private static final long serialVersionUID = -1582557771856947450L;

    public static FastJsonAnalyserVariableKey of(AnalyserVariableKey analyserVariableKey) {
        if (Objects.isNull(analyserVariableKey)) {
            return null;
        } else {
            return new FastJsonAnalyserVariableKey(
                    analyserVariableKey.getAnalyserInfoLongId(),
                    analyserVariableKey.getVariableStringId()
            );
        }
    }

    @JSONField(name = "analyser_info_long_id", ordinal = 1)
    private Long analyserInfoLongId;

    @JSONField(name = "variable_string_id", ordinal = 2)
    private String variableStringId;

    public FastJsonAnalyserVariableKey() {
    }

    public FastJsonAnalyserVariableKey(Long analyserInfoLongId, String variableStringId) {
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

        FastJsonAnalyserVariableKey that = (FastJsonAnalyserVariableKey) o;
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
        return "FastJsonAnalyserVariableKey{" +
                "analyserInfoLongId=" + analyserInfoLongId +
                ", variableStringId='" + variableStringId + '\'' +
                '}';
    }
}
