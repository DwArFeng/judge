package com.dwarfeng.judge.stack.cache;

import com.dwarfeng.judge.stack.bean.entity.AnalysisPicturePack;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;

/**
 * 分析结果图片包缓存。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public interface AnalysisPicturePackCache extends BatchBaseCache<LongIdKey, AnalysisPicturePack> {
}
