package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 分析器变量查看结果。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalyserVariableInspectResult implements Dto {

    private static final long serialVersionUID = -2351423449494442694L;

    /**
     * 变量类型。
     *
     * <p>
     * int 枚举，可能的状态为：
     * <ol>
     *     <li>文本</li>
     *     <li>整数</li>
     *     <li>浮点数</li>
     *     <li>布尔值</li>
     *     <li>日期值</li>
     * </ol>
     * 详细值参考 sdk 模块的常量工具类。
     */
    private int valueType;

    private Object value;

    public AnalyserVariableInspectResult() {
    }

    public AnalyserVariableInspectResult(int valueType, Object value) {
        this.valueType = valueType;
        this.value = value;
    }

    public int getValueType() {
        return valueType;
    }

    public void setValueType(int valueType) {
        this.valueType = valueType;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AnalyserVariableInspectResult{" +
                "valueType=" + valueType +
                ", value=" + value +
                '}';
    }
}
