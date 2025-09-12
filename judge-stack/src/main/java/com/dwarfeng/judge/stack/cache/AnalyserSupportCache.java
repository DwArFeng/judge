package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.AnalyserSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 分析器支持缓存。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AnalyserSupportCache extends BatchBaseCache<StringIdKey, AnalyserSupport> {
}
