package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.FastJsonVisualizeDataKey;
import com.dwarfeng.judge.stack.bean.entity.VisualizeData;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Objects;

/**
 * FastJson 可视化数据。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class FastJsonVisualizeData implements Dto {

    private static final long serialVersionUID = -6889390893745716144L;

    public static FastJsonVisualizeData of(VisualizeData visualizeData) {
        if (Objects.isNull(visualizeData)) {
            return null;
        } else {
            return new FastJsonVisualizeData(
                    FastJsonVisualizeDataKey.of(visualizeData.getKey()),
                    visualizeData.getContent()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonVisualizeDataKey key;

    @JSONField(name = "content", ordinal = 2)
    private String content;

    public FastJsonVisualizeData() {
    }

    public FastJsonVisualizeData(FastJsonVisualizeDataKey key, String content) {
        this.key = key;
        this.content = content;
    }

    public FastJsonVisualizeDataKey getKey() {
        return key;
    }

    public void setKey(FastJsonVisualizeDataKey key) {
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
        return "FastJsonVisualizeData{" +
                "key=" + key +
                ", content='" + content + '\'' +
                '}';
    }
}
