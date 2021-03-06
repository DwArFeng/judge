package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 部件。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JSFixedFastJsonSection implements Bean {

    private static final long serialVersionUID = -2690536510602856598L;

    public static JSFixedFastJsonSection of(Section section) {
        if (Objects.isNull(section)) {
            return null;
        }

        return new JSFixedFastJsonSection(
                JSFixedFastJsonLongIdKey.of(section.getKey()),
                section.getName(),
                section.isEnabled(),
                section.getExpected(),
                section.getVariance(),
                section.getRemark()
        );
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "name", ordinal = 2)
    private String name;

    @JSONField(name = "enabled", ordinal = 3)
    private boolean enabled;

    @JSONField(name = "expected", ordinal = 4)
    private double expected;

    @JSONField(name = "variance", ordinal = 5)
    private double variance;

    @JSONField(name = "remark", ordinal = 6)
    private String remark;

    public JSFixedFastJsonSection() {
    }

    public JSFixedFastJsonSection(
            JSFixedFastJsonLongIdKey key, String name, boolean enabled, double expected, double variance,
            String remark) {
        this.key = key;
        this.name = name;
        this.enabled = enabled;
        this.expected = expected;
        this.variance = variance;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
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
        return "JSFixedFastJsonSection{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", expected=" + expected +
                ", variance=" + variance +
                ", remark='" + remark + '\'' +
                '}';
    }
}
