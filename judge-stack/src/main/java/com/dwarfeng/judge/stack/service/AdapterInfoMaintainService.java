package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.AdapterInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 适配器信息维护服务。
 *
 * @author wangyc
 * @since 2.3.0
 */
public interface AdapterInfoMaintainService extends BatchCrudService<LongIdKey, AdapterInfo>,
        EntireLookupService<AdapterInfo>, PresetLookupService<AdapterInfo> {

}
