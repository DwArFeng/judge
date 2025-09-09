package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果文件文件下载信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalysisFileFileDownloadInfo implements Dto {

    private static final long serialVersionUID = 680480587482454953L;

    private LongIdKey analysisFileInfoKey;

    public AnalysisFileFileDownloadInfo() {
    }

    public AnalysisFileFileDownloadInfo(LongIdKey analysisFileInfoKey) {
        this.analysisFileInfoKey = analysisFileInfoKey;
    }

    public LongIdKey getAnalysisFileInfoKey() {
        return analysisFileInfoKey;
    }

    public void setAnalysisFileInfoKey(LongIdKey analysisFileInfoKey) {
        this.analysisFileInfoKey = analysisFileInfoKey;
    }

    @Override
    public String toString() {
        return "AnalysisFileFileDownloadInfo{" +
                "analysisFileInfoKey=" + analysisFileInfoKey +
                '}';
    }
}
