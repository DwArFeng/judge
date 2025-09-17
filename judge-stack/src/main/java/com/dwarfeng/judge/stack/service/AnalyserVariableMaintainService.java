package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.entity.AnalyserVariable;
import com.dwarfeng.judge.stack.bean.key.AnalyserVariableKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;

/**
 * 分析器变量维护服务。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface AnalyserVariableMaintainService extends BatchCrudService<AnalyserVariableKey, AnalyserVariable>,
        EntireLookupService<AnalyserVariable>, PresetLookupService<AnalyserVariable> {

    String CHILD_FOR_ANALYSER_INFO = "child_for_analyser_info";
}
