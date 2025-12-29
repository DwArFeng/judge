package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.key.SinkerVariableKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 下沉器变量主键。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class WebInputSinkerVariableKey implements Key {

    private static final long serialVersionUID = 8622946473638367672L;

    public static SinkerVariableKey toStackBean(WebInputSinkerVariableKey webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SinkerVariableKey(
                    webInput.getSinkerLongId(),
                    webInput.getVariableStringId()
            );
        }
    }

    @JSONField(name = "sinker_long_id")
    @NotNull
    private Long sinkerLongId;

    @JSONField(name = "variable_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String variableStringId;

    public WebInputSinkerVariableKey() {
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

        WebInputSinkerVariableKey that = (WebInputSinkerVariableKey) o;
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
        return "WebInputSinkerVariableKey{" +
                "sinkerLongId=" + sinkerLongId +
                ", variableStringId='" + variableStringId + '\'' +
                '}';
    }
}
