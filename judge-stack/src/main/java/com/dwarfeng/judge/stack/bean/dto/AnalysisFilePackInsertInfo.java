package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果文件包插入信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalysisFilePackInsertInfo implements Dto {

    private static final long serialVersionUID = -238264303922269048L;

    private LongIdKey analysisFilePackKey;

    public AnalysisFilePackInsertInfo() {
    }

    public AnalysisFilePackInsertInfo(LongIdKey analysisFilePackKey) {
        this.analysisFilePackKey = analysisFilePackKey;
    }

    public LongIdKey getAnalysisFilePackKey() {
        return analysisFilePackKey;
    }

    public void setAnalysisFilePackKey(LongIdKey analysisFilePackKey) {
        this.analysisFilePackKey = analysisFilePackKey;
    }

    @Override
    public String toString() {
        return "AnalysisFilePackInsertInfo{" +
                "analysisFilePackKey=" + analysisFilePackKey +
                '}';
    }
}
