package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.JSFixedFastJsonJudgementKey;
import com.dwarfeng.judge.stack.bean.entity.Judgement;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 判断结果。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class JSFixedFastJsonJudgement implements Bean {

    private static final long serialVersionUID = 3906550020426369692L;

    public static JSFixedFastJsonJudgement of(Judgement judgement) {
        if (Objects.isNull(judgement)) {
            return null;
        } else {
            return new JSFixedFastJsonJudgement(
                    JSFixedFastJsonJudgementKey.of(judgement.getKey()),
                    judgement.getValue(),
                    judgement.getMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonJudgementKey key;

    @JSONField(name = "value", ordinal = 2)
    private double value;

    @JSONField(name = "message", ordinal = 3)
    private String message;

    public JSFixedFastJsonJudgement() {
    }

    public JSFixedFastJsonJudgement(JSFixedFastJsonJudgementKey key, double value, String message) {
        this.key = key;
        this.value = value;
        this.message = message;
    }

    public JSFixedFastJsonJudgementKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonJudgementKey key) {
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
        return "JSFixedFastJsonJudgement{" +
                "key=" + key +
                ", value=" + value +
                ", message='" + message + '\'' +
                '}';
    }
}
