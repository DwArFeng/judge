package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.Judgement;
import com.dwarfeng.judge.stack.bean.key.JudgementKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 判断结果缓存。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public interface JudgementCache extends BatchBaseCache<JudgementKey, Judgement> {
}
