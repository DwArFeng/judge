package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.io.InputStream;

/**
 * 分析结果文件文件流上传信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalysisFileFileStreamUploadInfo implements Dto {

    private static final long serialVersionUID = 3403008622294577448L;

    private LongIdKey analysisFileKey;
    private String originName;
    private long length;
    private InputStream content;

    public AnalysisFileFileStreamUploadInfo() {
    }

    public AnalysisFileFileStreamUploadInfo(
            LongIdKey analysisFileKey, String originName, long length, InputStream content
    ) {
        this.analysisFileKey = analysisFileKey;
        this.originName = originName;
        this.length = length;
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
        return "AnalysisFileFileStreamUploadInfo{" +
                "analysisFileKey=" + analysisFileKey +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", content=" + content +
                '}';
    }
}
