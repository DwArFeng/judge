package com.dwarfeng.judge.impl.service.operation;

import com.dwarfeng.judge.stack.bean.entity.AnalysisFilePack;
import com.dwarfeng.judge.stack.bean.entity.AnalysisFilePackItemInfo;
import com.dwarfeng.judge.stack.cache.AnalysisFilePackCache;
import com.dwarfeng.judge.stack.dao.AnalysisFilePackDao;
import com.dwarfeng.judge.stack.dao.AnalysisFilePackItemInfoDao;
import com.dwarfeng.judge.stack.service.AnalysisFilePackItemInfoMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnalysisFilePackCrudOperation implements BatchCrudOperation<LongIdKey, AnalysisFilePack> {

    private final AnalysisFilePackDao analysisFilePackDao;
    private final AnalysisFilePackCache analysisFilePackCache;

    private final AnalysisFilePackItemInfoDao analysisFilePackItemInfoDao;
    private final AnalysisFilePackItemInfoCrudOperation analysisFilePackItemInfoCrudOperation;

    @Value("${cache.timeout.entity.analysis_file_pack}")
    private long analysisFilePackTimeout;

    public AnalysisFilePackCrudOperation(
            AnalysisFilePackDao analysisFilePackDao,
            AnalysisFilePackCache analysisFilePackCache,
            AnalysisFilePackItemInfoDao analysisFilePackItemInfoDao,
            AnalysisFilePackItemInfoCrudOperation analysisFilePackItemInfoCrudOperation
    ) {
        this.analysisFilePackDao = analysisFilePackDao;
        this.analysisFilePackCache = analysisFilePackCache;
        this.analysisFilePackItemInfoDao = analysisFilePackItemInfoDao;
        this.analysisFilePackItemInfoCrudOperation = analysisFilePackItemInfoCrudOperation;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return analysisFilePackCache.exists(key) || analysisFilePackDao.exists(key);
    }

    @Override
    public AnalysisFilePack get(LongIdKey key) throws Exception {
        if (analysisFilePackCache.exists(key)) {
            return analysisFilePackCache.get(key);
        } else {
            if (!analysisFilePackDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            AnalysisFilePack analysisFilePack = analysisFilePackDao.get(key);
            analysisFilePackCache.push(analysisFilePack, analysisFilePackTimeout);
            return analysisFilePack;
        }
    }

    @Override
    public LongIdKey insert(AnalysisFilePack analysisFilePack) throws Exception {
        analysisFilePackCache.push(analysisFilePack, analysisFilePackTimeout);
        return analysisFilePackDao.insert(analysisFilePack);
    }

    @Override
    public void update(AnalysisFilePack analysisFilePack) throws Exception {
        analysisFilePackCache.push(analysisFilePack, analysisFilePackTimeout);
        analysisFilePackDao.update(analysisFilePack);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 删除与分析结果文件包相关的分析结果文件包条目的关联。
        List<LongIdKey> analysisFilePackItemInfoKeys = analysisFilePackItemInfoDao.lookup(
                AnalysisFilePackItemInfoMaintainService.CHILD_FOR_PACK, new Object[]{key}
        ).stream().map(AnalysisFilePackItemInfo::getKey).collect(Collectors.toList());
        analysisFilePackItemInfoCrudOperation.batchDelete(analysisFilePackItemInfoKeys);

        // 删除 分析结果文件包 自身。
        analysisFilePackDao.delete(key);
        analysisFilePackCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return analysisFilePackCache.allExists(keys) || analysisFilePackDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return analysisFilePackCache.nonExists(keys) && analysisFilePackDao.nonExists(keys);
    }

    @Override
    public List<AnalysisFilePack> batchGet(List<LongIdKey> keys) throws Exception {
        if (analysisFilePackCache.allExists(keys)) {
            return analysisFilePackCache.batchGet(keys);
        } else {
            if (!analysisFilePackDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<AnalysisFilePack> analysisFilePacks = analysisFilePackDao.batchGet(keys);
            analysisFilePackCache.batchPush(analysisFilePacks, analysisFilePackTimeout);
            return analysisFilePacks;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<AnalysisFilePack> analysisFilePacks) throws Exception {
        analysisFilePackCache.batchPush(analysisFilePacks, analysisFilePackTimeout);
        return analysisFilePackDao.batchInsert(analysisFilePacks);
    }

    @Override
    public void batchUpdate(List<AnalysisFilePack> analysisFilePacks) throws Exception {
        analysisFilePackCache.batchPush(analysisFilePacks, analysisFilePackTimeout);
        analysisFilePackDao.batchUpdate(analysisFilePacks);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
