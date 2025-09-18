package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.SinkerMeta;
import com.dwarfeng.judge.stack.bean.key.SinkerMetaKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 下沉关联元数据缓存。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public interface SinkerMetaCache extends BatchBaseCache<SinkerMetaKey, SinkerMeta> {
}
