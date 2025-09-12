package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.JudgerSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 判断器支持维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface JudgerSupportMaintainService extends BatchCrudService<StringIdKey, JudgerSupport>,
        EntireLookupService<JudgerSupport>, PresetLookupService<JudgerSupport> {

    String ID_LIKE = "id_like";
    String LABEL_LIKE = "label_like";
}
