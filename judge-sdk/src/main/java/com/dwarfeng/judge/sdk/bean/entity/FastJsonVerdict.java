package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.FastJsonVerdictKey;
import com.dwarfeng.judge.stack.bean.entity.Verdict;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 部件裁决结果。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class FastJsonVerdict implements Bean {

    private static final long serialVersionUID = 5095939781707158323L;

    public static FastJsonVerdict of(Verdict verdict) {
        if (Objects.isNull(verdict)) {
            return null;
        } else {
            return new FastJsonVerdict(
                    FastJsonVerdictKey.of(verdict.getKey()),
                    verdict.getValue(),
                    verdict.getMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonVerdictKey key;

    @JSONField(name = "value", ordinal = 2)
    private double value;

    @JSONField(name = "message", ordinal = 3)
    private String message;

    public FastJsonVerdict() {
    }

    public FastJsonVerdict(FastJsonVerdictKey key, double value, String message) {
        this.key = key;
        this.value = value;
        this.message = message;
    }

    public FastJsonVerdictKey getKey() {
        return key;
    }

    public void setKey(FastJsonVerdictKey key) {
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
        return "FastJsonVerdict{" +
                "key=" + key +
                ", value=" + value +
                ", message='" + message + '\'' +
                '}';
    }
}
