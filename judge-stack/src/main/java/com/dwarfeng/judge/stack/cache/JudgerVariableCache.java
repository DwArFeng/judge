package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.JudgerVariable;
import com.dwarfeng.judge.stack.bean.key.JudgerVariableKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 判断器变量缓存。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface JudgerVariableCache extends BatchBaseCache<JudgerVariableKey, JudgerVariable> {
}
