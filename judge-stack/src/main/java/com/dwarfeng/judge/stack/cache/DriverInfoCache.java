package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 驱动器信息缓存。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface DriverInfoCache extends BatchBaseCache<LongIdKey, DriverInfo> {
}
