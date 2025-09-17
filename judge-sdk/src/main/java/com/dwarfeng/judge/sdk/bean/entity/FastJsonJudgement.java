package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.FastJsonJudgementKey;
import com.dwarfeng.judge.stack.bean.entity.Judgement;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 判断结果。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class FastJsonJudgement implements Bean {

    private static final long serialVersionUID = 1909048711537457865L;

    public static FastJsonJudgement of(Judgement judgement) {
        if (Objects.isNull(judgement)) {
            return null;
        } else {
            return new FastJsonJudgement(
                    FastJsonJudgementKey.of(judgement.getKey()),
                    judgement.getValue(),
                    judgement.getMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonJudgementKey key;

    @JSONField(name = "value", ordinal = 2)
    private double value;

    @JSONField(name = "message", ordinal = 3)
    private String message;

    public FastJsonJudgement() {
    }

    public FastJsonJudgement(FastJsonJudgementKey key, double value, String message) {
        this.key = key;
        this.value = value;
        this.message = message;
    }

    public FastJsonJudgementKey getKey() {
        return key;
    }

    public void setKey(FastJsonJudgementKey key) {
        this.key = key;
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
        return "FastJsonJudgement{" +
                "key=" + key +
                ", value=" + value +
                ", message='" + message + '\'' +
                '}';
    }
}
