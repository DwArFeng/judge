package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.io.InputStream;

/**
 * 分析结果图片包条目文件流。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalysisPicturePackItemFileStream implements Dto {

    private static final long serialVersionUID = 1553414390112381554L;

    private String originName;
    private long length;
    private InputStream content;

    public AnalysisPicturePackItemFileStream() {
    }

    public AnalysisPicturePackItemFileStream(String originName, long length, InputStream content) {
        this.originName = originName;
        this.length = length;
        this.content = content;
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
        return "AnalysisPicturePackItemFileStream{" +
                "originName='" + originName + '\'' +
                ", length=" + length +
                ", content=" + content +
                '}';
    }
}
