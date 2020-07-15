package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.JudgedValue;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;
import java.util.Objects;

public class FastJsonJudgedValue implements Dto {

    private static final long serialVersionUID = 6641831770759037754L;

    public static FastJsonJudgedValue of(JudgedValue judgedValue) {
        if (Objects.isNull(judgedValue)) {
            return null;
        }

        return new FastJsonJudgedValue(
                FastJsonLongIdKey.of(judgedValue.getJudgerKey()),
                judgedValue.getValue(),
                judgedValue.getDetail(),
                judgedValue.getHappenedDate()
        );
    }

    public static JudgedValue toStackBean(FastJsonJudgedValue fastJsonJudgedValue) {
        if (Objects.isNull(fastJsonJudgedValue)) {
            return null;
        }

        return new JudgedValue(
                new LongIdKey(fastJsonJudgedValue.getJudgerKey().getLongId()),
                fastJsonJudgedValue.getValue(),
                fastJsonJudgedValue.getDetail(),
                fastJsonJudgedValue.getHappenedDate()
        );
    }

    @JSONField(name = "judger_key", ordinal = 1)
    private FastJsonLongIdKey judgerKey;

    @JSONField(name = "value", ordinal = 2)
    private double value;

    @JSONField(name = "detail", ordinal = 3)
    private String detail;

    @JSONField(name = "happened_date", ordinal = 4)
    private Date happenedDate;

    public FastJsonJudgedValue() {
    }

    public FastJsonJudgedValue(FastJsonLongIdKey judgerKey, double value, String detail, Date happenedDate) {
        this.judgerKey = judgerKey;
        this.value = value;
        this.detail = detail;
        this.happenedDate = happenedDate;
    }

    public FastJsonLongIdKey getJudgerKey() {
        return judgerKey;
    }

    public void setJudgerKey(FastJsonLongIdKey judgerKey) {
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
        return "FastJsonJudgedValue{" +
                "judgerKey=" + judgerKey +
                ", value=" + value +
                ", detail='" + detail + '\'' +
                ", happenedDate=" + happenedDate +
                '}';
    }
}
