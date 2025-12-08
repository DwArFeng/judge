package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果图片包条目文件上传结果。
 *
 * @author DwArFeng
 * @since 2.3.1
 */
public class AnalysisPicturePackItemFileUploadResult implements Dto {

    private static final long serialVersionUID = -8618139436963756280L;

    private LongIdKey analysisPicturePackItemKey;

    public AnalysisPicturePackItemFileUploadResult() {
    }

    public AnalysisPicturePackItemFileUploadResult(LongIdKey analysisPicturePackItemKey) {
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
        return "AnalysisPicturePackItemFileUploadResult{" +
                "analysisPicturePackItemKey=" + analysisPicturePackItemKey +
                '}';
    }
}
