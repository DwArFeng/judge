package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.judge.stack.handler.AnalysisPicturePackItemFileOperateHandler;
import com.dwarfeng.judge.stack.service.AnalysisPicturePackItemFileOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class AnalysisPicturePackItemFileOperateServiceImpl implements AnalysisPicturePackItemFileOperateService {

    private final AnalysisPicturePackItemFileOperateHandler analysisPicturePackItemFileOperateHandler;

    private final ServiceExceptionMapper sem;

    public AnalysisPicturePackItemFileOperateServiceImpl(
            AnalysisPicturePackItemFileOperateHandler analysisPicturePackItemFileOperateHandler,
            ServiceExceptionMapper sem
    ) {
        this.analysisPicturePackItemFileOperateHandler = analysisPicturePackItemFileOperateHandler;
        this.sem = sem;
    }

    @Override
    public AnalysisPicturePackItemFile downloadFile(AnalysisPicturePackItemFileDownloadInfo info)
            throws ServiceException {
        try {
            return analysisPicturePackItemFileOperateHandler.downloadFile(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载分析结果包条目图片时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public AnalysisPicturePackItemFileStream downloadFileStream(AnalysisPicturePackItemFileStreamDownloadInfo info)
            throws ServiceException {
        try {
            return analysisPicturePackItemFileOperateHandler.downloadFileStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载分析结果包条目图片流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public AnalysisPicturePackItemThumbnail downloadThumbnail(AnalysisPicturePackItemThumbnailDownloadInfo info)
            throws ServiceException {
        try {
            return analysisPicturePackItemFileOperateHandler.downloadThumbnail(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载分析结果包条目图片缩略图时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
