package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果文件文件通过流上传信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisFileFileUploadByStreamInfo implements Dto {

    private static final long serialVersionUID = 6738318186514791015L;

    private LongIdKey analysisFileKey;
    private String originName;

    public AnalysisFileFileUploadByStreamInfo() {
    }

    public AnalysisFileFileUploadByStreamInfo(LongIdKey analysisFileKey, String originName) {
        this.analysisFileKey = analysisFileKey;
        this.originName = originName;
    }

    public LongIdKey getAnalysisFileKey() {
        return analysisFileKey;
    }

    public void setAnalysisFileKey(LongIdKey analysisFileKey) {
        this.analysisFileKey = analysisFileKey;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    @Override
    public String toString() {
        return "AnalysisFileFileUploadByStreamInfo{" +
                "analysisFileKey=" + analysisFileKey +
                ", originName='" + originName + '\'' +
                '}';
    }
}
