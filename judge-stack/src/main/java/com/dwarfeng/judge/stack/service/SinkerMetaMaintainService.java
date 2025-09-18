package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.SinkerMeta;
import com.dwarfeng.judge.stack.bean.key.SinkerMetaKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 下沉关联元数据维护服务。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public interface SinkerMetaMaintainService extends BatchCrudService<SinkerMetaKey, SinkerMeta>,
        EntireLookupService<SinkerMeta>, PresetLookupService<SinkerMeta> {

    String CHILD_FOR_SECTION = "child_for_section";
    String CHILD_FOR_SINKER_INFO = "child_for_sinker_info";
    String CHILD_FOR_SECTION_SINKER_INFO = "child_for_section_sinker_info";
}
