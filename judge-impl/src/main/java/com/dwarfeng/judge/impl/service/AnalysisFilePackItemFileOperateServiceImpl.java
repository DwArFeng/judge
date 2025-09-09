package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.dto.AnalysisFilePackItemFile;
import com.dwarfeng.judge.stack.bean.dto.AnalysisFilePackItemFileDownloadInfo;
import com.dwarfeng.judge.stack.bean.dto.AnalysisFilePackItemFileStream;
import com.dwarfeng.judge.stack.bean.dto.AnalysisFilePackItemFileStreamDownloadInfo;
import com.dwarfeng.judge.stack.handler.AnalysisFilePackItemFileOperateHandler;
import com.dwarfeng.judge.stack.service.AnalysisFilePackItemFileOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class AnalysisFilePackItemFileOperateServiceImpl implements AnalysisFilePackItemFileOperateService {

    private final AnalysisFilePackItemFileOperateHandler analysisFilePackItemFileOperateHandler;

    private final ServiceExceptionMapper sem;

    public AnalysisFilePackItemFileOperateServiceImpl(
            AnalysisFilePackItemFileOperateHandler analysisFilePackItemFileOperateHandler,
            ServiceExceptionMapper sem
    ) {
        this.analysisFilePackItemFileOperateHandler = analysisFilePackItemFileOperateHandler;
        this.sem = sem;
    }

    @Override
    public AnalysisFilePackItemFile downloadFile(AnalysisFilePackItemFileDownloadInfo info)
            throws ServiceException {
        try {
            return analysisFilePackItemFileOperateHandler.downloadFile(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载分析结果包条目文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public AnalysisFilePackItemFileStream downloadFileStream(AnalysisFilePackItemFileStreamDownloadInfo info)
            throws ServiceException {
        try {
            return analysisFilePackItemFileOperateHandler.downloadFileStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载分析结果包条目文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
