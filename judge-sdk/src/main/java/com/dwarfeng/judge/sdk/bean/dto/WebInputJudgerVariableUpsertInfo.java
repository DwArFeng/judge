package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableUpsertInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 判断器变量插入/更新信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class WebInputJudgerVariableUpsertInfo implements Bean {

    private static final long serialVersionUID = -306503080592319959L;

    public static JudgerVariableUpsertInfo toStackBean(WebInputJudgerVariableUpsertInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new JudgerVariableUpsertInfo(
                    WebInputLongIdKey.toStackBean(webInput.getJudgerInfoKey()),
                    webInput.getJudgerVariableId(),
                    webInput.getValue()
            );
        }
    }

    @JSONField(name = "judger_info_key")
    @NotNull
    @Valid
    private WebInputLongIdKey judgerInfoKey;

    @JSONField(name = "judger_variable_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String judgerVariableId;

    @JSONField(name = "value")
    private String value;

    public WebInputJudgerVariableUpsertInfo() {
    }

    public WebInputLongIdKey getJudgerInfoKey() {
        return judgerInfoKey;
    }

    public void setJudgerInfoKey(WebInputLongIdKey judgerInfoKey) {
        this.judgerInfoKey = judgerInfoKey;
    }

    public String getJudgerVariableId() {
        return judgerVariableId;
    }

    public void setJudgerVariableId(String judgerVariableId) {
        this.judgerVariableId = judgerVariableId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "WebInputJudgerVariableUpsertInfo{" +
                "judgerInfoKey=" + judgerInfoKey +
                ", judgerVariableId='" + judgerVariableId + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
