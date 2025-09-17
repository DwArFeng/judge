package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.AnalysisFileInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 分析结果文件信息维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface AnalysisFileInfoMaintainService extends BatchCrudService<LongIdKey, AnalysisFileInfo>,
        EntireLookupService<AnalysisFileInfo>, PresetLookupService<AnalysisFileInfo> {
}
