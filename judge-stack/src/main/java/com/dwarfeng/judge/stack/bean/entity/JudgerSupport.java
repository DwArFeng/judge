package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 判断器支持。
 *
 * @author DwArFeng
 * @since beta-1.1.0
 */
public class JudgerSupport implements Entity<StringIdKey> {

    private static final long serialVersionUID = -1493905598645077380L;

    private StringIdKey key;
    private String label;
    private String description;
    private String exampleContent;

    public JudgerSupport() {
    }

    public JudgerSupport(StringIdKey key, String label, String description, String exampleContent) {
        this.key = key;
        this.label = label;
        this.description = description;
        this.exampleContent = exampleContent;
    }

    @Override
    public StringIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(StringIdKey key) {
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
        return "JudgerSupport{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", exampleContent='" + exampleContent + '\'' +
                '}';
    }
}
