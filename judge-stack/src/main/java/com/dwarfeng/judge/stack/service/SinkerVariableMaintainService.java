package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.SinkerVariable;
import com.dwarfeng.judge.stack.bean.key.SinkerVariableKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 下沉器变量维护服务。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface SinkerVariableMaintainService extends BatchCrudService<SinkerVariableKey, SinkerVariable>,
        EntireLookupService<SinkerVariable>, PresetLookupService<SinkerVariable> {

    String CHILD_FOR_SINKER_INFO = "child_for_sinker_info";
}
