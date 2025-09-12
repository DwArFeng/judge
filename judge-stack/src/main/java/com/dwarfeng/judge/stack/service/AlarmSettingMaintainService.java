package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.AlarmSetting;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 报警设置维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AlarmSettingMaintainService extends BatchCrudService<LongIdKey, AlarmSetting>,
        EntireLookupService<AlarmSetting>, PresetLookupService<AlarmSetting> {

    String CHILD_FOR_SECTION = "child_for_section";
    String CHILD_FOR_SECTION_ENABLED_THRESHOLD_DESC = "child_for_section_enabled_threshold_desc";
}
