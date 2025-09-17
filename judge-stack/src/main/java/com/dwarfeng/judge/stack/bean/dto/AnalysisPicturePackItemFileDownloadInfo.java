package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果图片包条目文件下载信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisPicturePackItemFileDownloadInfo implements Dto {

    private static final long serialVersionUID = -2407961232972780970L;

    private LongIdKey analysisPicturePackItemKey;

    public AnalysisPicturePackItemFileDownloadInfo() {
    }

    public AnalysisPicturePackItemFileDownloadInfo(LongIdKey analysisPicturePackItemKey) {
        this.analysisPicturePackItemKey = analysisPicturePackItemKey;
    }

    public LongIdKey getAnalysisPicturePackItemKey() {
        return analysisPicturePackItemKey;
    }

    public void setAnalysisPicturePackItemKey(LongIdKey analysisPicturePackItemKey) {
        this.analysisPicturePackItemKey = analysisPicturePackItemKey;
    }

    @Override
    public String toString() {
        return "AnalysisPicturePackItemFileDownloadInfo{" +
                "analysisPicturePackItemKey=" + analysisPicturePackItemKey +
                '}';
    }
}
