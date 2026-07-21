package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.AlarmTypeIndicator;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 报警类型指示器数据访问层。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public interface AlarmTypeIndicatorDao extends BatchBaseDao<StringIdKey, AlarmTypeIndicator>,
        EntireLookupDao<AlarmTypeIndicator>, PresetLookupDao<AlarmTypeIndicator> {
}
