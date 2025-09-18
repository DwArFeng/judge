package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 下沉器支持。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public class SinkerSupport implements Entity<StringIdKey> {

    private static final long serialVersionUID = -7870525329609025460L;

    private StringIdKey key;
    private String exampleParam;
    private String label;
    private String description;

    public SinkerSupport() {
    }

    public SinkerSupport(StringIdKey key, String exampleParam, String label, String description) {
        this.key = key;
        this.exampleParam = exampleParam;
        this.label = label;
        this.description = description;
    }

    @Override
    public StringIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(StringIdKey key) {
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
        return "SinkerSupport{" +
                "key=" + key +
                ", exampleParam='" + exampleParam + '\'' +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
