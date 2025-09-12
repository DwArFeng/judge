package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.Analysis;
import com.dwarfeng.judge.stack.bean.key.AnalysisKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 分析结果数据访问层。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AnalysisDao extends BatchBaseDao<AnalysisKey, Analysis>, EntireLookupDao<Analysis>,
        PresetLookupDao<Analysis> {
}
