package com.dwarfeng.judge.impl.service.operation;

import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.judge.impl.handler.FtpPathResolver;
import com.dwarfeng.judge.stack.bean.entity.AnalysisFileInfo;
import com.dwarfeng.judge.stack.cache.AnalysisFileInfoCache;
import com.dwarfeng.judge.stack.dao.AnalysisFileInfoDao;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnalysisFileInfoCrudOperation implements BatchCrudOperation<LongIdKey, AnalysisFileInfo> {

    private final AnalysisFileInfoDao analysisFileInfoDao;
    private final AnalysisFileInfoCache analysisFileInfoCache;

    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    @Value("${cache.timeout.entity.analysis_file_info}")
    private long analysisFileInfoTimeout;

    public AnalysisFileInfoCrudOperation(
            AnalysisFileInfoDao analysisFileInfoDao,
            AnalysisFileInfoCache analysisFileInfoCache, FtpHandler ftpHandler, FtpPathResolver ftpPathResolver
    ) {
        this.analysisFileInfoDao = analysisFileInfoDao;
        this.analysisFileInfoCache = analysisFileInfoCache;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return analysisFileInfoCache.exists(key) || analysisFileInfoDao.exists(key);
    }

    @Override
    public AnalysisFileInfo get(LongIdKey key) throws Exception {
        if (analysisFileInfoCache.exists(key)) {
            return analysisFileInfoCache.get(key);
        } else {
            if (!analysisFileInfoDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            AnalysisFileInfo analysisFileInfo = analysisFileInfoDao.get(key);
            analysisFileInfoCache.push(analysisFileInfo, analysisFileInfoTimeout);
            return analysisFileInfo;
        }
    }

    @Override
    public LongIdKey insert(AnalysisFileInfo analysisFileInfo) throws Exception {
        analysisFileInfoCache.push(analysisFileInfo, analysisFileInfoTimeout);
        return analysisFileInfoDao.insert(analysisFileInfo);
    }

    @Override
    public void update(AnalysisFileInfo analysisFileInfo) throws Exception {
        analysisFileInfoCache.push(analysisFileInfo, analysisFileInfoTimeout);
        analysisFileInfoDao.update(analysisFileInfo);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        String fileName = Long.toString(key.getLongId());
        // 如果存在分析结果文件文件，则删除文件。
        if (ftpHandler.existsFile(ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_FILE_FILE), fileName)) {
            ftpHandler.deleteFile(ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_FILE_FILE), fileName);
        }

        // 删除 分析结果文件信息 自身。
        analysisFileInfoDao.delete(key);
        analysisFileInfoCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return analysisFileInfoCache.allExists(keys) || analysisFileInfoDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return analysisFileInfoCache.nonExists(keys) && analysisFileInfoDao.nonExists(keys);
    }

    @Override
    public List<AnalysisFileInfo> batchGet(List<LongIdKey> keys) throws Exception {
        if (analysisFileInfoCache.allExists(keys)) {
            return analysisFileInfoCache.batchGet(keys);
        } else {
            if (!analysisFileInfoDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<AnalysisFileInfo> analysisFileInfos = analysisFileInfoDao.batchGet(keys);
            analysisFileInfoCache.batchPush(analysisFileInfos, analysisFileInfoTimeout);
            return analysisFileInfos;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<AnalysisFileInfo> analysisFileInfos) throws Exception {
        analysisFileInfoCache.batchPush(analysisFileInfos, analysisFileInfoTimeout);
        return analysisFileInfoDao.batchInsert(analysisFileInfos);
    }

    @Override
    public void batchUpdate(List<AnalysisFileInfo> analysisFileInfos) throws Exception {
        analysisFileInfoCache.batchPush(analysisFileInfos, analysisFileInfoTimeout);
        analysisFileInfoDao.batchUpdate(analysisFileInfos);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
