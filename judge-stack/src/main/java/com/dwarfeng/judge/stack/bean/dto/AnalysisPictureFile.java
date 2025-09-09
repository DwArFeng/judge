package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Arrays;

/**
 * 分析结果图片文件。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalysisPictureFile implements Dto {

    private static final long serialVersionUID = 8974377351168732006L;

    private String originName;
    private byte[] content;

    public AnalysisPictureFile() {
    }

    public AnalysisPictureFile(String originName, byte[] content) {
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
        return "AnalysisPictureFile{" +
                "originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
