package com.dwarfeng.judge.impl.service.operation;

import com.dwarfeng.judge.stack.bean.entity.AnalyserInfo;
import com.dwarfeng.judge.stack.bean.entity.AnalyserVariable;
import com.dwarfeng.judge.stack.bean.key.AnalyserVariableKey;
import com.dwarfeng.judge.stack.cache.AnalyserInfoCache;
import com.dwarfeng.judge.stack.cache.AnalyserVariableCache;
import com.dwarfeng.judge.stack.dao.AnalyserInfoDao;
import com.dwarfeng.judge.stack.dao.AnalyserVariableDao;
import com.dwarfeng.judge.stack.service.AnalyserVariableMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnalyserInfoCrudOperation implements BatchCrudOperation<LongIdKey, AnalyserInfo> {

    private final AnalyserInfoDao analyserInfoDao;
    private final AnalyserInfoCache analyserInfoCache;

    private final AnalyserVariableDao analyserVariableDao;
    private final AnalyserVariableCache analyserVariableCache;

    @Value("${cache.timeout.entity.analyser_info}")
    private long analyserInfoTimeout;

    public AnalyserInfoCrudOperation(
            AnalyserInfoDao analyserInfoDao,
            AnalyserInfoCache analyserInfoCache,
            AnalyserVariableDao analyserVariableDao,
            AnalyserVariableCache analyserVariableCache
    ) {
        this.analyserInfoDao = analyserInfoDao;
        this.analyserInfoCache = analyserInfoCache;
        this.analyserVariableDao = analyserVariableDao;
        this.analyserVariableCache = analyserVariableCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return analyserInfoCache.exists(key) || analyserInfoDao.exists(key);
    }

    @Override
    public AnalyserInfo get(LongIdKey key) throws Exception {
        if (analyserInfoCache.exists(key)) {
            return analyserInfoCache.get(key);
        } else {
            if (!analyserInfoDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            AnalyserInfo analyserInfo = analyserInfoDao.get(key);
            analyserInfoCache.push(analyserInfo, analyserInfoTimeout);
            return analyserInfo;
        }
    }

    @Override
    public LongIdKey insert(AnalyserInfo analyserInfo) throws Exception {
        analyserInfoCache.push(analyserInfo, analyserInfoTimeout);
        return analyserInfoDao.insert(analyserInfo);
    }

    @Override
    public void update(AnalyserInfo analyserInfo) throws Exception {
        analyserInfoCache.push(analyserInfo, analyserInfoTimeout);
        analyserInfoDao.update(analyserInfo);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 删除 与 分析器信息 相关的 分析器变量。
        List<AnalyserVariableKey> analyserVariableKeys = analyserVariableDao.lookup(
                AnalyserVariableMaintainService.CHILD_FOR_ANALYSER_INFO, new Object[]{key}
        ).stream().map(AnalyserVariable::getKey).collect(Collectors.toList());
        analyserVariableDao.batchDelete(analyserVariableKeys);
        analyserVariableCache.batchDelete(analyserVariableKeys);

        // 删除 分析器信息 自身。
        analyserInfoDao.delete(key);
        analyserInfoCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return analyserInfoCache.allExists(keys) || analyserInfoDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return analyserInfoCache.nonExists(keys) && analyserInfoDao.nonExists(keys);
    }

    @Override
    public List<AnalyserInfo> batchGet(List<LongIdKey> keys) throws Exception {
        if (analyserInfoCache.allExists(keys)) {
            return analyserInfoCache.batchGet(keys);
        } else {
            if (!analyserInfoDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<AnalyserInfo> analyserInfos = analyserInfoDao.batchGet(keys);
            analyserInfoCache.batchPush(analyserInfos, analyserInfoTimeout);
            return analyserInfos;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<AnalyserInfo> analyserInfos) throws Exception {
        analyserInfoCache.batchPush(analyserInfos, analyserInfoTimeout);
        return analyserInfoDao.batchInsert(analyserInfos);
    }

    @Override
    public void batchUpdate(List<AnalyserInfo> analyserInfos) throws Exception {
        analyserInfoCache.batchPush(analyserInfos, analyserInfoTimeout);
        analyserInfoDao.batchUpdate(analyserInfos);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
