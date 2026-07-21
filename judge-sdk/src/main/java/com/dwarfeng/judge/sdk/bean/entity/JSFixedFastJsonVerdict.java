package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.JSFixedFastJsonVerdictKey;
import com.dwarfeng.judge.stack.bean.entity.Verdict;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 部件裁决结果。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class JSFixedFastJsonVerdict implements Bean {

    private static final long serialVersionUID = -7517430025249220926L;

    public static JSFixedFastJsonVerdict of(Verdict verdict) {
        if (Objects.isNull(verdict)) {
            return null;
        } else {
            return new JSFixedFastJsonVerdict(
                    JSFixedFastJsonVerdictKey.of(verdict.getKey()),
                    verdict.getValue(),
                    verdict.getMessage()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonVerdictKey key;

    @JSONField(name = "value", ordinal = 2)
    private double value;

    @JSONField(name = "message", ordinal = 3)
    private String message;

    public JSFixedFastJsonVerdict() {
    }

    public JSFixedFastJsonVerdict(JSFixedFastJsonVerdictKey key, double value, String message) {
        this.key = key;
        this.value = value;
        this.message = message;
    }

    public JSFixedFastJsonVerdictKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonVerdictKey key) {
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
        return "JSFixedFastJsonVerdict{" +
                "key=" + key +
                ", value=" + value +
                ", message='" + message + '\'' +
                '}';
    }
}
