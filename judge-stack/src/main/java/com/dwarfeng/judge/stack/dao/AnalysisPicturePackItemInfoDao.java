package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.AnalysisPicturePackItemInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 分析结果图片包条目信息数据访问层。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface AnalysisPicturePackItemInfoDao extends BatchBaseDao<LongIdKey, AnalysisPicturePackItemInfo>,
        EntireLookupDao<AnalysisPicturePackItemInfo>, PresetLookupDao<AnalysisPicturePackItemInfo> {
}
