package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.Verdict;
import com.dwarfeng.judge.stack.bean.key.VerdictKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 部件裁决结果数据访问层。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public interface VerdictDao extends BatchBaseDao<VerdictKey, Verdict>, EntireLookupDao<Verdict>,
        PresetLookupDao<Verdict> {
}
