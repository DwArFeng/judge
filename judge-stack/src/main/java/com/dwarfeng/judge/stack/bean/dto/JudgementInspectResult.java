package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 判断结果查看结果。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class JudgementInspectResult implements Dto {

    private static final long serialVersionUID = 7997795822096273332L;

    /**
     * 判断值。
     *
     * <p>
     * 一个归一化的数值，取值范围为 [0.0, 1.0]。
     */
    private double value;

    /**
     * 判断消息。
     *
     * <p>
     * 一段描述性的文本，用于说明判断值的含义或解释判断值如何得出。
     */
    private String message;

    public JudgementInspectResult() {
    }

    public JudgementInspectResult(double value, String message) {
        this.value = value;
        this.message = message;
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

    @Override
    public String toString() {
        return "JudgementInspectResult{" +
                "value=" + value +
                ", message='" + message + '\'' +
                '}';
    }
}
