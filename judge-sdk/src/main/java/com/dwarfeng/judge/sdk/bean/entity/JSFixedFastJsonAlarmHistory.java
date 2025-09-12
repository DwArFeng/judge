package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.AlarmHistory;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 报警历史。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class JSFixedFastJsonAlarmHistory implements Bean {

    private static final long serialVersionUID = 3893625978619698714L;

    public static JSFixedFastJsonAlarmHistory of(AlarmHistory alarmHistory) {
        if (Objects.isNull(alarmHistory)) {
            return null;
        } else {
            return new JSFixedFastJsonAlarmHistory(
                    JSFixedFastJsonLongIdKey.of(alarmHistory.getKey()),
                    JSFixedFastJsonLongIdKey.of(alarmHistory.getSectionKey()),
                    alarmHistory.getAlarmLevel(),
                    alarmHistory.getStartDate(),
                    alarmHistory.getEndDate(),
                    alarmHistory.getDuration(),
                    alarmHistory.getAlarmMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "section_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey sectionKey;

    @JSONField(name = "alarm_level", ordinal = 3)
    private String alarmLevel;

    @JSONField(name = "start_date", ordinal = 4)
    private Date startDate;

    @JSONField(name = "end_date", ordinal = 5)
    private Date endDate;

    @JSONField(name = "duration", ordinal = 6)
    private long duration;

    @JSONField(name = "alarm_message", ordinal = 7)
    private String alarmMessage;

    public JSFixedFastJsonAlarmHistory() {
    }

    public JSFixedFastJsonAlarmHistory(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey sectionKey, String alarmLevel, Date startDate,
            Date endDate, long duration, String alarmMessage
    ) {
        this.key = key;
        this.sectionKey = sectionKey;
        this.alarmLevel = alarmLevel;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.alarmMessage = alarmMessage;
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

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getAlarmMessage() {
        return alarmMessage;
    }

    public void setAlarmMessage(String alarmMessage) {
        this.alarmMessage = alarmMessage;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonAlarmHistory{" +
                "key=" + key +
                ", sectionKey=" + sectionKey +
                ", alarmLevel='" + alarmLevel + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", duration=" + duration +
                ", alarmMessage='" + alarmMessage + '\'' +
                '}';
    }
}
