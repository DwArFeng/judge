package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 报警模态更新信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AlarmModalUpdateInfo implements Dto {

    private static final long serialVersionUID = 1939426225925500063L;

    private LongIdKey sectionKey;
    private String alarmLevel;
    private Date happenedDate;
    private boolean alarming;
    private String alarmMessage;

    public AlarmModalUpdateInfo() {
    }

    public AlarmModalUpdateInfo(
            LongIdKey sectionKey, String alarmLevel, Date happenedDate, boolean alarming, String alarmMessage
    ) {
        this.sectionKey = sectionKey;
        this.alarmLevel = alarmLevel;
        this.happenedDate = happenedDate;
        this.alarming = alarming;
        this.alarmMessage = alarmMessage;
    }

    public LongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(LongIdKey sectionKey) {
        this.sectionKey = sectionKey;
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
        return "AlarmModalUpdateInfo{" +
                "sectionKey=" + sectionKey +
                ", alarmLevel='" + alarmLevel + '\'' +
                ", happenedDate=" + happenedDate +
                ", alarming=" + alarming +
                ", alarmMessage='" + alarmMessage + '\'' +
                '}';
    }
}
