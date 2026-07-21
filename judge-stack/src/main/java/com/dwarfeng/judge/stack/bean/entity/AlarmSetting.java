package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 报警设置。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class AlarmSetting implements Entity<LongIdKey> {

    private static final long serialVersionUID = 9222616832882891719L;

    private LongIdKey key;
    private LongIdKey sectionKey;
    private String dataId;
    private double threshold;
    private String message;
    private String type;
    private String remark;

    public AlarmSetting() {
    }

    public AlarmSetting(
            LongIdKey key, LongIdKey sectionKey, String dataId, double threshold, String message, String type,
            String remark
    ) {
        this.key = key;
        this.sectionKey = sectionKey;
        this.dataId = dataId;
        this.threshold = threshold;
        this.message = message;
        this.type = type;
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

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AlarmSetting{" +
                "key=" + key +
                ", sectionKey=" + sectionKey +
                ", dataId='" + dataId + '\'' +
                ", threshold=" + threshold +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
