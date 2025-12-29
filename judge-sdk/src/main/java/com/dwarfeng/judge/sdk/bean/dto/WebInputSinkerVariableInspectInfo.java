package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.dto.SinkerVariableInspectInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 下沉器变量查看信息。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class WebInputSinkerVariableInspectInfo implements Bean {

    private static final long serialVersionUID = -5353205599021138289L;

    public static SinkerVariableInspectInfo toStackBean(WebInputSinkerVariableInspectInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SinkerVariableInspectInfo(
                    WebInputLongIdKey.toStackBean(webInput.getSinkerInfoKey()),
                    webInput.getSinkerVariableId()
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

    public WebInputSinkerVariableInspectInfo() {
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

    @Override
    public String toString() {
        return "WebInputSinkerVariableInspectInfo{" +
                "sinkerInfoKey=" + sinkerInfoKey +
                ", sinkerVariableId='" + sinkerVariableId + '\'' +
                '}';
    }
}
