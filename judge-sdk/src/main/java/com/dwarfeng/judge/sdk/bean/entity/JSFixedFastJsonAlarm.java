package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.JSFixedFastJsonAlarmKey;
import com.dwarfeng.judge.stack.bean.entity.Alarm;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 当前报警。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class JSFixedFastJsonAlarm implements Bean {

    private static final long serialVersionUID = -741648021142941378L;

    public static JSFixedFastJsonAlarm of(Alarm alarm) {
        if (Objects.isNull(alarm)) {
            return null;
        } else {
            return new JSFixedFastJsonAlarm(
                    JSFixedFastJsonAlarmKey.of(alarm.getKey()),
                    alarm.getHappenedDate(),
                    alarm.getMessage(),
                    alarm.getType(),
                    alarm.getVerdictValue(),
                    alarm.getVerdictMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonAlarmKey key;

    @JSONField(name = "happened_date", ordinal = 2)
    private Date happenedDate;

    @JSONField(name = "message", ordinal = 3)
    private String message;

    @JSONField(name = "type", ordinal = 4)
    private String type;

    @JSONField(name = "verdict_value", ordinal = 5)
    private double verdictValue;

    @JSONField(name = "verdict_message", ordinal = 6)
    private String verdictMessage;

    public JSFixedFastJsonAlarm() {
    }

    public JSFixedFastJsonAlarm(
            JSFixedFastJsonAlarmKey key, Date happenedDate, String message, String type, double verdictValue,
            String verdictMessage
    ) {
        this.key = key;
        this.happenedDate = happenedDate;
        this.message = message;
        this.type = type;
        this.verdictValue = verdictValue;
        this.verdictMessage = verdictMessage;
    }

    public JSFixedFastJsonAlarmKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonAlarmKey key) {
        this.key = key;
    }

    public Date getHappenedDate() {
        return happenedDate;
    }

    public void setHappenedDate(Date happenedDate) {
        this.happenedDate = happenedDate;
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
        return "JSFixedFastJsonAlarm{" +
                "key=" + key +
                ", happenedDate=" + happenedDate +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", verdictValue=" + verdictValue +
                ", verdictMessage='" + verdictMessage + '\'' +
                '}';
    }
}
