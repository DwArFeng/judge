package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 下沉器元数据重置信息。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerMetaResetInfo implements Dto {

    private static final long serialVersionUID = -931492883042310020L;

    private LongIdKey sectionKey;
    private LongIdKey sinkerInfoKey;

    public SinkerMetaResetInfo() {
    }

    public SinkerMetaResetInfo(LongIdKey sectionKey, LongIdKey sinkerInfoKey) {
        this.sectionKey = sectionKey;
        this.sinkerInfoKey = sinkerInfoKey;
    }

    public LongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(LongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    public LongIdKey getSinkerInfoKey() {
        return sinkerInfoKey;
    }

    public void setSinkerInfoKey(LongIdKey sinkerInfoKey) {
        this.sinkerInfoKey = sinkerInfoKey;
    }

    @Override
    public String toString() {
        return "SinkerMetaResetInfo{" +
                "sectionKey=" + sectionKey +
                ", sinkerInfoKey=" + sinkerInfoKey +
                '}';
    }
}
