package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 判断器变量查看结果。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class JudgerVariableInspectResult implements Dto {

    private static final long serialVersionUID = 6390167393358641L;

    private String value;

    public JudgerVariableInspectResult() {
    }

    public JudgerVariableInspectResult(String value) {
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
        return "JudgerVariableInspectResult{" +
                "value='" + value + '\'' +
                '}';
    }
}
