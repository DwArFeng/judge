package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.DriverSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 驱动器支持数据访问层。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface DriverSupportDao extends BatchBaseDao<StringIdKey, DriverSupport>, EntireLookupDao<DriverSupport>,
        PresetLookupDao<DriverSupport> {
}
