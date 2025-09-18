package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.SinkerMeta;
import com.dwarfeng.judge.stack.bean.key.SinkerMetaKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 下沉关联元数据数据访问层。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public interface SinkerMetaDao extends BatchBaseDao<SinkerMetaKey, SinkerMeta>, EntireLookupDao<SinkerMeta>,
        PresetLookupDao<SinkerMeta> {
}
