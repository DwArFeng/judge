package com.dwarfeng.judge.node.configuration;

import com.dwarfeng.judge.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.judge.stack.exception.AnalyserInfoNotExistsException;
import com.dwarfeng.judge.stack.exception.AnalyserVariableNotExistsException;
import com.dwarfeng.judge.stack.exception.JudgerInfoNotExistsException;
import com.dwarfeng.judge.stack.exception.JudgerVariableNotExistsException;
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
        destination.put(AnalyserInfoNotExistsException.class, ServiceExceptionCodes.ANALYSER_INFO_NOT_EXISTS);
        destination.put(JudgerInfoNotExistsException.class, ServiceExceptionCodes.JUDGER_INFO_NOT_EXISTS);
        destination.put(AnalyserVariableNotExistsException.class, ServiceExceptionCodes.ANALYSER_VARIABLE_NOT_EXISTS);
        destination.put(JudgerVariableNotExistsException.class, ServiceExceptionCodes.JUDGER_VARIABLE_NOT_EXISTS);
        return new MapServiceExceptionMapper(destination, com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.UNDEFINED);
    }
}
