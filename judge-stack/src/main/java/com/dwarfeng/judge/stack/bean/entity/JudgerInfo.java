package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 评价信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JudgerInfo implements Entity<LongIdKey> {

    private static final long serialVersionUID = -5908021822283324721L;

    private LongIdKey key;
    private LongIdKey sectionKey;
    private boolean enabled;
    private String type;
    private String content;
    private String remark;

    public JudgerInfo() {
    }

    public JudgerInfo(
            LongIdKey key, LongIdKey sectionKey, boolean enabled, String type, String content, String remark) {
        this.key = key;
        this.sectionKey = sectionKey;
        this.enabled = enabled;
        this.type = type;
        this.content = content;
        this.remark = remark;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public LongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(LongIdKey sectionKey) {
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
        return "JudgerInfo{" +
                "key=" + key +
                ", sectionKey=" + sectionKey +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
