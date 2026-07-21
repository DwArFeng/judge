package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.AlarmTypeIndicator;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 报警类型指示器维护服务。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public interface AlarmTypeIndicatorMaintainService extends BatchCrudService<StringIdKey, AlarmTypeIndicator>,
        EntireLookupService<AlarmTypeIndicator>, PresetLookupService<AlarmTypeIndicator> {
}
