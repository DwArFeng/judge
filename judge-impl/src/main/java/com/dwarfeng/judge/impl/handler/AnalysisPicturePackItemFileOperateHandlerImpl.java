package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.judge.stack.bean.entity.AnalysisPicturePack;
import com.dwarfeng.judge.stack.bean.entity.AnalysisPicturePackItemInfo;
import com.dwarfeng.judge.stack.handler.AnalysisPicturePackItemFileOperateHandler;
import com.dwarfeng.judge.stack.service.AnalysisPicturePackItemInfoMaintainService;
import com.dwarfeng.judge.stack.service.AnalysisPicturePackMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.generation.KeyGenerator;
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
public class AnalysisPicturePackItemFileOperateHandlerImpl implements AnalysisPicturePackItemFileOperateHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisPicturePackItemFileOperateHandlerImpl.class);

    private final AnalysisPicturePackItemInfoMaintainService analysisPicturePackItemInfoMaintainService;

    private final AnalysisPicturePackMaintainService analysisPicturePackMaintainService;

    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    private final KeyGenerator<LongIdKey> keyGenerator;

    private final HandlerValidator handlerValidator;

    @Value("${thumbnail.width}")
    private int thumbnailWidth;
    @Value("${thumbnail.height}")
    private int thumbnailHeight;
    @Value("${thumbnail.quality}")
    private double thumbnailQuality;
    @Value("${thumbnail.output_format}")
    private String thumbnailOutputFormat;

    public AnalysisPicturePackItemFileOperateHandlerImpl(
            AnalysisPicturePackItemInfoMaintainService analysisPicturePackItemInfoMaintainService,
            AnalysisPicturePackMaintainService analysisPicturePackMaintainService,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver,
            KeyGenerator<LongIdKey> keyGenerator,
            HandlerValidator handlerValidator
    ) {
        this.analysisPicturePackItemInfoMaintainService = analysisPicturePackItemInfoMaintainService;
        this.analysisPicturePackMaintainService = analysisPicturePackMaintainService;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
        this.keyGenerator = keyGenerator;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public AnalysisPicturePackItemFile downloadFile(AnalysisPicturePackItemFileDownloadInfo info)
            throws HandlerException {
        try {
            return downloadFile0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private AnalysisPicturePackItemFile downloadFile0(AnalysisPicturePackItemFileDownloadInfo info) throws Exception {
        // 展开参数。
        LongIdKey analysisPicturePackItemKey = info.getAnalysisPicturePackItemKey();

        // 确认分析结果图片包条目文件存在。
        handlerValidator.makeSureAnalysisPicturePackItemExists(analysisPicturePackItemKey);

        AnalysisPicturePackItemInfo analysisPicturePackItemInfo =
                analysisPicturePackItemInfoMaintainService.get(analysisPicturePackItemKey);

        // 下载分析结果图片包条目文件。
        byte[] content = ftpHandler.retrieveFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_PACK_ITEM_FILE),
                getFileName(analysisPicturePackItemKey)
        );

        return new AnalysisPicturePackItemFile(analysisPicturePackItemInfo.getOriginName(), content);
    }

    @Override
    public AnalysisPicturePackItemFileStream downloadFileStream(AnalysisPicturePackItemFileStreamDownloadInfo info)
            throws HandlerException {
        try {
            return downloadFileStream0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private AnalysisPicturePackItemFileStream downloadFileStream0(AnalysisPicturePackItemFileStreamDownloadInfo info)
            throws Exception {
        // 展开参数。
        LongIdKey analysisPicturePackItemKey = info.getAnalysisPicturePackItemKey();

        // 确认分析结果图片包条目文件存在。
        handlerValidator.makeSureAnalysisPicturePackItemExists(analysisPicturePackItemKey);

        AnalysisPicturePackItemInfo analysisPicturePackItemInfo =
                analysisPicturePackItemInfoMaintainService.get(analysisPicturePackItemKey);

        // 下载图片文件流。
        InputStream content = ftpHandler.openInputStream(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_PACK_ITEM_FILE),
                analysisPicturePackItemInfo.getOriginName()
        );

        // 构造 AnalysisPicturePackItemFileStream 并返回。
        return new AnalysisPicturePackItemFileStream(
                analysisPicturePackItemInfo.getOriginName(), analysisPicturePackItemInfo.getLength(), content
        );
    }

    @Override
    public AnalysisPicturePackItemThumbnail downloadThumbnail(AnalysisPicturePackItemThumbnailDownloadInfo info)
            throws HandlerException {
        try {
            return downloadThumbnail0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private AnalysisPicturePackItemThumbnail downloadThumbnail0(AnalysisPicturePackItemThumbnailDownloadInfo info)
            throws Exception {
        // 展开参数。
        LongIdKey analysisPicturePackItemKey = info.getAnalysisPicturePackItemKey();

        // 确认分析结果图片包条目文件存在。
        handlerValidator.makeSureAnalysisPicturePackItemExists(analysisPicturePackItemKey);

        AnalysisPicturePackItemInfo analysisPicturePackItemInfo =
                analysisPicturePackItemInfoMaintainService.get(analysisPicturePackItemKey);

        // 如果不存在缩略图，则创建。
        boolean existsThumbnail = ftpHandler.existsFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_PACK_ITEM_THUMBNAIL),
                getFileName(analysisPicturePackItemKey)
        );
        if (!existsThumbnail) {
            LOGGER.info("分析结果图片包条目 {} 的缩略图不存在, 将创建缩略图...", analysisPicturePackItemKey);
            createAnalysisPicturePackThumbnail(getFileName(analysisPicturePackItemKey));
        }

        // 下载缩略图。
        byte[] content = ftpHandler.retrieveFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_PACK_ITEM_THUMBNAIL),
                getFileName(analysisPicturePackItemKey)
        );

        return new AnalysisPicturePackItemThumbnail(analysisPicturePackItemInfo.getOriginName(), content);
    }

    @Override
    public AnalysisPicturePackItemFileUploadResult uploadFile(AnalysisPicturePackItemFileUploadInfo info)
            throws HandlerException {
        try {
            return uploadFile0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private AnalysisPicturePackItemFileUploadResult uploadFile0(AnalysisPicturePackItemFileUploadInfo info)
            throws Exception {
        // 展开参数。
        LongIdKey analysisPicturePackKey = info.getAnalysisPicturePackKey();
        String originName = info.getOriginName();
        byte[] content = info.getContent();

        // 确认分析结果图片包存在。
        handlerValidator.makeSureAnalysisPicturePackExists(analysisPicturePackKey);

        // 生成新的分析结果图片包条目文件主键。
        LongIdKey analysisPicturePackItemKey = keyGenerator.generate();

        // 定义文件名。
        String fileName = getFileName(analysisPicturePackItemKey);

        // 存储文件。
        ftpHandler.storeFile(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_PACK_ITEM_FILE),
                fileName, content
        );

        // 生成缩略图并存储（覆盖）。
        createAnalysisPicturePackThumbnail(fileName);

        // 获取新索引。
        AnalysisPicturePack analysisPicturePack = analysisPicturePackMaintainService.get(analysisPicturePackKey);
        int neoIndex = analysisPicturePack.getItemAnchorIndex() + 1;

        // 根据参数生成 AnalysisPicturePackItemInfo 对象。
        AnalysisPicturePackItemInfo analysisPicturePackItemInfo = new AnalysisPicturePackItemInfo();
        analysisPicturePackItemInfo.setKey(analysisPicturePackItemKey);
        analysisPicturePackItemInfo.setPackKey(analysisPicturePackKey);
        analysisPicturePackItemInfo.setIndex(neoIndex);
        analysisPicturePackItemInfo.setOriginName(originName);
        analysisPicturePackItemInfo.setLength((long) content.length);
        analysisPicturePackItemInfo.setRemark("通过操作处理器上传");

        // 插入或更新分析结果图片包条目。
        analysisPicturePackItemInfoMaintainService.insertOrUpdate(analysisPicturePackItemInfo);

        // 更新分析结果图片包的锚点索引。
        analysisPicturePack.setItemAnchorIndex(neoIndex);
        analysisPicturePackMaintainService.insertOrUpdate(analysisPicturePack);

        // 返回新的分析结果图片包条目文件主键。
        return new AnalysisPicturePackItemFileUploadResult(analysisPicturePackItemKey);
    }

    @Override
    public AnalysisPicturePackItemFileUploadResult uploadFileStream(AnalysisPicturePackItemFileStreamUploadInfo info)
            throws HandlerException {
        try {
            return uploadFileByStream0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private AnalysisPicturePackItemFileUploadResult uploadFileByStream0(
            AnalysisPicturePackItemFileStreamUploadInfo info
    ) throws Exception {
        // 展开参数。
        LongIdKey analysisPicturePackKey = info.getAnalysisPicturePackKey();
        String originName = info.getOriginName();
        long length = info.getLength();

        // 确认分析结果图片包存在。
        handlerValidator.makeSureAnalysisPicturePackExists(analysisPicturePackKey);

        // 生成新的分析结果图片包条目文件主键。
        LongIdKey analysisPicturePackItemKey = keyGenerator.generate();

        // 定义文件名。
        String fileName = getFileName(analysisPicturePackItemKey);

        // 上传文件。
        InputStream cin = info.getContent();
        try (OutputStream fout = ftpHandler.openOutputStream(
                ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_PACK_ITEM_FILE), fileName
        )) {
            IOUtil.trans(cin, fout, 4096);
        }

        // 生成缩略图并存储（覆盖）。
        createAnalysisPicturePackThumbnail(fileName);

        // 获取新索引。
        AnalysisPicturePack analysisPicturePack = analysisPicturePackMaintainService.get(analysisPicturePackKey);
        int neoIndex = analysisPicturePack.getItemAnchorIndex() + 1;

        // 根据参数生成 AnalysisPicturePackItemInfo 对象。
        AnalysisPicturePackItemInfo analysisPicturePackItemInfo = new AnalysisPicturePackItemInfo();
        analysisPicturePackItemInfo.setKey(analysisPicturePackItemKey);
        analysisPicturePackItemInfo.setPackKey(analysisPicturePackKey);
        analysisPicturePackItemInfo.setIndex(neoIndex);
        analysisPicturePackItemInfo.setOriginName(originName);
        analysisPicturePackItemInfo.setLength(length);
        analysisPicturePackItemInfo.setRemark("通过操作处理器上传");

        // 插入或更新分析结果图片包条目。
        analysisPicturePackItemInfoMaintainService.insertOrUpdate(analysisPicturePackItemInfo);

        // 更新分析结果图片包的锚点索引。
        analysisPicturePack.setItemAnchorIndex(neoIndex);
        analysisPicturePackMaintainService.insertOrUpdate(analysisPicturePack);

        // 返回新的分析结果图片包条目文件主键。
        return new AnalysisPicturePackItemFileUploadResult(analysisPicturePackItemKey);
    }

    @SuppressWarnings("DuplicatedCode")
    private void createAnalysisPicturePackThumbnail(String fileName) throws Exception {
        // 定义临时变量，缩短代码长度。
        @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
        String[] pinf = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_PACK_ITEM_FILE);
        @SuppressWarnings({"SpellCheckingInspection", "RedundantSuppression"})
        String[] pint = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_ANALYSIS_PICTURE_PACK_ITEM_THUMBNAIL);
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

    private String getFileName(LongIdKey analysisPicturePackItemKey) {
        return Long.toString(analysisPicturePackItemKey.getLongId());
    }
}
