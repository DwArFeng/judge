package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.AdapterInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 适配器信息数据访问层。
 *
 * @author wangyc
 * @since 2.3.0
 */
public interface AdapterInfoDao extends BatchBaseDao<LongIdKey, AdapterInfo>, EntireLookupDao<AdapterInfo>,
        PresetLookupDao<AdapterInfo> {
}
