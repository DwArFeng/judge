package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 报警设置。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AlarmSetting implements Entity<LongIdKey> {

    private static final long serialVersionUID = -3230523827641294643L;

    private LongIdKey key;
    private LongIdKey sectionKey;
    private boolean enabled;
    private double threshold;
    private String alarmMessage;
    private String remark;

    public AlarmSetting() {
    }

    public AlarmSetting(
            LongIdKey key, LongIdKey sectionKey, boolean enabled, double threshold, String alarmMessage, String remark
    ) {
        this.key = key;
        this.sectionKey = sectionKey;
        this.enabled = enabled;
        this.threshold = threshold;
        this.alarmMessage = alarmMessage;
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

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public String getAlarmMessage() {
        return alarmMessage;
    }

    public void setAlarmMessage(String alarmMessage) {
        this.alarmMessage = alarmMessage;
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
                ", enabled=" + enabled +
                ", threshold=" + threshold +
                ", alarmMessage='" + alarmMessage + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
