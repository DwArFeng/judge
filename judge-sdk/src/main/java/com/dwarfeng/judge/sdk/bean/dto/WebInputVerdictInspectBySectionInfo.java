package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.VerdictInspectBySectionInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 部件裁决结果按部件查看信息。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class WebInputVerdictInspectBySectionInfo implements Dto {

    private static final long serialVersionUID = 8409488921038278129L;

    public static VerdictInspectBySectionInfo toStackBean(WebInputVerdictInspectBySectionInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VerdictInspectBySectionInfo(
                    WebInputLongIdKey.toStackBean(webInput.getSectionKey())
            );
        }
    }

    @JSONField(name = "section_key")
    @NotNull
    @Valid
    private WebInputLongIdKey sectionKey;

    public WebInputVerdictInspectBySectionInfo() {
    }

    public WebInputLongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(WebInputLongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    @Override
    public String toString() {
        return "WebInputVerdictInspectBySectionInfo{" +
                "sectionKey=" + sectionKey +
                '}';
    }
}
