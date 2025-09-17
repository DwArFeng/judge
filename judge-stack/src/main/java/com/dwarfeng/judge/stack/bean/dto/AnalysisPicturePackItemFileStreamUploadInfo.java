package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.io.InputStream;

/**
 * 分析结果图片包条目文件流上传信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisPicturePackItemFileStreamUploadInfo implements Dto {

    private static final long serialVersionUID = 6373307939803530619L;

    private LongIdKey analysisPicturePackKey;
    private String originName;
    private long length;
    private InputStream content;

    public AnalysisPicturePackItemFileStreamUploadInfo() {
    }

    public AnalysisPicturePackItemFileStreamUploadInfo(
            LongIdKey analysisPicturePackKey, String originName, long length, InputStream content
    ) {
        this.analysisPicturePackKey = analysisPicturePackKey;
        this.originName = originName;
        this.length = length;
        this.content = content;
    }

    public LongIdKey getAnalysisPicturePackKey() {
        return analysisPicturePackKey;
    }

    public void setAnalysisPicturePackKey(LongIdKey analysisPicturePackKey) {
        this.analysisPicturePackKey = analysisPicturePackKey;
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
        return "AnalysisPicturePackItemFileStreamUploadInfo{" +
                "analysisPicturePackKey=" + analysisPicturePackKey +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", content=" + content +
                '}';
    }
}
