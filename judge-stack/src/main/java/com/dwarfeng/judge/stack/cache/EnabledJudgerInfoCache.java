package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.KeyListCache;

/**
 * 部件持有评价子项的缓存。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface EnabledJudgerInfoCache extends KeyListCache<LongIdKey, JudgerInfo> {
}
