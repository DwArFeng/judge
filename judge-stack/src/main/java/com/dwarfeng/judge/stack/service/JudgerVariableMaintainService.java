package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.JudgerVariable;
import com.dwarfeng.judge.stack.bean.key.JudgerVariableKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 判断器变量维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface JudgerVariableMaintainService extends BatchCrudService<JudgerVariableKey, JudgerVariable>,
        EntireLookupService<JudgerVariable>, PresetLookupService<JudgerVariable> {

    String CHILD_FOR_JUDGER_INFO = "child_for_judger_info";
}
