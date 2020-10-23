package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.JudgerSupport;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 判断器支持。
 *
 * @author DwArFeng
 * @since beta-1.1.0
 */
public class WebInputJudgerSupport implements Bean {

    private static final long serialVersionUID = -5482770036496022021L;

    public static JudgerSupport toStackBean(WebInputJudgerSupport webInputJudgerSupport) {
        if (Objects.isNull(webInputJudgerSupport)) {
            return null;
        }
        return new JudgerSupport(
                WebInputStringIdKey.toStackBean(webInputJudgerSupport.getKey()),
                webInputJudgerSupport.getLabel(),
                webInputJudgerSupport.getDescription(),
                webInputJudgerSupport.getExampleContent()
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

    public WebInputJudgerSupport() {
    }

    public WebInputJudgerSupport(WebInputStringIdKey key, String label, String description, String exampleContent) {
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
        return "WebInputJudgerSupport{" +
                "key=" + key +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", exampleContent='" + exampleContent + '\'' +
                '}';
    }
}
