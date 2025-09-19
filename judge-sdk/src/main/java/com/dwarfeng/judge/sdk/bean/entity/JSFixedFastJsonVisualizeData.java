package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.bean.key.JSFixedFastJsonVisualizeDataKey;
import com.dwarfeng.judge.stack.bean.entity.VisualizeData;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Objects;

/**
 * JSFixed FastJson 可视化数据。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class JSFixedFastJsonVisualizeData implements Dto {

    private static final long serialVersionUID = -5210707162167381444L;

    public static JSFixedFastJsonVisualizeData of(VisualizeData visualizeData) {
        if (Objects.isNull(visualizeData)) {
            return null;
        } else {
            return new JSFixedFastJsonVisualizeData(
                    JSFixedFastJsonVisualizeDataKey.of(visualizeData.getKey()),
                    visualizeData.getContent()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonVisualizeDataKey key;

    @JSONField(name = "content", ordinal = 2)
    private String content;

    public JSFixedFastJsonVisualizeData() {
    }

    public JSFixedFastJsonVisualizeData(JSFixedFastJsonVisualizeDataKey key, String content) {
        this.key = key;
        this.content = content;
    }

    public JSFixedFastJsonVisualizeDataKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonVisualizeDataKey key) {
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
        return "JSFixedFastJsonVisualizeData{" +
                "key=" + key +
                ", content='" + content + '\'' +
                '}';
    }
}
