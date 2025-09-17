package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Arrays;

/**
 * 分析结果图片文件上传信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisPictureFileUploadInfo implements Dto {

    private static final long serialVersionUID = -3988808378184192772L;

    private LongIdKey analysisPictureKey;
    private String originName;
    private byte[] content;

    public AnalysisPictureFileUploadInfo() {
    }

    public AnalysisPictureFileUploadInfo(LongIdKey analysisPictureKey, String originName, byte[] content) {
        this.analysisPictureKey = analysisPictureKey;
        this.originName = originName;
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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "AnalysisPictureFileUploadInfo{" +
                "analysisPictureKey=" + analysisPictureKey +
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
