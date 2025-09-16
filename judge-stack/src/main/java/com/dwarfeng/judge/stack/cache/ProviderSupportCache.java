package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.ProviderSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 提供器支持缓存。
 *
 * @author wangyc
 * @since 2.1.0
 */
public interface ProviderSupportCache extends BatchBaseCache<StringIdKey, ProviderSupport> {
}
