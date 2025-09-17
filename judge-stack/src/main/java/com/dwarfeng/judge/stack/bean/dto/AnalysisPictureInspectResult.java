package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果图片查看结果。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisPictureInspectResult implements Dto {

    private static final long serialVersionUID = 8432846611587074455L;

    private LongIdKey analysisPictureInfoKey;
    private String originName;
    private Long length;
    private String remark;

    public AnalysisPictureInspectResult() {
    }

    public AnalysisPictureInspectResult(
            LongIdKey analysisPictureInfoKey, String originName, Long length, String remark
    ) {
        this.analysisPictureInfoKey = analysisPictureInfoKey;
        this.originName = originName;
        this.length = length;
        this.remark = remark;
    }

    public LongIdKey getAnalysisPictureInfoKey() {
        return analysisPictureInfoKey;
    }

    public void setAnalysisPictureInfoKey(LongIdKey analysisPictureInfoKey) {
        this.analysisPictureInfoKey = analysisPictureInfoKey;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public Long getLength() {
        return length;
    }

    public void setLength(Long length) {
        this.length = length;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "AnalysisPictureInspectResult{" +
                "analysisPictureInfoKey=" + analysisPictureInfoKey +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", remark='" + remark + '\'' +
                '}';
    }
}
