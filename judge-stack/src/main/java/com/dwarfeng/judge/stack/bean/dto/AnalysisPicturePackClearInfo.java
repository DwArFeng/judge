package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果文件包清除信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisPicturePackClearInfo implements Dto {

    private static final long serialVersionUID = 4521631512681520500L;

    private LongIdKey analysisPicturePackKey;

    public AnalysisPicturePackClearInfo() {
    }

    public AnalysisPicturePackClearInfo(LongIdKey analysisPicturePackKey) {
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
        return "AnalysisPicturePackClearInfo{" +
                "analysisPicturePackKey=" + analysisPicturePackKey +
                '}';
    }
}
