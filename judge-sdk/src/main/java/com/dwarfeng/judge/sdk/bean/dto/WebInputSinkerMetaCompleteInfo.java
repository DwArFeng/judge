package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.SinkerMetaCompleteInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 下沉器元数据补全信息。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class WebInputSinkerMetaCompleteInfo implements Dto {

    private static final long serialVersionUID = 1686476582853854059L;

    public static SinkerMetaCompleteInfo toStackBean(WebInputSinkerMetaCompleteInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SinkerMetaCompleteInfo(
                    WebInputLongIdKey.toStackBean(webInput.getSectionKey()),
                    WebInputLongIdKey.toStackBean(webInput.getSinkerInfoKey())
            );
        }
    }

    @JSONField(name = "section_key")
    @NotNull
    @Valid
    private WebInputLongIdKey sectionKey;

    @JSONField(name = "sinker_info_key")
    @NotNull
    @Valid
    private WebInputLongIdKey sinkerInfoKey;

    public WebInputSinkerMetaCompleteInfo() {
    }

    public WebInputLongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(WebInputLongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    public WebInputLongIdKey getSinkerInfoKey() {
        return sinkerInfoKey;
    }

    public void setSinkerInfoKey(WebInputLongIdKey sinkerInfoKey) {
        this.sinkerInfoKey = sinkerInfoKey;
    }

    @Override
    public String toString() {
        return "WebInputSinkerMetaCompleteInfo{" +
                "sectionKey=" + sectionKey +
                ", sinkerInfoKey=" + sinkerInfoKey +
                '}';
    }
}
