package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.SinkerRelation;
import com.dwarfeng.judge.stack.bean.key.SinkerRelationKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 下沉器关联信息数据访问层。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public interface SinkerRelationDao extends BatchBaseDao<SinkerRelationKey, SinkerRelation>, EntireLookupDao<SinkerRelation>,
        PresetLookupDao<SinkerRelation> {
}
