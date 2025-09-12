package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.AnalysisPictureInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 分析结果图片信息维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AnalysisPictureInfoMaintainService extends BatchCrudService<LongIdKey, AnalysisPictureInfo>,
        EntireLookupService<AnalysisPictureInfo>, PresetLookupService<AnalysisPictureInfo> {
}
