package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.AlarmHistory;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 报警历史。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class FastJsonAlarmHistory implements Bean {

    private static final long serialVersionUID = 651878453481063893L;

    public static FastJsonAlarmHistory of(AlarmHistory alarmHistory) {
        if (Objects.isNull(alarmHistory)) {
            return null;
        } else {
            return new FastJsonAlarmHistory(
                    FastJsonLongIdKey.of(alarmHistory.getKey()),
                    FastJsonLongIdKey.of(alarmHistory.getSectionKey()),
                    alarmHistory.getStartDate(), alarmHistory.getEndDate(),
                    alarmHistory.getDuration(), alarmHistory.getValue(),
                    alarmHistory.getAlarmMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "section_key", ordinal = 2)
    private FastJsonLongIdKey sectionKey;

    @JSONField(name = "start_date", ordinal = 3)
    private Date startDate;

    @JSONField(name = "end_date", ordinal = 4)
    private Date endDate;

    @JSONField(name = "duration", ordinal = 5)
    private long duration;

    @JSONField(name = "value", ordinal = 6)
    private double value;

    @JSONField(name = "alarm_message", ordinal = 7)
    private String alarmMessage;

    public FastJsonAlarmHistory() {
    }

    public FastJsonAlarmHistory(
            FastJsonLongIdKey key, FastJsonLongIdKey sectionKey, Date startDate, Date endDate, long duration,
            double value, String alarmMessage
    ) {
        this.key = key;
        this.sectionKey = sectionKey;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.value = value;
        this.alarmMessage = alarmMessage;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getAlarmMessage() {
        return alarmMessage;
    }

    public void setAlarmMessage(String alarmMessage) {
        this.alarmMessage = alarmMessage;
    }

    @Override
    public String toString() {
        return "FastJsonAlarmHistory{" +
                "key=" + key +
                ", sectionKey=" + sectionKey +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", duration=" + duration +
                ", value=" + value +
                ", alarmMessage='" + alarmMessage + '\'' +
                '}';
    }
}
