package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.JudgerSupport;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * @author DwArFeng
 * @since beta-1.1.0
 */
public class FastJsonJudgerSupport implements Bean {

    private static final long serialVersionUID = -2965347477288503740L;

    public static FastJsonJudgerSupport of(JudgerSupport judgerSupport) {
        if (Objects.isNull(judgerSupport)) {
            return null;
        }
        return new FastJsonJudgerSupport(
                FastJsonStringIdKey.of(judgerSupport.getKey()),
                judgerSupport.getLabel(),
                judgerSupport.getDescription(),
                judgerSupport.getExampleContent()
        );
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonStringIdKey key;

    @JSONField(name = "label", ordinal = 2)
    private String label;

    @JSONField(name = "description", ordinal = 3)
    private String description;

    @JSONField(name = "example_content", ordinal = 4)
    private String exampleContent;

    public FastJsonJudgerSupport() {
    }

    public FastJsonJudgerSupport(FastJsonStringIdKey key, String label, String description, String exampleContent) {
        this.key = key;
        this.label = label;
        this.description = description;
        this.exampleContent = exampleContent;
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

    public String getExampleContent() {
        return exampleContent;
    }

    public void setExampleContent(String exampleContent) {
        this.exampleContent = exampleContent;
    }

    @Override
    public String toString() {
        return "FastJsonJudgerSupport{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", exampleContent='" + exampleContent + '\'' +
                '}';
    }
}
