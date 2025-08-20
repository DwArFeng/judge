package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.JudgerSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 判断器支持数据访问层。
 *
 * @author DwArFeng
 * @since beta-1.1.0
 */
public interface JudgerSupportDao extends BatchBaseDao<StringIdKey, JudgerSupport>, EntireLookupDao<JudgerSupport>,
        PresetLookupDao<JudgerSupport> {
}
