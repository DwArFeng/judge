package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.AnalysisPictureInfo;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 分析结果图片信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class JSFixedFastJsonAnalysisPictureInfo implements Bean {

    private static final long serialVersionUID = -1997776755048686507L;

    public static JSFixedFastJsonAnalysisPictureInfo of(AnalysisPictureInfo analysisPictureInfo) {
        if (Objects.isNull(analysisPictureInfo)) {
            return null;
        } else {
            return new JSFixedFastJsonAnalysisPictureInfo(
                    JSFixedFastJsonLongIdKey.of(analysisPictureInfo.getKey()),
                    analysisPictureInfo.getOriginName(),
                    analysisPictureInfo.getLength(),
                    analysisPictureInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "origin_name", ordinal = 2)
    private String originName;

    @JSONField(name = "length", ordinal = 3)
    private Long length;

    @JSONField(name = "remark", ordinal = 4)
    private String remark;

    public JSFixedFastJsonAnalysisPictureInfo() {
    }

    public JSFixedFastJsonAnalysisPictureInfo(
            JSFixedFastJsonLongIdKey key, String originName, Long length, String remark
    ) {
        this.key = key;
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
        return "JSFixedFastJsonAnalysisPictureInfo{" +
                "key=" + key +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", remark='" + remark + '\'' +
                '}';
    }
}
