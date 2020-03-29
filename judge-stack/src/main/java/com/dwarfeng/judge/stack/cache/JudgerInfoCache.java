package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 评价信息缓存。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface JudgerInfoCache extends BatchBaseCache<LongIdKey, JudgerInfo> {

}
