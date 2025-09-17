package com.dwarfeng.judge.stack.struct;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 消费信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public final class ConsumeInfo {

    private final LongIdKey sectionKey;

    public ConsumeInfo(LongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    public LongIdKey getSectionKey() {
        return sectionKey;
    }

    @Override
    public String toString() {
        return "ConsumeInfo{" +
                "sectionKey=" + sectionKey +
                '}';
    }
}
