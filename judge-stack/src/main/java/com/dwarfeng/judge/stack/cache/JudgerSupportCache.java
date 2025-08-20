package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.JudgerSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 判断器支持缓存。
 *
 * @author DwArFeng
 * @since beta-1.1.0
 */
public interface JudgerSupportCache extends BatchBaseCache<StringIdKey, JudgerSupport> {
}
