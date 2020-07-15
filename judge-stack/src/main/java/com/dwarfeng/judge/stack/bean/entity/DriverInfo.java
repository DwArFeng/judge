package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 驱动器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class DriverInfo implements Entity<LongIdKey> {

    private static final long serialVersionUID = -5899558035182963610L;

    private LongIdKey key;
    private LongIdKey sectionKey;
    private String type;
    private String content;
    private String remark;

    public DriverInfo() {
    }

    public DriverInfo(LongIdKey key, LongIdKey sectionKey, String type, String content, String remark) {
        this.key = key;
        this.sectionKey = sectionKey;
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
        return "DriverInfo{" +
                "key=" + key +
                ", sectionKey=" + sectionKey +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
