package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 下沉器变量查看结果。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerVariableInspectResult implements Dto {

    private static final long serialVersionUID = -4894839698344758218L;
    
    private String value;

    public SinkerVariableInspectResult() {
    }

    public SinkerVariableInspectResult(String value) {
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
        return "SinkerVariableInspectResult{" +
                "value='" + value + '\'' +
                '}';
    }
}
