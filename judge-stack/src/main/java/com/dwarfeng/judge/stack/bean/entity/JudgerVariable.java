package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.judge.stack.bean.key.JudgerVariableKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 判断器变量。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class JudgerVariable implements Entity<JudgerVariableKey> {

    private static final long serialVersionUID = 7127143537435569429L;

    private JudgerVariableKey key;
    private String value;

    public JudgerVariable() {
    }

    public JudgerVariable(JudgerVariableKey key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public JudgerVariableKey getKey() {
        return key;
    }

    @Override
    public void setKey(JudgerVariableKey key) {
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
        return "JudgerVariable{" +
                "key=" + key +
                ", value='" + value + '\'' +
                '}';
    }
}
