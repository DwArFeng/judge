package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.AlarmModal;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 报警模态。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class FastJsonAlarmModal implements Bean {

    private static final long serialVersionUID = 3712366346359139409L;

    public static FastJsonAlarmModal of(AlarmModal alarmModal) {
        if (Objects.isNull(alarmModal)) {
            return null;
        } else {
            return new FastJsonAlarmModal(
                    FastJsonLongIdKey.of(alarmModal.getKey()),
                    alarmModal.getAlarmLevel(),
                    alarmModal.getHappenedDate(),
                    alarmModal.isAlarming(),
                    alarmModal.getAlarmMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "alarm_level", ordinal = 2)
    private String alarmLevel;

    @JSONField(name = "happened_date", ordinal = 3)
    private Date happenedDate;

    @JSONField(name = "alarming", ordinal = 5)
    private boolean alarming;

    @JSONField(name = "alarm_message", ordinal = 6)
    private String alarmMessage;

    public FastJsonAlarmModal() {
    }

    public FastJsonAlarmModal(
            FastJsonLongIdKey key, String alarmLevel, Date happenedDate, boolean alarming, String alarmMessage
    ) {
        this.key = key;
        this.alarmLevel = alarmLevel;
        this.happenedDate = happenedDate;
        this.alarming = alarming;
        this.alarmMessage = alarmMessage;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public Date getHappenedDate() {
        return happenedDate;
    }

    public void setHappenedDate(Date happenedDate) {
        this.happenedDate = happenedDate;
    }

    public boolean isAlarming() {
        return alarming;
    }

    public void setAlarming(boolean alarming) {
        this.alarming = alarming;
    }

    public String getAlarmMessage() {
        return alarmMessage;
    }

    public void setAlarmMessage(String alarmMessage) {
        this.alarmMessage = alarmMessage;
    }

    @Override
    public String toString() {
        return "FastJsonAlarmModal{" +
                "key=" + key +
                ", alarmLevel='" + alarmLevel + '\'' +
                ", happenedDate=" + happenedDate +
                ", alarming=" + alarming +
                ", alarmMessage='" + alarmMessage + '\'' +
                '}';
    }
}
