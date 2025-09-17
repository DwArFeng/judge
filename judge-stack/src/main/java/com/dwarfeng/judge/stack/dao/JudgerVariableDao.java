package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.JudgerVariable;
import com.dwarfeng.judge.stack.bean.key.JudgerVariableKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 判断器变量数据访问层。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface JudgerVariableDao extends BatchBaseDao<JudgerVariableKey, JudgerVariable>,
        EntireLookupDao<JudgerVariable>, PresetLookupDao<JudgerVariable> {
}
