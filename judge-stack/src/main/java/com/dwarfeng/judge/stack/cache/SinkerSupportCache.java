package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.SinkerSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 下沉器支持缓存。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public interface SinkerSupportCache extends BatchBaseCache<StringIdKey, SinkerSupport> {
}
