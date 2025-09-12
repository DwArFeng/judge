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

    private static final long serialVersionUID = 1478552200515290176L;

    private LongIdKey key;
    private String alarmLevel;
    private Date happenedDate;
    private boolean alarming;
    private String alarmMessage;

    public AlarmModal() {
    }

    public AlarmModal(LongIdKey key, String alarmLevel, Date happenedDate, boolean alarming, String alarmMessage) {
        this.key = key;
        this.alarmLevel = alarmLevel;
        this.happenedDate = happenedDate;
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
        return "AlarmModal{" +
                "key=" + key +
                ", alarmLevel='" + alarmLevel + '\'' +
                ", happenedDate=" + happenedDate +
                ", alarming=" + alarming +
                ", alarmMessage='" + alarmMessage + '\'' +
                '}';
    }
}
