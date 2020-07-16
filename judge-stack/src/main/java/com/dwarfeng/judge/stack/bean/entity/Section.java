package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 部件。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class Section implements Entity<LongIdKey> {

    private static final long serialVersionUID = -7044877098866471878L;

    private LongIdKey key;
    private String name;
    private boolean enabled;
    private double expected;
    private double variance;
    private String remark;

    public Section() {
    }

    public Section(
            LongIdKey key, String name, boolean enabled, double expected, double variance, String remark) {
        this.key = key;
        this.name = name;
        this.enabled = enabled;
        this.expected = expected;
        this.variance = variance;
        this.remark = remark;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public double getExpected() {
        return expected;
    }

    public void setExpected(double expected) {
        this.expected = expected;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Section{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", expected=" + expected +
                ", variance=" + variance +
                ", remark='" + remark + '\'' +
                '}';
    }
}
