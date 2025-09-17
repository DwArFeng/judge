package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析器变量删除信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalyserVariableRemoveInfo implements Dto {

    private static final long serialVersionUID = 3678498577120904855L;

    private LongIdKey analyserInfoKey;
    private String analyserVariableId;

    public AnalyserVariableRemoveInfo() {
    }

    public AnalyserVariableRemoveInfo(LongIdKey analyserInfoKey, String analyserVariableId) {
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
        return "AnalyserVariableRemoveInfo{" +
                "analyserInfoKey=" + analyserInfoKey +
                ", analyserVariableId='" + analyserVariableId + '\'' +
                '}';
    }
}
