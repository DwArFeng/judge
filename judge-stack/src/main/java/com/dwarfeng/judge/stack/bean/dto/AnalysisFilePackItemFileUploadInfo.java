package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Arrays;

/**
 * 分析结果文件包文件上传信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisFilePackItemFileUploadInfo implements Dto {

    private static final long serialVersionUID = 737811195026722899L;

    private LongIdKey analysisFilePackKey;
    private String originName;
    private byte[] content;

    public AnalysisFilePackItemFileUploadInfo() {
    }

    public AnalysisFilePackItemFileUploadInfo(LongIdKey analysisFilePackKey, String originName, byte[] content) {
        this.analysisFilePackKey = analysisFilePackKey;
        this.originName = originName;
        this.content = content;
    }

    public LongIdKey getAnalysisFilePackKey() {
        return analysisFilePackKey;
    }

    public void setAnalysisFilePackKey(LongIdKey analysisFilePackKey) {
        this.analysisFilePackKey = analysisFilePackKey;
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
        return "AnalysisFilePackItemFileUploadInfo{" +
                "analysisFilePackKey=" + analysisFilePackKey +
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
