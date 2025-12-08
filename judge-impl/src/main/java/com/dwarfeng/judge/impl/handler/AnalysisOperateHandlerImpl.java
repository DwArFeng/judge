package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.sdk.util.Constants;
import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.judge.stack.bean.key.AnalysisKey;
import com.dwarfeng.judge.stack.handler.*;
import com.dwarfeng.judge.stack.service.*;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.generation.KeyGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AnalysisOperateHandlerImpl implements AnalysisOperateHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AnalysisOperateHandlerImpl.class);

    private final AnalysisMaintainService analysisMaintainService;
    private final AnalysisPictureInfoMaintainService analysisPictureInfoMaintainService;
    private final AnalysisPicturePackMaintainService analysisPicturePackMaintainService;
    private final AnalysisPicturePackItemInfoMaintainService analysisPicturePackItemInfoMaintainService;
    private final AnalysisFileInfoMaintainService analysisFileInfoMaintainService;
    private final AnalysisFilePackMaintainService analysisFilePackMaintainService;
    private final AnalysisFilePackItemInfoMaintainService analysisFilePackItemInfoMaintainService;

    private final AnalysisPictureFileOperateHandler analysisPictureFileOperateHandler;
    private final AnalysisPicturePackOperateHandler analysisPicturePackOperateHandler;
    private final AnalysisPicturePackItemFileOperateHandler analysisPicturePackItemFileOperateHandler;
    private final AnalysisFileFileOperateHandler analysisFileFileOperateHandler;
    private final AnalysisFilePackOperateHandler analysisFilePackOperateHandler;
    private final AnalysisFilePackItemFileOperateHandler analysisFilePackItemFileOperateHandler;

    private final KeyGenerator<LongIdKey> longIdKeyGenerator;

    private final HandlerValidator handlerValidator;

    public AnalysisOperateHandlerImpl(
            AnalysisMaintainService analysisMaintainService,
            AnalysisPictureInfoMaintainService analysisPictureInfoMaintainService,
            AnalysisPicturePackMaintainService analysisPicturePackMaintainService,
            AnalysisPicturePackItemInfoMaintainService analysisPicturePackItemInfoMaintainService,
            AnalysisFileInfoMaintainService analysisFileInfoMaintainService,
            AnalysisFilePackMaintainService analysisFilePackMaintainService,
            AnalysisFilePackItemInfoMaintainService analysisFilePackItemInfoMaintainService,
            AnalysisPictureFileOperateHandler analysisPictureFileOperateHandler,
            AnalysisPicturePackOperateHandler analysisPicturePackOperateHandler,
            AnalysisPicturePackItemFileOperateHandler analysisPicturePackItemFileOperateHandler,
            AnalysisFileFileOperateHandler analysisFileFileOperateHandler,
            AnalysisFilePackOperateHandler analysisFilePackOperateHandler,
            AnalysisFilePackItemFileOperateHandler analysisFilePackItemFileOperateHandler,
            KeyGenerator<LongIdKey> longIdKeyGenerator,
            HandlerValidator handlerValidator
    ) {
        this.analysisMaintainService = analysisMaintainService;
        this.analysisPictureInfoMaintainService = analysisPictureInfoMaintainService;
        this.analysisPicturePackMaintainService = analysisPicturePackMaintainService;
        this.analysisPicturePackItemInfoMaintainService = analysisPicturePackItemInfoMaintainService;
        this.analysisFileInfoMaintainService = analysisFileInfoMaintainService;
        this.analysisFilePackMaintainService = analysisFilePackMaintainService;
        this.analysisFilePackItemInfoMaintainService = analysisFilePackItemInfoMaintainService;
        this.analysisPictureFileOperateHandler = analysisPictureFileOperateHandler;
        this.analysisPicturePackOperateHandler = analysisPicturePackOperateHandler;
        this.analysisPicturePackItemFileOperateHandler = analysisPicturePackItemFileOperateHandler;
        this.analysisFileFileOperateHandler = analysisFileFileOperateHandler;
        this.analysisFilePackOperateHandler = analysisFilePackOperateHandler;
        this.analysisFilePackItemFileOperateHandler = analysisFilePackItemFileOperateHandler;
        this.longIdKeyGenerator = longIdKeyGenerator;
        this.handlerValidator = handlerValidator;
    }

    @Nullable
    @Override
    public AnalysisInspectResult inspect(AnalysisInspectInfo info) throws HandlerException {
        try {
            return inspect0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private AnalysisInspectResult inspect0(AnalysisInspectInfo info) throws Exception {
        // 展开参数。
        LongIdKey taskKey = info.getTaskKey();
        String dataStringId = info.getDataStringId();

        // 确认任务存在。
        handlerValidator.makeSureTaskExists(taskKey);

        // 构造分析结果主键。
        AnalysisKey analysisKey = new AnalysisKey(taskKey.getLongId(), dataStringId);

        // 获取分析结果。
        Analysis analysis = analysisMaintainService.getIfExists(analysisKey);

        // 如果分析结果不存在，则返回 null。
        if (Objects.isNull(analysis)) {
            return null;
        }

        // 获取获取分析结果的数据类型。
        int dataType = analysis.getDataType();

        // 构造分析结果查看结果。
        AnalysisInspectResult result = new AnalysisInspectResult();
        result.setDataType(dataType);
        // 根据 dataType 的类型，设置不同的值。
        switch (dataType) {
            case Constants.ANALYSIS_TYPE_STRING:
                result.setValue(analysis.getStringValue());
                break;
            case Constants.ANALYSIS_TYPE_LONG:
                result.setValue(analysis.getLongValue());
                break;
            case Constants.ANALYSIS_TYPE_DOUBLE:
                result.setValue(analysis.getDoubleValue());
                break;
            case Constants.ANALYSIS_TYPE_BOOLEAN:
                result.setValue(analysis.getBooleanValue());
                break;
            case Constants.ANALYSIS_TYPE_DATE:
                result.setValue(analysis.getDateValue());
                break;
            case Constants.ANALYSIS_TYPE_PICTURE:
                result.setValue(inspectPicture(analysis));
                break;
            case Constants.ANALYSIS_TYPE_PICTURE_PACK:
                result.setValue(inspectPicturePack(analysis));
                break;
            case Constants.ANALYSIS_TYPE_FILE:
                result.setValue(inspectFile(analysis));
                break;
            case Constants.ANALYSIS_TYPE_FILE_PACK:
                result.setValue(inspectFilePack(analysis));
                break;
            default:
                throw new IllegalStateException("非法的 dataType 值: " + dataType);
        }

        // 返回结果。
        return result;
    }

    private AnalysisPictureInspectResult inspectPicture(Analysis analysis) throws Exception {
        // 构造分析结果图片信息键。
        LongIdKey analysisPictureInfoKey = new LongIdKey(analysis.getPictureValue());

        // 查询分析结果图片信息。
        AnalysisPictureInfo analysisPictureInfo =
                analysisPictureInfoMaintainService.getIfExists(analysisPictureInfoKey);

        // 构造分析结果图片查看结果并返回。
        return new AnalysisPictureInspectResult(
                analysisPictureInfoKey, analysisPictureInfo.getOriginName(),
                analysisPictureInfo.getLength(), analysisPictureInfo.getRemark()
        );
    }

    private AnalysisPicturePackInspectResult inspectPicturePack(Analysis analysis) throws Exception {
        // 构造分析结果图片包键。
        LongIdKey analysisPicturePackKey = new LongIdKey(analysis.getPicturePackValue());

        // 查询分析结果图片包信息。
        AnalysisPicturePack analysisPicturePack =
                analysisPicturePackMaintainService.getIfExists(analysisPicturePackKey);

        // 查询分析结果图片包信息所属的分析结果图片包条目信息列表。
        List<AnalysisPicturePackItemInfo> analysisPicturePackItemInfos =
                analysisPicturePackItemInfoMaintainService.lookupAsList(
                        AnalysisPicturePackItemInfoMaintainService.CHILD_FOR_PACK_INDEX_ASC,
                        new Object[]{analysisPicturePackKey}
                );

        // 构造分析结果图片包查看结果并返回。
        List<AnalysisPicturePackInspectResult.Item> items = analysisPicturePackItemInfos.stream().map(
                analysisPicturePackItemInfo -> new AnalysisPicturePackInspectResult.Item(
                        analysisPicturePackItemInfo.getKey(),
                        analysisPicturePackItemInfo.getIndex(),
                        analysisPicturePackItemInfo.getOriginName(),
                        analysisPicturePackItemInfo.getLength(),
                        analysisPicturePackItemInfo.getRemark()
                )
        ).collect(Collectors.toList());
        return new AnalysisPicturePackInspectResult(
                analysisPicturePackKey, analysisPicturePack.getItemAnchorIndex(),
                analysisPicturePack.getRemark(), items
        );
    }

    private AnalysisFileInspectResult inspectFile(Analysis analysis) throws Exception {
        // 构造分析结果文件信息键。
        LongIdKey analysisFileInfoKey = new LongIdKey(analysis.getFileValue());

        // 查询分析结果文件信息。
        AnalysisFileInfo analysisFileInfo =
                analysisFileInfoMaintainService.getIfExists(analysisFileInfoKey);

        // 构造分析结果文件查看结果并返回。
        return new AnalysisFileInspectResult(
                analysisFileInfoKey, analysisFileInfo.getOriginName(),
                analysisFileInfo.getLength(), analysisFileInfo.getRemark()
        );
    }

    private AnalysisFilePackInspectResult inspectFilePack(Analysis analysis) throws Exception {
        // 构造分析结果文件包键。
        LongIdKey analysisFilePackKey = new LongIdKey(analysis.getFilePackValue());

        // 查询分析结果文件包信息。
        AnalysisFilePack analysisFilePack =
                analysisFilePackMaintainService.getIfExists(analysisFilePackKey);

        // 查询分析结果文件包信息所属的分析结果文件包条目信息列表。
        List<AnalysisFilePackItemInfo> analysisFilePackItemInfos =
                analysisFilePackItemInfoMaintainService.lookupAsList(
                        AnalysisFilePackItemInfoMaintainService.CHILD_FOR_PACK_INDEX_ASC,
                        new Object[]{analysisFilePackKey}
                );

        // 构造分析结果文件包查看结果并返回。
        List<AnalysisFilePackInspectResult.Item> items = analysisFilePackItemInfos.stream().map(
                analysisFilePackItemInfo -> new AnalysisFilePackInspectResult.Item(
                        analysisFilePackItemInfo.getKey(),
                        analysisFilePackItemInfo.getIndex(),
                        analysisFilePackItemInfo.getOriginName(),
                        analysisFilePackItemInfo.getLength(),
                        analysisFilePackItemInfo.getRemark()
                )
        ).collect(Collectors.toList());
        return new AnalysisFilePackInspectResult(
                analysisFilePackKey, analysisFilePack.getItemAnchorIndex(),
                analysisFilePack.getRemark(), items
        );
    }

    @Override
    public void upsert(AnalysisUpsertInfo info) throws HandlerException {
        try {
            upsert0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void upsert0(AnalysisUpsertInfo info) throws Exception {
        // 展开参数。
        LongIdKey taskKey = info.getTaskKey();
        String dataStringId = info.getDataStringId();
        int dataType = info.getDataType();
        Object value = info.getValue();

        // 确认任务存在。
        handlerValidator.makeSureTaskExists(taskKey);
        // 确认数据类型与值匹配。
        handlerValidator.makeSureAnalysisDataTypeAnalysisValueMatch(dataType, value);

        // 构造分析结果主键。
        AnalysisKey analysisKey = new AnalysisKey(taskKey.getLongId(), dataStringId);

        // 查询分析结果，如果不存在，则创新新的分析结果。
        Analysis analysis = analysisMaintainService.getIfExists(analysisKey);
        if (Objects.isNull(analysis)) {
            analysis = new Analysis();
            analysis.setKey(analysisKey);
        }
        analysis.setDataType(dataType);
        // 由于在校验过程中已经确认了 dataType 的有效性，因此此处不再进行判断。
        switch (dataType) {
            case Constants.ANALYSIS_TYPE_STRING:
                analysis.setStringValue((String) value);
                break;
            case Constants.ANALYSIS_TYPE_LONG:
                analysis.setLongValue((Long) value);
                break;
            case Constants.ANALYSIS_TYPE_DOUBLE:
                analysis.setDoubleValue((Double) value);
                break;
            case Constants.ANALYSIS_TYPE_BOOLEAN:
                analysis.setBooleanValue((Boolean) value);
                break;
            case Constants.ANALYSIS_TYPE_DATE:
                analysis.setDateValue((Date) value);
                break;
            case Constants.ANALYSIS_TYPE_PICTURE:
                upsertPicture(analysis, (AnalysisPictureUpsertInfo) value);
                break;
            case Constants.ANALYSIS_TYPE_PICTURE_PACK:
                upsertPicturePack(analysis, (AnalysisPicturePackUpsertInfo) value);
                break;
            case Constants.ANALYSIS_TYPE_FILE:
                upsertFile(analysis, (AnalysisFileUpsertInfo) value);
                break;
            case Constants.ANALYSIS_TYPE_FILE_PACK:
                upsertFilePack(analysis, (AnalysisFilePackUpsertInfo) value);
                break;
        }

        // 插入或更新分析结果。
        analysisMaintainService.insertOrUpdate(analysis);
    }

    @SuppressWarnings("DuplicatedCode")
    private void upsertPicture(Analysis analysis, AnalysisPictureUpsertInfo value) throws Exception {
        // 获取图片主键，如果没有，则生成一个。
        Long pictureValue = analysis.getPictureValue();
        boolean keyGeneratedFlag;
        LongIdKey pictureKey;
        if (Objects.isNull(pictureValue)) {
            pictureKey = longIdKeyGenerator.generate();
            pictureValue = pictureKey.getLongId();
            keyGeneratedFlag = true;
        } else {
            pictureKey = new LongIdKey(pictureValue);
            keyGeneratedFlag = false;
        }

        // 展开参数。
        String originName = value.getOriginName();
        Object content = value.getContent();

        // 判断参数类型，并根据类型进行不同处理。
        // 如果 content 是 byte[]。
        if (content instanceof byte[]) {
            byte[] bytes = (byte[]) content;
            AnalysisPictureFileUploadResult uploadResult = analysisPictureFileOperateHandler.uploadFile(
                    new AnalysisPictureFileUploadInfo(pictureKey, originName, bytes)
            );
            LOGGER.debug("uploadResult: {}", uploadResult);
        }
        // 如果 content 是 AnalysisPictureUpsertInfo.FileStream。
        else if (content instanceof AnalysisPictureUpsertInfo.FileStream) {
            AnalysisPictureUpsertInfo.FileStream fileStream = (AnalysisPictureUpsertInfo.FileStream) content;
            long length = fileStream.getLength();
            InputStream inputStream = fileStream.getInputStream();
            AnalysisPictureFileUploadResult uploadResult = analysisPictureFileOperateHandler.uploadFileStream(
                    new AnalysisPictureFileStreamUploadInfo(pictureKey, originName, length, inputStream)
            );
            LOGGER.debug("uploadResult: {}", uploadResult);
        }
        // 其余情况。
        else {
            throw new IllegalArgumentException("不支持的参数类型：" + content.getClass());
        }

        // 如果图片主键是新生成的，则更新分析结果中的图片主键。
        if (keyGeneratedFlag) {
            analysis.setPictureValue(pictureValue);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void upsertPicturePack(Analysis analysis, AnalysisPicturePackUpsertInfo value) throws Exception {
        // 展开参数。
        int upsertType = value.getUpsertType();
        List<AnalysisPicturePackUpsertInfo.Item> items = value.getItems();

        // 确认 upsertType 合法。
        handlerValidator.makeSureAnalysisPicturePackUpsertTypeValid(upsertType);

        // 获取图片包主键，如果没有，则生成一个。
        Long picturePackValue = analysis.getPicturePackValue();
        boolean keyGeneratedFlag;
        LongIdKey picturePackKey;
        if (Objects.isNull(picturePackValue)) {
            picturePackKey = longIdKeyGenerator.generate();
            picturePackValue = picturePackKey.getLongId();
            keyGeneratedFlag = true;
        } else {
            picturePackKey = new LongIdKey(picturePackValue);
            keyGeneratedFlag = false;
        }

        // 如果图片包已经存在，且 recordType 是替换模式，则执行清除操作。
        if (!keyGeneratedFlag && upsertType == Constants.ANALYSE_PICTURE_PACK_UPSERT_TYPE_REPLACE) {
            analysisPicturePackOperateHandler.clear(new AnalysisPicturePackClearInfo(picturePackKey));
        }

        // 如果图片包不存在，则执行插入操作。
        if (keyGeneratedFlag) {
            analysisPicturePackOperateHandler.insert(new AnalysisPicturePackInsertInfo(picturePackKey));
        }

        // 遍历 items，执行插入操作。
        for (AnalysisPicturePackUpsertInfo.Item item : items) {
            processSinglePicturePackUpsertInfoItem(item, picturePackKey);
        }

        // 如果图片包主键是新生成的，则更新分析结果中的图片包主键。
        if (keyGeneratedFlag) {
            analysis.setPicturePackValue(picturePackValue);
        }
    }

    private void processSinglePicturePackUpsertInfoItem(
            AnalysisPicturePackUpsertInfo.Item item, LongIdKey picturePackKey
    ) throws Exception {
        // 展开参数。
        String originName = item.getOriginName();
        Object content = item.getContent();

        // 判断参数类型，并根据类型进行不同处理。
        // 如果 content 是 byte[]。
        if (content instanceof byte[]) {
            byte[] bytes = (byte[]) content;
            AnalysisPicturePackItemFileUploadResult uploadResult = analysisPicturePackItemFileOperateHandler.uploadFile(
                    new AnalysisPicturePackItemFileUploadInfo(picturePackKey, originName, bytes)
            );
            LOGGER.debug("uploadResult: {}", uploadResult);
        }
        // 如果 content 是 AnalysisPicturePackUpsertInfo.FileStream。
        else if (content instanceof AnalysisPicturePackUpsertInfo.FileStream) {
            AnalysisPicturePackUpsertInfo.FileStream fileStream = (AnalysisPicturePackUpsertInfo.FileStream) content;
            long length = fileStream.getLength();
            InputStream inputStream = fileStream.getInputStream();
            AnalysisPicturePackItemFileUploadResult uploadResult =
                    analysisPicturePackItemFileOperateHandler.uploadFileStream(
                            new AnalysisPicturePackItemFileStreamUploadInfo(
                                    picturePackKey, originName, length, inputStream
                            )
                    );
            LOGGER.debug("uploadResult: {}", uploadResult);
        }
        // 其余情况。
        else {
            throw new IllegalArgumentException("不支持的参数类型：" + content.getClass());
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void upsertFile(Analysis analysis, AnalysisFileUpsertInfo value) throws Exception {
        // 获取文件主键，如果没有，则生成一个。
        Long fileValue = analysis.getFileValue();
        boolean keyGeneratedFlag;
        LongIdKey fileKey;
        if (Objects.isNull(fileValue)) {
            fileKey = longIdKeyGenerator.generate();
            fileValue = fileKey.getLongId();
            keyGeneratedFlag = true;
        } else {
            fileKey = new LongIdKey(fileValue);
            keyGeneratedFlag = false;
        }

        // 展开参数。
        String originName = value.getOriginName();
        Object content = value.getContent();

        // 判断参数类型，并根据类型进行不同处理。
        // 如果 content 是 byte[]。
        if (content instanceof byte[]) {
            byte[] bytes = (byte[]) content;
            AnalysisFileFileUploadResult uploadResult = analysisFileFileOperateHandler.uploadFile(
                    new AnalysisFileFileUploadInfo(fileKey, originName, bytes)
            );
            LOGGER.debug("uploadResult: {}", uploadResult);
        }
        // 如果 content 是 AnalysisFileUpsertInfo.FileStream。
        else if (content instanceof AnalysisFileUpsertInfo.FileStream) {
            AnalysisFileUpsertInfo.FileStream fileStream = (AnalysisFileUpsertInfo.FileStream) content;
            long length = fileStream.getLength();
            InputStream inputStream = fileStream.getInputStream();
            AnalysisFileFileUploadResult uploadResult = analysisFileFileOperateHandler.uploadFileStream(
                    new AnalysisFileFileStreamUploadInfo(fileKey, originName, length, inputStream)
            );
            LOGGER.debug("uploadResult: {}", uploadResult);
        }
        // 其余情况。
        else {
            throw new IllegalArgumentException("不支持的参数类型：" + content.getClass());
        }

        // 如果文件主键是新生成的，则更新分析结果中的文件主键。
        if (keyGeneratedFlag) {
            analysis.setFileValue(fileValue);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void upsertFilePack(Analysis analysis, AnalysisFilePackUpsertInfo value) throws Exception {
        // 展开参数。
        int upsertType = value.getUpsertType();
        List<AnalysisFilePackUpsertInfo.Item> items = value.getItems();

        // 确认 upsertType 合法。
        handlerValidator.makeSureAnalysisFilePackUpsertTypeValid(upsertType);

        // 获取文件包主键，如果没有，则生成一个。
        Long filePackValue = analysis.getFilePackValue();
        boolean keyGeneratedFlag;
        LongIdKey filePackKey;
        if (Objects.isNull(filePackValue)) {
            filePackKey = longIdKeyGenerator.generate();
            filePackValue = filePackKey.getLongId();
            keyGeneratedFlag = true;
        } else {
            filePackKey = new LongIdKey(filePackValue);
            keyGeneratedFlag = false;
        }

        // 如果文件包已经存在，且 recordType 是替换模式，则执行清除操作。
        if (!keyGeneratedFlag && upsertType == Constants.ANALYSE_FILE_PACK_UPSERT_TYPE_REPLACE) {
            analysisFilePackOperateHandler.clear(new AnalysisFilePackClearInfo(filePackKey));
        }

        // 如果文件包不存在，则执行插入操作。
        if (keyGeneratedFlag) {
            analysisFilePackOperateHandler.insert(new AnalysisFilePackInsertInfo(filePackKey));
        }

        // 遍历 items，执行插入操作。
        for (AnalysisFilePackUpsertInfo.Item item : items) {
            processSingleFilePackUpsertInfoItem(item, filePackKey);
        }

        // 如果文件包主键是新生成的，则更新分析结果中的文件包主键。
        if (keyGeneratedFlag) {
            analysis.setFilePackValue(filePackValue);
        }
    }

    private void processSingleFilePackUpsertInfoItem(
            AnalysisFilePackUpsertInfo.Item item, LongIdKey filePackKey
    ) throws Exception {
        // 展开参数。
        String originName = item.getOriginName();
        Object content = item.getContent();

        // 判断参数类型，并根据类型进行不同处理。
        // 如果 content 是 byte[]。
        if (content instanceof byte[]) {
            byte[] bytes = (byte[]) content;
            AnalysisFilePackItemFileUploadResult uploadResult = analysisFilePackItemFileOperateHandler.uploadFile(
                    new AnalysisFilePackItemFileUploadInfo(filePackKey, originName, bytes)
            );
            LOGGER.debug("uploadResult: {}", uploadResult);
        }
        // 如果 content 是 AnalysisFilePackUpsertInfo.FileStream。
        else if (content instanceof AnalysisFilePackUpsertInfo.FileStream) {
            AnalysisFilePackUpsertInfo.FileStream fileStream = (AnalysisFilePackUpsertInfo.FileStream) content;
            long length = fileStream.getLength();
            InputStream inputStream = fileStream.getInputStream();
            AnalysisFilePackItemFileUploadResult uploadResult = analysisFilePackItemFileOperateHandler.uploadFileStream(
                    new AnalysisFilePackItemFileStreamUploadInfo(filePackKey, originName, length, inputStream)
            );
            LOGGER.debug("uploadResult: {}", uploadResult);
        }
        // 其余情况。
        else {
            throw new IllegalArgumentException("不支持的参数类型：" + content.getClass());
        }
    }

    @Override
    public void remove(AnalysisRemoveInfo info) throws HandlerException {
        try {
            remove0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void remove0(AnalysisRemoveInfo info) throws Exception {
        // 展开参数。
        LongIdKey taskKey = info.getTaskKey();
        String dataStringId = info.getDataStringId();

        // 确认任务存在。
        handlerValidator.makeSureTaskExists(taskKey);
        // 确认分析结果存在。
        AnalysisKey analysisKey = new AnalysisKey(taskKey.getLongId(), dataStringId);
        handlerValidator.makeSureAnalysisExists(analysisKey);

        // 删除分析结果。
        analysisMaintainService.delete(analysisKey);
    }
}
