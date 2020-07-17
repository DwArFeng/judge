package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.Objects;

public class WebInputJudgerInfo implements Bean {

    private static final long serialVersionUID = -7706153657541723646L;

    public static JudgerInfo toStackBean(WebInputJudgerInfo webInputJudgerInfo) {
        if (Objects.isNull(webInputJudgerInfo)) {
            return null;
        }

        return new JudgerInfo(
                WebInputLongIdKey.toStackBean(webInputJudgerInfo.getKey()),
                WebInputLongIdKey.toStackBean(webInputJudgerInfo.getSectionKey()),
                webInputJudgerInfo.isEnabled(),
                webInputJudgerInfo.getType(),
                webInputJudgerInfo.getContent(),
                webInputJudgerInfo.getRemark()
        );
    }

    @JSONField(name = "key")
    @Valid
    @NotNull(groups = Default.class)
    private WebInputLongIdKey key;

    @JSONField(name = "section_key")
    @Valid
    private WebInputLongIdKey sectionKey;

    @JSONField(name = "enabled")
    private boolean enabled;

    @JSONField(name = "type")
    @NotNull
    @NotEmpty
    private String type;

    @JSONField(name = "content")
    private String content;

    @JSONField(name = "remark")
    private String remark;

    public WebInputJudgerInfo() {
    }

    public WebInputJudgerInfo(
            WebInputLongIdKey key, WebInputLongIdKey sectionKey, boolean enabled, String type, String content,
            String remark) {
        this.key = key;
        this.sectionKey = sectionKey;
        this.enabled = enabled;
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
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
        return "WebInputJudgerInfo{" +
                "key=" + key +
                ", sectionKey=" + sectionKey +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
