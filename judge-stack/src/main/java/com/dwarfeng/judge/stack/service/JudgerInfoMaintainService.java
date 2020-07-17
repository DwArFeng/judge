package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 驱动器信息维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface JudgerInfoMaintainService extends BatchCrudService<LongIdKey, JudgerInfo>, EntireLookupService<JudgerInfo>,
        PresetLookupService<JudgerInfo> {

    String CHILD_FOR_SECTION = "child_for_section";
    String CHILD_FOR_SECTION_SET = "child_for_section_set";
    String ENABLED_CHILD_FOR_SECTION = "enabled_child_for_section";
}
