package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.AnalysisFileInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 分析结果文件信息缓存。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface AnalysisFileInfoCache extends BatchBaseCache<LongIdKey, AnalysisFileInfo> {
}
