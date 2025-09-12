package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.AnalyserSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 分析器支持数据访问层。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AnalyserSupportDao extends BatchBaseDao<StringIdKey, AnalyserSupport>,
        EntireLookupDao<AnalyserSupport>, PresetLookupDao<AnalyserSupport> {
}
