package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果文件信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalysisFileInfo implements Entity<LongIdKey> {

    private static final long serialVersionUID = -8239694418384637255L;

    private LongIdKey key;
    private String originName;
    private Long length;
    private String remark;

    public AnalysisFileInfo() {
    }

    public AnalysisFileInfo(LongIdKey key, String originName, Long length, String remark) {
        this.key = key;
        this.originName = originName;
        this.length = length;
        this.remark = remark;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
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
        return "AnalysisFileInfo{" +
                "key=" + key +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", remark='" + remark + '\'' +
                '}';
    }
}
