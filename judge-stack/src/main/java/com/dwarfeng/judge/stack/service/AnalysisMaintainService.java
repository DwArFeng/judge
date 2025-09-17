package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.Analysis;
import com.dwarfeng.judge.stack.bean.key.AnalysisKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 分析结果维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface AnalysisMaintainService extends BatchCrudService<AnalysisKey, Analysis>,
        EntireLookupService<Analysis>, PresetLookupService<Analysis> {

    String CHILD_FOR_TASK = "child_for_task";
    String CHILD_FOR_TASK_DATA_STRING_ID_ASC = "child_for_task_data_string_id_asc";
}
