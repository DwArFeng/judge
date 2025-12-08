package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.judge.stack.bean.entity.AnalysisPictureInfo;
import com.dwarfeng.judge.stack.handler.AnalysisPictureFileOperateHandler;
import com.dwarfeng.judge.stack.service.AnalysisPictureInfoMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

@Component
public class AnalysisPictureFileOperateHandlerImpl implements AnalysisPictureFileOperateHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisPictureFileOperateHandlerImpl.class);

    private final AnalysisPictureInfoMaintainService analysisPictureInfoMaintainService;

    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    private final HandlerValidator handlerValidator;

    @Value("${thumbnail.width}")
    private int thumbnailWidth;
    @Value("${thumbnail.height}")
    private int thumbnailHeight;
    @Value("${thumbnail.quality}")
    private double thumbnailQuality;
    @Value("${thumbnail.output_format}")
    private String thumbnailOutputFormat;

    public AnalysisPictureFileOperateHandlerImpl(
            AnalysisPictureInfoMaintainService analysisPictureInfoMaintainService,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver,
            HandlerValidator handlerValidator
    ) {
        this.analysisPictureInfoMaintainService = analysisPictureInfoMaintainService;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public AnalysisPictureFile downloadFile(AnalysisPictureFileDownloadInfo info) throws HandlerException {
        try {
            return downloadFile0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private AnalysisPictureFile downloadFile0(AnalysisPictureFileDownloadInfo info) throws Exception {
        // 展开参数。
        LongIdKey analysisPictureInfoKey = info.getAnalysisPictureInfoKey();

        // 确认分析结果图片文件存在。
        handlerValidator.makeSureAnalysisPictureExists(analysisPictureInfoKey);

        AnalysisPictureInfo analysisPictureInfo = analysisPictureInfoMaintainService.get(analysisPictureInfoKey);

        // 下载分析结果图片文件。
        byte[] content = ftpHandler.retrieveFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_FILE),
                getFileName(analysisPictureInfoKey)
        );

        return new AnalysisPictureFile(analysisPictureInfo.getOriginName(), content);
    }

    @Override
    public AnalysisPictureFileStream downloadFileStream(AnalysisPictureFileStreamDownloadInfo info)
            throws HandlerException {
        try {
            return downloadFileStream0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private AnalysisPictureFileStream downloadFileStream0(AnalysisPictureFileStreamDownloadInfo info) throws Exception {
        // 展开参数。
        LongIdKey analysisPictureInfoKey = info.getAnalysisPictureInfoKey();

        // 确认分析结果图片文件存在。
        handlerValidator.makeSureAnalysisPictureExists(analysisPictureInfoKey);

        AnalysisPictureInfo analysisPictureInfo = analysisPictureInfoMaintainService.get(analysisPictureInfoKey);

        // 下载图片文件流。
        InputStream content = ftpHandler.openInputStream(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_FILE),
                analysisPictureInfo.getOriginName()
        );

        // 构造 AnalysisPictureFileStream 并返回。
        return new AnalysisPictureFileStream(
                analysisPictureInfo.getOriginName(), analysisPictureInfo.getLength(), content
        );
    }

    @Override
    public AnalysisPictureThumbnail downloadThumbnail(AnalysisPictureThumbnailDownloadInfo info)
            throws HandlerException {
        try {
            return downloadThumbnail0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private AnalysisPictureThumbnail downloadThumbnail0(AnalysisPictureThumbnailDownloadInfo info) throws Exception {
        // 展开参数。
        LongIdKey analysisPictureInfoKey = info.getAnalysisPictureInfoKey();

        // 确认分析结果图片文件存在。
        handlerValidator.makeSureAnalysisPictureExists(analysisPictureInfoKey);

        AnalysisPictureInfo analysisPictureInfo = analysisPictureInfoMaintainService.get(analysisPictureInfoKey);

        // 如果不存在缩略图，则创建。
        boolean existsThumbnail = ftpHandler.existsFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_THUMBNAIL),
                getFileName(analysisPictureInfoKey)
        );
        if (!existsThumbnail) {
            LOGGER.info("分析结果图片 {} 的缩略图不存在, 将创建缩略图...", analysisPictureInfoKey);
            createAnalysisPictureThumbnail(getFileName(analysisPictureInfoKey));
        }

        // 下载缩略图。
        byte[] content = ftpHandler.retrieveFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_THUMBNAIL),
                getFileName(analysisPictureInfoKey)
        );

        return new AnalysisPictureThumbnail(analysisPictureInfo.getOriginName(), content);
    }

    @Override
    public AnalysisPictureFileUploadResult uploadFile(AnalysisPictureFileUploadInfo info) throws HandlerException {
        try {
            return uploadFile0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private AnalysisPictureFileUploadResult uploadFile0(AnalysisPictureFileUploadInfo info) throws Exception {
        // 展开参数。
        LongIdKey analysisPictureKey = info.getAnalysisPictureKey();
        String originName = info.getOriginName();
        byte[] content = info.getContent();

        // 定义文件名。
        String fileName = getFileName(analysisPictureKey);

        // 存储文件。
        ftpHandler.storeFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_FILE),
                fileName, content
        );

        // 生成缩略图并存储（覆盖）。
        createAnalysisPictureThumbnail(fileName);

        // 根据参数生成 AnalysisPictureInfo 对象。
        AnalysisPictureInfo analysisPictureInfo = new AnalysisPictureInfo();
        analysisPictureInfo.setKey(analysisPictureKey);
        analysisPictureInfo.setOriginName(originName);
        analysisPictureInfo.setLength((long) content.length);
        analysisPictureInfo.setRemark("通过操作处理器上传");

        // 插入或更新 AnalysisPictureInfo 对象。
        analysisPictureInfoMaintainService.insertOrUpdate(analysisPictureInfo);

        // 返回上传结果。
        return new AnalysisPictureFileUploadResult(analysisPictureKey);
    }

    @Override
    public AnalysisPictureFileUploadResult uploadFileStream(AnalysisPictureFileStreamUploadInfo info)
            throws HandlerException {
        try {
            return uploadFileByStream0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private AnalysisPictureFileUploadResult uploadFileByStream0(AnalysisPictureFileStreamUploadInfo info)
            throws Exception {
        // 展开参数。
        LongIdKey analysisPictureKey = info.getAnalysisPictureKey();
        String originName = info.getOriginName();
        long length = info.getLength();

        // 定义文件名。
        String fileName = getFileName(analysisPictureKey);

        // 上传文件。
        InputStream cin = info.getContent();
        try (OutputStream fout = ftpHandler.openOutputStream(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_FILE), fileName
        )) {
            IOUtil.trans(cin, fout, 4096);
        }

        // 生成缩略图并存储（覆盖）。
        createAnalysisPictureThumbnail(fileName);

        // 根据参数生成 AnalysisPictureInfo 对象。
        AnalysisPictureInfo analysisPictureInfo = new AnalysisPictureInfo();
        analysisPictureInfo.setKey(analysisPictureKey);
        analysisPictureInfo.setOriginName(originName);
        analysisPictureInfo.setLength(length);
        analysisPictureInfo.setRemark("通过操作处理器上传");

        // 插入或更新 AnalysisPictureInfo 对象。
        analysisPictureInfoMaintainService.insertOrUpdate(analysisPictureInfo);

        // 返回上传结果。
        return new AnalysisPictureFileUploadResult(analysisPictureKey);
    }

    @SuppressWarnings("DuplicatedCode")
    private void createAnalysisPictureThumbnail(String fileName) throws Exception {
        // 定义临时变量，缩短代码长度。
        @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
        String[] pinf = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_FILE);
        @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
        String[] pint = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_THUMBNAIL);
        // 定义缩略图。
        byte[] thumbnailContent;
        // 打开原图的输入流，并在 try-with-resources 中创建缩略图。
        try (
                InputStream in = ftpHandler.openInputStream(pinf, fileName);
                ByteArrayOutputStream bout = new ByteArrayOutputStream()
        ) {
            Thumbnails.of(in).size(thumbnailWidth, thumbnailHeight).outputQuality(thumbnailQuality)
                    .outputFormat(thumbnailOutputFormat).toOutputStream(bout);
            thumbnailContent = bout.toByteArray();
        }
        // 打开缩略图的输出流，并在 try-with-resources 中写入缩略图。
        try (
                InputStream in = new ByteArrayInputStream(thumbnailContent);
                OutputStream out = ftpHandler.openOutputStream(pint, fileName)
        ) {
            IOUtil.trans(in, out, 4096);
        }
    }

    private String getFileName(LongIdKey analysisPictureFileKey) {
        return Long.toString(analysisPictureFileKey.getLongId());
    }
}
