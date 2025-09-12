package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.Analysis;
import com.dwarfeng.judge.stack.bean.key.AnalysisKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 分析结果缓存。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AnalysisCache extends BatchBaseCache<AnalysisKey, Analysis> {
}
