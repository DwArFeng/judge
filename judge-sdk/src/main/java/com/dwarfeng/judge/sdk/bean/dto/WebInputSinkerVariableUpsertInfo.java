package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.dto.SinkerVariableUpsertInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 下沉器变量插入/更新信息。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class WebInputSinkerVariableUpsertInfo implements Bean {

    private static final long serialVersionUID = -262216417944227319L;

    public static SinkerVariableUpsertInfo toStackBean(WebInputSinkerVariableUpsertInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SinkerVariableUpsertInfo(
                    WebInputLongIdKey.toStackBean(webInput.getSinkerInfoKey()),
                    webInput.getSinkerVariableId(),
                    webInput.getValue()
            );
        }
    }

    @JSONField(name = "sinker_info_key")
    @NotNull
    @Valid
    private WebInputLongIdKey sinkerInfoKey;

    @JSONField(name = "sinker_variable_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String sinkerVariableId;

    @JSONField(name = "value")
    private String value;

    public WebInputSinkerVariableUpsertInfo() {
    }

    public WebInputLongIdKey getSinkerInfoKey() {
        return sinkerInfoKey;
    }

    public void setSinkerInfoKey(WebInputLongIdKey sinkerInfoKey) {
        this.sinkerInfoKey = sinkerInfoKey;
    }

    public String getSinkerVariableId() {
        return sinkerVariableId;
    }

    public void setSinkerVariableId(String sinkerVariableId) {
        this.sinkerVariableId = sinkerVariableId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "WebInputSinkerVariableUpsertInfo{" +
                "sinkerInfoKey=" + sinkerInfoKey +
                ", sinkerVariableId='" + sinkerVariableId + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
