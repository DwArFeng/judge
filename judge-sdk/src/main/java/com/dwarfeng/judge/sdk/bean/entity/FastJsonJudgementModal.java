package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.JudgementModal;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 判断结果模态。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class FastJsonJudgementModal implements Bean {

    private static final long serialVersionUID = 7638100442695731664L;

    public static FastJsonJudgementModal of(JudgementModal judgementModal) {
        if (Objects.isNull(judgementModal)) {
            return null;
        } else {
            return new FastJsonJudgementModal(
                    FastJsonLongIdKey.of(judgementModal.getKey()),
                    FastJsonLongIdKey.of(judgementModal.getJudgerInfoKey()),
                    judgementModal.getHappenedDate(),
                    judgementModal.getValue(),
                    judgementModal.getMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "judger_info_key", ordinal = 2)
    private FastJsonLongIdKey judgerInfoKey;

    @JSONField(name = "happened_date", ordinal = 3)
    private Date happenedDate;

    @JSONField(name = "value", ordinal = 4)
    private double value;

    @JSONField(name = "message", ordinal = 5)
    private String message;

    public FastJsonJudgementModal() {
    }

    public FastJsonJudgementModal(
            FastJsonLongIdKey key, FastJsonLongIdKey judgerInfoKey, Date happenedDate, double value, String message
    ) {
        this.key = key;
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
        return "FastJsonJudgementModal{" +
                "key=" + key +
                ", judgerInfoKey=" + judgerInfoKey +
                ", happenedDate=" + happenedDate +
                ", value=" + value +
                ", message='" + message + '\'' +
                '}';
    }
}
