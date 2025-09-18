package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.SinkerVariable;
import com.dwarfeng.judge.stack.bean.key.SinkerVariableKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 下沉器变量缓存。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface SinkerVariableCache extends BatchBaseCache<SinkerVariableKey, SinkerVariable> {
}
