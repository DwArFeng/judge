package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.VisualizerInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 可视化器信息缓存。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public interface VisualizerInfoCache extends BatchBaseCache<LongIdKey, VisualizerInfo> {
}
