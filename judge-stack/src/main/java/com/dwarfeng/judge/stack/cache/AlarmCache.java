package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.Alarm;
import com.dwarfeng.judge.stack.bean.key.AlarmKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 当前报警缓存。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public interface AlarmCache extends BatchBaseCache<AlarmKey, Alarm> {
}
