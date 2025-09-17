package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.AnalyserSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 分析器支持维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface AnalyserSupportMaintainService extends BatchCrudService<StringIdKey, AnalyserSupport>,
        EntireLookupService<AnalyserSupport>, PresetLookupService<AnalyserSupport> {

    String ID_LIKE = "id_like";
    String LABEL_LIKE = "label_like";
}
