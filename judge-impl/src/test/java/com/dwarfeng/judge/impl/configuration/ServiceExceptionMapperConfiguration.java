package com.dwarfeng.judge.impl.configuration;

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
        Map<Class<? extends Exception>, ServiceException.Code> destination = ServiceExceptionHelper.putDefaultDestination(null);
        destination = com.dwarfeng.datamark.util.ServiceExceptionHelper.putDefaultDestination(destination);
        destination = com.dwarfeng.ftp.util.ServiceExceptionHelper.putDefaultDestination(destination);
        destination.put(AnalyserInfoNotExistsException.class, ServiceExceptionCodes.ANALYSER_INFO_NOT_EXISTS);
        destination.put(JudgerInfoNotExistsException.class, ServiceExceptionCodes.JUDGER_INFO_NOT_EXISTS);
        destination.put(AnalyserVariableNotExistsException.class, ServiceExceptionCodes.ANALYSER_VARIABLE_NOT_EXISTS);
        destination.put(JudgerVariableNotExistsException.class, ServiceExceptionCodes.JUDGER_VARIABLE_NOT_EXISTS);
        destination.put(SectionNotExistsException.class, ServiceExceptionCodes.SECTION_NOT_EXISTS);
        destination.put(TaskNotExistsException.class, ServiceExceptionCodes.TASK_NOT_EXISTS);
        destination.put(TaskStatusMismatchException.class, ServiceExceptionCodes.TASK_STATUS_MISMATCH);
        destination.put(InvalidTaskStatusException.class, ServiceExceptionCodes.INVALID_TASK_STATUS);
        destination.put(AnalysisFileNotExistsException.class, ServiceExceptionCodes.ANALYSIS_FILE_NOT_EXISTS);
        destination.put(AnalysisFilePackItemNotExistsException.class, ServiceExceptionCodes.ANALYSIS_FILE_PACK_ITEM_NOT_EXISTS);
        destination.put(AnalysisFilePackNotExistsException.class, ServiceExceptionCodes.ANALYSIS_FILE_PACK_NOT_EXISTS);
        destination.put(AnalysisPictureNotExistsException.class, ServiceExceptionCodes.ANALYSIS_PICTURE_NOT_EXISTS);
        destination.put(AnalysisPicturePackItemNotExistsException.class, ServiceExceptionCodes.ANALYSIS_PICTURE_PACK_ITEM_NOT_EXISTS);
        destination.put(AnalysisPicturePackNotExistsException.class, ServiceExceptionCodes.ANALYSIS_PICTURE_PACK_NOT_EXISTS);
        destination.put(AnalysisDataTypeAnalysisValueMismatchException.class, ServiceExceptionCodes.ANALYSE_DATA_TYPE_ANALYSIS_VALUE_MISMATCH);
        destination.put(AnalysisNotExistsException.class, ServiceExceptionCodes.ANALYSIS_NOT_EXISTS);
        destination.put(InvalidAnalysisDataTypeException.class, ServiceExceptionCodes.INVALID_ANALYSIS_DATA_TYPE);
        destination.put(InvalidAnalysisFilePackUpsertTypeException.class, ServiceExceptionCodes.INVALID_ANALYSIS_FILE_PACK_UPSERT_TYPE);
        destination.put(InvalidAnalysisPicturePackUpsertTypeException.class, ServiceExceptionCodes.INVALID_ANALYSIS_PICTURE_PACK_UPSERT_TYPE);
        destination.put(AnalyserException.class, ServiceExceptionCodes.ANALYSER_FAILED);
        destination.put(AnalyserMakeException.class, ServiceExceptionCodes.ANALYSER_MAKE_FAILED);
        destination.put(AnalyserExecutionException.class, ServiceExceptionCodes.ANALYSER_EXECUTION_FAILED);
        destination.put(UnsupportedAnalyserTypeException.class, ServiceExceptionCodes.ANALYSER_TYPE_UNSUPPORTED);
        return new MapServiceExceptionMapper(destination, com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.UNDEFINED);
    }
}
