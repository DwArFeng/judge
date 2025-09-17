package com.dwarfeng.judge.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.entity.AnalysisFilePack;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;

import java.util.Objects;

/**
 * JSFixed FastJson 分析结果文件包。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class JSFixedFastJsonAnalysisFilePack implements Bean {

    private static final long serialVersionUID = 811860227713724021L;

    public static JSFixedFastJsonAnalysisFilePack of(AnalysisFilePack analysisFilePack) {
        if (Objects.isNull(analysisFilePack)) {
            return null;
        } else {
            return new JSFixedFastJsonAnalysisFilePack(
                    JSFixedFastJsonLongIdKey.of(analysisFilePack.getKey()),
                    analysisFilePack.getItemAnchorIndex(),
                    analysisFilePack.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "item_anchor_index", ordinal = 2)
    private int itemAnchorIndex;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public JSFixedFastJsonAnalysisFilePack() {
    }

    public JSFixedFastJsonAnalysisFilePack(JSFixedFastJsonLongIdKey key, int itemAnchorIndex, String remark) {
        this.key = key;
        this.itemAnchorIndex = itemAnchorIndex;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
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
        return "JSFixedFastJsonAnalysisFilePack{" +
                "key=" + key +
                ", itemAnchorIndex=" + itemAnchorIndex +
                ", remark='" + remark + '\'' +
                '}';
    }
}
