package com.dwarfeng.judge.impl.service.operation;

import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.judge.impl.handler.FtpPathResolver;
import com.dwarfeng.judge.stack.bean.entity.AnalysisPicturePackItemInfo;
import com.dwarfeng.judge.stack.cache.AnalysisPicturePackItemInfoCache;
import com.dwarfeng.judge.stack.dao.AnalysisPicturePackItemInfoDao;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnalysisPicturePackItemInfoCrudOperation implements
        BatchCrudOperation<LongIdKey, AnalysisPicturePackItemInfo> {

    private final AnalysisPicturePackItemInfoDao analysisPicturePackItemInfoDao;
    private final AnalysisPicturePackItemInfoCache analysisPicturePackItemInfoCache;

    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    @Value("${cache.timeout.entity.analysis_picture_pack_item_info}")
    private long analysisPicturePackItemInfoTimeout;

    public AnalysisPicturePackItemInfoCrudOperation(
            AnalysisPicturePackItemInfoDao analysisPicturePackItemInfoDao,
            AnalysisPicturePackItemInfoCache analysisPicturePackItemInfoCache,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver
    ) {
        this.analysisPicturePackItemInfoDao = analysisPicturePackItemInfoDao;
        this.analysisPicturePackItemInfoCache = analysisPicturePackItemInfoCache;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return analysisPicturePackItemInfoCache.exists(key) || analysisPicturePackItemInfoDao.exists(key);
    }

    @Override
    public AnalysisPicturePackItemInfo get(LongIdKey key) throws Exception {
        if (analysisPicturePackItemInfoCache.exists(key)) {
            return analysisPicturePackItemInfoCache.get(key);
        } else {
            if (!analysisPicturePackItemInfoDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            AnalysisPicturePackItemInfo analysisPicturePackItemInfo = analysisPicturePackItemInfoDao.get(key);
            analysisPicturePackItemInfoCache.push(analysisPicturePackItemInfo, analysisPicturePackItemInfoTimeout);
            return analysisPicturePackItemInfo;
        }
    }

    @Override
    public LongIdKey insert(AnalysisPicturePackItemInfo analysisPicturePackItemInfo) throws Exception {
        analysisPicturePackItemInfoCache.push(analysisPicturePackItemInfo, analysisPicturePackItemInfoTimeout);
        return analysisPicturePackItemInfoDao.insert(analysisPicturePackItemInfo);
    }

    @Override
    public void update(AnalysisPicturePackItemInfo analysisPicturePackItemInfo) throws Exception {
        analysisPicturePackItemInfoCache.push(analysisPicturePackItemInfo, analysisPicturePackItemInfoTimeout);
        analysisPicturePackItemInfoDao.update(analysisPicturePackItemInfo);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        String fileName = Long.toString(key.getLongId());
        // 如果存在分析结果图片包条目文件，则删除文件。
        if (ftpHandler.existsFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_PACK_ITEM_FILE), fileName
        )) {
            ftpHandler.deleteFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_PACK_ITEM_FILE), fileName
            );
        }

        // 如果存在分析结果图片包条目缩略图，则删除缩略图。
        if (ftpHandler.existsFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_PACK_ITEM_THUMBNAIL), fileName
        )) {
            ftpHandler.deleteFile(
                    ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_PACK_ITEM_THUMBNAIL),
                    fileName
            );
        }

        // 删除 分析结果图片包条目信息 自身。
        analysisPicturePackItemInfoDao.delete(key);
        analysisPicturePackItemInfoCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return analysisPicturePackItemInfoCache.allExists(keys) || analysisPicturePackItemInfoDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return analysisPicturePackItemInfoCache.nonExists(keys) && analysisPicturePackItemInfoDao.nonExists(keys);
    }

    @Override
    public List<AnalysisPicturePackItemInfo> batchGet(List<LongIdKey> keys) throws Exception {
        if (analysisPicturePackItemInfoCache.allExists(keys)) {
            return analysisPicturePackItemInfoCache.batchGet(keys);
        } else {
            if (!analysisPicturePackItemInfoDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<AnalysisPicturePackItemInfo> analysisPicturePackItemInfos =
                    analysisPicturePackItemInfoDao.batchGet(keys);
            analysisPicturePackItemInfoCache.batchPush(
                    analysisPicturePackItemInfos, analysisPicturePackItemInfoTimeout
            );
            return analysisPicturePackItemInfos;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<AnalysisPicturePackItemInfo> analysisPicturePackItemInfos)
            throws Exception {
        analysisPicturePackItemInfoCache.batchPush(analysisPicturePackItemInfos, analysisPicturePackItemInfoTimeout);
        return analysisPicturePackItemInfoDao.batchInsert(analysisPicturePackItemInfos);
    }

    @Override
    public void batchUpdate(List<AnalysisPicturePackItemInfo> analysisPicturePackItemInfos) throws Exception {
        analysisPicturePackItemInfoCache.batchPush(analysisPicturePackItemInfos, analysisPicturePackItemInfoTimeout);
        analysisPicturePackItemInfoDao.batchUpdate(analysisPicturePackItemInfos);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
