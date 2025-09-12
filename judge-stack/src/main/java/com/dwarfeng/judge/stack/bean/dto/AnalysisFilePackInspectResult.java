package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.List;

/**
 * 分析结果文件包查看结果。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalysisFilePackInspectResult implements Dto {

    private static final long serialVersionUID = 817713166450858936L;

    private LongIdKey analysisFilePackKey;

    /**
     * 条目锚点索引。
     *
     * <p>
     * 用来指示文件包所属条目的最大索引。
     */
    private int itemAnchorIndex;

    private String remark;
    private List<Item> items;

    public AnalysisFilePackInspectResult() {
    }

    public AnalysisFilePackInspectResult(
            LongIdKey analysisFilePackKey, int itemAnchorIndex, String remark, List<Item> items
    ) {
        this.analysisFilePackKey = analysisFilePackKey;
        this.itemAnchorIndex = itemAnchorIndex;
        this.remark = remark;
        this.items = items;
    }

    public LongIdKey getAnalysisFilePackKey() {
        return analysisFilePackKey;
    }

    public void setAnalysisFilePackKey(LongIdKey analysisFilePackKey) {
        this.analysisFilePackKey = analysisFilePackKey;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "AnalysisFilePackInspectResult{" +
                "analysisFilePackKey=" + analysisFilePackKey +
                ", itemAnchorIndex=" + itemAnchorIndex +
                ", remark='" + remark + '\'' +
                ", items=" + items +
                '}';
    }

    public static class Item implements Dto {

        private static final long serialVersionUID = -1123218674395963296L;

        private LongIdKey analysisFilePackItemInfoKey;
        private int index;
        private String originName;
        private Long length;
        private String remark;

        public Item() {
        }

        public Item(LongIdKey analysisFilePackItemInfoKey, int index, String originName, Long length, String remark) {
            this.analysisFilePackItemInfoKey = analysisFilePackItemInfoKey;
            this.index = index;
            this.originName = originName;
            this.length = length;
            this.remark = remark;
        }

        public LongIdKey getAnalysisFilePackItemInfoKey() {
            return analysisFilePackItemInfoKey;
        }

        public void setAnalysisFilePackItemInfoKey(LongIdKey analysisFilePackItemInfoKey) {
            this.analysisFilePackItemInfoKey = analysisFilePackItemInfoKey;
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
            return "Item{" +
                    "analysisFilePackItemInfoKey=" + analysisFilePackItemInfoKey +
                    ", index=" + index +
                    ", originName='" + originName + '\'' +
                    ", length=" + length +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }
}
