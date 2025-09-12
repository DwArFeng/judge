package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.AnalysisPicturePackItemInfo;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 分析结果图片包条目信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class FastJsonAnalysisPicturePackItemInfo implements Bean {

    private static final long serialVersionUID = 1376772740359343015L;

    public static FastJsonAnalysisPicturePackItemInfo of(AnalysisPicturePackItemInfo analysisPicturePackItemInfo) {
        if (Objects.isNull(analysisPicturePackItemInfo)) {
            return null;
        } else {
            return new FastJsonAnalysisPicturePackItemInfo(
                    FastJsonLongIdKey.of(analysisPicturePackItemInfo.getKey()),
                    FastJsonLongIdKey.of(analysisPicturePackItemInfo.getPackKey()),
                    analysisPicturePackItemInfo.getIndex(),
                    analysisPicturePackItemInfo.getOriginName(),
                    analysisPicturePackItemInfo.getLength(),
                    analysisPicturePackItemInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "pack_key", ordinal = 2)
    private FastJsonLongIdKey packKey;

    @JSONField(name = "index", ordinal = 3)
    private int index;

    @JSONField(name = "origin_name", ordinal = 4)
    private String originName;

    @JSONField(name = "length", ordinal = 5)
    private Long length;

    @JSONField(name = "remark", ordinal = 6)
    private String remark;

    public FastJsonAnalysisPicturePackItemInfo() {
    }

    public FastJsonAnalysisPicturePackItemInfo(
            FastJsonLongIdKey key, FastJsonLongIdKey packKey, int index, String originName, Long length, String remark
    ) {
        this.key = key;
        this.packKey = packKey;
        this.index = index;
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

    public FastJsonLongIdKey getPackKey() {
        return packKey;
    }

    public void setPackKey(FastJsonLongIdKey packKey) {
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
        return "FastJsonAnalysisPicturePackItemInfo{" +
                "key=" + key +
                ", packKey=" + packKey +
                ", index=" + index +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", remark='" + remark + '\'' +
                '}';
    }
}
