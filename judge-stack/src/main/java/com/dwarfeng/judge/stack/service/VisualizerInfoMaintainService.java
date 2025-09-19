package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.VisualizerInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 可视化器信息维护服务。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public interface VisualizerInfoMaintainService extends BatchCrudService<LongIdKey, VisualizerInfo>,
        EntireLookupService<VisualizerInfo>, PresetLookupService<VisualizerInfo> {

    String CHILD_FOR_SECTION = "child_for_section";
    String CHILD_FOR_SECTION_INDEX_ASC = "child_for_section_index_asc";
    String CHILD_FOR_SECTION_ENABLED = "child_for_section_enabled";
    String CHILD_FOR_SECTION_ENABLED_INDEX_ASC = "child_for_section_enabled_index_asc";
}
