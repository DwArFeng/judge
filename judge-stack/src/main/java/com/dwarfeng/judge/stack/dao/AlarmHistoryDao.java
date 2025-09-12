package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.AlarmHistory;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 报警历史数据访问层。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AlarmHistoryDao extends BatchBaseDao<LongIdKey, AlarmHistory>, EntireLookupDao<AlarmHistory>,
        PresetLookupDao<AlarmHistory> {
}
