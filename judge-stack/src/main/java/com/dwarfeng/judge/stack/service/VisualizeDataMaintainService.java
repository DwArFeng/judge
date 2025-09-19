package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.VisualizeData;
import com.dwarfeng.judge.stack.bean.key.VisualizeDataKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 可视化数据维护服务。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public interface VisualizeDataMaintainService extends BatchCrudService<VisualizeDataKey, VisualizeData>,
        EntireLookupService<VisualizeData>, PresetLookupService<VisualizeData> {

    String CHILD_FOR_TASK = "child_for_task";
}
