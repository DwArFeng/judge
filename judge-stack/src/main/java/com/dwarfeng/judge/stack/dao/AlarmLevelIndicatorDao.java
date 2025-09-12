package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.AlarmLevelIndicator;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 报警等级指示器数据访问层。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AlarmLevelIndicatorDao extends BatchBaseDao<StringIdKey, AlarmLevelIndicator>,
        EntireLookupDao<AlarmLevelIndicator>, PresetLookupDao<AlarmLevelIndicator> {
}
