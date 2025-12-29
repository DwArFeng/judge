package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.dto.AnalyserVariableUpsertInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 分析器变量插入/更新信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class WebInputAnalyserVariableUpsertInfo implements Bean {

    private static final long serialVersionUID = -3494223091239872700L;

    public static AnalyserVariableUpsertInfo toStackBean(WebInputAnalyserVariableUpsertInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new AnalyserVariableUpsertInfo(
                    WebInputLongIdKey.toStackBean(webInput.getAnalyserInfoKey()),
                    webInput.getAnalyserVariableId(),
                    webInput.getValue()
            );
        }
    }

    @JSONField(name = "analyser_info_key")
    @NotNull
    @Valid
    private WebInputLongIdKey analyserInfoKey;

    @JSONField(name = "analyser_variable_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String analyserVariableId;

    @JSONField(name = "value")
    private String value;

    public WebInputAnalyserVariableUpsertInfo() {
    }

    public WebInputLongIdKey getAnalyserInfoKey() {
        return analyserInfoKey;
    }

    public void setAnalyserInfoKey(WebInputLongIdKey analyserInfoKey) {
        this.analyserInfoKey = analyserInfoKey;
    }

    public String getAnalyserVariableId() {
        return analyserVariableId;
    }

    public void setAnalyserVariableId(String analyserVariableId) {
        this.analyserVariableId = analyserVariableId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "WebInputAnalyserVariableUpsertInfo{" +
                "analyserInfoKey=" + analyserInfoKey +
                ", analyserVariableId='" + analyserVariableId + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
