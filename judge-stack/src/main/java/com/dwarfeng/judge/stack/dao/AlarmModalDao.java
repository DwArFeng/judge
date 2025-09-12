package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.AlarmModal;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 报警模态数据访问层。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AlarmModalDao extends BatchBaseDao<LongIdKey, AlarmModal>, EntireLookupDao<AlarmModal>,
        PresetLookupDao<AlarmModal> {
}
