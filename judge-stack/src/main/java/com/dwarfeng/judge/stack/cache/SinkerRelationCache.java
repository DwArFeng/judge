package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.SinkerRelation;
import com.dwarfeng.judge.stack.bean.key.SinkerRelationKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 下沉器关联信息缓存。
 *
 * @author wangyc
 * @since 2.1.0-beta
 */
public interface SinkerRelationCache extends BatchBaseCache<SinkerRelationKey, SinkerRelation> {
}
