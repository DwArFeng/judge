package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.SinkerMetaIndicator;
import com.dwarfeng.judge.stack.bean.key.SinkerMetaIndicatorKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 下沉关联元数据指示器缓存。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public interface SinkerMetaIndicatorCache extends BatchBaseCache<SinkerMetaIndicatorKey, SinkerMetaIndicator> {
}
