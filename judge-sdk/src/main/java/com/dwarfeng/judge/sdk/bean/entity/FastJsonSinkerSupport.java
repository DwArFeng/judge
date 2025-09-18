package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.SinkerSupport;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 下沉器支持。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public class FastJsonSinkerSupport implements Bean {

    private static final long serialVersionUID = -3027264499896065050L;

    public static FastJsonSinkerSupport of(SinkerSupport sinkerSupport) {
        if (Objects.isNull(sinkerSupport)) {
            return null;
        } else {
            return new FastJsonSinkerSupport(
                    FastJsonStringIdKey.of(sinkerSupport.getKey()),
                    sinkerSupport.getExampleParam(),
                    sinkerSupport.getLabel(),
                    sinkerSupport.getDescription()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "example_param", ordinal = 2)
    private String exampleParam;

    @JSONField(name = "label", ordinal = 3)
    private String label;

    @JSONField(name = "description", ordinal = 4)
    private String description;

    public FastJsonSinkerSupport() {
    }

    public FastJsonSinkerSupport(FastJsonStringIdKey key, String exampleParam, String label, String description) {
        this.key = key;
        this.exampleParam = exampleParam;
        this.label = label;
        this.description = description;
    }

    public FastJsonStringIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonStringIdKey key) {
        this.key = key;
    }

    public String getExampleParam() {
        return exampleParam;
    }

    public void setExampleParam(String exampleParam) {
        this.exampleParam = exampleParam;
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

    @Override
    public String toString() {
        return "FastJsonSinkerSupport{" +
                "key=" + key +
                ", exampleParam=" + exampleParam + '\'' +
                ", label=" + label + '\'' +
                ", description=" + description + '\'' +
                '}';
    }
}

