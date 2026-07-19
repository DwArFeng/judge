package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.dto.ProvideLookupInfo;
import com.dwarfeng.judge.stack.bean.dto.ProvideLookupResult;
import com.dwarfeng.judge.stack.handler.ProvideHandler;
import com.dwarfeng.judge.stack.service.ProvideQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class ProvideQosServiceImpl implements ProvideQosService {

    private final ProvideHandler provideHandler;

    private final ServiceExceptionMapper sem;

    public ProvideQosServiceImpl(ProvideHandler provideHandler, ServiceExceptionMapper sem) {
        this.provideHandler = provideHandler;
        this.sem = sem;
    }

    @Override
    public ProvideLookupResult lookup(ProvideLookupInfo info) throws ServiceException {
        try {
            return provideHandler.lookup(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查询提供数据时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
