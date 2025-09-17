package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.SinkerMetaResetInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 下沉器元数据重置信息。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class WebInputSinkerMetaResetInfo implements Dto {

    private static final long serialVersionUID = 8555479393537273114L;

    public static SinkerMetaResetInfo toStackBean(WebInputSinkerMetaResetInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SinkerMetaResetInfo(
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

    public WebInputSinkerMetaResetInfo() {
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
        return "WebInputSinkerMetaResetInfo{" +
                "sectionKey=" + sectionKey +
                ", sinkerInfoKey=" + sinkerInfoKey +
                '}';
    }
}
