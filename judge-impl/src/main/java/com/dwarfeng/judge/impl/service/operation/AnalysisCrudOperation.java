package com.dwarfeng.judge.impl.service.operation;

import com.dwarfeng.judge.stack.bean.entity.Analysis;
import com.dwarfeng.judge.stack.bean.key.AnalysisKey;
import com.dwarfeng.judge.stack.cache.AnalysisCache;
import com.dwarfeng.judge.stack.dao.*;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class AnalysisCrudOperation implements BatchCrudOperation<AnalysisKey, Analysis> {

    private final AnalysisDao analysisDao;
    private final AnalysisCache analysisCache;

    private final AnalysisFileInfoDao analysisFileInfoDao;
    private final AnalysisFileInfoCrudOperation analysisFileInfoCrudOperation;

    private final AnalysisFilePackDao analysisFilePackDao;
    private final AnalysisFilePackCrudOperation analysisFilePackCrudOperation;

    private final AnalysisPictureInfoDao analysisPictureInfoDao;
    private final AnalysisPictureInfoCrudOperation analysisPictureInfoCrudOperation;

    private final AnalysisPicturePackDao analysisPicturePackDao;
    private final AnalysisPicturePackCrudOperation analysisPicturePackCrudOperation;

    @Value("${cache.timeout.entity.analysis}")
    private long analysisTimeout;

    public AnalysisCrudOperation(
            AnalysisDao analysisDao,
            AnalysisCache analysisCache,
            AnalysisFileInfoDao analysisFileInfoDao,
            AnalysisFileInfoCrudOperation analysisFileInfoCrudOperation,
            AnalysisFilePackDao analysisFilePackDao,
            AnalysisFilePackCrudOperation analysisFilePackCrudOperation,
            AnalysisPictureInfoDao analysisPictureInfoDao,
            AnalysisPictureInfoCrudOperation analysisPictureInfoCrudOperation,
            AnalysisPicturePackDao analysisPicturePackDao,
            AnalysisPicturePackCrudOperation analysisPicturePackCrudOperation
    ) {
        this.analysisDao = analysisDao;
        this.analysisCache = analysisCache;
        this.analysisFileInfoDao = analysisFileInfoDao;
        this.analysisFileInfoCrudOperation = analysisFileInfoCrudOperation;
        this.analysisFilePackDao = analysisFilePackDao;
        this.analysisFilePackCrudOperation = analysisFilePackCrudOperation;
        this.analysisPictureInfoDao = analysisPictureInfoDao;
        this.analysisPictureInfoCrudOperation = analysisPictureInfoCrudOperation;
        this.analysisPicturePackDao = analysisPicturePackDao;
        this.analysisPicturePackCrudOperation = analysisPicturePackCrudOperation;
    }

    @Override
    public boolean exists(AnalysisKey key) throws Exception {
        return analysisCache.exists(key) || analysisDao.exists(key);
    }

    @Override
    public Analysis get(AnalysisKey key) throws Exception {
        if (analysisCache.exists(key)) {
            return analysisCache.get(key);
        } else {
            if (!analysisDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            Analysis analysis = analysisDao.get(key);
            analysisCache.push(analysis, analysisTimeout);
            return analysis;
        }
    }

    @Override
    public AnalysisKey insert(Analysis analysis) throws Exception {
        analysisCache.push(analysis, analysisTimeout);
        return analysisDao.insert(analysis);
    }

    @Override
    public void update(Analysis analysis) throws Exception {
        analysisCache.push(analysis, analysisTimeout);
        analysisDao.update(analysis);
    }

    @Override
    public void delete(AnalysisKey key) throws Exception {
        // 获取当前 分析结果 信息。
        Analysis analysis = analysisDao.get(key);

        // 删除 与 分析结果 相关的 分析结果文件信息。
        if (Objects.nonNull(analysis.getFileValue())) {
            LongIdKey fileInfoKey = new LongIdKey(analysis.getFileValue());
            if (analysisFileInfoDao.exists(fileInfoKey)) {
                analysisFileInfoCrudOperation.delete(fileInfoKey);
            }
        }

        // 删除 与 分析结果 相关的 分析结果文件包。
        if (Objects.nonNull(analysis.getFilePackValue())) {
            LongIdKey filePackKey = new LongIdKey(analysis.getFilePackValue());
            if (analysisFilePackDao.exists(filePackKey)) {
                analysisFilePackCrudOperation.delete(filePackKey);
            }
        }

        // 删除 与 分析结果 相关的 分析结果图片信息。
        if (Objects.nonNull(analysis.getPictureValue())) {
            LongIdKey pictureInfoKey = new LongIdKey(analysis.getPictureValue());
            if (analysisPictureInfoDao.exists(pictureInfoKey)) {
                analysisPictureInfoCrudOperation.delete(pictureInfoKey);
            }
        }

        // 删除 与 分析结果 相关的 分析结果图片包。
        if (Objects.nonNull(analysis.getPicturePackValue())) {
            LongIdKey picturePackKey = new LongIdKey(analysis.getPicturePackValue());
            if (analysisPicturePackDao.exists(picturePackKey)) {
                analysisPicturePackCrudOperation.delete(picturePackKey);
            }
        }

        // 删除 分析结果 自身。
        analysisDao.delete(key);
        analysisCache.delete(key);
    }

    @Override
    public boolean allExists(List<AnalysisKey> keys) throws Exception {
        return analysisCache.allExists(keys) || analysisDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<AnalysisKey> keys) throws Exception {
        return analysisCache.nonExists(keys) && analysisDao.nonExists(keys);
    }

    @Override
    public List<Analysis> batchGet(List<AnalysisKey> keys) throws Exception {
        if (analysisCache.allExists(keys)) {
            return analysisCache.batchGet(keys);
        } else {
            if (!analysisDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<Analysis> analysises = analysisDao.batchGet(keys);
            analysisCache.batchPush(analysises, analysisTimeout);
            return analysises;
        }
    }

    @Override
    public List<AnalysisKey> batchInsert(List<Analysis> analysises) throws Exception {
        analysisCache.batchPush(analysises, analysisTimeout);
        return analysisDao.batchInsert(analysises);
    }

    @Override
    public void batchUpdate(List<Analysis> analysises) throws Exception {
        analysisCache.batchPush(analysises, analysisTimeout);
        analysisDao.batchUpdate(analysises);
    }

    @Override
    public void batchDelete(List<AnalysisKey> keys) throws Exception {
        for (AnalysisKey key : keys) {
            delete(key);
        }
    }
}
