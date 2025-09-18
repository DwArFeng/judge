package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.SinkerInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 下沉器信息缓存。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public interface SinkerInfoCache extends BatchBaseCache<LongIdKey, SinkerInfo> {
}
