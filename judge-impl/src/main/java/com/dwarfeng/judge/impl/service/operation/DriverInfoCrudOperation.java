package com.dwarfeng.judge.impl.service.operation;

import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.cache.DriverInfoCache;
import com.dwarfeng.judge.stack.cache.EnabledDriverInfoCache;
import com.dwarfeng.judge.stack.dao.DriverInfoDao;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.CrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class DriverInfoCrudOperation implements CrudOperation<LongIdKey, DriverInfo> {

    @Autowired
    private DriverInfoDao driverInfoDao;

    @Autowired
    private DriverInfoCache driverInfoCache;
    @Autowired
    private EnabledDriverInfoCache enabledDriverInfoCache;

    @Value("${cache.timeout.entity.driver_info}")
    private long driveTimeout;

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
            driverInfoCache.push(driverInfo, driveTimeout);
            return driverInfo;
        }
    }

    @Override
    public LongIdKey insert(DriverInfo driverInfo) throws Exception {
        if (Objects.nonNull(driverInfo.getSectionKey())) {
            enabledDriverInfoCache.delete(driverInfo.getSectionKey());
        }

        driverInfoCache.push(driverInfo, driveTimeout);
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

        driverInfoCache.push(driverInfo, driveTimeout);
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
}
