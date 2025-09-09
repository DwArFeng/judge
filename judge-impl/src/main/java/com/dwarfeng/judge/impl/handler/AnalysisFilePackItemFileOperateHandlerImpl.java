package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.judge.stack.bean.entity.AnalysisFilePack;
import com.dwarfeng.judge.stack.bean.entity.AnalysisFilePackItemInfo;
import com.dwarfeng.judge.stack.handler.AnalysisFilePackItemFileOperateHandler;
import com.dwarfeng.judge.stack.service.AnalysisFilePackItemInfoMaintainService;
import com.dwarfeng.judge.stack.service.AnalysisFilePackMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.generation.KeyGenerator;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;

@Component
public class AnalysisFilePackItemFileOperateHandlerImpl implements AnalysisFilePackItemFileOperateHandler {

    private final AnalysisFilePackItemInfoMaintainService analysisFilePackItemInfoMaintainService;

    private final AnalysisFilePackMaintainService analysisFilePackMaintainService;

    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    private final KeyGenerator<LongIdKey> keyGenerator;

    private final HandlerValidator handlerValidator;

    public AnalysisFilePackItemFileOperateHandlerImpl(
            AnalysisFilePackItemInfoMaintainService analysisFilePackItemInfoMaintainService,
            AnalysisFilePackMaintainService analysisFilePackMaintainService,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver,
            KeyGenerator<LongIdKey> keyGenerator,
            HandlerValidator handlerValidator
    ) {
        this.analysisFilePackItemInfoMaintainService = analysisFilePackItemInfoMaintainService;
        this.analysisFilePackMaintainService = analysisFilePackMaintainService;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
        this.keyGenerator = keyGenerator;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public AnalysisFilePackItemFile downloadFile(AnalysisFilePackItemFileDownloadInfo info)
            throws HandlerException {
        try {
            return downloadFile0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private AnalysisFilePackItemFile downloadFile0(AnalysisFilePackItemFileDownloadInfo info) throws Exception {
        // 展开参数。
        LongIdKey analysisFilePackItemKey = info.getAnalysisFilePackItemKey();

        // 确认分析结果文件包条目文件存在。
        handlerValidator.makeSureAnalysisFilePackItemExists(analysisFilePackItemKey);

        AnalysisFilePackItemInfo analysisFilePackItemInfo =
                analysisFilePackItemInfoMaintainService.get(analysisFilePackItemKey);

        // 下载分析结果文件包条目文件。
        byte[] content = ftpHandler.retrieveFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_FILE_PACK_ITEM_FILE),
                getFileName(analysisFilePackItemKey)
        );

        return new AnalysisFilePackItemFile(analysisFilePackItemInfo.getOriginName(), content);
    }

    @Override
    public AnalysisFilePackItemFileStream downloadFileStream(AnalysisFilePackItemFileStreamDownloadInfo info)
            throws HandlerException {
        try {
            return downloadFileStream0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private AnalysisFilePackItemFileStream downloadFileStream0(AnalysisFilePackItemFileStreamDownloadInfo info)
            throws Exception {
        // 展开参数。
        LongIdKey analysisFilePackItemKey = info.getAnalysisFilePackItemKey();

        // 确认分析结果文件包条目文件存在。
        handlerValidator.makeSureAnalysisFilePackItemExists(analysisFilePackItemKey);

        AnalysisFilePackItemInfo analysisFilePackItemInfo =
                analysisFilePackItemInfoMaintainService.get(analysisFilePackItemKey);

        // 下载文件文件流。
        InputStream content = ftpHandler.openInputStream(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_FILE_PACK_ITEM_FILE),
                analysisFilePackItemInfo.getOriginName()
        );

        // 构造 AnalysisFilePackItemFileStream 并返回。
        return new AnalysisFilePackItemFileStream(
                analysisFilePackItemInfo.getOriginName(), analysisFilePackItemInfo.getLength(), content
        );
    }

    @Override
    public LongIdKey uploadFile(AnalysisFilePackItemFileUploadInfo info) throws HandlerException {
        try {
            return uploadFile0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private LongIdKey uploadFile0(AnalysisFilePackItemFileUploadInfo info) throws Exception {
        // 展开参数。
        LongIdKey analysisFilePackKey = info.getAnalysisFilePackKey();
        String originName = info.getOriginName();
        byte[] content = info.getContent();

        // 确认分析结果文件包存在。
        handlerValidator.makeSureAnalysisFilePackExists(analysisFilePackKey);

        // 生成新的分析结果文件包条目文件主键。
        LongIdKey analysisFilePackItemKey = keyGenerator.generate();

        // 定义文件名。
        String fileName = getFileName(analysisFilePackItemKey);

        // 存储文件。
        ftpHandler.storeFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_FILE_PACK_ITEM_FILE),
                fileName, content
        );

        // 获取新索引。
        AnalysisFilePack analysisFilePack = analysisFilePackMaintainService.get(analysisFilePackKey);
        int neoIndex = analysisFilePack.getItemAnchorIndex() + 1;

        // 根据参数生成 AnalysisFilePackItemInfo 对象。
        AnalysisFilePackItemInfo analysisFilePackItemInfo = new AnalysisFilePackItemInfo();
        analysisFilePackItemInfo.setKey(analysisFilePackItemKey);
        analysisFilePackItemInfo.setPackKey(analysisFilePackKey);
        analysisFilePackItemInfo.setIndex(neoIndex);
        analysisFilePackItemInfo.setOriginName(originName);
        analysisFilePackItemInfo.setLength((long) content.length);
        analysisFilePackItemInfo.setRemark("通过操作处理器上传");

        // 插入或更新分析结果文件包条目。
        analysisFilePackItemInfoMaintainService.insertOrUpdate(analysisFilePackItemInfo);

        // 更新分析结果文件包的锚点索引。
        analysisFilePack.setItemAnchorIndex(neoIndex);
        analysisFilePackMaintainService.insertOrUpdate(analysisFilePack);

        // 返回新的分析结果文件包条目文件主键。
        return analysisFilePackItemKey;
    }

    @Override
    public LongIdKey uploadFileStream(AnalysisFilePackItemFileStreamUploadInfo info) throws HandlerException {
        try {
            return uploadFileByStream0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private LongIdKey uploadFileByStream0(AnalysisFilePackItemFileStreamUploadInfo info) throws Exception {
        // 展开参数。
        LongIdKey analysisFilePackKey = info.getAnalysisFilePackKey();
        String originName = info.getOriginName();
        long length = info.getLength();

        // 确认分析结果文件包存在。
        handlerValidator.makeSureAnalysisFilePackExists(analysisFilePackKey);

        // 生成新的分析结果文件包条目文件主键。
        LongIdKey analysisFilePackItemKey = keyGenerator.generate();

        // 定义文件名。
        String fileName = getFileName(analysisFilePackItemKey);

        // 上传文件。
        InputStream cin = info.getContent();
        try (OutputStream fout = ftpHandler.openOutputStream(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_FILE_PACK_ITEM_FILE), fileName
        )) {
            IOUtil.trans(cin, fout, 4096);
        }

        // 获取新索引。
        AnalysisFilePack analysisFilePack = analysisFilePackMaintainService.get(analysisFilePackKey);
        int neoIndex = analysisFilePack.getItemAnchorIndex() + 1;

        // 根据参数生成 AnalysisFilePackItemInfo 对象。
        AnalysisFilePackItemInfo analysisFilePackItemInfo = new AnalysisFilePackItemInfo();
        analysisFilePackItemInfo.setKey(analysisFilePackItemKey);
        analysisFilePackItemInfo.setPackKey(analysisFilePackKey);
        analysisFilePackItemInfo.setIndex(neoIndex);
        analysisFilePackItemInfo.setOriginName(originName);
        analysisFilePackItemInfo.setLength(length);
        analysisFilePackItemInfo.setRemark("通过操作处理器上传");

        // 插入或更新分析结果文件包条目。
        analysisFilePackItemInfoMaintainService.insertOrUpdate(analysisFilePackItemInfo);

        // 更新分析结果文件包的锚点索引。
        analysisFilePack.setItemAnchorIndex(neoIndex);
        analysisFilePackMaintainService.insertOrUpdate(analysisFilePack);

        // 返回新的分析结果文件包条目文件主键。
        return analysisFilePackItemKey;
    }

    private String getFileName(LongIdKey analysisFilePackItemKey) {
        return Long.toString(analysisFilePackItemKey.getLongId());
    }
}
