package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.List;

/**
 * 分析结果图片包查看结果。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalysisPicturePackInspectResult implements Dto {

    private static final long serialVersionUID = 9072181165860532534L;

    private LongIdKey analysisPicturePackKey;

    /**
     * 条目锚点索引。
     *
     * <p>
     * 用来指示图片包所属条目的最大索引。
     */
    private int itemAnchorIndex;

    private String remark;
    private List<Item> items;

    public AnalysisPicturePackInspectResult() {
    }

    public AnalysisPicturePackInspectResult(
            LongIdKey analysisPicturePackKey, int itemAnchorIndex, String remark, List<Item> items
    ) {
        this.analysisPicturePackKey = analysisPicturePackKey;
        this.itemAnchorIndex = itemAnchorIndex;
        this.remark = remark;
        this.items = items;
    }

    public LongIdKey getAnalysisPicturePackKey() {
        return analysisPicturePackKey;
    }

    public void setAnalysisPicturePackKey(LongIdKey analysisPicturePackKey) {
        this.analysisPicturePackKey = analysisPicturePackKey;
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
        return "AnalysisPicturePackInspectResult{" +
                "analysisPicturePackKey=" + analysisPicturePackKey +
                ", itemAnchorIndex=" + itemAnchorIndex +
                ", remark='" + remark + '\'' +
                ", items=" + items +
                '}';
    }

    public static class Item implements Dto {

        private static final long serialVersionUID = 5125237218569464170L;

        private LongIdKey analysisPicturePackItemInfoKey;
        private int index;
        private String originName;
        private Long length;
        private String remark;

        public Item() {
        }

        public Item(
                LongIdKey analysisPicturePackItemInfoKey, int index, String originName, Long length, String remark
        ) {
            this.analysisPicturePackItemInfoKey = analysisPicturePackItemInfoKey;
            this.index = index;
            this.originName = originName;
            this.length = length;
            this.remark = remark;
        }

        public LongIdKey getAnalysisPicturePackItemInfoKey() {
            return analysisPicturePackItemInfoKey;
        }

        public void setAnalysisPicturePackItemInfoKey(LongIdKey analysisPicturePackItemInfoKey) {
            this.analysisPicturePackItemInfoKey = analysisPicturePackItemInfoKey;
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
                    "analysisPicturePackItemInfoKey=" + analysisPicturePackItemInfoKey +
                    ", index=" + index +
                    ", originName='" + originName + '\'' +
                    ", length=" + length +
                    ", remark='" + remark + '\'' +
                    '}';
        }
    }
}
