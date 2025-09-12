package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.AlarmModal;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 报警模态缓存。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AlarmModalCache extends BatchBaseCache<LongIdKey, AlarmModal> {
}
