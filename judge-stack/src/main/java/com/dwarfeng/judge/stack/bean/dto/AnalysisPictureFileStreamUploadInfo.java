package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.io.InputStream;

/**
 * 分析结果图片文件流上传信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisPictureFileStreamUploadInfo implements Dto {

    private static final long serialVersionUID = 2592102803573427888L;

    private LongIdKey analysisPictureKey;
    private String originName;
    private long length;
    private InputStream content;

    public AnalysisPictureFileStreamUploadInfo() {
    }

    public AnalysisPictureFileStreamUploadInfo(
            LongIdKey analysisPictureKey, String originName, long length, InputStream content
    ) {
        this.analysisPictureKey = analysisPictureKey;
        this.originName = originName;
        this.length = length;
        this.content = content;
    }

    public LongIdKey getAnalysisPictureKey() {
        return analysisPictureKey;
    }

    public void setAnalysisPictureKey(LongIdKey analysisPictureKey) {
        this.analysisPictureKey = analysisPictureKey;
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
        return "AnalysisPictureFileStreamUploadInfo{" +
                "analysisPictureKey=" + analysisPictureKey +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", content=" + content +
                '}';
    }
}
