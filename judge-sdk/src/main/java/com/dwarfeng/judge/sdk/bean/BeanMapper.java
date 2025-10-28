package com.dwarfeng.judge.sdk.bean;

import com.dwarfeng.judge.sdk.bean.dto.*;
import com.dwarfeng.judge.sdk.bean.entity.*;
import com.dwarfeng.judge.sdk.bean.key.*;
import com.dwarfeng.judge.stack.bean.dto.*;
import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.judge.stack.bean.key.*;
import com.dwarfeng.subgrade.sdk.bean.key.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * Bean 映射器。
 *
 * <p>
 * 该映射器中包含了 <code>sdk</code> 模块中所有实体与 <code>stack</code> 模块中对应实体的映射方法。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
@Mapper
public interface BeanMapper {

    // -----------------------------------------------------------Subgrade Key-----------------------------------------------------------
    FastJsonLongIdKey longIdKeyToFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromFastJson(FastJsonLongIdKey fastJsonLongIdKey);

    FastJsonStringIdKey stringIdKeyToFastJson(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromFastJson(FastJsonStringIdKey fastJsonStringIdKey);

    JSFixedFastJsonLongIdKey longIdKeyToJSFixedFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromJSFixedFastJson(JSFixedFastJsonLongIdKey jSFixedFastJsonLongIdKey);

    WebInputLongIdKey longIdKeyToWebInput(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromWebInput(WebInputLongIdKey webInputLongIdKey);

    WebInputStringIdKey stringIdKeyToWebInput(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromWebInput(WebInputStringIdKey webInputStringIdKey);

    // -----------------------------------------------------------Judge Key-----------------------------------------------------------
    FastJsonAnalyserVariableKey analyserVariableKeyToFastJson(AnalyserVariableKey analyserVariableKey);

    @InheritInverseConfiguration
    AnalyserVariableKey analyserVariableKeyFromFastJson(FastJsonAnalyserVariableKey fastJsonAnalyserVariableKey);

    FastJsonAnalysisKey analysisKeyToFastJson(AnalysisKey analysisKey);

    @InheritInverseConfiguration
    AnalysisKey analysisKeyFromFastJson(FastJsonAnalysisKey fastJsonAnalysisKey);

    FastJsonJudgerVariableKey judgerVariableKeyToFastJson(JudgerVariableKey judgerVariableKey);

    @InheritInverseConfiguration
    JudgerVariableKey judgerVariableKeyFromFastJson(FastJsonJudgerVariableKey fastJsonJudgerVariableKey);

    JSFixedFastJsonAnalyserVariableKey analyserVariableKeyToJSFixedFastJson(AnalyserVariableKey analyserVariableKey);

    @InheritInverseConfiguration
    AnalyserVariableKey analyserVariableKeyFromJSFixedFastJson(JSFixedFastJsonAnalyserVariableKey jSFixedFastJsonAnalyserVariableKey);

    JSFixedFastJsonAnalysisKey analysisKeyToJSFixedFastJson(AnalysisKey analysisKey);

    @InheritInverseConfiguration
    AnalysisKey analysisKeyFromJSFixedFastJson(JSFixedFastJsonAnalysisKey jSFixedFastJsonAnalysisKey);

    JSFixedFastJsonJudgerVariableKey judgerVariableKeyToJSFixedFastJson(JudgerVariableKey judgerVariableKey);

    @InheritInverseConfiguration
    JudgerVariableKey judgerVariableKeyFromJSFixedFastJson(JSFixedFastJsonJudgerVariableKey jSFixedFastJsonJudgerVariableKey);

    WebInputAnalyserVariableKey analyserVariableKeyToWebInput(AnalyserVariableKey analyserVariableKey);

    @InheritInverseConfiguration
    AnalyserVariableKey analyserVariableKeyFromWebInput(WebInputAnalyserVariableKey webInputAnalyserVariableKey);

    WebInputAnalysisKey analysisKeyToWebInput(AnalysisKey analysisKey);

    @InheritInverseConfiguration
    AnalysisKey analysisKeyFromWebInput(WebInputAnalysisKey webInputAnalysisKey);

    WebInputJudgerVariableKey judgerVariableKeyToWebInput(JudgerVariableKey judgerVariableKey);

    @InheritInverseConfiguration
    JudgerVariableKey judgerVariableKeyFromWebInput(WebInputJudgerVariableKey webInputJudgerVariableKey);

    FastJsonJudgementKey judgementKeyToFastJson(JudgementKey judgementKey);

    @InheritInverseConfiguration
    JudgementKey judgementKeyFromFastJson(FastJsonJudgementKey fastJsonJudgementKey);

    JSFixedFastJsonJudgementKey judgementKeyToJSFixedFastJson(JudgementKey judgementKey);

    @InheritInverseConfiguration
    JudgementKey judgementKeyFromJSFixedFastJson(JSFixedFastJsonJudgementKey jSFixedFastJsonJudgementKey);

    WebInputJudgementKey judgementKeyToWebInput(JudgementKey judgementKey);

    @InheritInverseConfiguration
    JudgementKey judgementKeyFromWebInput(WebInputJudgementKey webInputJudgementKey);

    FastJsonSinkerMetaIndicatorKey sinkerMetaIndicatorKeyToFastJson(SinkerMetaIndicatorKey sinkerMetaIndicatorKey);

    @InheritInverseConfiguration
    SinkerMetaIndicatorKey sinkerMetaIndicatorKeyFromFastJson(FastJsonSinkerMetaIndicatorKey fastJsonSinkerMetaIndicatorKey);

    FastJsonSinkerMetaKey sinkerMetaKeyToFastJson(SinkerMetaKey sinkerMetaKey);

    @InheritInverseConfiguration
    SinkerMetaKey sinkerMetaKeyFromFastJson(FastJsonSinkerMetaKey fastJsonSinkerMetaKey);

    FastJsonSinkerRelationKey sinkerRelationKeyToFastJson(SinkerRelationKey sinkerRelationKey);

    @InheritInverseConfiguration
    SinkerRelationKey sinkerRelationKeyFromFastJson(FastJsonSinkerRelationKey fastJsonSinkerRelationKey);

    FastJsonSinkerVariableKey sinkerVariableKeyToFastJson(SinkerVariableKey sinkerVariableKey);

    @InheritInverseConfiguration
    SinkerVariableKey sinkerVariableKeyFromFastJson(FastJsonSinkerVariableKey fastJsonSinkerVariableKey);

    JSFixedFastJsonSinkerMetaKey sinkerMetaKeyToJSFixedFastJson(SinkerMetaKey sinkerMetaKey);

    @InheritInverseConfiguration
    SinkerMetaKey sinkerMetaKeyFromJSFixedFastJson(JSFixedFastJsonSinkerMetaKey jSFixedFastJsonSinkerMetaKey);

    JSFixedFastJsonSinkerRelationKey sinkerRelationKeyToJSFixedFastJson(SinkerRelationKey sinkerRelationKey);

    @InheritInverseConfiguration
    SinkerRelationKey sinkerRelationKeyFromJSFixedFastJson(JSFixedFastJsonSinkerRelationKey jSFixedFastJsonSinkerRelationKey);

    JSFixedFastJsonSinkerVariableKey sinkerVariableKeyToJSFixedFastJson(SinkerVariableKey sinkerVariableKey);

    @InheritInverseConfiguration
    SinkerVariableKey sinkerVariableKeyFromJSFixedFastJson(JSFixedFastJsonSinkerVariableKey jSFixedFastJsonSinkerVariableKey);

    WebInputSinkerMetaKey sinkerMetaKeyToWebInput(SinkerMetaKey sinkerMetaKey);

    @InheritInverseConfiguration
    SinkerMetaKey sinkerMetaKeyFromWebInput(WebInputSinkerMetaKey webInputSinkerMetaKey);

    WebInputSinkerRelationKey sinkerRelationKeyToWebInput(SinkerRelationKey sinkerRelationKey);

    @InheritInverseConfiguration
    SinkerRelationKey sinkerRelationKeyFromWebInput(WebInputSinkerRelationKey webInputSinkerRelationKey);

    FastJsonVisualizeDataKey visualizeDataKeyToFastJson(VisualizeDataKey visualizeDataKey);

    @InheritInverseConfiguration
    VisualizeDataKey visualizeDataKeyFromFastJson(FastJsonVisualizeDataKey fastJsonVisualizeDataKey);

    JSFixedFastJsonVisualizeDataKey visualizeDataKeyToJSFixedFastJson(VisualizeDataKey visualizeDataKey);

    @InheritInverseConfiguration
    VisualizeDataKey visualizeDataKeyFromJSFixedFastJson(
            JSFixedFastJsonVisualizeDataKey jSFixedFastJsonVisualizeDataKey
    );

    WebInputVisualizeDataKey visualizeDataKeyToWebInput(VisualizeDataKey visualizeDataKey);

    @InheritInverseConfiguration
    VisualizeDataKey visualizeDataKeyFromWebInput(WebInputVisualizeDataKey webInputVisualizeDataKey);

    // -----------------------------------------------------------Judge Entity-----------------------------------------------------------
    FastJsonAnalyserInfo analyserInfoToFastJson(AnalyserInfo analyserInfo);

    @InheritInverseConfiguration
    AnalyserInfo analyserInfoFromFastJson(FastJsonAnalyserInfo fastJsonAnalyserInfo);

    FastJsonAnalyserSupport analyserSupportToFastJson(AnalyserSupport analyserSupport);

    @InheritInverseConfiguration
    AnalyserSupport analyserSupportFromFastJson(FastJsonAnalyserSupport fastJsonAnalyserSupport);

    FastJsonAnalyserVariable analyserVariableToFastJson(AnalyserVariable analyserVariable);

    @InheritInverseConfiguration
    AnalyserVariable analyserVariableFromFastJson(FastJsonAnalyserVariable fastJsonAnalyserVariable);

    FastJsonAnalysis analysisToFastJson(Analysis analysis);

    @InheritInverseConfiguration
    Analysis analysisFromFastJson(FastJsonAnalysis fastJsonAnalysis);

    FastJsonDriverInfo driverInfoToFastJson(DriverInfo driverInfo);

    @InheritInverseConfiguration
    DriverInfo driverInfoFromFastJson(FastJsonDriverInfo fastJsonDriverInfo);

    FastJsonDriverSupport driverSupportToFastJson(DriverSupport driverSupport);

    @InheritInverseConfiguration
    DriverSupport driverSupportFromFastJson(FastJsonDriverSupport fastJsonDriverSupport);

    FastJsonJudgerInfo judgerInfoToFastJson(JudgerInfo judgerInfo);

    @InheritInverseConfiguration
    JudgerInfo judgerInfoFromFastJson(FastJsonJudgerInfo fastJsonJudgerInfo);

    FastJsonJudgerSupport judgerSupportToFastJson(JudgerSupport judgerSupport);

    @InheritInverseConfiguration
    JudgerSupport judgerSupportFromFastJson(FastJsonJudgerSupport fastJsonJudgerSupport);

    FastJsonJudgerVariable judgerVariableToFastJson(JudgerVariable judgerVariable);

    @InheritInverseConfiguration
    JudgerVariable judgerVariableFromFastJson(FastJsonJudgerVariable fastJsonJudgerVariable);

    FastJsonSection sectionToFastJson(Section section);

    @InheritInverseConfiguration
    Section sectionFromFastJson(FastJsonSection fastJsonSection);

    FastJsonTask taskToFastJson(Task task);

    @InheritInverseConfiguration
    Task taskFromFastJson(FastJsonTask fastJsonTask);

    FastJsonTaskEvent taskEventToFastJson(TaskEvent taskEvent);

    @InheritInverseConfiguration
    TaskEvent taskEventFromFastJson(FastJsonTaskEvent fastJsonTaskEvent);

    JSFixedFastJsonAnalyserInfo analyserInfoToJSFixedFastJson(AnalyserInfo analyserInfo);

    @InheritInverseConfiguration
    AnalyserInfo analyserInfoFromJSFixedFastJson(JSFixedFastJsonAnalyserInfo jSFixedFastJsonAnalyserInfo);

    JSFixedFastJsonAnalyserVariable analyserVariableToJSFixedFastJson(AnalyserVariable analyserVariable);

    @InheritInverseConfiguration
    AnalyserVariable analyserVariableFromJSFixedFastJson(
            JSFixedFastJsonAnalyserVariable jSFixedFastJsonAnalyserVariable
    );

    JSFixedFastJsonAnalysis analysisToJSFixedFastJson(Analysis analysis);

    @InheritInverseConfiguration
    Analysis analysisFromJSFixedFastJson(JSFixedFastJsonAnalysis jSFixedFastJsonAnalysis);

    JSFixedFastJsonDriverInfo driverInfoToJSFixedFastJson(DriverInfo driverInfo);

    @InheritInverseConfiguration
    DriverInfo driverInfoFromJSFixedFastJson(JSFixedFastJsonDriverInfo jSFixedFastJsonDriverInfo);

    JSFixedFastJsonJudgerInfo judgerInfoToJSFixedFastJson(JudgerInfo judgerInfo);

    @InheritInverseConfiguration
    JudgerInfo judgerInfoFromJSFixedFastJson(JSFixedFastJsonJudgerInfo jSFixedFastJsonJudgerInfo);

    JSFixedFastJsonJudgerVariable judgerVariableToJSFixedFastJson(JudgerVariable judgerVariable);

    @InheritInverseConfiguration
    JudgerVariable judgerVariableFromJSFixedFastJson(JSFixedFastJsonJudgerVariable jSFixedFastJsonJudgerVariable);

    JSFixedFastJsonSection sectionToJSFixedFastJson(Section section);

    @InheritInverseConfiguration
    Section sectionFromJSFixedFastJson(JSFixedFastJsonSection jSFixedFastJsonSection);

    JSFixedFastJsonTask taskToJSFixedFastJson(Task task);

    @InheritInverseConfiguration
    Task taskFromJSFixedFastJson(JSFixedFastJsonTask jSFixedFastJsonTask);

    JSFixedFastJsonTaskEvent taskEventToJSFixedFastJson(TaskEvent taskEvent);

    @InheritInverseConfiguration
    TaskEvent taskEventFromJSFixedFastJson(JSFixedFastJsonTaskEvent jSFixedFastJsonTaskEvent);

    WebInputAnalyserInfo analyserInfoToWebInput(AnalyserInfo analyserInfo);

    @InheritInverseConfiguration
    AnalyserInfo analyserInfoFromWebInput(WebInputAnalyserInfo webInputAnalyserInfo);

    WebInputDriverInfo driverInfoToWebInput(DriverInfo driverInfo);

    @InheritInverseConfiguration
    DriverInfo driverInfoFromWebInput(WebInputDriverInfo webInputDriverInfo);

    WebInputJudgerInfo judgerInfoToWebInput(JudgerInfo judgerInfo);

    @InheritInverseConfiguration
    JudgerInfo judgerInfoFromWebInput(WebInputJudgerInfo webInputJudgerInfo);

    WebInputSection sectionToWebInput(Section section);

    @InheritInverseConfiguration
    Section sectionFromWebInput(WebInputSection webInputSection);

    FastJsonAnalysisFileInfo analysisFileInfoToFastJson(AnalysisFileInfo analysisFileInfo);

    @InheritInverseConfiguration
    AnalysisFileInfo analysisFileInfoFromFastJson(FastJsonAnalysisFileInfo fastJsonAnalysisFileInfo);

    FastJsonAnalysisFilePack analysisFilePackToFastJson(AnalysisFilePack analysisFilePack);

    @InheritInverseConfiguration
    AnalysisFilePack analysisFilePackFromFastJson(FastJsonAnalysisFilePack fastJsonAnalysisFilePack);

    FastJsonAnalysisFilePackItemInfo analysisFilePackItemInfoToFastJson(
            AnalysisFilePackItemInfo analysisFilePackItemInfo
    );

    @InheritInverseConfiguration
    AnalysisFilePackItemInfo analysisFilePackItemInfoFromFastJson(
            FastJsonAnalysisFilePackItemInfo fastJsonAnalysisFilePackItemInfo
    );

    FastJsonAnalysisPictureInfo analysisPictureInfoToFastJson(AnalysisPictureInfo analysisPictureInfo);

    @InheritInverseConfiguration
    AnalysisPictureInfo analysisPictureInfoFromFastJson(FastJsonAnalysisPictureInfo fastJsonAnalysisPictureInfo);

    FastJsonAnalysisPicturePack analysisPicturePackToFastJson(AnalysisPicturePack analysisPicturePack);

    @InheritInverseConfiguration
    AnalysisPicturePack analysisPicturePackFromFastJson(FastJsonAnalysisPicturePack fastJsonAnalysisPicturePack);

    FastJsonAnalysisPicturePackItemInfo analysisPicturePackItemInfoToFastJson(
            AnalysisPicturePackItemInfo analysisPicturePackItemInfo
    );

    @InheritInverseConfiguration
    AnalysisPicturePackItemInfo analysisPicturePackItemInfoFromFastJson(
            FastJsonAnalysisPicturePackItemInfo fastJsonAnalysisPicturePackItemInfo
    );

    JSFixedFastJsonAnalysisFileInfo analysisFileInfoToJSFixedFastJson(AnalysisFileInfo analysisFileInfo);

    @InheritInverseConfiguration
    AnalysisFileInfo analysisFileInfoFromJSFixedFastJson(
            JSFixedFastJsonAnalysisFileInfo jSFixedFastJsonAnalysisFileInfo
    );

    JSFixedFastJsonAnalysisFilePack analysisFilePackToJSFixedFastJson(AnalysisFilePack analysisFilePack);

    @InheritInverseConfiguration
    AnalysisFilePack analysisFilePackFromJSFixedFastJson(
            JSFixedFastJsonAnalysisFilePack jSFixedFastJsonAnalysisFilePack
    );

    JSFixedFastJsonAnalysisFilePackItemInfo analysisFilePackItemInfoToJSFixedFastJson(
            AnalysisFilePackItemInfo analysisFilePackItemInfo
    );

    @InheritInverseConfiguration
    AnalysisFilePackItemInfo analysisFilePackItemInfoFromJSFixedFastJson(
            JSFixedFastJsonAnalysisFilePackItemInfo jSFixedFastJsonAnalysisFilePackItemInfo
    );

    JSFixedFastJsonAnalysisPictureInfo analysisPictureInfoToJSFixedFastJson(AnalysisPictureInfo analysisPictureInfo);

    @InheritInverseConfiguration
    AnalysisPictureInfo analysisPictureInfoFromJSFixedFastJson(
            JSFixedFastJsonAnalysisPictureInfo jSFixedFastJsonAnalysisPictureInfo
    );

    JSFixedFastJsonAnalysisPicturePack analysisPicturePackToJSFixedFastJson(AnalysisPicturePack analysisPicturePack);

    @InheritInverseConfiguration
    AnalysisPicturePack analysisPicturePackFromJSFixedFastJson(
            JSFixedFastJsonAnalysisPicturePack jSFixedFastJsonAnalysisPicturePack
    );

    JSFixedFastJsonAnalysisPicturePackItemInfo analysisPicturePackItemInfoToJSFixedFastJson(
            AnalysisPicturePackItemInfo analysisPicturePackItemInfo
    );

    @InheritInverseConfiguration
    AnalysisPicturePackItemInfo analysisPicturePackItemInfoFromJSFixedFastJson(
            JSFixedFastJsonAnalysisPicturePackItemInfo jSFixedFastJsonAnalysisPicturePackItemInfo
    );

    FastJsonJudgement judgementToFastJson(Judgement judgement);

    @InheritInverseConfiguration
    Judgement judgementFromFastJson(FastJsonJudgement fastJsonJudgement);

    JSFixedFastJsonJudgement judgementToJSFixedFastJson(Judgement judgement);

    @InheritInverseConfiguration
    Judgement judgementFromJSFixedFastJson(JSFixedFastJsonJudgement jSFixedFastJsonJudgement);

    FastJsonSinkerInfo sinkerInfoToFastJson(SinkerInfo sinkerInfo);

    @InheritInverseConfiguration
    SinkerInfo sinkerInfoFromFastJson(FastJsonSinkerInfo fastJsonSinkerInfo);

    FastJsonSinkerMeta sinkerMetaToFastJson(SinkerMeta sinkerMeta);

    @InheritInverseConfiguration
    SinkerMeta sinkerMetaFromFastJson(FastJsonSinkerMeta fastJsonSinkerMeta);

    FastJsonSinkerMetaIndicator sinkerMetaIndicatorToFastJson(SinkerMetaIndicator sinkerMetaIndicator);

    @InheritInverseConfiguration
    SinkerMetaIndicator sinkerMetaIndicatorFromFastJson(FastJsonSinkerMetaIndicator fastJsonSinkerMetaIndicator);

    FastJsonSinkerRelation sinkerRelationToFastJson(SinkerRelation sinkerRelation);

    @InheritInverseConfiguration
    SinkerRelation sinkerRelationFromFastJson(FastJsonSinkerRelation fastJsonSinkerRelation);

    FastJsonSinkerSupport sinkerSupportToFastJson(SinkerSupport sinkerSupport);

    @InheritInverseConfiguration
    SinkerSupport sinkerSupportFromFastJson(FastJsonSinkerSupport fastJsonSinkerSupport);

    FastJsonSinkerVariable sinkerVariableToFastJson(SinkerVariable sinkerVariable);

    @InheritInverseConfiguration
    SinkerVariable sinkerVariableFromFastJson(FastJsonSinkerVariable fastJsonSinkerVariable);

    JSFixedFastJsonSinkerInfo sinkerInfoToJSFixedFastJson(SinkerInfo sinkerInfo);

    @InheritInverseConfiguration
    SinkerInfo sinkerInfoFromJSFixedFastJson(JSFixedFastJsonSinkerInfo jSFixedFastJsonSinkerInfo);

    JSFixedFastJsonSinkerMeta sinkerMetaToJSFixedFastJson(SinkerMeta sinkerMeta);

    @InheritInverseConfiguration
    SinkerMeta sinkerMetaFromJSFixedFastJson(JSFixedFastJsonSinkerMeta jSFixedFastJsonSinkerMeta);

    JSFixedFastJsonSinkerRelation sinkerRelationToJSFixedFastJson(SinkerRelation sinkerRelation);

    @InheritInverseConfiguration
    SinkerRelation sinkerRelationFromJSFixedFastJson(JSFixedFastJsonSinkerRelation jSFixedFastJsonSinkerRelation);

    JSFixedFastJsonSinkerVariable sinkerVariableToJSFixedFastJson(SinkerVariable sinkerVariable);

    @InheritInverseConfiguration
    SinkerVariable sinkerVariableFromJSFixedFastJson(JSFixedFastJsonSinkerVariable jSFixedFastJsonSinkerVariable);

    WebInputSinkerInfo sinkerInfoToWebInput(SinkerInfo sinkerInfo);

    @InheritInverseConfiguration
    SinkerInfo sinkerInfoFromWebInput(WebInputSinkerInfo webInputSinkerInfo);

    WebInputSinkerMeta sinkerMetaToWebInput(SinkerMeta sinkerMeta);

    @InheritInverseConfiguration
    SinkerMeta sinkerMetaFromWebInput(WebInputSinkerMeta webInputSinkerMeta);

    WebInputSinkerRelation sinkerRelationToWebInput(SinkerRelation sinkerRelation);

    @InheritInverseConfiguration
    SinkerRelation sinkerRelationFromWebInput(WebInputSinkerRelation webInputSinkerRelation);

    FastJsonProviderInfo providerInfoToFastJson(ProviderInfo providerInfo);

    @InheritInverseConfiguration
    ProviderInfo providerInfoFromFastJson(FastJsonProviderInfo fastJsonProviderInfo);

    FastJsonProviderSupport providerSupportToFastJson(ProviderSupport providerSupport);

    @InheritInverseConfiguration
    ProviderSupport providerSupportFromFastJson(FastJsonProviderSupport fastJsonProviderSupport);

    JSFixedFastJsonProviderInfo providerInfoToJSFixedFastJson(ProviderInfo providerInfo);

    @InheritInverseConfiguration
    ProviderInfo providerInfoFromJSFixedFastJson(JSFixedFastJsonProviderInfo jSFixedFastJsonProviderInfo);

    WebInputProviderInfo providerInfoToWebInput(ProviderInfo providerInfo);

    @InheritInverseConfiguration
    ProviderInfo providerInfoFromWebInput(WebInputProviderInfo webInputProviderInfo);

    FastJsonVisualizeData visualizeDataToFastJson(VisualizeData visualizeData);

    @InheritInverseConfiguration
    VisualizeData visualizeDataFromFastJson(FastJsonVisualizeData fastJsonVisualizeData);

    FastJsonVisualizerInfo visualizerInfoToFastJson(VisualizerInfo visualizerInfo);

    @InheritInverseConfiguration
    VisualizerInfo visualizerInfoFromFastJson(FastJsonVisualizerInfo fastJsonVisualizerInfo);

    FastJsonVisualizerSupport visualizerSupportToFastJson(VisualizerSupport visualizerSupport);

    @InheritInverseConfiguration
    VisualizerSupport visualizerSupportFromFastJson(FastJsonVisualizerSupport fastJsonVisualizerSupport);

    JSFixedFastJsonVisualizeData visualizeDataToJSFixedFastJson(VisualizeData visualizeData);

    @InheritInverseConfiguration
    VisualizeData visualizeDataFromJSFixedFastJson(JSFixedFastJsonVisualizeData jSFixedFastJsonVisualizeData);

    JSFixedFastJsonVisualizerInfo visualizerInfoToJSFixedFastJson(VisualizerInfo visualizerInfo);

    @InheritInverseConfiguration
    VisualizerInfo visualizerInfoFromJSFixedFastJson(JSFixedFastJsonVisualizerInfo jSFixedFastJsonVisualizerInfo);

    FastJsonAdapterInfo adapterInfoToFastJson(AdapterInfo adapterInfo);

    @InheritInverseConfiguration
    AdapterInfo adapterInfoFromFastJson(FastJsonAdapterInfo fastJsonAdapterInfo);

    FastJsonAdapterSupport adapterSupportToFastJson(AdapterSupport adapterSupport);

    @InheritInverseConfiguration
    AdapterSupport adapterSupportFromFastJson(FastJsonAdapterSupport fastJsonAdapterSupport);

    JSFixedFastJsonAdapterInfo adapterInfoToJSFixedFastJson(AdapterInfo adapterInfo);

    @InheritInverseConfiguration
    AdapterInfo adapterInfoFromJSFixedFastJson(JSFixedFastJsonAdapterInfo jSFixedFastJsonAdapterInfo);

    WebInputAdapterInfo adapterInfoToWebInput(AdapterInfo adapterInfo);

    @InheritInverseConfiguration
    AdapterInfo adapterInfoFromWebInput(WebInputAdapterInfo webInputAdapterInfo);

    FastJsonFilterInfo filterInfoToFastJson(FilterInfo filterInfo);

    @InheritInverseConfiguration
    FilterInfo filterInfoFromFastJson(FastJsonFilterInfo fastJsonFilterInfo);

    FastJsonFilterSupport filterSupportToFastJson(FilterSupport filterSupport);

    @InheritInverseConfiguration
    FilterSupport filterSupportFromFastJson(FastJsonFilterSupport fastJsonFilterSupport);

    JSFixedFastJsonFilterInfo filterInfoToJSFixedFastJson(FilterInfo filterInfo);

    @InheritInverseConfiguration
    FilterInfo filterInfoFromJSFixedFastJson(JSFixedFastJsonFilterInfo jSFixedFastJsonFilterInfo);

    WebInputFilterInfo filterInfoToWebInput(FilterInfo filterInfo);

    @InheritInverseConfiguration
    FilterInfo filterInfoFromWebInput(WebInputFilterInfo webInputFilterInfo);

    // -----------------------------------------------------------Judge DTO-----------------------------------------------------------
    WebInputAnalysisFileFileDownloadInfo analysisFileFileDownloadInfoToWebInput(
            AnalysisFileFileDownloadInfo analysisFileFileDownloadInfo
    );

    @InheritInverseConfiguration
    AnalysisFileFileDownloadInfo analysisFileFileDownloadInfoFromWebInput(
            WebInputAnalysisFileFileDownloadInfo webInputAnalysisFileFileDownloadInfo
    );

    WebInputAnalysisFilePackItemFileDownloadInfo analysisFilePackItemFileDownloadInfoToWebInput(
            AnalysisFilePackItemFileDownloadInfo analysisFilePackItemFileDownloadInfo
    );

    @InheritInverseConfiguration
    AnalysisFilePackItemFileDownloadInfo analysisFilePackItemFileDownloadInfoFromWebInput(
            WebInputAnalysisFilePackItemFileDownloadInfo webInputAnalysisFilePackItemFileDownloadInfo
    );

    WebInputAnalysisPictureFileDownloadInfo analysisPictureFileDownloadInfoToWebInput(
            AnalysisPictureFileDownloadInfo analysisPictureFileDownloadInfo
    );

    @InheritInverseConfiguration
    AnalysisPictureFileDownloadInfo analysisPictureFileDownloadInfoFromWebInput(
            WebInputAnalysisPictureFileDownloadInfo webInputAnalysisPictureFileDownloadInfo
    );

    WebInputAnalysisPicturePackItemFileDownloadInfo analysisPicturePackItemFileDownloadInfoToWebInput(
            AnalysisPicturePackItemFileDownloadInfo analysisPicturePackItemFileDownloadInfo
    );

    @InheritInverseConfiguration
    AnalysisPicturePackItemFileDownloadInfo analysisPicturePackItemFileDownloadInfoFromWebInput(
            WebInputAnalysisPicturePackItemFileDownloadInfo webInputAnalysisPicturePackItemFileDownloadInfo
    );

    WebInputAnalysisPicturePackItemThumbnailDownloadInfo analysisPicturePackItemThumbnailDownloadInfoToWebInput(
            AnalysisPicturePackItemThumbnailDownloadInfo analysisPicturePackItemThumbnailDownloadInfo
    );

    @InheritInverseConfiguration
    AnalysisPicturePackItemThumbnailDownloadInfo analysisPicturePackItemThumbnailDownloadInfoFromWebInput(
            WebInputAnalysisPicturePackItemThumbnailDownloadInfo webInputAnalysisPicturePackItemThumbnailDownloadInfo
    );

    WebInputAnalysisPictureThumbnailDownloadInfo analysisPictureThumbnailDownloadInfoToWebInput(
            AnalysisPictureThumbnailDownloadInfo analysisPictureThumbnailDownloadInfo
    );

    @InheritInverseConfiguration
    AnalysisPictureThumbnailDownloadInfo analysisPictureThumbnailDownloadInfoFromWebInput(
            WebInputAnalysisPictureThumbnailDownloadInfo webInputAnalysisPictureThumbnailDownloadInfo
    );

    WebInputSinkerMetaCompleteInfo sinkerMetaCompleteInfoToWebInput(
            WebInputSinkerMetaCompleteInfo webInputSinkerMetaCompleteInfo
    );

    WebInputSinkerMetaCompleteInfo sinkerMetaCompleteInfoToWebInput(SinkerMetaCompleteInfo sinkerMetaCompleteInfo);

    @InheritInverseConfiguration
    SinkerMetaCompleteInfo sinkerMetaCompleteInfoFromWebInput(
            WebInputSinkerMetaCompleteInfo webInputSinkerMetaCompleteInfo
    );

    WebInputSinkerMetaResetInfo sinkerMetaResetInfoToWebInput(SinkerMetaResetInfo sinkerMetaResetInfo);

    @InheritInverseConfiguration
    SinkerMetaResetInfo sinkerMetaResetInfoFromWebInput(WebInputSinkerMetaResetInfo webInputSinkerMetaResetInfo);

    FastJsonSinkInfo sinkInfoToFastJson(SinkInfo sinkInfo);

    @InheritInverseConfiguration
    SinkInfo sinkInfoFromFastJson(FastJsonSinkInfo fastJsonSinkInfo);

    JSFixedFastJsonSinkInfo sinkInfoToJSFixedFastJson(SinkInfo sinkInfo);

    @InheritInverseConfiguration
    SinkInfo sinkInfoFromJSFixedFastJson(JSFixedFastJsonSinkInfo jSFixedFastJsonSinkInfo);
}
