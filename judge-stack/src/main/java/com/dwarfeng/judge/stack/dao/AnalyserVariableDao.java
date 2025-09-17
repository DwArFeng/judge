package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.AnalyserVariable;
import com.dwarfeng.judge.stack.bean.key.AnalyserVariableKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 分析器变量数据访问层。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface AnalyserVariableDao extends BatchBaseDao<AnalyserVariableKey, AnalyserVariable>,
        EntireLookupDao<AnalyserVariable>, PresetLookupDao<AnalyserVariable> {
}
