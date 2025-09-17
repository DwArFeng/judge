package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.struct.DriveLocalCache;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.handler.LocalCacheHandler;

/**
 * 驱动用本地缓存处理器。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface DriveLocalCacheHandler extends LocalCacheHandler<LongIdKey, DriveLocalCache> {
}
