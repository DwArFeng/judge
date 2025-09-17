package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 驱动器信息维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface DriverInfoMaintainService extends BatchCrudService<LongIdKey, DriverInfo>,
        EntireLookupService<DriverInfo>, PresetLookupService<DriverInfo> {

    String CHILD_FOR_SECTION = "child_for_section";
    String CHILD_FOR_SECTION_ENABLED = "child_for_section_enabled";
}
