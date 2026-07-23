package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 报警模态更新信息。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class AlarmModalUpdateInfo implements Dto {

    private static final long serialVersionUID = 6403378655022889002L;

    private LongIdKey sectionKey;
    private String dataId;
    private boolean alarming;
    private Date happenedDate;
    private String message;
    private String type;
    private double verdictValue;
    private String verdictMessage;

    public AlarmModalUpdateInfo() {
    }

    public AlarmModalUpdateInfo(
            LongIdKey sectionKey, String dataId, boolean alarming, Date happenedDate, String message, String type,
            double verdictValue, String verdictMessage
    ) {
        this.sectionKey = sectionKey;
        this.dataId = dataId;
        this.alarming = alarming;
        this.happenedDate = happenedDate;
        this.message = message;
        this.type = type;
        this.verdictValue = verdictValue;
        this.verdictMessage = verdictMessage;
    }

    public LongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(LongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
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
        return "AlarmModalUpdateInfo{" +
                "sectionKey=" + sectionKey +
                ", dataId='" + dataId + '\'' +
                ", alarming=" + alarming +
                ", happenedDate=" + happenedDate +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", verdictValue=" + verdictValue +
                ", verdictMessage='" + verdictMessage + '\'' +
                '}';
    }
}
