package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.AlarmSetting;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 报警设置。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class FastJsonAlarmSetting implements Bean {

    private static final long serialVersionUID = 2663412086480179067L;

    public static FastJsonAlarmSetting of(AlarmSetting alarmSetting) {
        if (Objects.isNull(alarmSetting)) {
            return null;
        } else {
            return new FastJsonAlarmSetting(
                    FastJsonLongIdKey.of(alarmSetting.getKey()),
                    FastJsonLongIdKey.of(alarmSetting.getSectionKey()),
                    alarmSetting.isEnabled(),
                    alarmSetting.getThreshold(),
                    alarmSetting.getAlarmMessage(),
                    alarmSetting.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "section_key", ordinal = 2)
    private FastJsonLongIdKey sectionKey;

    @JSONField(name = "enabled", ordinal = 3)
    private boolean enabled;

    @JSONField(name = "threshold", ordinal = 4)
    private double threshold;

    @JSONField(name = "alarm_message", ordinal = 5)
    private String alarmMessage;

    @JSONField(name = "remark", ordinal = 6)
    private String remark;

    public FastJsonAlarmSetting() {
    }

    public FastJsonAlarmSetting(
            FastJsonLongIdKey key, FastJsonLongIdKey sectionKey, boolean enabled, double threshold,
            String alarmMessage, String remark
    ) {
        this.key = key;
        this.sectionKey = sectionKey;
        this.enabled = enabled;
        this.threshold = threshold;
        this.alarmMessage = alarmMessage;
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
        return "FastJsonAlarmSetting{" +
                "key=" + key +
                ", sectionKey=" + sectionKey +
                ", enabled=" + enabled +
                ", threshold=" + threshold +
                ", alarmMessage='" + alarmMessage + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
