package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.FastJsonSinkerMetaIndicatorKey;
import com.dwarfeng.judge.stack.bean.entity.SinkerMetaIndicator;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 下沉关联元数据指示器。
 *
 * @author wangyc
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class FastJsonSinkerMetaIndicator implements Bean {

    private static final long serialVersionUID = 4557829892591475562L;

    public static FastJsonSinkerMetaIndicator of(SinkerMetaIndicator sinkerMetaIndicator) {
        if (Objects.isNull(sinkerMetaIndicator)) {
            return null;
        } else {
            return new FastJsonSinkerMetaIndicator(
                    FastJsonSinkerMetaIndicatorKey.of(sinkerMetaIndicator.getKey()),
                    sinkerMetaIndicator.getLabel(),
                    sinkerMetaIndicator.getDefaultValue(),
                    sinkerMetaIndicator.getDescription()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonSinkerMetaIndicatorKey key;

    @JSONField(name = "label", ordinal = 2)
    private String label;

    @JSONField(name = "initial_value", ordinal = 3)
    private String defaultValue;

    @JSONField(name = "description", ordinal = 4)
    private String description;

    public FastJsonSinkerMetaIndicator() {
    }

    public FastJsonSinkerMetaIndicator(
            FastJsonSinkerMetaIndicatorKey key, String label, String defaultValue, String description
    ) {
        this.key = key;
        this.label = label;
        this.defaultValue = defaultValue;
        this.description = description;
    }

    public FastJsonSinkerMetaIndicatorKey getKey() {
        return key;
    }

    public void setKey(FastJsonSinkerMetaIndicatorKey key) {
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
        return "FastJsonSinkerMetaIndicator{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
