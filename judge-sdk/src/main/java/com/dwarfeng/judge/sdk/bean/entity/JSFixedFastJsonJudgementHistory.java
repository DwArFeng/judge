package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.JudgementHistory;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 判断结果历史。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class JSFixedFastJsonJudgementHistory implements Bean {

    private static final long serialVersionUID = 4769228573095083122L;

    public static JSFixedFastJsonJudgementHistory of(JudgementHistory judgementHistory) {
        if (Objects.isNull(judgementHistory)) {
            return null;
        } else {
            return new JSFixedFastJsonJudgementHistory(
                    JSFixedFastJsonLongIdKey.of(judgementHistory.getKey()),
                    JSFixedFastJsonLongIdKey.of(judgementHistory.getSectionKey()),
                    JSFixedFastJsonLongIdKey.of(judgementHistory.getJudgerInfoKey()),
                    judgementHistory.getHappenedDate(),
                    judgementHistory.getValue(),
                    judgementHistory.getMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "section_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey sectionKey;

    @JSONField(name = "judger_info_key", ordinal = 3)
    private JSFixedFastJsonLongIdKey judgerInfoKey;

    @JSONField(name = "happened_date", ordinal = 4)
    private Date happenedDate;

    @JSONField(name = "value", ordinal = 5)
    private double value;

    @JSONField(name = "message", ordinal = 6)
    private String message;

    public JSFixedFastJsonJudgementHistory() {
    }

    public JSFixedFastJsonJudgementHistory(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey sectionKey, JSFixedFastJsonLongIdKey judgerInfoKey,
            Date happenedDate, double value, String message

    ) {
        this.key = key;
        this.sectionKey = sectionKey;
        this.judgerInfoKey = judgerInfoKey;
        this.happenedDate = happenedDate;
        this.value = value;
        this.message = message;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(JSFixedFastJsonLongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    public JSFixedFastJsonLongIdKey getJudgerInfoKey() {
        return judgerInfoKey;
    }

    public void setJudgerInfoKey(JSFixedFastJsonLongIdKey judgerInfoKey) {
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
        return "JSFixedFastJsonJudgementHistory{" +
                "key=" + key +
                ", sectionKey=" + sectionKey +
                ", judgerInfoKey=" + judgerInfoKey +
                ", happenedDate=" + happenedDate +
                ", value=" + value +
                ", message='" + message + '\'' +
                '}';
    }
}
