package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.JudgementHistory;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 判断结果历史缓存。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface JudgementHistoryCache extends BatchBaseCache<LongIdKey, JudgementHistory> {
}
