package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.AlarmHistory;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 报警历史维护服务。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public interface AlarmHistoryMaintainService extends BatchCrudService<LongIdKey, AlarmHistory>,
        EntireLookupService<AlarmHistory>, PresetLookupService<AlarmHistory> {

    // region 预设查询 - 级联

    String CHILD_FOR_SECTION = "child_for_section";

    // endregion
}
