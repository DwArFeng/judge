package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 部件裁决结果按部件查看信息。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class VerdictInspectBySectionInfo implements Dto {

    private static final long serialVersionUID = -2095666228842559786L;

    private LongIdKey sectionKey;

    public VerdictInspectBySectionInfo() {
    }

    public VerdictInspectBySectionInfo(LongIdKey sectionKey) {
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
        return "VerdictInspectBySectionInfo{" +
                "sectionKey=" + sectionKey +
                '}';
    }
}
