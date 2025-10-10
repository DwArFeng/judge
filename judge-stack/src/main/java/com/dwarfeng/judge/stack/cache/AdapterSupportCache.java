package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.AdapterSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 适配器支持缓存。
 *
 * @author wangyc
 * @since 2.3.0
 */
public interface AdapterSupportCache extends BatchBaseCache<StringIdKey, AdapterSupport> {
}
