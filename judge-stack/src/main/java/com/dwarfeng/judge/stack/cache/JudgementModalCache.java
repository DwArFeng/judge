package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.JudgementModal;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 判断结果模态缓存。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface JudgementModalCache extends BatchBaseCache<LongIdKey, JudgementModal> {
}
