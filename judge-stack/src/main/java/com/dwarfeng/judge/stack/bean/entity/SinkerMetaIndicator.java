package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.judge.stack.bean.key.SinkerMetaIndicatorKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * 下沉器关联元数据指示器。
 *
 * @author wangyc
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerMetaIndicator implements Entity<SinkerMetaIndicatorKey> {

    private static final long serialVersionUID = -2334896500541452781L;

    private SinkerMetaIndicatorKey key;
    private String label;
    private String defaultValue;

    /**
     * 描述。
     *
     * <p>
     * 描述是对指示器的详细说明，通常用于解释指示器的用途、使用方法或其他相关信息。
     *
     * @since 2.1.0-beta
     */
    private String description;

    public SinkerMetaIndicator() {
    }

    public SinkerMetaIndicator(SinkerMetaIndicatorKey key, String label, String defaultValue, String description) {
        this.key = key;
        this.label = label;
        this.defaultValue = defaultValue;
        this.description = description;
    }

    @Override
    public SinkerMetaIndicatorKey getKey() {
        return key;
    }

    @Override
    public void setKey(SinkerMetaIndicatorKey key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SinkerMetaIndicator{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
