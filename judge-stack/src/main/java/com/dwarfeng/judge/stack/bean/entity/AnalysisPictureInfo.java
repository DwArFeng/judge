package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果图片信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalysisPictureInfo implements Entity<LongIdKey> {

    private static final long serialVersionUID = -1605708653071169566L;

    private LongIdKey key;
    private String originName;
    private Long length;
    private String remark;

    public AnalysisPictureInfo() {
    }

    public AnalysisPictureInfo(LongIdKey key, String originName, Long length, String remark) {
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
        return "AnalysisPictureInfo{" +
                "key=" + key +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", remark='" + remark + '\'' +
                '}';
    }
}
