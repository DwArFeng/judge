package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.KeyListCache;

/**
 * 部件持有驱动器子项的缓存。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface EnabledDriverInfoCache extends KeyListCache<LongIdKey, DriverInfo> {
}
