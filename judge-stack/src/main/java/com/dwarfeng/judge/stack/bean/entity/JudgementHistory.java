package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 判断结果历史。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class JudgementHistory implements Entity<LongIdKey> {

    private static final long serialVersionUID = 8795001658251495517L;

    private LongIdKey key;
    private LongIdKey sectionKey;
    private LongIdKey judgerInfoKey;
    private Date happenedDate;
    private double value;
    private String message;

    public JudgementHistory() {
    }

    public JudgementHistory(
            LongIdKey key, LongIdKey sectionKey, LongIdKey judgerInfoKey, Date happenedDate, double value,
            String message
    ) {
        this.key = key;
        this.sectionKey = sectionKey;
        this.judgerInfoKey = judgerInfoKey;
        this.happenedDate = happenedDate;
        this.value = value;
        this.message = message;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public LongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(LongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    public LongIdKey getJudgerInfoKey() {
        return judgerInfoKey;
    }

    public void setJudgerInfoKey(LongIdKey judgerInfoKey) {
        this.judgerInfoKey = judgerInfoKey;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "JudgementHistory{" +
                "key=" + key +
                ", sectionKey=" + sectionKey +
                ", judgerInfoKey=" + judgerInfoKey +
                ", happenedDate=" + happenedDate +
                ", value=" + value +
                ", message='" + message + '\'' +
                '}';
    }
}
