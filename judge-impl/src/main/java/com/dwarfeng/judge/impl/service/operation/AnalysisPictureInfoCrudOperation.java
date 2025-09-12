package com.dwarfeng.judge.impl.service.operation;

import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.judge.impl.handler.FtpPathResolver;
import com.dwarfeng.judge.stack.bean.entity.AnalysisPictureInfo;
import com.dwarfeng.judge.stack.cache.AnalysisPictureInfoCache;
import com.dwarfeng.judge.stack.dao.AnalysisPictureInfoDao;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnalysisPictureInfoCrudOperation implements BatchCrudOperation<LongIdKey, AnalysisPictureInfo> {

    private final AnalysisPictureInfoDao analysisPictureInfoDao;
    private final AnalysisPictureInfoCache analysisPictureInfoCache;

    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    @Value("${cache.timeout.entity.analysis_picture_info}")
    private long analysisPictureInfoTimeout;

    public AnalysisPictureInfoCrudOperation(
            AnalysisPictureInfoDao analysisPictureInfoDao,
            AnalysisPictureInfoCache analysisPictureInfoCache, FtpHandler ftpHandler, FtpPathResolver ftpPathResolver
    ) {
        this.analysisPictureInfoDao = analysisPictureInfoDao;
        this.analysisPictureInfoCache = analysisPictureInfoCache;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return analysisPictureInfoCache.exists(key) || analysisPictureInfoDao.exists(key);
    }

    @Override
    public AnalysisPictureInfo get(LongIdKey key) throws Exception {
        if (analysisPictureInfoCache.exists(key)) {
            return analysisPictureInfoCache.get(key);
        } else {
            if (!analysisPictureInfoDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            AnalysisPictureInfo analysisPictureInfo = analysisPictureInfoDao.get(key);
            analysisPictureInfoCache.push(analysisPictureInfo, analysisPictureInfoTimeout);
            return analysisPictureInfo;
        }
    }

    @Override
    public LongIdKey insert(AnalysisPictureInfo analysisPictureInfo) throws Exception {
        analysisPictureInfoCache.push(analysisPictureInfo, analysisPictureInfoTimeout);
        return analysisPictureInfoDao.insert(analysisPictureInfo);
    }

    @Override
    public void update(AnalysisPictureInfo analysisPictureInfo) throws Exception {
        analysisPictureInfoCache.push(analysisPictureInfo, analysisPictureInfoTimeout);
        analysisPictureInfoDao.update(analysisPictureInfo);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        String fileName = Long.toString(key.getLongId());
        // 如果存在分析结果图片文件，则删除文件。
        if (ftpHandler.existsFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_FILE), fileName
        )) {
            ftpHandler.deleteFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_FILE), fileName
            );
        }

        // 如果存在分析结果图片缩略图，则删除缩略图。
        if (ftpHandler.existsFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_THUMBNAIL), fileName
        )) {
            ftpHandler.deleteFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_THUMBNAIL), fileName
            );
        }

        // 删除 分析结果图片信息 自身。
        analysisPictureInfoDao.delete(key);
        analysisPictureInfoCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return analysisPictureInfoCache.allExists(keys) || analysisPictureInfoDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return analysisPictureInfoCache.nonExists(keys) && analysisPictureInfoDao.nonExists(keys);
    }

    @Override
    public List<AnalysisPictureInfo> batchGet(List<LongIdKey> keys) throws Exception {
        if (analysisPictureInfoCache.allExists(keys)) {
            return analysisPictureInfoCache.batchGet(keys);
        } else {
            if (!analysisPictureInfoDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<AnalysisPictureInfo> analysisPictureInfos = analysisPictureInfoDao.batchGet(keys);
            analysisPictureInfoCache.batchPush(analysisPictureInfos, analysisPictureInfoTimeout);
            return analysisPictureInfos;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<AnalysisPictureInfo> analysisPictureInfos) throws Exception {
        analysisPictureInfoCache.batchPush(analysisPictureInfos, analysisPictureInfoTimeout);
        return analysisPictureInfoDao.batchInsert(analysisPictureInfos);
    }

    @Override
    public void batchUpdate(List<AnalysisPictureInfo> analysisPictureInfos) throws Exception {
        analysisPictureInfoCache.batchPush(analysisPictureInfos, analysisPictureInfoTimeout);
        analysisPictureInfoDao.batchUpdate(analysisPictureInfos);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
