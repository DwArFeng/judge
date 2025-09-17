package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果图片文件流下载信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisPictureFileStreamDownloadInfo implements Dto {

    private static final long serialVersionUID = 5237644990192320010L;

    private LongIdKey analysisPictureInfoKey;

    public AnalysisPictureFileStreamDownloadInfo() {
    }

    public AnalysisPictureFileStreamDownloadInfo(LongIdKey analysisPictureInfoKey) {
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
        return "AnalysisPictureFileStreamDownloadInfo{" +
                "analysisPictureInfoKey=" + analysisPictureInfoKey +
                '}';
    }
}
