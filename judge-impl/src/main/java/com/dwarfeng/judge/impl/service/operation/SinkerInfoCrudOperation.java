package com.dwarfeng.judge.impl.service.operation;

import com.dwarfeng.judge.stack.bean.entity.SinkerInfo;
import com.dwarfeng.judge.stack.bean.entity.SinkerMeta;
import com.dwarfeng.judge.stack.bean.entity.SinkerRelation;
import com.dwarfeng.judge.stack.bean.entity.SinkerVariable;
import com.dwarfeng.judge.stack.bean.key.SinkerMetaKey;
import com.dwarfeng.judge.stack.bean.key.SinkerRelationKey;
import com.dwarfeng.judge.stack.bean.key.SinkerVariableKey;
import com.dwarfeng.judge.stack.cache.SinkerInfoCache;
import com.dwarfeng.judge.stack.cache.SinkerMetaCache;
import com.dwarfeng.judge.stack.cache.SinkerRelationCache;
import com.dwarfeng.judge.stack.cache.SinkerVariableCache;
import com.dwarfeng.judge.stack.dao.SinkerInfoDao;
import com.dwarfeng.judge.stack.dao.SinkerMetaDao;
import com.dwarfeng.judge.stack.dao.SinkerRelationDao;
import com.dwarfeng.judge.stack.dao.SinkerVariableDao;
import com.dwarfeng.judge.stack.service.SinkerMetaMaintainService;
import com.dwarfeng.judge.stack.service.SinkerRelationMaintainService;
import com.dwarfeng.judge.stack.service.SinkerVariableMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SinkerInfoCrudOperation implements BatchCrudOperation<LongIdKey, SinkerInfo> {

    private final SinkerInfoDao sinkerInfoDao;
    private final SinkerInfoCache sinkerInfoCache;

    private final SinkerRelationDao sinkerRelationDao;
    private final SinkerRelationCache sinkerRelationCache;

    private final SinkerMetaDao sinkerMetaDao;
    private final SinkerMetaCache sinkerMetaCache;

    private final SinkerVariableDao sinkerVariableDao;
    private final SinkerVariableCache sinkerVariableCache;

    @Value("${cache.timeout.entity.sinker_info}")
    private long sinkerInfoTimeout;

    public SinkerInfoCrudOperation(
            SinkerInfoDao sinkerInfoDao,
            SinkerInfoCache sinkerInfoCache,
            SinkerRelationDao sinkerRelationDao,
            SinkerRelationCache sinkerRelationCache,
            SinkerMetaDao sinkerMetaDao,
            SinkerMetaCache sinkerMetaCache,
            SinkerVariableDao sinkerVariableDao,
            SinkerVariableCache sinkerVariableCache
    ) {
        this.sinkerInfoDao = sinkerInfoDao;
        this.sinkerInfoCache = sinkerInfoCache;
        this.sinkerRelationDao = sinkerRelationDao;
        this.sinkerRelationCache = sinkerRelationCache;
        this.sinkerMetaDao = sinkerMetaDao;
        this.sinkerMetaCache = sinkerMetaCache;
        this.sinkerVariableDao = sinkerVariableDao;
        this.sinkerVariableCache = sinkerVariableCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return sinkerInfoCache.exists(key) || sinkerInfoDao.exists(key);
    }

    @Override
    public SinkerInfo get(LongIdKey key) throws Exception {
        if (sinkerInfoCache.exists(key)) {
            return sinkerInfoCache.get(key);
        } else {
            if (!sinkerInfoDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            SinkerInfo sinkerInfo = sinkerInfoDao.get(key);
            sinkerInfoCache.push(sinkerInfo, sinkerInfoTimeout);
            return sinkerInfo;
        }
    }

    @Override
    public LongIdKey insert(SinkerInfo sinkerInfo) throws Exception {
        sinkerInfoCache.push(sinkerInfo, sinkerInfoTimeout);
        return sinkerInfoDao.insert(sinkerInfo);
    }

    @Override
    public void update(SinkerInfo sinkerInfo) throws Exception {
        sinkerInfoCache.push(sinkerInfo, sinkerInfoTimeout);
        sinkerInfoDao.update(sinkerInfo);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 删除与下沉器相关的下沉器关联信息。
        List<SinkerRelationKey> sinkerRelationKeys = sinkerRelationDao.lookup(
                SinkerRelationMaintainService.CHILD_FOR_SINKER_INFO, new Object[]{key}
        ).stream().map(SinkerRelation::getKey).collect(Collectors.toList());
        sinkerRelationCache.batchDelete(sinkerRelationKeys);
        sinkerRelationDao.batchDelete(sinkerRelationKeys);

        // 删除与下沉器相关的下沉关联元数据。
        List<SinkerMetaKey> sinkerMetaKeys = sinkerMetaDao.lookup(
                SinkerMetaMaintainService.CHILD_FOR_SINKER_INFO, new Object[]{key}
        ).stream().map(SinkerMeta::getKey).collect(Collectors.toList());
        sinkerMetaCache.batchDelete(sinkerMetaKeys);
        sinkerMetaDao.batchDelete(sinkerMetaKeys);

        // 删除与下沉器相关的下沉器变量。
        List<SinkerVariableKey> sinkerVariableKeys = sinkerVariableDao.lookup(
                SinkerVariableMaintainService.CHILD_FOR_SINKER_INFO, new Object[]{key}
        ).stream().map(SinkerVariable::getKey).collect(Collectors.toList());
        sinkerVariableCache.batchDelete(sinkerVariableKeys);
        sinkerVariableDao.batchDelete(sinkerVariableKeys);

        // 删除 下沉器信息 自身。
        sinkerInfoDao.delete(key);
        sinkerInfoCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return sinkerInfoCache.allExists(keys) || sinkerInfoDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return sinkerInfoCache.nonExists(keys) && sinkerInfoDao.nonExists(keys);
    }

    @Override
    public List<SinkerInfo> batchGet(List<LongIdKey> keys) throws Exception {
        if (sinkerInfoCache.allExists(keys)) {
            return sinkerInfoCache.batchGet(keys);
        } else {
            if (!sinkerInfoDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<SinkerInfo> sinkerInfos = sinkerInfoDao.batchGet(keys);
            sinkerInfoCache.batchPush(sinkerInfos, sinkerInfoTimeout);
            return sinkerInfos;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<SinkerInfo> sinkerInfos) throws Exception {
        sinkerInfoCache.batchPush(sinkerInfos, sinkerInfoTimeout);
        return sinkerInfoDao.batchInsert(sinkerInfos);
    }

    @Override
    public void batchUpdate(List<SinkerInfo> sinkerInfos) throws Exception {
        sinkerInfoCache.batchPush(sinkerInfos, sinkerInfoTimeout);
        sinkerInfoDao.batchUpdate(sinkerInfos);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
