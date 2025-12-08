package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.SinkerSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 下沉器支持维护服务。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public interface SinkerSupportMaintainService extends BatchCrudService<StringIdKey, SinkerSupport>,
        EntireLookupService<SinkerSupport>, PresetLookupService<SinkerSupport> {

    /**
     * @since 2.3.1
     */
    String ID_LIKE = "id_like";

    /**
     * @since 2.3.1
     */
    String LABEL_LIKE = "label_like";
}
