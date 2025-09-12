package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 报警模态。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AlarmModal implements Entity<LongIdKey> {

    private static final long serialVersionUID = -8015018046003987350L;

    private LongIdKey key;
    private Date happenedDate;
    private double value;
    private boolean alarming;
    private String alarmMessage;

    public AlarmModal() {
    }

    public AlarmModal(LongIdKey key, Date happenedDate, double value, boolean alarming, String alarmMessage) {
        this.key = key;
        this.happenedDate = happenedDate;
        this.value = value;
        this.alarming = alarming;
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
        return "AlarmModal{" +
                "key=" + key +
                ", happenedDate=" + happenedDate +
                ", value=" + value +
                ", alarming=" + alarming +
                ", alarmMessage='" + alarmMessage + '\'' +
                '}';
    }
}
