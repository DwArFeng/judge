package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果文件文件上传结果。
 *
 * @author DwArFeng
 * @since 2.3.1
 */
public class AnalysisFileFileUploadResult implements Dto {

    private static final long serialVersionUID = 3412466529636958933L;

    private LongIdKey analysisFileKey;

    public AnalysisFileFileUploadResult() {
    }

    public AnalysisFileFileUploadResult(LongIdKey analysisFileKey) {
        this.analysisFileKey = analysisFileKey;
    }

    public LongIdKey getAnalysisFileKey() {
        return analysisFileKey;
    }

    public void setAnalysisFileKey(LongIdKey analysisFileKey) {
        this.analysisFileKey = analysisFileKey;
    }

    @Override
    public String toString() {
        return "AnalysisFileFileUploadResult{" +
                "analysisFileKey=" + analysisFileKey +
                '}';
    }
}
