package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.judge.stack.bean.entity.AnalysisFileInfo;
import com.dwarfeng.judge.stack.handler.AnalysisFileFileOperateHandler;
import com.dwarfeng.judge.stack.service.AnalysisFileInfoMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;

@Component
public class AnalysisFileFileOperateHandlerImpl implements AnalysisFileFileOperateHandler {

    private final AnalysisFileInfoMaintainService analysisFileInfoMaintainService;

    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    private final HandlerValidator handlerValidator;

    public AnalysisFileFileOperateHandlerImpl(
            AnalysisFileInfoMaintainService analysisFileInfoMaintainService,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver,
            HandlerValidator handlerValidator
    ) {
        this.analysisFileInfoMaintainService = analysisFileInfoMaintainService;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public AnalysisFileFile downloadFile(AnalysisFileFileDownloadInfo info) throws HandlerException {
        try {
            return downloadFile0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private AnalysisFileFile downloadFile0(AnalysisFileFileDownloadInfo info) throws Exception {
        // 展开参数。
        LongIdKey analysisFileInfoKey = info.getAnalysisFileInfoKey();

        // 确认分析结果文件文件存在。
        handlerValidator.makeSureAnalysisFileExists(analysisFileInfoKey);

        AnalysisFileInfo analysisFileInfo = analysisFileInfoMaintainService.get(analysisFileInfoKey);

        // 下载分析结果文件文件。
        byte[] content = ftpHandler.retrieveFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_FILE_FILE),
                getFileName(analysisFileInfoKey)
        );

        return new AnalysisFileFile(analysisFileInfo.getOriginName(), content);
    }

    @Override
    public AnalysisFileFileStream downloadFileStream(AnalysisFileFileStreamDownloadInfo info)
            throws HandlerException {
        try {
            return downloadFileStream0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private AnalysisFileFileStream downloadFileStream0(AnalysisFileFileStreamDownloadInfo info) throws Exception {
        // 展开参数。
        LongIdKey analysisFileInfoKey = info.getAnalysisFileInfoKey();

        // 确认分析结果文件文件存在。
        handlerValidator.makeSureAnalysisFileExists(analysisFileInfoKey);

        AnalysisFileInfo analysisFileInfo = analysisFileInfoMaintainService.get(analysisFileInfoKey);

        // 下载文件文件流。
        InputStream content = ftpHandler.openInputStream(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_FILE_FILE),
                analysisFileInfo.getOriginName()
        );

        // 构造 AnalysisFileFileStream 并返回。
        return new AnalysisFileFileStream(
                analysisFileInfo.getOriginName(), analysisFileInfo.getLength(), content
        );
    }

    @Override
    public AnalysisFileFileUploadResult uploadFile(AnalysisFileFileUploadInfo info) throws HandlerException {
        try {
            return uploadFile0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private AnalysisFileFileUploadResult uploadFile0(AnalysisFileFileUploadInfo info) throws Exception {
        // 展开参数。
        LongIdKey analysisFileKey = info.getAnalysisFileKey();
        String originName = info.getOriginName();
        byte[] content = info.getContent();

        // 定义文件名。
        String fileName = getFileName(analysisFileKey);

        // 存储文件。
        ftpHandler.storeFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_FILE_FILE),
                fileName, content
        );

        // 根据参数生成 AnalysisFileInfo 对象。
        AnalysisFileInfo analysisFileInfo = new AnalysisFileInfo();
        analysisFileInfo.setKey(analysisFileKey);
        analysisFileInfo.setOriginName(originName);
        analysisFileInfo.setLength((long) content.length);
        analysisFileInfo.setRemark("通过操作处理器上传");

        // 插入或更新 AnalysisFileInfo 对象。
        analysisFileInfoMaintainService.insertOrUpdate(analysisFileInfo);

        // 返回上传结果。
        return new AnalysisFileFileUploadResult(analysisFileKey);
    }

    @Override
    public AnalysisFileFileUploadResult uploadFileStream(AnalysisFileFileStreamUploadInfo info)
            throws HandlerException {
        try {
            return uploadFileByStream0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private AnalysisFileFileUploadResult uploadFileByStream0(AnalysisFileFileStreamUploadInfo info) throws Exception {
        // 展开参数。
        LongIdKey analysisFileKey = info.getAnalysisFileKey();
        String originName = info.getOriginName();
        long length = info.getLength();

        // 定义文件名。
        String fileName = getFileName(analysisFileKey);

        // 上传文件。
        InputStream cin = info.getContent();
        try (OutputStream fout = ftpHandler.openOutputStream(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_FILE_FILE), fileName
        )) {
            IOUtil.trans(cin, fout, 4096);
        }

        // 根据参数生成 AnalysisFileInfo 对象。
        AnalysisFileInfo analysisFileInfo = new AnalysisFileInfo();
        analysisFileInfo.setKey(analysisFileKey);
        analysisFileInfo.setOriginName(originName);
        analysisFileInfo.setLength(length);
        analysisFileInfo.setRemark("通过操作处理器上传");

        // 插入或更新 AnalysisFileInfo 对象。
        analysisFileInfoMaintainService.insertOrUpdate(analysisFileInfo);

        // 返回上传结果。
        return new AnalysisFileFileUploadResult(analysisFileKey);
    }

    private String getFileName(LongIdKey analysisFileFileKey) {
        return Long.toString(analysisFileFileKey.getLongId());
    }
}
