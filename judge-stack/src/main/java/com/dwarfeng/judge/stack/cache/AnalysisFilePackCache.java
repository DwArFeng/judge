package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.AnalysisFilePack;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 分析结果文件包缓存。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AnalysisFilePackCache extends BatchBaseCache<LongIdKey, AnalysisFilePack> {
}
