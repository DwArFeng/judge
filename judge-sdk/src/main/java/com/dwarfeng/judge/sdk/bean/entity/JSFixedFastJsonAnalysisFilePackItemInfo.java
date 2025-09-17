package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.AnalysisFilePackItemInfo;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 分析结果文件包条目信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class JSFixedFastJsonAnalysisFilePackItemInfo implements Bean {

    private static final long serialVersionUID = 428159201782588008L;

    public static JSFixedFastJsonAnalysisFilePackItemInfo of(AnalysisFilePackItemInfo analysisFilePackItemInfo) {
        if (Objects.isNull(analysisFilePackItemInfo)) {
            return null;
        } else {
            return new JSFixedFastJsonAnalysisFilePackItemInfo(
                    JSFixedFastJsonLongIdKey.of(analysisFilePackItemInfo.getKey()),
                    JSFixedFastJsonLongIdKey.of(analysisFilePackItemInfo.getPackKey()),
                    analysisFilePackItemInfo.getIndex(),
                    analysisFilePackItemInfo.getOriginName(),
                    analysisFilePackItemInfo.getLength(),
                    analysisFilePackItemInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "pack_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey packKey;

    @JSONField(name = "index", ordinal = 3)
    private int index;

    @JSONField(name = "origin_name", ordinal = 4)
    private String originName;

    @JSONField(name = "length", ordinal = 5)
    private Long length;

    @JSONField(name = "remark", ordinal = 6)
    private String remark;

    public JSFixedFastJsonAnalysisFilePackItemInfo() {
    }

    public JSFixedFastJsonAnalysisFilePackItemInfo(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey packKey, int index, String originName, Long length,
            String remark
    ) {
        this.key = key;
        this.packKey = packKey;
        this.index = index;
        this.originName = originName;
        this.length = length;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getPackKey() {
        return packKey;
    }

    public void setPackKey(JSFixedFastJsonLongIdKey packKey) {
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
        return "JSFixedFastJsonAnalysisFilePackItemInfo{" +
                "key=" + key +
                ", packKey=" + packKey +
                ", index=" + index +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", remark='" + remark + '\'' +
                '}';
    }
}
