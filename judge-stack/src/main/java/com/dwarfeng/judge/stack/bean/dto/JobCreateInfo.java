package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 作业创建信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class JobCreateInfo implements Dto {

    private static final long serialVersionUID = 3326046942703751777L;

    private LongIdKey sectionKey;

    public JobCreateInfo() {
    }

    public JobCreateInfo(LongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    public LongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(LongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    @Override
    public String toString() {
        return "JobCreateInfo{" +
                "sectionKey=" + sectionKey +
                '}';
    }
}
