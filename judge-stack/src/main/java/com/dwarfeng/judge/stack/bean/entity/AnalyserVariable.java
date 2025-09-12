package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.judge.stack.bean.key.AnalyserVariableKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 分析器变量。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalyserVariable implements Entity<AnalyserVariableKey> {

    private static final long serialVersionUID = 1489739120556631944L;

    private AnalyserVariableKey key;
    private String value;

    public AnalyserVariable() {
    }

    public AnalyserVariable(AnalyserVariableKey key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public AnalyserVariableKey getKey() {
        return key;
    }

    @Override
    public void setKey(AnalyserVariableKey key) {
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
        return "AnalyserVariable{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
