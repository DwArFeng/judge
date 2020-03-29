package com.dwarfeng.judge.impl.cache;

import com.dwarfeng.judge.sdk.bean.entity.FastJsonJudgerInfo;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.cache.EnabledJudgerInfoCache;
import com.dwarfeng.subgrade.impl.cache.RedisKeyListCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.dto.PagingInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public class EnabledJudgerInfoCacheImpl implements EnabledJudgerInfoCache {

    @Autowired
    private RedisKeyListCache<LongIdKey, JudgerInfo, FastJsonJudgerInfo> listCache;

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public boolean exists(LongIdKey key) throws CacheException {
        return listCache.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public int size(LongIdKey key) throws CacheException {
        return listCache.size(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public List<JudgerInfo> get(LongIdKey key) throws CacheException {
        return listCache.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public List<JudgerInfo> get(LongIdKey key, int beginIndex, int maxEntity) throws CacheException {
        return listCache.get(key, beginIndex, maxEntity);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
    public List<JudgerInfo> get(LongIdKey key, PagingInfo pagingInfo) throws CacheException {
        return listCache.get(key, pagingInfo);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void set(LongIdKey key, Collection<JudgerInfo> entities, long timeout) throws CacheException {
        listCache.set(key, entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void leftPush(LongIdKey key, Collection<JudgerInfo> entities, long timeout) throws CacheException {
        listCache.leftPush(key, entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void rightPush(LongIdKey key, Collection<JudgerInfo> entities, long timeout) throws CacheException {
        listCache.rightPush(key, entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void delete(LongIdKey key) throws CacheException {
        listCache.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager")
    public void clear() throws CacheException {
        listCache.clear();
    }
}
