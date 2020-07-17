package com.dwarfeng.judge.node.assign.configuration;

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
        destination.put(JudgerException.class, ServiceExceptionCodes.JUDGER_FAILED);
        destination.put(JudgerMakeException.class, ServiceExceptionCodes.JUDGER_MAKE_FAILED);
        destination.put(UnsupportedJudgerTypeException.class, ServiceExceptionCodes.JUDGER_TYPE_UNSUPPORTED);
        destination.put(RepositoryException.class, ServiceExceptionCodes.REPOSITORY_FAILED);
        destination.put(UnsupportedRepositoryCategoryException.class, ServiceExceptionCodes.REPOSITORY_CATEGORY_UNSUPPORTED);
        destination.put(SinkException.class, ServiceExceptionCodes.SINK_FAILED);
        destination.put(DriverException.class, ServiceExceptionCodes.DRIVER_FAILED);
        destination.put(UnsupportedDriverTypeException.class, ServiceExceptionCodes.DRIVER_TYPE_UNSUPPORTED);
        destination.put(JudgeWorkException.class, ServiceExceptionCodes.JUDGE_WORK_FAILED);
        destination.put(JudgeWorkDisabledException.class, ServiceExceptionCodes.JUDGE_WORK_DISABLED);
        destination.put(SectionNotExistsException.class, ServiceExceptionCodes.SECTION_NOT_EXISTS);
        return new MapServiceExceptionMapper(destination, com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.UNDEFINE);
    }
}
