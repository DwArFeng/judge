package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.AnalysisPicturePack;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * FastJson 分析结果图片包。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class FastJsonAnalysisPicturePack implements Bean {

    private static final long serialVersionUID = 2727578901137705900L;

    public static FastJsonAnalysisPicturePack of(AnalysisPicturePack analysisPicturePack) {
        if (Objects.isNull(analysisPicturePack)) {
            return null;
        } else {
            return new FastJsonAnalysisPicturePack(
                    FastJsonLongIdKey.of(analysisPicturePack.getKey()),
                    analysisPicturePack.getItemAnchorIndex(),
                    analysisPicturePack.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "item_anchor_index", ordinal = 2)
    private int itemAnchorIndex;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public FastJsonAnalysisPicturePack() {
    }

    public FastJsonAnalysisPicturePack(FastJsonLongIdKey key, int itemAnchorIndex, String remark) {
        this.key = key;
        this.itemAnchorIndex = itemAnchorIndex;
        this.remark = remark;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public int getItemAnchorIndex() {
        return itemAnchorIndex;
    }

    public void setItemAnchorIndex(int itemAnchorIndex) {
        this.itemAnchorIndex = itemAnchorIndex;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonAnalysisPicturePack{" +
                "key=" + key +
                ", itemAnchorIndex=" + itemAnchorIndex +
                ", remark='" + remark + '\'' +
                '}';
    }
}
