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
        destination.put(JudgerException.class, ServiceExceptionCodes.JUDGER_FAILED);
        destination.put(JudgerMakeException.class, ServiceExceptionCodes.JUDGER_MAKE_FAILED);
        destination.put(UnsupportedJudgerTypeException.class, ServiceExceptionCodes.JUDGER_TYPE_UNSUPPORTED);
        destination.put(RepositoryException.class, ServiceExceptionCodes.REPOSITORY_FAILED);
        destination.put(SinkException.class, ServiceExceptionCodes.SINK_FAILED);
        return new MapServiceExceptionMapper(destination, com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.UNDEFINE);
    }
}
