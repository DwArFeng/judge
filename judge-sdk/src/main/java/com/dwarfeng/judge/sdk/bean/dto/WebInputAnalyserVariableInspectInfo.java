package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.dto.AnalyserVariableInspectInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 分析器变量查看信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class WebInputAnalyserVariableInspectInfo implements Bean {

    private static final long serialVersionUID = 1710729430233827313L;

    public static AnalyserVariableInspectInfo toStackBean(WebInputAnalyserVariableInspectInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new AnalyserVariableInspectInfo(
                    WebInputLongIdKey.toStackBean(webInput.getAnalyserInfoKey()),
                    webInput.getAnalyserVariableId()
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

    public WebInputAnalyserVariableInspectInfo() {
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

    @Override
    public String toString() {
        return "WebInputAnalyserVariableInspectInfo{" +
                "analyserInfoKey=" + analyserInfoKey +
                ", analyserVariableId='" + analyserVariableId + '\'' +
                '}';
    }
}
