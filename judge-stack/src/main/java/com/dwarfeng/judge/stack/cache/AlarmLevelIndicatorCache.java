package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.AlarmLevelIndicator;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 报警等级指示器缓存。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AlarmLevelIndicatorCache extends BatchBaseCache<StringIdKey, AlarmLevelIndicator> {
}
