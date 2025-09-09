package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Arrays;

/**
 * 分析结果图片包条目文件上传信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalysisPicturePackItemFileUploadInfo implements Dto {

    private static final long serialVersionUID = -6194816640296743476L;

    private LongIdKey analysisPicturePackKey;
    private String originName;
    private byte[] content;

    public AnalysisPicturePackItemFileUploadInfo() {
    }

    public AnalysisPicturePackItemFileUploadInfo(LongIdKey analysisPicturePackKey, String originName, byte[] content) {
        this.analysisPicturePackKey = analysisPicturePackKey;
        this.originName = originName;
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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "AnalysisPicturePackItemFileUploadInfo{" +
                "analysisPicturePackKey=" + analysisPicturePackKey +
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
