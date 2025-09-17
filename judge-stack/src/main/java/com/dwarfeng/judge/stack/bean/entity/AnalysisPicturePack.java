package com.dwarfeng.judge.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果图片包。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisPicturePack implements Entity<LongIdKey> {

    private static final long serialVersionUID = 6323817204398029175L;

    private LongIdKey key;

    /**
     * 条目锚点索引。
     *
     * <p>
     * 用来指示图片包所属条目的最大索引，用于提高图片插入的速度。<br>
     * 可以用 <code>itemAnchorIndex + 1</code> 的值作为新条目的索引值。
     */
    private int itemAnchorIndex;

    private String remark;

    public AnalysisPicturePack() {
    }

    public AnalysisPicturePack(LongIdKey key, int itemAnchorIndex, String remark) {
        this.key = key;
        this.itemAnchorIndex = itemAnchorIndex;
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
        return "AnalysisPicturePack{" +
                "key=" + key +
                ", itemAnchorIndex=" + itemAnchorIndex +
                ", remark='" + remark + '\'' +
                '}';
    }
}
