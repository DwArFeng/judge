package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.AlarmLevelIndicator;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 报警等级指示器维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AlarmLevelIndicatorMaintainService extends BatchCrudService<StringIdKey, AlarmLevelIndicator>,
        EntireLookupService<AlarmLevelIndicator>, PresetLookupService<AlarmLevelIndicator> {
}
