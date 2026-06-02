package com.dwarfeng.judge.node.configuration;

import com.dwarfeng.judge.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.judge.stack.exception.*;
import com.dwarfeng.subgrade.impl.exception.MapServiceExceptionMapper;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ServiceExceptionMapperConfiguration {

    @Bean
    public MapServiceExceptionMapper mapServiceExceptionMapper() {
        Map<Class<? extends Exception>, ServiceException.Code> des = ServiceExceptionHelper.putDefaultDestination(null);
        des = com.dwarfeng.datamark.util.ServiceExceptionHelper.putDefaultDestination(des);
        des = com.dwarfeng.ftp.util.ServiceExceptionHelper.putDefaultDestination(des);
        des.put(AnalyserInfoNotExistsException.class, ServiceExceptionCodes.ANALYSER_INFO_NOT_EXISTS);
        des.put(JudgerInfoNotExistsException.class, ServiceExceptionCodes.JUDGER_INFO_NOT_EXISTS);
        des.put(AnalyserVariableNotExistsException.class, ServiceExceptionCodes.ANALYSER_VARIABLE_NOT_EXISTS);
        des.put(JudgerVariableNotExistsException.class, ServiceExceptionCodes.JUDGER_VARIABLE_NOT_EXISTS);
        des.put(SectionNotExistsException.class, ServiceExceptionCodes.SECTION_NOT_EXISTS);
        des.put(TaskNotExistsException.class, ServiceExceptionCodes.TASK_NOT_EXISTS);
        des.put(TaskStatusMismatchException.class, ServiceExceptionCodes.TASK_STATUS_MISMATCH);
        des.put(InvalidTaskStatusException.class, ServiceExceptionCodes.INVALID_TASK_STATUS);
        des.put(AnalysisFileNotExistsException.class, ServiceExceptionCodes.ANALYSIS_FILE_NOT_EXISTS);
        des.put(AnalysisFilePackItemNotExistsException.class, ServiceExceptionCodes.ANALYSIS_FILE_PACK_ITEM_NOT_EXISTS);
        des.put(AnalysisFilePackNotExistsException.class, ServiceExceptionCodes.ANALYSIS_FILE_PACK_NOT_EXISTS);
        des.put(AnalysisPictureNotExistsException.class, ServiceExceptionCodes.ANALYSIS_PICTURE_NOT_EXISTS);
        des.put(AnalysisPicturePackItemNotExistsException.class, ServiceExceptionCodes.ANALYSIS_PICTURE_PACK_ITEM_NOT_EXISTS);
        des.put(AnalysisPicturePackNotExistsException.class, ServiceExceptionCodes.ANALYSIS_PICTURE_PACK_NOT_EXISTS);
        des.put(AnalysisDataTypeAnalysisValueMismatchException.class, ServiceExceptionCodes.ANALYSE_DATA_TYPE_ANALYSIS_VALUE_MISMATCH);
        des.put(AnalysisNotExistsException.class, ServiceExceptionCodes.ANALYSIS_NOT_EXISTS);
        des.put(InvalidAnalysisDataTypeException.class, ServiceExceptionCodes.INVALID_ANALYSIS_DATA_TYPE);
        des.put(InvalidAnalysisFilePackUpsertTypeException.class, ServiceExceptionCodes.INVALID_ANALYSIS_FILE_PACK_UPSERT_TYPE);
        des.put(InvalidAnalysisPicturePackUpsertTypeException.class, ServiceExceptionCodes.INVALID_ANALYSIS_PICTURE_PACK_UPSERT_TYPE);
        des.put(AnalyserException.class, ServiceExceptionCodes.ANALYSER_FAILED);
        des.put(AnalyserMakeException.class, ServiceExceptionCodes.ANALYSER_MAKE_FAILED);
        des.put(AnalyserExecutionException.class, ServiceExceptionCodes.ANALYSER_EXECUTION_FAILED);
        des.put(UnsupportedAnalyserTypeException.class, ServiceExceptionCodes.ANALYSER_TYPE_UNSUPPORTED);
        des.put(JudgerException.class, ServiceExceptionCodes.JUDGER_FAILED);
        des.put(JudgerMakeException.class, ServiceExceptionCodes.JUDGER_MAKE_FAILED);
        des.put(JudgerExecutionException.class, ServiceExceptionCodes.JUDGER_EXECUTION_FAILED);
        des.put(UnsupportedJudgerTypeException.class, ServiceExceptionCodes.JUDGER_TYPE_UNSUPPORTED);
        des.put(ReceiverException.class, ServiceExceptionCodes.RECEIVER_FAILED);
        des.put(ReceiverNotStartException.class, ServiceExceptionCodes.RECEIVER_NOT_START);
        des.put(ReceiverExecutionException.class, ServiceExceptionCodes.RECEIVER_EXECUTION_FAILED);
        des.put(DispatcherException.class, ServiceExceptionCodes.DISPATCHER_FAILED);
        des.put(DispatcherNotStartException.class, ServiceExceptionCodes.DISPATCHER_NOT_START);
        des.put(DispatcherExecutionException.class, ServiceExceptionCodes.DISPATCHER_EXECUTION_FAILED);
        des.put(DriverException.class, ServiceExceptionCodes.DRIVER_FAILED);
        des.put(UnsupportedDriverTypeException.class, ServiceExceptionCodes.DRIVER_TYPE_UNSUPPORTED);
        des.put(JudgementNotExistsException.class, ServiceExceptionCodes.JUDGEMENT_NOT_EXISTS);
        des.put(InvalidJudgementValueException.class, ServiceExceptionCodes.INVALID_JUDGEMENT_VALUE);
        des.put(SinkerInfoNotExistsException.class, ServiceExceptionCodes.SINKER_INFO_NOT_EXISTS);
        des.put(SinkerVariableNotExistsException.class, ServiceExceptionCodes.SINKER_VARIABLE_NOT_EXISTS);
        des.put(SinkerNotExistsException.class, ServiceExceptionCodes.SINKER_NOT_EXISTS);
        des.put(SinkerException.class, ServiceExceptionCodes.SINKER_FAILED);
        des.put(SinkerMakeException.class, ServiceExceptionCodes.SINKER_MAKE_FAILED);
        des.put(UnsupportedSinkerTypeException.class, ServiceExceptionCodes.SINKER_TYPE_UNSUPPORTED);
        des.put(SinkerExecutionException.class, ServiceExceptionCodes.SINKER_EXECUTION_FAILED);
        des.put(SinkerSessionException.class, ServiceExceptionCodes.SINKER_SESSION_FAILED);
        des.put(ProviderNotExistsException.class, ServiceExceptionCodes.PROVIDER_NOT_EXISTS);
        des.put(ProviderException.class, ServiceExceptionCodes.PROVIDER_FAILED);
        des.put(ProviderMakeException.class, ServiceExceptionCodes.PROVIDER_MAKE_FAILED);
        des.put(UnsupportedProviderTypeException.class, ServiceExceptionCodes.PROVIDER_TYPE_UNSUPPORTED);
        des.put(ProviderExecutionException.class, ServiceExceptionCodes.PROVIDER_EXECUTION_FAILED);
        des.put(ProviderSessionException.class, ServiceExceptionCodes.PROVIDER_SESSION_FAILED);
        des.put(VisualizerException.class, ServiceExceptionCodes.VISUALIZER_FAILED);
        des.put(VisualizerMakeException.class, ServiceExceptionCodes.VISUALIZER_MAKE_FAILED);
        des.put(VisualizerExecutionException.class, ServiceExceptionCodes.VISUALIZER_EXECUTION_FAILED);
        des.put(UnsupportedVisualizerTypeException.class, ServiceExceptionCodes.VISUALIZER_TYPE_UNSUPPORTED);
        des.put(AdapterNotExistsException.class, ServiceExceptionCodes.ADAPTER_NOT_EXISTS);
        des.put(AdapterException.class, ServiceExceptionCodes.ADAPTER_FAILED);
        des.put(AdapterMakeException.class, ServiceExceptionCodes.ADAPTER_MAKE_FAILED);
        des.put(UnsupportedAdapterTypeException.class, ServiceExceptionCodes.ADAPTER_TYPE_UNSUPPORTED);
        des.put(AdapterExecutionException.class, ServiceExceptionCodes.ADAPTER_EXECUTION_FAILED);
        des.put(FilterNotExistsException.class, ServiceExceptionCodes.FILTER_NOT_EXISTS);
        des.put(FilterException.class, ServiceExceptionCodes.FILTER_FAILED);
        des.put(FilterMakeException.class, ServiceExceptionCodes.FILTER_MAKE_FAILED);
        des.put(UnsupportedFilterTypeException.class, ServiceExceptionCodes.FILTER_TYPE_UNSUPPORTED);
        des.put(FilterExecutionException.class, ServiceExceptionCodes.FILTER_EXECUTION_FAILED);
        return new MapServiceExceptionMapper(des, com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.UNDEFINED);
    }
}
