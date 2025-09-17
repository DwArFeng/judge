package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果图片包插入信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisPicturePackInsertInfo implements Dto {

    private static final long serialVersionUID = -6157738713916492004L;

    private LongIdKey analysisPicturePackKey;

    public AnalysisPicturePackInsertInfo() {
    }

    public AnalysisPicturePackInsertInfo(LongIdKey analysisPicturePackKey) {
        this.analysisPicturePackKey = analysisPicturePackKey;
    }

    public LongIdKey getAnalysisPicturePackKey() {
        return analysisPicturePackKey;
    }

    public void setAnalysisPicturePackKey(LongIdKey analysisPicturePackKey) {
        this.analysisPicturePackKey = analysisPicturePackKey;
    }

    @Override
    public String toString() {
        return "AnalysisPicturePackInsertInfo{" +
                "analysisPicturePackKey=" + analysisPicturePackKey +
                '}';
    }
}
