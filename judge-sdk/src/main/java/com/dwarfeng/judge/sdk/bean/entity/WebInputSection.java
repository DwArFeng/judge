package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.groups.Default;
import java.util.Objects;

/**
 * WebInput 部件。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputSection implements Bean {

    private static final long serialVersionUID = -2768437209877111532L;

    public static Section toStackBean(WebInputSection webInputSection) {
        if (Objects.isNull(webInputSection)) {
            return null;
        }

        return new Section(
                WebInputLongIdKey.toStackBean(webInputSection.getKey()),
                webInputSection.getName(),
                webInputSection.isEnabled(),
                webInputSection.getExpected(),
                webInputSection.getVariance(),
                webInputSection.getRemark()
        );
    }

    @JSONField(name = "key")
    @Valid
    @NotNull(groups = Default.class)
    private WebInputLongIdKey key;

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @JSONField(name = "enabled")
    private boolean enabled;

    @JSONField(name = "expected")
    private double expected;

    @JSONField(name = "variance")
    @Positive
    private double variance;

    @JSONField(name = "remark")
    private String remark;

    public WebInputSection() {
    }

    public WebInputSection(
            WebInputLongIdKey key, String name, boolean enabled, double expected, double variance, String remark) {
        this.key = key;
        this.name = name;
        this.enabled = enabled;
        this.expected = expected;
        this.variance = variance;
        this.remark = remark;
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
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
        return "WebInputSection{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", enabled=" + enabled +
                ", expected=" + expected +
                ", variance=" + variance +
                ", remark='" + remark + '\'' +
                '}';
    }
}
