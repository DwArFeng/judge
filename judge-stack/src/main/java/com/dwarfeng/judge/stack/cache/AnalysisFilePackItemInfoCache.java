package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.AnalysisFilePackItemInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 分析结果文件包条目信息缓存。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface AnalysisFilePackItemInfoCache extends BatchBaseCache<LongIdKey, AnalysisFilePackItemInfo> {
}
