package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析器变量插入/更新信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalyserVariableUpsertInfo implements Dto {

    private static final long serialVersionUID = 3860480962691162631L;

    private LongIdKey analyserInfoKey;
    private String analyserVariableId;
    private String value;

    public AnalyserVariableUpsertInfo() {
    }

    public AnalyserVariableUpsertInfo(LongIdKey analyserInfoKey, String analyserVariableId, String value) {
        this.analyserInfoKey = analyserInfoKey;
        this.analyserVariableId = analyserVariableId;
        this.value = value;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AnalyserVariableUpsertInfo{" +
                "analyserInfoKey=" + analyserInfoKey +
                ", analyserVariableId='" + analyserVariableId + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
