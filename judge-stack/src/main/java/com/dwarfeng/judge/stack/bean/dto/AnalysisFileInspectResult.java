package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果文件查看结果。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalysisFileInspectResult implements Dto {

    private static final long serialVersionUID = 4736746816343786758L;

    private LongIdKey analysisFileInfoKey;
    private String originName;
    private Long length;
    private String remark;

    public AnalysisFileInspectResult() {
    }

    public AnalysisFileInspectResult(LongIdKey analysisFileInfoKey, String originName, Long length, String remark) {
        this.analysisFileInfoKey = analysisFileInfoKey;
        this.originName = originName;
        this.length = length;
        this.remark = remark;
    }

    public LongIdKey getAnalysisFileInfoKey() {
        return analysisFileInfoKey;
    }

    public void setAnalysisFileInfoKey(LongIdKey analysisFileInfoKey) {
        this.analysisFileInfoKey = analysisFileInfoKey;
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
        return "AnalysisFileInspectResult{" +
                "analysisFileInfoKey=" + analysisFileInfoKey +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", remark='" + remark + '\'' +
                '}';
    }
}
