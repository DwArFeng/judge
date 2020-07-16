package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 判断器报告。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class JudgerReport implements Dto {

    private static final long serialVersionUID = 4475709619276994000L;

    private LongIdKey judgerKey;
    private double value;
    private String message;
    private String contextData;
    private String type;
    private String content;

    public JudgerReport() {
    }

    public JudgerReport(
            LongIdKey judgerKey, double value, String message, String contextData, String type, String content) {
        this.judgerKey = judgerKey;
        this.value = value;
        this.message = message;
        this.contextData = contextData;
        this.type = type;
        this.content = content;
    }

    public LongIdKey getJudgerKey() {
        return judgerKey;
    }

    public void setJudgerKey(LongIdKey judgerKey) {
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
        return "JudgerReport{" +
                "judgerKey=" + judgerKey +
                ", value=" + value +
                ", message='" + message + '\'' +
                ", contextData='" + contextData + '\'' +
                ", type='" + type + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
