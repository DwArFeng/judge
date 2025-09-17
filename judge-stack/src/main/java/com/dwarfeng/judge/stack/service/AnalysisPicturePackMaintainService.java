package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.AnalysisPicturePack;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 分析结果图片包维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface AnalysisPicturePackMaintainService extends BatchCrudService<LongIdKey, AnalysisPicturePack>,
        EntireLookupService<AnalysisPicturePack>, PresetLookupService<AnalysisPicturePack> {
}
