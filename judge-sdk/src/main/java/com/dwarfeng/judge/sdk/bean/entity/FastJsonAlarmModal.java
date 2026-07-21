package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.FastJsonAlarmKey;
import com.dwarfeng.judge.stack.bean.entity.AlarmModal;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 报警模态。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class FastJsonAlarmModal implements Bean {

    private static final long serialVersionUID = -947952015777088396L;

    public static FastJsonAlarmModal of(AlarmModal alarmModal) {
        if (Objects.isNull(alarmModal)) {
            return null;
        } else {
            return new FastJsonAlarmModal(
                    FastJsonAlarmKey.of(alarmModal.getKey()),
                    alarmModal.isAlarming(),
                    alarmModal.getHappenedDate(),
                    alarmModal.getMessage(),
                    alarmModal.getType(),
                    alarmModal.getVerdictValue(),
                    alarmModal.getVerdictMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonAlarmKey key;

    @JSONField(name = "alarming", ordinal = 2)
    private boolean alarming;

    @JSONField(name = "happened_date", ordinal = 3)
    private Date happenedDate;

    @JSONField(name = "message", ordinal = 4)
    private String message;

    @JSONField(name = "type", ordinal = 5)
    private String type;

    @JSONField(name = "verdict_value", ordinal = 6)
    private double verdictValue;

    @JSONField(name = "verdict_message", ordinal = 7)
    private String verdictMessage;

    public FastJsonAlarmModal() {
    }

    public FastJsonAlarmModal(
            FastJsonAlarmKey key, boolean alarming, Date happenedDate, String message, String type, double verdictValue,
            String verdictMessage
    ) {
        this.key = key;
        this.alarming = alarming;
        this.happenedDate = happenedDate;
        this.message = message;
        this.type = type;
        this.verdictValue = verdictValue;
        this.verdictMessage = verdictMessage;
    }

    public FastJsonAlarmKey getKey() {
        return key;
    }

    public void setKey(FastJsonAlarmKey key) {
        this.key = key;
    }

    public boolean isAlarming() {
        return alarming;
    }

    public void setAlarming(boolean alarming) {
        this.alarming = alarming;
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
        return "FastJsonAlarmModal{" +
                "key=" + key +
                ", alarming=" + alarming +
                ", happenedDate=" + happenedDate +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", verdictValue=" + verdictValue +
                ", verdictMessage='" + verdictMessage + '\'' +
                '}';
    }
}
