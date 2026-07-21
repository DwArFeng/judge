package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.judge.stack.bean.entity.AlarmHistory;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 报警历史。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class JSFixedFastJsonAlarmHistory implements Bean {

    private static final long serialVersionUID = 6980364109229473916L;

    public static JSFixedFastJsonAlarmHistory of(AlarmHistory alarmHistory) {
        if (Objects.isNull(alarmHistory)) {
            return null;
        } else {
            return new JSFixedFastJsonAlarmHistory(
                    JSFixedFastJsonLongIdKey.of(alarmHistory.getKey()),
                    JSFixedFastJsonLongIdKey.of(alarmHistory.getSectionKey()),
                    alarmHistory.getDataId(),
                    alarmHistory.getStartDate(),
                    alarmHistory.getEndDate(),
                    alarmHistory.getDuration(),
                    alarmHistory.getMessage(),
                    alarmHistory.getType(),
                    alarmHistory.getVerdictValue(),
                    alarmHistory.getVerdictMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "section_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey sectionKey;

    @JSONField(name = "data_id", ordinal = 3)
    private String dataId;

    @JSONField(name = "start_date", ordinal = 4)
    private Date startDate;

    @JSONField(name = "end_date", ordinal = 5)
    private Date endDate;

    @JSONField(name = "duration", ordinal = 6, serializeUsing = ToStringSerializer.class)
    private long duration;

    @JSONField(name = "message", ordinal = 7)
    private String message;

    @JSONField(name = "type", ordinal = 8)
    private String type;

    @JSONField(name = "verdict_value", ordinal = 9)
    private double verdictValue;

    @JSONField(name = "verdict_message", ordinal = 10)
    private String verdictMessage;

    public JSFixedFastJsonAlarmHistory() {
    }

    public JSFixedFastJsonAlarmHistory(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey sectionKey, String dataId, Date startDate,
            Date endDate, long duration, String message, String type, double verdictValue, String verdictMessage
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
        return "JSFixedFastJsonAlarmHistory{" +
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
