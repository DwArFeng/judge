package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.AdapterSupport;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 适配器支持。
 *
 * @author wangyc
 * @since 2.3.0
 */
public class FastJsonAdapterSupport implements Bean {

    private static final long serialVersionUID = 6767380919230044503L;

    public static FastJsonAdapterSupport of(AdapterSupport adapterSupport) {
        if (Objects.isNull(adapterSupport)) {
            return null;
        } else {
            return new FastJsonAdapterSupport(
                    FastJsonStringIdKey.of(adapterSupport.getKey()),
                    adapterSupport.getLabel(),
                    adapterSupport.getDescription(),
                    adapterSupport.getExampleParam()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;
    @JSONField(name = "label", ordinal = 2)
    private String label;
    @JSONField(name = "description", ordinal = 3)
    private String description;
    @JSONField(name = "example_param", ordinal = 4)
    private String exampleParam;

    public FastJsonAdapterSupport() {
    }

    public FastJsonAdapterSupport(
            FastJsonStringIdKey key,
            String label,
            String description,
            String exampleParam) {
        this.key = key;
        this.label = label;
        this.description = description;
        this.exampleParam = exampleParam;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
        this.key = key;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExampleParam() {
        return exampleParam;
    }

    public void setExampleParam(String exampleParam) {
        this.exampleParam = exampleParam;
    }

    @Override
    public String toString() {
        return "FastJsonAdapterSupport{" +
                "key=" + key +
                ", label=" + label + '\'' +
                ", description=" + description + '\'' +
                ", exampleParam=" + exampleParam + '\'' +
                '}';
    }
} 
