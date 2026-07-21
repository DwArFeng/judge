package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.Alarm;
import com.dwarfeng.judge.stack.bean.key.AlarmKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 当前报警维护服务。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public interface AlarmMaintainService extends BatchCrudService<AlarmKey, Alarm>, EntireLookupService<Alarm>,
        PresetLookupService<Alarm> {

    // region 预设查询 - 级联

    String CHILD_FOR_SECTION = "child_for_section";

    // endregion
}
