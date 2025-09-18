package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.SinkerInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 下沉器信息维护服务。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public interface SinkerInfoMaintainService extends BatchCrudService<LongIdKey, SinkerInfo>,
        EntireLookupService<SinkerInfo>, PresetLookupService<SinkerInfo> {
}
