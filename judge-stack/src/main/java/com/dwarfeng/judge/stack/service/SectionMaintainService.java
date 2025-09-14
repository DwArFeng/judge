package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 部件维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface SectionMaintainService extends BatchCrudService<LongIdKey, Section>, EntireLookupService<Section>,
        PresetLookupService<Section> {

    String NAME_LIKE = "name_like";
    String ENABLED = "enabled";
}
