package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.judge.stack.bean.key.VerdictKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 部件裁决结果。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class Verdict implements Entity<VerdictKey> {

    private static final long serialVersionUID = -4594150835414330436L;

    private VerdictKey key;

    /**
     * 判断值。
     *
     * <p>
     * 一个归一化的数值，取值范围为 [0.0, 1.0]。
     */
    private double value;

    /**
     * 判断消息。
     *
     * <p>
     * 一段描述性的文本，用于说明判断值的含义或解释判断值如何得出。
     */
    private String message;

    public Verdict() {
    }

    public Verdict(VerdictKey key, double value, String message) {
        this.key = key;
        this.value = value;
        this.message = message;
    }

    @Override
    public VerdictKey getKey() {
        return key;
    }

    @Override
    public void setKey(VerdictKey key) {
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
        return "Verdict{" +
                "key=" + key +
                ", value=" + value +
                ", message='" + message + '\'' +
                '}';
    }
}
