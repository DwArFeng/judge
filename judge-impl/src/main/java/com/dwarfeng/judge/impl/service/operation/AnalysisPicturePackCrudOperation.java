package com.dwarfeng.judge.impl.service.operation;

import com.dwarfeng.judge.stack.bean.entity.AnalysisPicturePack;
import com.dwarfeng.judge.stack.bean.entity.AnalysisPicturePackItemInfo;
import com.dwarfeng.judge.stack.cache.AnalysisPicturePackCache;
import com.dwarfeng.judge.stack.dao.AnalysisPicturePackDao;
import com.dwarfeng.judge.stack.dao.AnalysisPicturePackItemInfoDao;
import com.dwarfeng.judge.stack.service.AnalysisPicturePackItemInfoMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnalysisPicturePackCrudOperation implements BatchCrudOperation<LongIdKey, AnalysisPicturePack> {

    private final AnalysisPicturePackDao analysisPicturePackDao;
    private final AnalysisPicturePackCache analysisPicturePackCache;

    private final AnalysisPicturePackItemInfoDao analysisPicturePackItemInfoDao;
    private final AnalysisPicturePackItemInfoCrudOperation analysisPicturePackItemInfoCrudOperation;

    @Value("${cache.timeout.entity.analysis_picture_pack}")
    private long analysisPicturePackTimeout;

    public AnalysisPicturePackCrudOperation(
            AnalysisPicturePackDao analysisPicturePackDao,
            AnalysisPicturePackCache analysisPicturePackCache,
            AnalysisPicturePackItemInfoDao analysisPicturePackItemInfoDao,
            AnalysisPicturePackItemInfoCrudOperation analysisPicturePackItemInfoCrudOperation
    ) {
        this.analysisPicturePackDao = analysisPicturePackDao;
        this.analysisPicturePackCache = analysisPicturePackCache;
        this.analysisPicturePackItemInfoDao = analysisPicturePackItemInfoDao;
        this.analysisPicturePackItemInfoCrudOperation = analysisPicturePackItemInfoCrudOperation;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return analysisPicturePackCache.exists(key) || analysisPicturePackDao.exists(key);
    }

    @Override
    public AnalysisPicturePack get(LongIdKey key) throws Exception {
        if (analysisPicturePackCache.exists(key)) {
            return analysisPicturePackCache.get(key);
        } else {
            if (!analysisPicturePackDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            AnalysisPicturePack analysisPicturePack = analysisPicturePackDao.get(key);
            analysisPicturePackCache.push(analysisPicturePack, analysisPicturePackTimeout);
            return analysisPicturePack;
        }
    }

    @Override
    public LongIdKey insert(AnalysisPicturePack analysisPicturePack) throws Exception {
        analysisPicturePackCache.push(analysisPicturePack, analysisPicturePackTimeout);
        return analysisPicturePackDao.insert(analysisPicturePack);
    }

    @Override
    public void update(AnalysisPicturePack analysisPicturePack) throws Exception {
        analysisPicturePackCache.push(analysisPicturePack, analysisPicturePackTimeout);
        analysisPicturePackDao.update(analysisPicturePack);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 删除与分析结果图片包相关的分析结果图片包条目的关联。
        List<LongIdKey> analysisPicturePackItemInfoKeys = analysisPicturePackItemInfoDao.lookup(
                AnalysisPicturePackItemInfoMaintainService.CHILD_FOR_PACK, new Object[]{key}
        ).stream().map(AnalysisPicturePackItemInfo::getKey).collect(Collectors.toList());
        analysisPicturePackItemInfoCrudOperation.batchDelete(analysisPicturePackItemInfoKeys);

        // 删除 分析结果图片包 自身。
        analysisPicturePackDao.delete(key);
        analysisPicturePackCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return analysisPicturePackCache.allExists(keys) || analysisPicturePackDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return analysisPicturePackCache.nonExists(keys) && analysisPicturePackDao.nonExists(keys);
    }

    @Override
    public List<AnalysisPicturePack> batchGet(List<LongIdKey> keys) throws Exception {
        if (analysisPicturePackCache.allExists(keys)) {
            return analysisPicturePackCache.batchGet(keys);
        } else {
            if (!analysisPicturePackDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<AnalysisPicturePack> analysisPicturePacks = analysisPicturePackDao.batchGet(keys);
            analysisPicturePackCache.batchPush(analysisPicturePacks, analysisPicturePackTimeout);
            return analysisPicturePacks;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<AnalysisPicturePack> analysisPicturePacks) throws Exception {
        analysisPicturePackCache.batchPush(analysisPicturePacks, analysisPicturePackTimeout);
        return analysisPicturePackDao.batchInsert(analysisPicturePacks);
    }

    @Override
    public void batchUpdate(List<AnalysisPicturePack> analysisPicturePacks) throws Exception {
        analysisPicturePackCache.batchPush(analysisPicturePacks, analysisPicturePackTimeout);
        analysisPicturePackDao.batchUpdate(analysisPicturePacks);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
