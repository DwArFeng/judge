package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 判断器信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonJudgerInfo implements Bean {

    private static final long serialVersionUID = -3448223964615132543L;

    public static FastJsonJudgerInfo of(JudgerInfo judgerInfo) {
        if (Objects.isNull(judgerInfo)) {
            return null;
        }

        return new FastJsonJudgerInfo(
                FastJsonLongIdKey.of(judgerInfo.getKey()),
                FastJsonLongIdKey.of(judgerInfo.getSectionKey()),
                judgerInfo.isEnabled(),
                judgerInfo.getType(),
                judgerInfo.getContent(),
                judgerInfo.getRemark()
        );
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "section_key", ordinal = 2)
    private FastJsonLongIdKey sectionKey;

    @JSONField(name = "enabled", ordinal = 3)
    private boolean enabled;

    @JSONField(name = "type", ordinal = 4)
    private String type;

    @JSONField(name = "content", ordinal = 5)
    private String content;

    @JSONField(name = "remark", ordinal = 6)
    private String remark;

    public FastJsonJudgerInfo() {
    }

    public FastJsonJudgerInfo(
            FastJsonLongIdKey key, FastJsonLongIdKey sectionKey, boolean enabled, String type, String content,
            String remark) {
        this.key = key;
        this.sectionKey = sectionKey;
        this.enabled = enabled;
        this.type = type;
        this.content = content;
        this.remark = remark;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(FastJsonLongIdKey sectionKey) {
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
        return "FastJsonJudgerInfo{" +
                "key=" + key +
                ", sectionKey=" + sectionKey +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
