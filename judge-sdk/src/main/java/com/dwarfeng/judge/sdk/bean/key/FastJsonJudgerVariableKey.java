package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.key.JudgerVariableKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 判断器变量键。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class FastJsonJudgerVariableKey implements Key {

    private static final long serialVersionUID = -3432829820969298866L;

    public static FastJsonJudgerVariableKey of(JudgerVariableKey judgerVariableKey) {
        if (Objects.isNull(judgerVariableKey)) {
            return null;
        } else {
            return new FastJsonJudgerVariableKey(
                    judgerVariableKey.getJudgerInfoLongId(),
                    judgerVariableKey.getVariableStringId()
            );
        }
    }

    @JSONField(name = "judger_info_long_id", ordinal = 1)
    private Long judgerInfoLongId;

    @JSONField(name = "variable_string_id", ordinal = 2)
    private String variableStringId;

    public FastJsonJudgerVariableKey() {
    }

    public FastJsonJudgerVariableKey(Long judgerInfoLongId, String variableStringId) {
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

        FastJsonJudgerVariableKey that = (FastJsonJudgerVariableKey) o;
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
        return "FastJsonJudgerVariableKey{" +
                "judgerInfoLongId=" + judgerInfoLongId +
                ", variableStringId='" + variableStringId + '\'' +
                '}';
    }
}
