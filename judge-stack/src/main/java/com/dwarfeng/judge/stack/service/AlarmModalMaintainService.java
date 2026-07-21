package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.AlarmModal;
import com.dwarfeng.judge.stack.bean.key.AlarmKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 报警模态维护服务。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public interface AlarmModalMaintainService extends BatchCrudService<AlarmKey, AlarmModal>,
        EntireLookupService<AlarmModal>, PresetLookupService<AlarmModal> {

    // region 预设查询 - 级联

    String CHILD_FOR_SECTION = "child_for_section";

    // endregion
}
