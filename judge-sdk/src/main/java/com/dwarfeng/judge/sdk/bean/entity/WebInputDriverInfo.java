package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.Objects;

public class WebInputDriverInfo implements Bean {

    private static final long serialVersionUID = -3975993124807740934L;

    public static DriverInfo toStackBean(WebInputDriverInfo webInputDriverInfo) {
        if (Objects.isNull(webInputDriverInfo)) {
            return null;
        }

        return new DriverInfo(
                WebInputLongIdKey.toStackBean(webInputDriverInfo.getKey()),
                WebInputLongIdKey.toStackBean(webInputDriverInfo.getSectionKey()),
                webInputDriverInfo.getType(),
                webInputDriverInfo.getContent(),
                webInputDriverInfo.getRemark()
        );
    }

    @JSONField(name = "key")
    @Valid
    @NotNull(groups = Default.class)
    private WebInputLongIdKey key;

    @JSONField(name = "section_key")
    @Valid
    private WebInputLongIdKey sectionKey;

    @JSONField(name = "type")
    @NotNull
    @NotEmpty
    private String type;

    @JSONField(name = "content")
    private String content;

    @JSONField(name = "remark")
    private String remark;

    public WebInputDriverInfo() {
    }

    public WebInputDriverInfo(
            WebInputLongIdKey key, WebInputLongIdKey sectionKey, String type, String content, String remark) {
        this.key = key;
        this.sectionKey = sectionKey;
        this.type = type;
        this.content = content;
        this.remark = remark;
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
        this.key = key;
    }

    public WebInputLongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(WebInputLongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputDrive{" +
                "key=" + key +
                ", sectionKey=" + sectionKey +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
