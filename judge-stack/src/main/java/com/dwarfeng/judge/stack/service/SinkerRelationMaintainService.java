package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.SinkerRelation;
import com.dwarfeng.judge.stack.bean.key.SinkerRelationKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 下沉器关联信息维护服务。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public interface SinkerRelationMaintainService extends BatchCrudService<SinkerRelationKey, SinkerRelation>,
        EntireLookupService<SinkerRelation>, PresetLookupService<SinkerRelation> {

    String CHILD_FOR_SECTION = "child_for_section";
    String CHILD_FOR_SINKER_INFO = "child_for_sinker_info";
    String CHILD_FOR_SECTION_ENABLED = "child_for_section_enabled";
    String CHILD_FOR_SECTION_ENABLED_SECTION_ENABLED = "child_for_section_enabled_section_enabled";
    String CHILD_FOR_SINKER_INFO_ENABLED_SECTION_ENABLED = "child_for_sinker_info_enabled_section_enabled";
}
