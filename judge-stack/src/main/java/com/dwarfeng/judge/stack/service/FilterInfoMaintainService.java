package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.FilterInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 过滤器信息维护服务。
 *
 * @author wangyc
 * @since 2.3.0
 */
public interface FilterInfoMaintainService extends BatchCrudService<LongIdKey, FilterInfo>,
        EntireLookupService<FilterInfo>, PresetLookupService<FilterInfo> {

}
