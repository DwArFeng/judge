package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.VisualizerSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 可视化器支持缓存。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public interface VisualizerSupportCache extends BatchBaseCache<StringIdKey, VisualizerSupport> {
}
