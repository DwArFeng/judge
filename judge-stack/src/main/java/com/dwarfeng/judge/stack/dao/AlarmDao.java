package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.Alarm;
import com.dwarfeng.judge.stack.bean.key.AlarmKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 当前报警数据访问层。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public interface AlarmDao extends BatchBaseDao<AlarmKey, Alarm>, EntireLookupDao<Alarm>, PresetLookupDao<Alarm> {
}
