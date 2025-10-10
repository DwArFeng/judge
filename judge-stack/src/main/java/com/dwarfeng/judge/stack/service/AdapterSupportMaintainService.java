package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.AdapterSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 适配器支持维护服务。
 *
 * @author wangyc
 * @since 2.3.0
 */
public interface AdapterSupportMaintainService extends BatchCrudService<StringIdKey, AdapterSupport>,
        EntireLookupService<AdapterSupport>, PresetLookupService<AdapterSupport> {

    String ID_LIKE = "id_like";
    String LABEL_LIKE = "label_like";
}
