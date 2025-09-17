package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.JudgementHistory;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 判断结果历史数据访问层。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface JudgementHistoryDao extends BatchBaseDao<LongIdKey, JudgementHistory>,
        EntireLookupDao<JudgementHistory>, PresetLookupDao<JudgementHistory> {
}
