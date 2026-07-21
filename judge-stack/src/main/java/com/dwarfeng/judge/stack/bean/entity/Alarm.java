package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.judge.stack.bean.key.AlarmKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

import java.util.Date;

/**
 * 当前报警。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class Alarm implements Entity<AlarmKey> {

    private static final long serialVersionUID = -776954105130762165L;

    private AlarmKey key;
    private Date happenedDate;
    private String message;
    private String type;
    private double verdictValue;
    private String verdictMessage;

    public Alarm() {
    }

    public Alarm(
            AlarmKey key, Date happenedDate, String message, String type, double verdictValue, String verdictMessage
    ) {
        this.key = key;
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
        return "Alarm{" +
                "key=" + key +
                ", happenedDate=" + happenedDate +
                ", message='" + message + '\'' +
                ", type='" + type + '\'' +
                ", verdictValue=" + verdictValue +
                ", verdictMessage='" + verdictMessage + '\'' +
                '}';
    }
}
