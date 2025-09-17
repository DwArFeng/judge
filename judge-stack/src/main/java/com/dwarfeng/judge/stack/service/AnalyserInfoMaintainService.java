package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.AnalyserInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 分析器信息维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface AnalyserInfoMaintainService extends BatchCrudService<LongIdKey, AnalyserInfo>,
        EntireLookupService<AnalyserInfo>, PresetLookupService<AnalyserInfo> {

    String CHILD_FOR_SECTION = "child_for_section";
    String CHILD_FOR_SECTION_INDEX_ASC = "child_for_section_index_asc";
    String CHILD_FOR_SECTION_ENABLED = "child_for_section_enabled";
    String CHILD_FOR_SECTION_ENABLED_INDEX_ASC = "child_for_section_enabled_index_asc";
}
