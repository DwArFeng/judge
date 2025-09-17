package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.JudgementHistory;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 判断结果历史。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class FastJsonJudgementHistory implements Bean {

    private static final long serialVersionUID = -8506189130547366274L;

    public static FastJsonJudgementHistory of(JudgementHistory judgementHistory) {
        if (Objects.isNull(judgementHistory)) {
            return null;
        } else {
            return new FastJsonJudgementHistory(
                    FastJsonLongIdKey.of(judgementHistory.getKey()),
                    FastJsonLongIdKey.of(judgementHistory.getSectionKey()),
                    FastJsonLongIdKey.of(judgementHistory.getJudgerInfoKey()),
                    judgementHistory.getHappenedDate(),
                    judgementHistory.getValue(),
                    judgementHistory.getMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "section_key", ordinal = 2)
    private FastJsonLongIdKey sectionKey;

    @JSONField(name = "judger_info_key", ordinal = 3)
    private FastJsonLongIdKey judgerInfoKey;

    @JSONField(name = "happened_date", ordinal = 4)
    private Date happenedDate;

    @JSONField(name = "value", ordinal = 5)
    private double value;

    @JSONField(name = "message", ordinal = 6)
    private String message;

    public FastJsonJudgementHistory() {
    }

    public FastJsonJudgementHistory(
            FastJsonLongIdKey key, FastJsonLongIdKey sectionKey, FastJsonLongIdKey judgerInfoKey, Date happenedDate,
            double value, String message
    ) {
        this.key = key;
        this.sectionKey = sectionKey;
        this.judgerInfoKey = judgerInfoKey;
        this.happenedDate = happenedDate;
        this.value = value;
        this.message = message;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(FastJsonLongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    public FastJsonLongIdKey getJudgerInfoKey() {
        return judgerInfoKey;
    }

    public void setJudgerInfoKey(FastJsonLongIdKey judgerInfoKey) {
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
        return "FastJsonJudgementHistory{" +
                "key=" + key +
                ", sectionKey=" + sectionKey +
                ", judgerInfoKey=" + judgerInfoKey +
                ", happenedDate=" + happenedDate +
                ", value=" + value +
                ", message='" + message + '\'' +
                '}';
    }
}
