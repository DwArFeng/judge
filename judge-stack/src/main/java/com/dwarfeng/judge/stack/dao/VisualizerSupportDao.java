package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.VisualizerSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 可视化器支持数据访问层。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public interface VisualizerSupportDao extends BatchBaseDao<StringIdKey, VisualizerSupport>,
        EntireLookupDao<VisualizerSupport>, PresetLookupDao<VisualizerSupport> {
}
