package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.TaskEvent;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 任务事件缓存。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface TaskEventCache extends BatchBaseCache<LongIdKey, TaskEvent> {
}
