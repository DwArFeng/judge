package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 判断结果。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JudgedValue implements Dto {

    private static final long serialVersionUID = 5245537708632282369L;

    private LongIdKey judgerKey;
    private double value;
    private String detail;
    private Date happenedDate;

    public JudgedValue() {
    }

    public JudgedValue(LongIdKey judgerKey, double value, String detail, Date happenedDate) {
        this.judgerKey = judgerKey;
        this.value = value;
        this.detail = detail;
        this.happenedDate = happenedDate;
    }

    public LongIdKey getJudgerKey() {
        return judgerKey;
    }

    public void setJudgerKey(LongIdKey judgerKey) {
        this.judgerKey = judgerKey;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Date getHappenedDate() {
        return happenedDate;
    }

    public void setHappenedDate(Date happenedDate) {
        this.happenedDate = happenedDate;
    }

    @Override
    public String toString() {
        return "JudgedValue{" +
                "judgerKey=" + judgerKey +
                ", value=" + value +
                ", detail='" + detail + '\'' +
                ", happenedDate=" + happenedDate +
                '}';
    }
}
