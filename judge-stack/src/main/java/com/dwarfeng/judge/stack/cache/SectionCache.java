package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 部件缓存。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface SectionCache extends BatchBaseCache<LongIdKey, Section> {

}
