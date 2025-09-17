package com.dwarfeng.judge.stack.dao;

import com.dwarfeng.judge.stack.bean.entity.AnalysisPictureInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;

/**
 * 分析结果图片信息数据访问层。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface AnalysisPictureInfoDao extends BatchBaseDao<LongIdKey, AnalysisPictureInfo>,
        EntireLookupDao<AnalysisPictureInfo>, PresetLookupDao<AnalysisPictureInfo> {
}
