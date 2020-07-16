package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.JudgerReport;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 判断器报告。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
public class JSFixedFastJsonJudgerReport implements Bean {

    private static final long serialVersionUID = -6740822342067289751L;

    public static JSFixedFastJsonJudgerReport of(JudgerReport judgerReport) {
        if (Objects.isNull(judgerReport)) {
            return null;
        }
        return new JSFixedFastJsonJudgerReport(
                JSFixedFastJsonLongIdKey.of(judgerReport.getJudgerKey()),
                judgerReport.getValue(),
                judgerReport.getMessage(),
                judgerReport.getContextData(),
                judgerReport.getType(),
                judgerReport.getContent());
    }

    @JSONField(name = "judger_key", ordinal = 1)
    private JSFixedFastJsonLongIdKey judgerKey;

    @JSONField(name = "value", ordinal = 2)
    private double value;

    @JSONField(name = "message", ordinal = 3)
    private String message;

    @JSONField(name = "context_data", ordinal = 4)
    private String contextData;

    @JSONField(name = "type", ordinal = 5)
    private String type;

    @JSONField(name = "content", ordinal = 6)
    private String content;

    public JSFixedFastJsonJudgerReport() {
    }

    public JSFixedFastJsonJudgerReport(
            JSFixedFastJsonLongIdKey judgerKey, double value, String message, String contextData, String type,
            String content) {
        this.judgerKey = judgerKey;
        this.value = value;
        this.message = message;
        this.contextData = contextData;
        this.type = type;
        this.content = content;
    }

    public JSFixedFastJsonLongIdKey getJudgerKey() {
        return judgerKey;
    }

    public void setJudgerKey(JSFixedFastJsonLongIdKey judgerKey) {
        this.judgerKey = judgerKey;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContextData() {
        return contextData;
    }

    public void setContextData(String contextData) {
        this.contextData = contextData;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonJudgerReport{" +
                "judgerKey=" + judgerKey +
                ", value=" + value +
                ", message='" + message + '\'' +
                ", contextData='" + contextData + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
