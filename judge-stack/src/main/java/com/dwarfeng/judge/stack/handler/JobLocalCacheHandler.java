package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.struct.JobLocalCache;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.handler.LocalCacheHandler;

/**
 * 作业用本地缓存处理器。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface JobLocalCacheHandler extends LocalCacheHandler<LongIdKey, JobLocalCache> {
}
