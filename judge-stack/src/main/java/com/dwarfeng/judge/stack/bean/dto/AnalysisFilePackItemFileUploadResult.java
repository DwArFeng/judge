package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果文件包条目文件上传结果。
 *
 * @author DwArFeng
 * @since 2.3.1
 */
public class AnalysisFilePackItemFileUploadResult implements Dto {

    private static final long serialVersionUID = 6547153216687531491L;

    private LongIdKey analysisFilePackItemKey;

    public AnalysisFilePackItemFileUploadResult() {
    }

    public AnalysisFilePackItemFileUploadResult(LongIdKey analysisFilePackItemKey) {
        this.analysisFilePackItemKey = analysisFilePackItemKey;
    }

    public LongIdKey getAnalysisFilePackItemKey() {
        return analysisFilePackItemKey;
    }

    public void setAnalysisFilePackItemKey(LongIdKey analysisFilePackItemKey) {
        this.analysisFilePackItemKey = analysisFilePackItemKey;
    }

    @Override
    public String toString() {
        return "AnalysisFilePackItemFileUploadResult{" +
                "analysisFilePackItemKey=" + analysisFilePackItemKey +
                '}';
    }
}
