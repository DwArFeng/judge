package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.dto.SinkerVariableRemoveInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 下沉器变量删除信息。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class WebInputSinkerVariableRemoveInfo implements Bean {

    private static final long serialVersionUID = 7957183303927454189L;

    public static SinkerVariableRemoveInfo toStackBean(WebInputSinkerVariableRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SinkerVariableRemoveInfo(
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

    public WebInputSinkerVariableRemoveInfo() {
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
        return "WebInputSinkerVariableRemoveInfo{" +
                "sinkerInfoKey=" + sinkerInfoKey +
                ", sinkerVariableId='" + sinkerVariableId + '\'' +
                '}';
    }
}
