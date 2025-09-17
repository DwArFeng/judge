package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果文件包条目文件下载信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisFilePackItemFileDownloadInfo implements Dto {

    private static final long serialVersionUID = -6098485455351293665L;

    private LongIdKey analysisFilePackItemKey;

    public AnalysisFilePackItemFileDownloadInfo() {
    }

    public AnalysisFilePackItemFileDownloadInfo(LongIdKey analysisFilePackItemKey) {
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
        return "AnalysisFilePackItemFileDownloadInfo{" +
                "analysisFilePackItemKey=" + analysisFilePackItemKey +
                '}';
    }
}
