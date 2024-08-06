package com.dwarfeng.judge.impl.service.operation;

import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.cache.DriverInfoCache;
import com.dwarfeng.judge.stack.cache.EnabledDriverInfoCache;
import com.dwarfeng.judge.stack.dao.DriverInfoDao;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class DriverInfoCrudOperation implements BatchCrudOperation<LongIdKey, DriverInfo> {

    private final DriverInfoDao driverInfoDao;
    private final DriverInfoCache driverInfoCache;

    private final EnabledDriverInfoCache enabledDriverInfoCache;

    @Value("${cache.timeout.entity.driver_info}")
    private long driverInfoTimeout;

    public DriverInfoCrudOperation(
            DriverInfoDao driverInfoDao,
            DriverInfoCache driverInfoCache,
            EnabledDriverInfoCache enabledDriverInfoCache
    ) {
        this.driverInfoDao = driverInfoDao;
        this.driverInfoCache = driverInfoCache;
        this.enabledDriverInfoCache = enabledDriverInfoCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return driverInfoCache.exists(key) || driverInfoDao.exists(key);
    }

    @Override
    public DriverInfo get(LongIdKey key) throws Exception {
        if (driverInfoCache.exists(key)) {
            return driverInfoCache.get(key);
        } else {
            if (!driverInfoDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            DriverInfo driverInfo = driverInfoDao.get(key);
            driverInfoCache.push(driverInfo, driverInfoTimeout);
            return driverInfo;
        }
    }

    @Override
    public LongIdKey insert(DriverInfo driverInfo) throws Exception {
        if (Objects.nonNull(driverInfo.getSectionKey())) {
            enabledDriverInfoCache.delete(driverInfo.getSectionKey());
        }

        driverInfoCache.push(driverInfo, driverInfoTimeout);
        return driverInfoDao.insert(driverInfo);
    }

    @Override
    public void update(DriverInfo driverInfo) throws Exception {
        DriverInfo oldDriverInfo = get(driverInfo.getKey());
        if (Objects.nonNull(oldDriverInfo.getSectionKey())) {
            enabledDriverInfoCache.delete(oldDriverInfo.getSectionKey());
        }

        if (Objects.nonNull(driverInfo.getSectionKey())) {
            enabledDriverInfoCache.delete(driverInfo.getSectionKey());
        }

        driverInfoCache.push(driverInfo, driverInfoTimeout);
        driverInfoDao.update(driverInfo);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        DriverInfo oldDriverInfo = get(key);
        if (Objects.nonNull(oldDriverInfo.getSectionKey())) {
            enabledDriverInfoCache.delete(oldDriverInfo.getSectionKey());
        }

        driverInfoDao.delete(key);
        driverInfoCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return driverInfoCache.allExists(keys) || driverInfoDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return driverInfoCache.nonExists(keys) && driverInfoCache.nonExists(keys);
    }

    @Override
    public List<DriverInfo> batchGet(List<LongIdKey> keys) throws Exception {
        if (driverInfoCache.allExists(keys)) {
            return driverInfoCache.batchGet(keys);
        } else {
            if (!driverInfoDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<DriverInfo> driverInfos = driverInfoDao.batchGet(keys);
            driverInfoCache.batchPush(driverInfos, driverInfoTimeout);
            return driverInfos;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<DriverInfo> driverInfos) throws Exception {
        List<LongIdKey> keys = new ArrayList<>();
        for (DriverInfo driverInfo : driverInfos) {
            keys.add(insert(driverInfo));
        }
        return keys;
    }

    @Override
    public void batchUpdate(List<DriverInfo> driverInfos) throws Exception {
        for (DriverInfo driverInfo : driverInfos) {
            update(driverInfo);
        }
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
