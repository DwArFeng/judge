package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.dto.AnalyserVariableRemoveInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 分析器变量删除信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class WebInputAnalyserVariableRemoveInfo implements Bean {

    private static final long serialVersionUID = -6563095381847656856L;

    public static AnalyserVariableRemoveInfo toStackBean(WebInputAnalyserVariableRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new AnalyserVariableRemoveInfo(
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

    public WebInputAnalyserVariableRemoveInfo() {
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
        return "WebInputAnalyserVariableRemoveInfo{" +
                "analyserInfoKey=" + analyserInfoKey +
                ", analyserVariableId='" + analyserVariableId + '\'' +
                '}';
    }
}
