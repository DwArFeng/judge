package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.SinkerMetaIndicator;
import com.dwarfeng.judge.stack.bean.key.SinkerMetaIndicatorKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 下沉关联元数据指示器维护服务。
 *
 * @author wangyc
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface SinkerMetaIndicatorMaintainService extends
        BatchCrudService<SinkerMetaIndicatorKey, SinkerMetaIndicator>, EntireLookupService<SinkerMetaIndicator>,
        PresetLookupService<SinkerMetaIndicator> {

    String SINKER_TYPE_EQ = "sinker_type_eq";
    String SINKER_TYPE_EQ_META_ID_ASC = "sinker_type_eq_meta_id_asc";
}
