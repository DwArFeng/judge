package com.dwarfeng.judge.impl.cache;

import com.dwarfeng.judge.sdk.bean.entity.FastJsonDriverSupport;
import com.dwarfeng.judge.stack.bean.entity.DriverSupport;
import com.dwarfeng.judge.stack.cache.DriverSupportCache;
import com.dwarfeng.subgrade.impl.cache.RedisBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class DriverSupportCacheImpl implements DriverSupportCache {

    private final RedisBaseCache<StringIdKey, DriverSupport, FastJsonDriverSupport> baseCache;

    public DriverSupportCacheImpl(RedisBaseCache<StringIdKey, DriverSupport, FastJsonDriverSupport> baseCache) {
        this.baseCache = baseCache;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(StringIdKey key) throws CacheException {
        return baseCache.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public DriverSupport get(StringIdKey key) throws CacheException {
        return baseCache.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(DriverSupport value, long timeout) throws CacheException {
        baseCache.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(StringIdKey key) throws CacheException {
        baseCache.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        baseCache.clear();
    }
}
