package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 下沉器元数据查询信息。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class SinkerMetaInspectInfo implements Dto {

    private static final long serialVersionUID = 4916815503927873813L;

    private LongIdKey sectionKey;
    private LongIdKey sinkerInfoKey;

    public SinkerMetaInspectInfo() {
    }

    public SinkerMetaInspectInfo(LongIdKey sectionKey, LongIdKey sinkerInfoKey) {
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
        return "SinkerMetaInspectInfo{" +
                "sectionKey=" + sectionKey +
                ", sinkerInfoKey=" + sinkerInfoKey +
                '}';
    }
}
