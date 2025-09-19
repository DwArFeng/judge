package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.VisualizerSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 可视化器支持维护服务。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public interface VisualizerSupportMaintainService extends BatchCrudService<StringIdKey, VisualizerSupport>,
        EntireLookupService<VisualizerSupport>, PresetLookupService<VisualizerSupport> {

    String ID_LIKE = "id_like";
    String LABEL_LIKE = "label_like";
}
