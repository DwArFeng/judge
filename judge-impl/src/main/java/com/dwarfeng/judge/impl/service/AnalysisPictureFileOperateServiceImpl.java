package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.judge.stack.handler.AnalysisPictureFileOperateHandler;
import com.dwarfeng.judge.stack.service.AnalysisPictureFileOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class AnalysisPictureFileOperateServiceImpl implements AnalysisPictureFileOperateService {

    private final AnalysisPictureFileOperateHandler analysisPictureFileOperateHandler;

    private final ServiceExceptionMapper sem;

    public AnalysisPictureFileOperateServiceImpl(
            AnalysisPictureFileOperateHandler analysisPictureFileOperateHandler,
            ServiceExceptionMapper sem
    ) {
        this.analysisPictureFileOperateHandler = analysisPictureFileOperateHandler;
        this.sem = sem;
    }

    @Override
    public AnalysisPictureFile downloadFile(AnalysisPictureFileDownloadInfo info) throws ServiceException {
        try {
            return analysisPictureFileOperateHandler.downloadFile(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载分析结果图片时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public AnalysisPictureFileStream downloadFileStream(AnalysisPictureFileStreamDownloadInfo info)
            throws ServiceException {
        try {
            return analysisPictureFileOperateHandler.downloadFileStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载分析结果图片流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public AnalysisPictureThumbnail downloadThumbnail(AnalysisPictureThumbnailDownloadInfo info)
            throws ServiceException {
        try {
            return analysisPictureFileOperateHandler.downloadThumbnail(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载分析结果图片缩略图时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
