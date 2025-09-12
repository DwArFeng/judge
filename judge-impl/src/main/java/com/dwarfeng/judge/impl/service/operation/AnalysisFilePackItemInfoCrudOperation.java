package com.dwarfeng.judge.impl.service.operation;

import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.judge.impl.handler.FtpPathResolver;
import com.dwarfeng.judge.stack.bean.entity.AnalysisFilePackItemInfo;
import com.dwarfeng.judge.stack.cache.AnalysisFilePackItemInfoCache;
import com.dwarfeng.judge.stack.dao.AnalysisFilePackItemInfoDao;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnalysisFilePackItemInfoCrudOperation implements BatchCrudOperation<LongIdKey, AnalysisFilePackItemInfo> {

    private final AnalysisFilePackItemInfoDao analysisFilePackItemInfoDao;
    private final AnalysisFilePackItemInfoCache analysisFilePackItemInfoCache;

    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    @Value("${cache.timeout.entity.analysis_file_pack_item_info}")
    private long analysisFilePackItemInfoTimeout;

    public AnalysisFilePackItemInfoCrudOperation(
            AnalysisFilePackItemInfoDao analysisFilePackItemInfoDao,
            AnalysisFilePackItemInfoCache analysisFilePackItemInfoCache,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver
    ) {
        this.analysisFilePackItemInfoDao = analysisFilePackItemInfoDao;
        this.analysisFilePackItemInfoCache = analysisFilePackItemInfoCache;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return analysisFilePackItemInfoCache.exists(key) || analysisFilePackItemInfoDao.exists(key);
    }

    @Override
    public AnalysisFilePackItemInfo get(LongIdKey key) throws Exception {
        if (analysisFilePackItemInfoCache.exists(key)) {
            return analysisFilePackItemInfoCache.get(key);
        } else {
            if (!analysisFilePackItemInfoDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            AnalysisFilePackItemInfo analysisFilePackItemInfo = analysisFilePackItemInfoDao.get(key);
            analysisFilePackItemInfoCache.push(analysisFilePackItemInfo, analysisFilePackItemInfoTimeout);
            return analysisFilePackItemInfo;
        }
    }

    @Override
    public LongIdKey insert(AnalysisFilePackItemInfo analysisFilePackItemInfo) throws Exception {
        analysisFilePackItemInfoCache.push(analysisFilePackItemInfo, analysisFilePackItemInfoTimeout);
        return analysisFilePackItemInfoDao.insert(analysisFilePackItemInfo);
    }

    @Override
    public void update(AnalysisFilePackItemInfo analysisFilePackItemInfo) throws Exception {
        analysisFilePackItemInfoCache.push(analysisFilePackItemInfo, analysisFilePackItemInfoTimeout);
        analysisFilePackItemInfoDao.update(analysisFilePackItemInfo);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        String fileName = Long.toString(key.getLongId());
        // 如果存在分析结果文件包条目文件，则删除文件。
        if (ftpHandler.existsFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_FILE_PACK_ITEM_FILE), fileName
        )) {
            ftpHandler.deleteFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_FILE_PACK_ITEM_FILE), fileName
            );
        }

        // 删除 分析结果文件包条目信息 自身。
        analysisFilePackItemInfoDao.delete(key);
        analysisFilePackItemInfoCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return analysisFilePackItemInfoCache.allExists(keys) || analysisFilePackItemInfoDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return analysisFilePackItemInfoCache.nonExists(keys) && analysisFilePackItemInfoDao.nonExists(keys);
    }

    @Override
    public List<AnalysisFilePackItemInfo> batchGet(List<LongIdKey> keys) throws Exception {
        if (analysisFilePackItemInfoCache.allExists(keys)) {
            return analysisFilePackItemInfoCache.batchGet(keys);
        } else {
            if (!analysisFilePackItemInfoDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<AnalysisFilePackItemInfo> analysisFilePackItemInfos = analysisFilePackItemInfoDao.batchGet(keys);
            analysisFilePackItemInfoCache.batchPush(analysisFilePackItemInfos, analysisFilePackItemInfoTimeout);
            return analysisFilePackItemInfos;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<AnalysisFilePackItemInfo> analysisFilePackItemInfos) throws Exception {
        analysisFilePackItemInfoCache.batchPush(analysisFilePackItemInfos, analysisFilePackItemInfoTimeout);
        return analysisFilePackItemInfoDao.batchInsert(analysisFilePackItemInfos);
    }

    @Override
    public void batchUpdate(List<AnalysisFilePackItemInfo> analysisFilePackItemInfos) throws Exception {
        analysisFilePackItemInfoCache.batchPush(analysisFilePackItemInfos, analysisFilePackItemInfoTimeout);
        analysisFilePackItemInfoDao.batchUpdate(analysisFilePackItemInfos);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
