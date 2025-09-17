package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.JudgementModal;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 判断结果模态数据访问层。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface JudgementModalDao extends BatchBaseDao<LongIdKey, JudgementModal>, EntireLookupDao<JudgementModal>,
        PresetLookupDao<JudgementModal> {
}
