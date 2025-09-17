package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.AnalyserInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 分析器信息数据访问层。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface AnalyserInfoDao extends BatchBaseDao<LongIdKey, AnalyserInfo>, EntireLookupDao<AnalyserInfo>,
        PresetLookupDao<AnalyserInfo> {
}
