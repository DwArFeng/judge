package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.judge.stack.bean.key.VariableKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 变量。
 *
 * @author DwArFeng
 * @since 1.5.0
 */
public class Variable implements Entity<VariableKey> {

    private static final long serialVersionUID = 8139226748672661092L;

    private VariableKey key;
    private String value;
    private String remark;

    public Variable() {
    }

    public Variable(VariableKey key, String value, String remark) {
        this.key = key;
        this.value = value;
        this.remark = remark;
    }

    @Override
    public VariableKey getKey() {
        return key;
    }

    @Override
    public void setKey(VariableKey key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "key=" + key +
                ", value='" + value + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
