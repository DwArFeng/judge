package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果缩略图文件下载信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalysisPictureThumbnailDownloadInfo implements Dto {

    private static final long serialVersionUID = 908863659938453437L;

    private LongIdKey analysisPictureInfoKey;

    public AnalysisPictureThumbnailDownloadInfo() {
    }

    public AnalysisPictureThumbnailDownloadInfo(LongIdKey analysisPictureInfoKey) {
        this.analysisPictureInfoKey = analysisPictureInfoKey;
    }

    public LongIdKey getAnalysisPictureInfoKey() {
        return analysisPictureInfoKey;
    }

    public void setAnalysisPictureInfoKey(LongIdKey analysisPictureInfoKey) {
        this.analysisPictureInfoKey = analysisPictureInfoKey;
    }

    @Override
    public String toString() {
        return "AnalysisPictureThumbnailDownloadInfo{" +
                "analysisPictureInfoKey=" + analysisPictureInfoKey +
                '}';
    }
}
