package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.key.JudgerVariableKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 判断器变量键。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class WebInputJudgerVariableKey implements Key {

    private static final long serialVersionUID = 6730841703826297203L;

    public static JudgerVariableKey toStackBean(WebInputJudgerVariableKey webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new JudgerVariableKey(
                    webInput.getJudgerInfoLongId(),
                    webInput.getVariableStringId()
            );
        }
    }

    @JSONField(name = "judger_info_long_id")
    @NotNull
    private Long judgerInfoLongId;

    @JSONField(name = "variable_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String variableStringId;

    public WebInputJudgerVariableKey() {
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

        WebInputJudgerVariableKey that = (WebInputJudgerVariableKey) o;
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
        return "WebInputJudgerVariableKey{" +
                "judgerInfoLongId=" + judgerInfoLongId +
                ", variableStringId='" + variableStringId + '\'' +
                '}';
    }
}
