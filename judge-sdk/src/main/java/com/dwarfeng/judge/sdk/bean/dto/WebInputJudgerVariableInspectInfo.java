package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableInspectInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 判断器变量查看信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class WebInputJudgerVariableInspectInfo implements Bean {

    private static final long serialVersionUID = -5765113723752926499L;

    public static JudgerVariableInspectInfo toStackBean(WebInputJudgerVariableInspectInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new JudgerVariableInspectInfo(
                    WebInputLongIdKey.toStackBean(webInput.getJudgerInfoKey()),
                    webInput.getJudgerVariableId()
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

    public WebInputJudgerVariableInspectInfo() {
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

    @Override
    public String toString() {
        return "WebInputJudgerVariableInspectInfo{" +
                "judgerInfoKey=" + judgerInfoKey +
                ", judgerVariableId='" + judgerVariableId + '\'' +
                '}';
    }
}
