package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.SinkerVariable;
import com.dwarfeng.judge.stack.bean.key.SinkerVariableKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 下沉器变量数据访问层。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface SinkerVariableDao extends BatchBaseDao<SinkerVariableKey, SinkerVariable>,
        EntireLookupDao<SinkerVariable>, PresetLookupDao<SinkerVariable> {
}
