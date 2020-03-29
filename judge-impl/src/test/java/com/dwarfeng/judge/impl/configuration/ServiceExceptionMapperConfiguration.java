package com.dwarfeng.judge.impl.configuration;

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
//        destination.put(FilterException.class, ServiceExceptionCodes.FILTER_FAILED);
//        destination.put(FilterMakeException.class, ServiceExceptionCodes.FILTER_MAKE_FAILED);
//        destination.put(UnsupportedFilterTypeException.class, ServiceExceptionCodes.FILTER_TYPE_UNSUPPORTED);
//        destination.put(TriggerException.class, ServiceExceptionCodes.TRIGGER_FAILED);
//        destination.put(TriggerMakeException.class, ServiceExceptionCodes.TRIGGER_MAKE_FAILED);
//        destination.put(UnsupportedTriggerTypeException.class, ServiceExceptionCodes.TRIGGER_TYPE_UNSUPPORTED);
//        destination.put(PointNotExistsException.class, ServiceExceptionCodes.POINT_NOT_EXISTS);
//        destination.put(RecordDisabledException.class, ServiceExceptionCodes.RECORD_HANDLER_DISABLED);
        return new MapServiceExceptionMapper(destination, com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.UNDEFINE);
    }
}
