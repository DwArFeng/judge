package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.judge.stack.bean.key.AlarmKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

import java.util.Date;

/**
 * 报警模态。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class AlarmModal implements Entity<AlarmKey> {

    private static final long serialVersionUID = 2764923058771049343L;

    private AlarmKey key;
    private boolean alarming;
    private Date happenedDate;
    private String message;
    private String type;
    private double verdictValue;
    private String verdictMessage;

    public AlarmModal() {
    }

    public AlarmModal(
            AlarmKey key, boolean alarming, Date happenedDate, String message, String type, double verdictValue,
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

    @Override
    public AlarmKey getKey() {
        return key;
    }

    @Override
    public void setKey(AlarmKey key) {
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
        return "AlarmModal{" +
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
