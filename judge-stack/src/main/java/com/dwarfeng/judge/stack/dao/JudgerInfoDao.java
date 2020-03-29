package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 评价信息数据访问层。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface JudgerInfoDao extends BatchBaseDao<LongIdKey, JudgerInfo>, EntireLookupDao<JudgerInfo>,
        PresetLookupDao<JudgerInfo> {

}
