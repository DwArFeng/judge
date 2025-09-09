package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Arrays;

/**
 * 分析结果文件文件上传信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalysisFileFileUploadInfo implements Dto {

    private static final long serialVersionUID = 5428726356969599600L;

    private LongIdKey analysisFileKey;
    private String originName;
    private byte[] content;

    public AnalysisFileFileUploadInfo() {
    }

    public AnalysisFileFileUploadInfo(LongIdKey analysisFileKey, String originName, byte[] content) {
        this.analysisFileKey = analysisFileKey;
        this.originName = originName;
        this.content = content;
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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "AnalysisFileFileUploadInfo{" +
                "analysisFileKey=" + analysisFileKey +
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
