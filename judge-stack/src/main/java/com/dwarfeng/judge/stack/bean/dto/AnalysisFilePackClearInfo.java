package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果文件包清除信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisFilePackClearInfo implements Dto {

    private static final long serialVersionUID = -1784084156602562065L;

    private LongIdKey analysisFilePackKey;

    public AnalysisFilePackClearInfo() {
    }

    public AnalysisFilePackClearInfo(LongIdKey analysisFilePackKey) {
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
        return "AnalysisFilePackClearInfo{" +
                "analysisFilePackKey=" + analysisFilePackKey +
                '}';
    }
}
