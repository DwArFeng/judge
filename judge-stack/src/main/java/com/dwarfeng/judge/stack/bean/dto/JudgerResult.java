package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 判断器报告。
 *
 * @author DwArFeng
 * @since 1.3.0
 */
public class JudgerResult implements Dto {

    private static final long serialVersionUID = 7553856383295295478L;

    private double value;
    private String message;
    private String contextData;

    public JudgerResult() {
    }

    public JudgerResult(double value, String message, String contextData) {
        this.value = value;
        this.message = message;
        this.contextData = contextData;
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

    @Override
    public String toString() {
        return "JudgerResult{" +
                "value=" + value +
                ", message='" + message + '\'' +
                ", contextData='" + contextData + '\'' +
                '}';
    }
}
