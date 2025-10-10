package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.AdapterSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 适配器支持数据访问层。
 *
 * @author wangyc
 * @since 2.3.0
 */
public interface AdapterSupportDao extends BatchBaseDao<StringIdKey, AdapterSupport>, EntireLookupDao<AdapterSupport>,
        PresetLookupDao<AdapterSupport> {
}
