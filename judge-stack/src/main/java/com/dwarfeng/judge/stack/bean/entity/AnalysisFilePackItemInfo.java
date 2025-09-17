package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果文件包条目信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisFilePackItemInfo implements Entity<LongIdKey> {

    private static final long serialVersionUID = -5651757810806179730L;

    private LongIdKey key;
    private LongIdKey packKey;
    private int index;
    private String originName;
    private Long length;
    private String remark;

    public AnalysisFilePackItemInfo() {
    }

    public AnalysisFilePackItemInfo(
            LongIdKey key, LongIdKey packKey, int index, String originName, Long length, String remark
    ) {
        this.key = key;
        this.packKey = packKey;
        this.index = index;
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

    public LongIdKey getPackKey() {
        return packKey;
    }

    public void setPackKey(LongIdKey packKey) {
        this.packKey = packKey;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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
        return "AnalysisFilePackItemInfo{" +
                "key=" + key +
                ", packKey=" + packKey +
                ", index=" + index +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", remark='" + remark + '\'' +
                '}';
    }
}
