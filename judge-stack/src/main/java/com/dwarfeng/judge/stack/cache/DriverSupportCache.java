package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.DriverSupport;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 驱动器支持缓存。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface DriverSupportCache extends BatchBaseCache<StringIdKey, DriverSupport> {
}
