package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.VisualizerInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 可视化器信息数据访问层。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public interface VisualizerInfoDao extends BatchBaseDao<LongIdKey, VisualizerInfo>,
        EntireLookupDao<VisualizerInfo>, PresetLookupDao<VisualizerInfo> {
}
