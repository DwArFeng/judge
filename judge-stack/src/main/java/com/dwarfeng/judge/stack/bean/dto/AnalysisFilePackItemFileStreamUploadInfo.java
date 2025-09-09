package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.io.InputStream;

/**
 * 分析结果文件包条目文件流上传信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalysisFilePackItemFileStreamUploadInfo implements Dto {

    private static final long serialVersionUID = 3465493348589357359L;

    private LongIdKey analysisFilePackKey;
    private String originName;
    private long length;
    private InputStream content;

    public AnalysisFilePackItemFileStreamUploadInfo() {
    }

    public AnalysisFilePackItemFileStreamUploadInfo(
            LongIdKey analysisFilePackKey, String originName, long length, InputStream content
    ) {
        this.analysisFilePackKey = analysisFilePackKey;
        this.originName = originName;
        this.length = length;
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

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public InputStream getContent() {
        return content;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "AnalysisFilePackItemFileStreamUploadInfo{" +
                "analysisFilePackKey=" + analysisFilePackKey +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", content=" + content +
                '}';
    }
}
