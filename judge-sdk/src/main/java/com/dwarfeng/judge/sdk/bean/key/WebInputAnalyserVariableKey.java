package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.key.AnalyserVariableKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 分析器变量键。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class WebInputAnalyserVariableKey implements Key {

    private static final long serialVersionUID = 2471005027792497269L;

    public static AnalyserVariableKey toStackBean(WebInputAnalyserVariableKey webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new AnalyserVariableKey(
                    webInput.getAnalyserInfoLongId(),
                    webInput.getVariableStringId()
            );
        }
    }

    @JSONField(name = "analyser_info_long_id")
    @NotNull
    private Long analyserInfoLongId;

    @JSONField(name = "variable_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String variableStringId;

    public WebInputAnalyserVariableKey() {
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

        WebInputAnalyserVariableKey that = (WebInputAnalyserVariableKey) o;
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
        return "WebInputAnalyserVariableKey{" +
                "analyserInfoLongId=" + analyserInfoLongId +
                ", variableStringId='" + variableStringId + '\'' +
                '}';
    }
}
