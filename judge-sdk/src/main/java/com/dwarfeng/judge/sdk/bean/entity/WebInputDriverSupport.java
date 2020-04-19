package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.DriverSupport;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

public class WebInputDriverSupport implements Bean {

    private static final long serialVersionUID = -8851012611715817838L;

    public static DriverSupport toStackBean(WebInputDriverSupport webInputDriverSupport) {
        if (Objects.isNull(webInputDriverSupport)) {
            return null;
        }
        return new DriverSupport(
                WebInputStringIdKey.toStackBean(webInputDriverSupport.getKey()),
                webInputDriverSupport.getLabel(),
                webInputDriverSupport.getDescription(),
                webInputDriverSupport.getExampleContent()
        );
    }

    @JSONField(name = "key")
    @Valid
    @NotNull
    private WebInputStringIdKey key;

    @JSONField(name = "label")
    @NotNull
    private String label;

    @JSONField(name = "description")
    private String description;

    @JSONField(name = "example_content")
    private String exampleContent;

    public WebInputDriverSupport() {
    }

    public WebInputDriverSupport(WebInputStringIdKey key, String label, String description, String exampleContent) {
        this.key = key;
        this.label = label;
        this.description = description;
        this.exampleContent = exampleContent;
    }

    public WebInputStringIdKey getKey() {
        return key;
    }

    public void setKey(WebInputStringIdKey key) {
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
        return "WebInputDriverSupport{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", exampleContent='" + exampleContent + '\'' +
                '}';
    }
}
