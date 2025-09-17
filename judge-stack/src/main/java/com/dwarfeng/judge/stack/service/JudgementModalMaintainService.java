package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.JudgementModal;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 判断结果模态维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface JudgementModalMaintainService extends BatchCrudService<LongIdKey, JudgementModal>,
        EntireLookupService<JudgementModal>, PresetLookupService<JudgementModal> {

    String CHILD_FOR_JUDGER_INFO = "child_for_judger_info";
}
