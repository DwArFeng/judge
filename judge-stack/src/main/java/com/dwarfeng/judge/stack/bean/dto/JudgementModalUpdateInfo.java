package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 判断结果模态更新信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class JudgementModalUpdateInfo implements Dto {

    private static final long serialVersionUID = 3244671235692790976L;

    private LongIdKey sectionKey;
    private LongIdKey judgerInfoKey;
    private Date happenedDate;
    private double value;
    private String message;

    public JudgementModalUpdateInfo() {
    }

    public JudgementModalUpdateInfo(
            LongIdKey sectionKey, LongIdKey judgerInfoKey, Date happenedDate, double value, String message
    ) {
        this.sectionKey = sectionKey;
        this.judgerInfoKey = judgerInfoKey;
        this.happenedDate = happenedDate;
        this.value = value;
        this.message = message;
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
        return "JudgementModalUpdateInfo{" +
                "sectionKey=" + sectionKey +
                ", judgerInfoKey=" + judgerInfoKey +
                ", happenedDate=" + happenedDate +
                ", value=" + value +
                ", message='" + message + '\'' +
                '}';
    }
}
