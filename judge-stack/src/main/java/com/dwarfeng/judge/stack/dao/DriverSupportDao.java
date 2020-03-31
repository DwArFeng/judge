package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.DriverSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 驱动器支持数据访问层。
 *
 * @author DwArFeng
 * @since beta-1.1.0
 */
public interface DriverSupportDao extends BaseDao<StringIdKey, DriverSupport>, EntireLookupDao<DriverSupport>,
        PresetLookupDao<DriverSupport> {
}
