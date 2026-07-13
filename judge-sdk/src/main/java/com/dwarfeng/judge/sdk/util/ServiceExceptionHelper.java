package com.dwarfeng.judge.sdk.util;

import com.dwarfeng.judge.stack.exception.*;
import com.dwarfeng.subgrade.stack.exception.ServiceException;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 异常的帮助工具类。
 *
 * @author DwArFeng
 * @since 3.0.0
 */
public final class ServiceExceptionHelper {

    /**
     * 向指定的映射中添加 judge 默认的目标映射。
     *
     * <p>
     * 该方法可以在配置类中快速的搭建目标映射。
     *
     * @param map 指定的映射，允许为 <code>null</code>。
     * @return 添加了默认目标的映射。
     */
    public static Map<Class<? extends Exception>, ServiceException.Code> putDefaultDestination(
            Map<Class<? extends Exception>, ServiceException.Code> map
    ) {
        if (Objects.isNull(map)) {
            map = new HashMap<>();
        }

        map.put(AnalyserInfoNotExistsException.class, ServiceExceptionCodes.ANALYSER_INFO_NOT_EXISTS);
        map.put(JudgerInfoNotExistsException.class, ServiceExceptionCodes.JUDGER_INFO_NOT_EXISTS);
        map.put(AnalyserVariableNotExistsException.class, ServiceExceptionCodes.ANALYSER_VARIABLE_NOT_EXISTS);
        map.put(JudgerVariableNotExistsException.class, ServiceExceptionCodes.JUDGER_VARIABLE_NOT_EXISTS);
        map.put(SectionNotExistsException.class, ServiceExceptionCodes.SECTION_NOT_EXISTS);
        map.put(TaskNotExistsException.class, ServiceExceptionCodes.TASK_NOT_EXISTS);
        map.put(TaskStatusMismatchException.class, ServiceExceptionCodes.TASK_STATUS_MISMATCH);
        map.put(InvalidTaskStatusException.class, ServiceExceptionCodes.INVALID_TASK_STATUS);
        map.put(AnalysisFileNotExistsException.class, ServiceExceptionCodes.ANALYSIS_FILE_NOT_EXISTS);
        map.put(
                AnalysisFilePackItemNotExistsException.class,
                ServiceExceptionCodes.ANALYSIS_FILE_PACK_ITEM_NOT_EXISTS
        );
        map.put(AnalysisFilePackNotExistsException.class, ServiceExceptionCodes.ANALYSIS_FILE_PACK_NOT_EXISTS);
        map.put(AnalysisPictureNotExistsException.class, ServiceExceptionCodes.ANALYSIS_PICTURE_NOT_EXISTS);
        map.put(
                AnalysisPicturePackItemNotExistsException.class,
                ServiceExceptionCodes.ANALYSIS_PICTURE_PACK_ITEM_NOT_EXISTS
        );
        map.put(AnalysisPicturePackNotExistsException.class, ServiceExceptionCodes.ANALYSIS_PICTURE_PACK_NOT_EXISTS);
        map.put(
                AnalysisDataTypeAnalysisValueMismatchException.class,
                ServiceExceptionCodes.ANALYSE_DATA_TYPE_ANALYSIS_VALUE_MISMATCH
        );
        map.put(AnalysisNotExistsException.class, ServiceExceptionCodes.ANALYSIS_NOT_EXISTS);
        map.put(InvalidAnalysisDataTypeException.class, ServiceExceptionCodes.INVALID_ANALYSIS_DATA_TYPE);
        map.put(
                InvalidAnalysisFilePackUpsertTypeException.class,
                ServiceExceptionCodes.INVALID_ANALYSIS_FILE_PACK_UPSERT_TYPE
        );
        map.put(
                InvalidAnalysisPicturePackUpsertTypeException.class,
                ServiceExceptionCodes.INVALID_ANALYSIS_PICTURE_PACK_UPSERT_TYPE
        );
        map.put(AnalyserException.class, ServiceExceptionCodes.ANALYSER_FAILED);
        map.put(AnalyserMakeException.class, ServiceExceptionCodes.ANALYSER_MAKE_FAILED);
        map.put(AnalyserExecutionException.class, ServiceExceptionCodes.ANALYSER_EXECUTION_FAILED);
        map.put(UnsupportedAnalyserTypeException.class, ServiceExceptionCodes.ANALYSER_TYPE_UNSUPPORTED);
        map.put(JudgerException.class, ServiceExceptionCodes.JUDGER_FAILED);
        map.put(JudgerMakeException.class, ServiceExceptionCodes.JUDGER_MAKE_FAILED);
        map.put(JudgerExecutionException.class, ServiceExceptionCodes.JUDGER_EXECUTION_FAILED);
        map.put(UnsupportedJudgerTypeException.class, ServiceExceptionCodes.JUDGER_TYPE_UNSUPPORTED);
        map.put(ReceiverException.class, ServiceExceptionCodes.RECEIVER_FAILED);
        map.put(ReceiverNotStartException.class, ServiceExceptionCodes.RECEIVER_NOT_START);
        map.put(ReceiverExecutionException.class, ServiceExceptionCodes.RECEIVER_EXECUTION_FAILED);
        map.put(DispatcherException.class, ServiceExceptionCodes.DISPATCHER_FAILED);
        map.put(DispatcherNotStartException.class, ServiceExceptionCodes.DISPATCHER_NOT_START);
        map.put(DispatcherExecutionException.class, ServiceExceptionCodes.DISPATCHER_EXECUTION_FAILED);
        map.put(DriverException.class, ServiceExceptionCodes.DRIVER_FAILED);
        map.put(UnsupportedDriverTypeException.class, ServiceExceptionCodes.DRIVER_TYPE_UNSUPPORTED);
        map.put(JudgementNotExistsException.class, ServiceExceptionCodes.JUDGEMENT_NOT_EXISTS);
        map.put(InvalidJudgementValueException.class, ServiceExceptionCodes.INVALID_JUDGEMENT_VALUE);
        map.put(SinkerInfoNotExistsException.class, ServiceExceptionCodes.SINKER_INFO_NOT_EXISTS);
        map.put(SinkerVariableNotExistsException.class, ServiceExceptionCodes.SINKER_VARIABLE_NOT_EXISTS);
        map.put(SinkerNotExistsException.class, ServiceExceptionCodes.SINKER_NOT_EXISTS);
        map.put(SinkerException.class, ServiceExceptionCodes.SINKER_FAILED);
        map.put(SinkerMakeException.class, ServiceExceptionCodes.SINKER_MAKE_FAILED);
        map.put(UnsupportedSinkerTypeException.class, ServiceExceptionCodes.SINKER_TYPE_UNSUPPORTED);
        map.put(SinkerExecutionException.class, ServiceExceptionCodes.SINKER_EXECUTION_FAILED);
        map.put(SinkerSessionException.class, ServiceExceptionCodes.SINKER_SESSION_FAILED);
        map.put(ProviderNotExistsException.class, ServiceExceptionCodes.PROVIDER_NOT_EXISTS);
        map.put(ProviderException.class, ServiceExceptionCodes.PROVIDER_FAILED);
        map.put(ProviderMakeException.class, ServiceExceptionCodes.PROVIDER_MAKE_FAILED);
        map.put(UnsupportedProviderTypeException.class, ServiceExceptionCodes.PROVIDER_TYPE_UNSUPPORTED);
        map.put(ProviderExecutionException.class, ServiceExceptionCodes.PROVIDER_EXECUTION_FAILED);
        map.put(ProviderSessionException.class, ServiceExceptionCodes.PROVIDER_SESSION_FAILED);
        map.put(VisualizerException.class, ServiceExceptionCodes.VISUALIZER_FAILED);
        map.put(VisualizerMakeException.class, ServiceExceptionCodes.VISUALIZER_MAKE_FAILED);
        map.put(VisualizerExecutionException.class, ServiceExceptionCodes.VISUALIZER_EXECUTION_FAILED);
        map.put(UnsupportedVisualizerTypeException.class, ServiceExceptionCodes.VISUALIZER_TYPE_UNSUPPORTED);
        map.put(AdapterNotExistsException.class, ServiceExceptionCodes.ADAPTER_NOT_EXISTS);
        map.put(AdapterException.class, ServiceExceptionCodes.ADAPTER_FAILED);
        map.put(AdapterMakeException.class, ServiceExceptionCodes.ADAPTER_MAKE_FAILED);
        map.put(UnsupportedAdapterTypeException.class, ServiceExceptionCodes.ADAPTER_TYPE_UNSUPPORTED);
        map.put(AdapterExecutionException.class, ServiceExceptionCodes.ADAPTER_EXECUTION_FAILED);
        map.put(FilterNotExistsException.class, ServiceExceptionCodes.FILTER_NOT_EXISTS);
        map.put(FilterException.class, ServiceExceptionCodes.FILTER_FAILED);
        map.put(FilterMakeException.class, ServiceExceptionCodes.FILTER_MAKE_FAILED);
        map.put(UnsupportedFilterTypeException.class, ServiceExceptionCodes.FILTER_TYPE_UNSUPPORTED);
        map.put(FilterExecutionException.class, ServiceExceptionCodes.FILTER_EXECUTION_FAILED);
        map.put(InvalidVariableValueTypeException.class, ServiceExceptionCodes.INVALID_VARIABLE_VALUE_TYPE);
        map.put(VariableValueTypeMismatchException.class, ServiceExceptionCodes.VARIABLE_VALUE_TYPE_MISMATCH);
        return map;
    }

    private ServiceExceptionHelper() {
        throw new IllegalStateException("禁止外部实例化");
    }
}
