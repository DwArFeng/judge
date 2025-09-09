package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.dto.AnalysisFileFile;
import com.dwarfeng.judge.stack.bean.dto.AnalysisFileFileDownloadInfo;
import com.dwarfeng.judge.stack.bean.dto.AnalysisFileFileStream;
import com.dwarfeng.judge.stack.bean.dto.AnalysisFileFileStreamDownloadInfo;
import com.dwarfeng.judge.stack.handler.AnalysisFileFileOperateHandler;
import com.dwarfeng.judge.stack.service.AnalysisFileFileOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class AnalysisFileFileOperateServiceImpl implements AnalysisFileFileOperateService {

    private final AnalysisFileFileOperateHandler analysisFileFileOperateHandler;

    private final ServiceExceptionMapper sem;

    public AnalysisFileFileOperateServiceImpl(
            AnalysisFileFileOperateHandler analysisFileFileOperateHandler,
            ServiceExceptionMapper sem
    ) {
        this.analysisFileFileOperateHandler = analysisFileFileOperateHandler;
        this.sem = sem;
    }

    @Override
    public AnalysisFileFile downloadFile(AnalysisFileFileDownloadInfo info) throws ServiceException {
        try {
            return analysisFileFileOperateHandler.downloadFile(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载分析结果文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public AnalysisFileFileStream downloadFileStream(AnalysisFileFileStreamDownloadInfo info) throws ServiceException {
        try {
            return analysisFileFileOperateHandler.downloadFileStream(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下载分析结果文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
