package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果文件文件流下载信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisFileFileStreamDownloadInfo implements Dto {

    private static final long serialVersionUID = -5872405543706485147L;

    private LongIdKey analysisFileInfoKey;

    public AnalysisFileFileStreamDownloadInfo() {
    }

    public AnalysisFileFileStreamDownloadInfo(LongIdKey analysisFileInfoKey) {
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
        return "AnalysisFileFileStreamDownloadInfo{" +
                "analysisFileInfoKey=" + analysisFileInfoKey +
                '}';
    }
}
