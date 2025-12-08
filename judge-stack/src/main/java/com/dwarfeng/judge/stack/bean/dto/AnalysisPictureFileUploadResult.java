package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果图片文件上传结果。
 *
 * @author DwArFeng
 * @since 2.3.1
 */
public class AnalysisPictureFileUploadResult implements Dto {

    private static final long serialVersionUID = -4035516800653424533L;

    private LongIdKey analysisPictureKey;

    public AnalysisPictureFileUploadResult() {
    }

    public AnalysisPictureFileUploadResult(LongIdKey analysisPictureKey) {
        this.analysisPictureKey = analysisPictureKey;
    }

    public LongIdKey getAnalysisPictureKey() {
        return analysisPictureKey;
    }

    public void setAnalysisPictureKey(LongIdKey analysisPictureKey) {
        this.analysisPictureKey = analysisPictureKey;
    }

    @Override
    public String toString() {
        return "AnalysisPictureFileUploadResult{" +
                "analysisPictureKey=" + analysisPictureKey +
                '}';
    }
}
