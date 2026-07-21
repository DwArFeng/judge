package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.Verdict;
import com.dwarfeng.judge.stack.bean.key.VerdictKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 部件裁决结果缓存。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public interface VerdictCache extends BatchBaseCache<VerdictKey, Verdict> {
}
