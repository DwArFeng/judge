package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.AlarmModal;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 报警模态维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AlarmModalMaintainService extends BatchCrudService<LongIdKey, AlarmModal>,
        EntireLookupService<AlarmModal>, PresetLookupService<AlarmModal> {

    String ALARMING_EQUALS = "alarming_equals";
}
