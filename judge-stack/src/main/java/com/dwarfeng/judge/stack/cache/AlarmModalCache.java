package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.AlarmModal;
import com.dwarfeng.judge.stack.bean.key.AlarmKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 报警模态缓存。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public interface AlarmModalCache extends BatchBaseCache<AlarmKey, AlarmModal> {
}
