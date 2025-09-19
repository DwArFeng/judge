package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.VisualizeData;
import com.dwarfeng.judge.stack.bean.key.VisualizeDataKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 可视化数据数据访问层。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public interface VisualizeDataDao extends BatchBaseDao<VisualizeDataKey, VisualizeData>,
        EntireLookupDao<VisualizeData>, PresetLookupDao<VisualizeData> {
}
