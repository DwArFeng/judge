package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.ProviderInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 提供器信息维护服务。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public interface ProviderInfoMaintainService extends BatchCrudService<LongIdKey, ProviderInfo>,
        EntireLookupService<ProviderInfo>, PresetLookupService<ProviderInfo> {

}
