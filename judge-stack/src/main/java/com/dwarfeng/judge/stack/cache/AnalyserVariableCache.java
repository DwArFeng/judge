package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.AnalyserVariable;
import com.dwarfeng.judge.stack.bean.key.AnalyserVariableKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 分析器变量缓存。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AnalyserVariableCache extends BatchBaseCache<AnalyserVariableKey, AnalyserVariable> {
}
