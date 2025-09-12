package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.AnalyserSupport;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 分析器支持。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class FastJsonAnalyserSupport implements Bean {

    private static final long serialVersionUID = -7267780886318732613L;

    public static FastJsonAnalyserSupport of(AnalyserSupport analyserSupport) {
        if (Objects.isNull(analyserSupport)) {
            return null;
        } else {
            return new FastJsonAnalyserSupport(
                    FastJsonStringIdKey.of(analyserSupport.getKey()),
                    analyserSupport.getLabel(),
                    analyserSupport.getDescription(),
                    analyserSupport.getExampleParam()
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

    public FastJsonAnalyserSupport() {
    }

    public FastJsonAnalyserSupport(FastJsonStringIdKey key, String label, String description, String exampleParam) {
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
        return "FastJsonAnalyserSupport{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", exampleParam='" + exampleParam + '\'' +
                '}';
    }
}
