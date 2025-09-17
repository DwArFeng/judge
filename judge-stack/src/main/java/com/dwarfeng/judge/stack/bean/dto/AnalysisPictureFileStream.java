package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.io.InputStream;

/**
 * 分析图片文件流。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisPictureFileStream implements Dto {

    private static final long serialVersionUID = 9025561580787572942L;

    private String originName;
    private long length;
    private InputStream content;

    public AnalysisPictureFileStream() {
    }

    public AnalysisPictureFileStream(String originName, long length, InputStream content) {
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
        return "AnalysisPictureFileStream{" +
                "originName='" + originName + '\'' +
                ", length=" + length +
                ", content=" + content +
                '}';
    }
}
