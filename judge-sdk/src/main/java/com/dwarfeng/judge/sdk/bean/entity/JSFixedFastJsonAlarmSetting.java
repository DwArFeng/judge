package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.AlarmSetting;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 报警设置。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class JSFixedFastJsonAlarmSetting implements Bean {

    private static final long serialVersionUID = -7087636371011615558L;

    public static JSFixedFastJsonAlarmSetting of(AlarmSetting alarmSetting) {
        if (Objects.isNull(alarmSetting)) {
            return null;
        } else {
            return new JSFixedFastJsonAlarmSetting(
                    JSFixedFastJsonLongIdKey.of(alarmSetting.getKey()),
                    JSFixedFastJsonLongIdKey.of(alarmSetting.getSectionKey()),
                    alarmSetting.isEnabled(),
                    alarmSetting.getThreshold(),
                    alarmSetting.getAlarmMessage(),
                    alarmSetting.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "section_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey sectionKey;

    @JSONField(name = "enabled", ordinal = 3)
    private boolean enabled;

    @JSONField(name = "threshold", ordinal = 4)
    private double threshold;

    @JSONField(name = "alarm_message", ordinal = 5)
    private String alarmMessage;

    @JSONField(name = "remark", ordinal = 6)
    private String remark;

    public JSFixedFastJsonAlarmSetting() {
    }

    public JSFixedFastJsonAlarmSetting(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey sectionKey, boolean enabled, double threshold,
            String alarmMessage, String remark
    ) {
        this.key = key;
        this.sectionKey = sectionKey;
        this.enabled = enabled;
        this.threshold = threshold;
        this.alarmMessage = alarmMessage;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(JSFixedFastJsonLongIdKey sectionKey) {
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
        return "JSFixedFastJsonAlarmSetting{" +
                "key=" + key +
                ", sectionKey=" + sectionKey +
                ", enabled=" + enabled +
                ", threshold=" + threshold +
                ", alarmMessage='" + alarmMessage + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
