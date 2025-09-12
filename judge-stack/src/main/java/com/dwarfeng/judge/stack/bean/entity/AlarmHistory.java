package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 报警历史。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AlarmHistory implements Entity<LongIdKey> {

    private static final long serialVersionUID = 8074696989649762599L;

    private LongIdKey key;
    private LongIdKey sectionKey;
    private Date startDate;
    private Date endDate;
    private long duration;
    private double value;
    private String alarmMessage;

    public AlarmHistory() {
    }

    public AlarmHistory(
            LongIdKey key, LongIdKey sectionKey, Date startDate, Date endDate, long duration, double value,
            String alarmMessage
    ) {
        this.key = key;
        this.sectionKey = sectionKey;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.value = value;
        this.alarmMessage = alarmMessage;
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
        return "AlarmHistory{" +
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
