package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.Judgement;
import com.dwarfeng.judge.stack.bean.key.JudgementKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 判断结果数据访问层。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface JudgementDao extends BatchBaseDao<JudgementKey, Judgement>, EntireLookupDao<Judgement>,
        PresetLookupDao<Judgement> {
}
