package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.SinkerMetaIndicator;
import com.dwarfeng.judge.stack.bean.key.SinkerMetaIndicatorKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 下沉关联元数据指示器数据访问层。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public interface SinkerMetaIndicatorDao extends BatchBaseDao<SinkerMetaIndicatorKey, SinkerMetaIndicator>, EntireLookupDao<SinkerMetaIndicator>,
        PresetLookupDao<SinkerMetaIndicator> {
}
