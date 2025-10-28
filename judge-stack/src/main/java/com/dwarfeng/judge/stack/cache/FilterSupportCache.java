package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.FilterSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 过滤器支持缓存。
 *
 * @author wangyc
 * @since 2.3.0
 */
public interface FilterSupportCache extends BatchBaseCache<StringIdKey, FilterSupport> {
}
