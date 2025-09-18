package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.judge.stack.bean.key.SinkerVariableKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 下沉器变量。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerVariable implements Entity<SinkerVariableKey> {

    private static final long serialVersionUID = -802651619337075483L;

    private SinkerVariableKey key;
    private String value;

    public SinkerVariable() {
    }

    public SinkerVariable(SinkerVariableKey key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public SinkerVariableKey getKey() {
        return key;
    }

    @Override
    public void setKey(SinkerVariableKey key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SinkerVariable{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
