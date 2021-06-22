package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.Variable;
import com.dwarfeng.judge.stack.bean.key.VariableKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 变量缓存。
 *
 * @author DwArFeng
 * @since 1.5.0
 */
public interface VariableCache extends BatchBaseCache<VariableKey, Variable> {

}
