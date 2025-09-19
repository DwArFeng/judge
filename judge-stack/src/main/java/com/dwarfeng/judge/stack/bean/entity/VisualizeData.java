package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.judge.stack.bean.key.VisualizeDataKey;
import com.dwarfeng.subgrade.stack.bean.entity.Entity;

/**
 * FastJson 可视化数据。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class VisualizeData implements Entity<VisualizeDataKey> {

    private static final long serialVersionUID = 2967287337280709645L;

    private VisualizeDataKey key;
    private String content;

    public VisualizeData() {
    }

    public VisualizeData(VisualizeDataKey key, String content) {
        this.key = key;
        this.content = content;
    }

    @Override
    public VisualizeDataKey getKey() {
        return key;
    }

    @Override
    public void setKey(VisualizeDataKey key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "VisualizeData{" +
                "key=" + key +
                ", content='" + content + '\'' +
                '}';
    }
}
