package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 分析器变量查看结果。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalyserVariableInspectResult implements Dto {

    private static final long serialVersionUID = 4364000986525768008L;

    private String value;

    public AnalyserVariableInspectResult() {
    }

    public AnalyserVariableInspectResult(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AnalyserVariableInspectResult{" +
                "value='" + value + '\'' +
                '}';
    }
}
