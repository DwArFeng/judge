package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.AnalysisPictureInfo;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 分析结果图片信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class FastJsonAnalysisPictureInfo implements Bean {

    private static final long serialVersionUID = 4913476511672146933L;

    public static FastJsonAnalysisPictureInfo of(AnalysisPictureInfo analysisPictureInfo) {
        if (Objects.isNull(analysisPictureInfo)) {
            return null;
        } else {
            return new FastJsonAnalysisPictureInfo(
                    FastJsonLongIdKey.of(analysisPictureInfo.getKey()),
                    analysisPictureInfo.getOriginName(),
                    analysisPictureInfo.getLength(),
                    analysisPictureInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "origin_name", ordinal = 2)
    private String originName;

    @JSONField(name = "length", ordinal = 3)
    private Long length;

    @JSONField(name = "remark", ordinal = 4)
    private String remark;

    public FastJsonAnalysisPictureInfo() {
    }

    public FastJsonAnalysisPictureInfo(FastJsonLongIdKey key, String originName, Long length, String remark) {
        this.key = key;
        this.originName = originName;
        this.length = length;
        this.remark = remark;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
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
        return "FastJsonAnalysisPictureInfo{" +
                "key=" + key +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", remark='" + remark + '\'' +
                '}';
    }
}
