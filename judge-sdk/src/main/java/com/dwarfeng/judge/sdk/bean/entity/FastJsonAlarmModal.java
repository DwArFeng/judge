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

    private static final long serialVersionUID = -5403239153462511715L;

    public static FastJsonAlarmModal of(AlarmModal alarmModal) {
        if (Objects.isNull(alarmModal)) {
            return null;
        } else {
            return new FastJsonAlarmModal(
                    FastJsonLongIdKey.of(alarmModal.getKey()),
                    alarmModal.getHappenedDate(),
                    alarmModal.getValue(),
                    alarmModal.isAlarming(),
                    alarmModal.getAlarmMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "happened_date", ordinal = 2)
    private Date happenedDate;

    @JSONField(name = "value", ordinal = 3)
    private double value;

    @JSONField(name = "alarming", ordinal = 4)
    private boolean alarming;

    @JSONField(name = "alarm_message", ordinal = 5)
    private String alarmMessage;

    public FastJsonAlarmModal() {
    }

    public FastJsonAlarmModal(
            FastJsonLongIdKey key, Date happenedDate, double value, boolean alarming, String alarmMessage
    ) {
        this.key = key;
        this.happenedDate = happenedDate;
        this.value = value;
        this.alarming = alarming;
        this.alarmMessage = alarmMessage;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public Date getHappenedDate() {
        return happenedDate;
    }

    public void setHappenedDate(Date happenedDate) {
        this.happenedDate = happenedDate;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
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
                ", happenedDate=" + happenedDate +
                ", value=" + value +
                ", alarming=" + alarming +
                ", alarmMessage='" + alarmMessage + '\'' +
                '}';
    }
}
