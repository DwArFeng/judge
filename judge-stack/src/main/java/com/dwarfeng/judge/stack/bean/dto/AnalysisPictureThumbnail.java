package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 分析结果图片缩略图文件。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisPictureThumbnail implements Dto {

    private static final long serialVersionUID = -741327899488374530L;

    private String originName;
    private byte[] content;

    public AnalysisPictureThumbnail() {
    }

    public AnalysisPictureThumbnail(String originName, byte[] content) {
        this.originName = originName;
        this.content = content;
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
        return "AnalysisPictureThumbnail{" +
                "originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
