package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.AlarmModal;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 报警模态。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class JSFixedFastJsonAlarmModal implements Bean {

    private static final long serialVersionUID = -9063734273140947664L;

    public static JSFixedFastJsonAlarmModal of(AlarmModal alarmModal) {
        if (Objects.isNull(alarmModal)) {
            return null;
        } else {
            return new JSFixedFastJsonAlarmModal(
                    JSFixedFastJsonLongIdKey.of(alarmModal.getKey()),
                    alarmModal.getHappenedDate(),
                    alarmModal.getValue(),
                    alarmModal.isAlarming(),
                    alarmModal.getAlarmMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "happened_date", ordinal = 2)
    private Date happenedDate;

    @JSONField(name = "value", ordinal = 3)
    private double value;

    @JSONField(name = "alarming", ordinal = 4)
    private boolean alarming;

    @JSONField(name = "alarm_message", ordinal = 5)
    private String alarmMessage;

    public JSFixedFastJsonAlarmModal() {
    }

    public JSFixedFastJsonAlarmModal(
            JSFixedFastJsonLongIdKey key, Date happenedDate, double value, boolean alarming, String alarmMessage
    ) {
        this.key = key;
        this.happenedDate = happenedDate;
        this.value = value;
        this.alarming = alarming;
        this.alarmMessage = alarmMessage;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
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
        return "JSFixedFastJsonAlarmModal{" +
                "key=" + key +
                ", happenedDate=" + happenedDate +
                ", value=" + value +
                ", alarming=" + alarming +
                ", alarmMessage='" + alarmMessage + '\'' +
                '}';
    }
}
