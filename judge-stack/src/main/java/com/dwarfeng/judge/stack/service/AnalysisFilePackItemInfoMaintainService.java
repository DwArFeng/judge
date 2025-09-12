package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.AnalysisFilePackItemInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 分析结果文件包条目信息维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AnalysisFilePackItemInfoMaintainService extends BatchCrudService<LongIdKey, AnalysisFilePackItemInfo>,
        EntireLookupService<AnalysisFilePackItemInfo>, PresetLookupService<AnalysisFilePackItemInfo> {

    String CHILD_FOR_PACK = "child_for_pack";
    String CHILD_FOR_PACK_INDEX_ASC = "child_for_pack_index_asc";
}
