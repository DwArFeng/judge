package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析器变量查看信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalyserVariableInspectInfo implements Dto {

    private static final long serialVersionUID = -8197120656819755620L;

    private LongIdKey analyserInfoKey;
    private String analyserVariableId;

    public AnalyserVariableInspectInfo() {
    }

    public AnalyserVariableInspectInfo(LongIdKey analyserInfoKey, String analyserVariableId) {
        this.analyserInfoKey = analyserInfoKey;
        this.analyserVariableId = analyserVariableId;
    }

    public LongIdKey getAnalyserInfoKey() {
        return analyserInfoKey;
    }

    public void setAnalyserInfoKey(LongIdKey analyserInfoKey) {
        this.analyserInfoKey = analyserInfoKey;
    }

    public String getAnalyserVariableId() {
        return analyserVariableId;
    }

    public void setAnalyserVariableId(String analyserVariableId) {
        this.analyserVariableId = analyserVariableId;
    }

    @Override
    public String toString() {
        return "AnalyserVariableInspectInfo{" +
                "analyserInfoKey=" + analyserInfoKey +
                ", analyserVariableId='" + analyserVariableId + '\'' +
                '}';
    }
}
