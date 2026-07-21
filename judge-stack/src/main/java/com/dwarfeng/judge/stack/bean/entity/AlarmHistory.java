package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 报警历史。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class AlarmHistory implements Entity<LongIdKey> {

    private static final long serialVersionUID = 2776553275779362352L;

    private LongIdKey key;
    private LongIdKey sectionKey;
    private String dataId;
    private Date startDate;
    private Date endDate;
    private long duration;
    private String message;
    private String type;
    private double verdictValue;
    private String verdictMessage;

    public AlarmHistory() {
    }

    public AlarmHistory(
            LongIdKey key, LongIdKey sectionKey, String dataId, Date startDate, Date endDate, long duration,
            String message, String type, double verdictValue, String verdictMessage
    ) {
        this.key = key;
        this.sectionKey = sectionKey;
        this.dataId = dataId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.duration = duration;
        this.message = message;
        this.type = type;
        this.verdictValue = verdictValue;
        this.verdictMessage = verdictMessage;
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

    public double getVerdictValue() {
        return verdictValue;
    }

    public void setVerdictValue(double verdictValue) {
        this.verdictValue = verdictValue;
    }

    public String getVerdictMessage() {
        return verdictMessage;
    }

    public void setVerdictMessage(String verdictMessage) {
        this.verdictMessage = verdictMessage;
    }

    @Override
    public String toString() {
        return "AlarmHistory{" +
                "key=" + key +
                ", sectionKey=" + sectionKey +
                ", dataId='" + dataId + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", duration=" + duration +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", verdictValue=" + verdictValue +
                ", verdictMessage='" + verdictMessage + '\'' +
                '}';
    }
}
