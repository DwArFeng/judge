package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.ProviderInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 提供器信息数据访问层。
 *
 * @author wangyc
 * @since 2.1.0
 */
public interface ProviderInfoDao extends BatchBaseDao<LongIdKey, ProviderInfo>, EntireLookupDao<ProviderInfo>,
        PresetLookupDao<ProviderInfo> {
}
