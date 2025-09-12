package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.AnalysisPictureInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 分析结果图片信息缓存。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AnalysisPictureInfoCache extends BatchBaseCache<LongIdKey, AnalysisPictureInfo> {
}
