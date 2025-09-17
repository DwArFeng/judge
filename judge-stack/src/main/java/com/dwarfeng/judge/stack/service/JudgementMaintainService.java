package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.Judgement;
import com.dwarfeng.judge.stack.bean.key.JudgementKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 判断结果维护服务。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface JudgementMaintainService extends BatchCrudService<JudgementKey, Judgement>,
        EntireLookupService<Judgement>, PresetLookupService<Judgement> {

    String CHILD_FOR_TASK = "child_for_task";
    String CHILD_FOR_TASK_DATA_STRING_ID_ASC = "child_for_task_data_string_id_asc";
}
