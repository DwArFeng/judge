package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.ProviderSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 提供器支持数据访问层。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public interface ProviderSupportDao extends BatchBaseDao<StringIdKey, ProviderSupport>, EntireLookupDao<ProviderSupport>,
        PresetLookupDao<ProviderSupport> {
}
