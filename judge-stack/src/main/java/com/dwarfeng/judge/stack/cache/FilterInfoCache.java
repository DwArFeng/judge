package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.FilterInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 过滤器信息缓存。
 *
 * @author wangyc
 * @since 2.3.0
 */
public interface FilterInfoCache extends BatchBaseCache<LongIdKey, FilterInfo> {
}
