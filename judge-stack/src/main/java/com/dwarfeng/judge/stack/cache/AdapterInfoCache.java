package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.AdapterInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 适配器信息缓存。
 *
 * @author wangyc
 * @since 2.3.0
 */
public interface AdapterInfoCache extends BatchBaseCache<LongIdKey, AdapterInfo> {
}
