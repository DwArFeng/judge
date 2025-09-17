package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.handler.SupportHandler;
import com.dwarfeng.judge.stack.service.SupportQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class SupportQosServiceImpl implements SupportQosService {

    private final SupportHandler supportHandler;

    private final ServiceExceptionMapper sem;

    public SupportQosServiceImpl(SupportHandler supportHandler, ServiceExceptionMapper sem) {
        this.supportHandler = supportHandler;
        this.sem = sem;
    }

    @Override
    public void resetAnalyser() throws ServiceException {
        try {
            supportHandler.resetAnalyser();
        } catch (HandlerException e) {
            throw ServiceExceptionHelper.logParse("重置分析器时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void resetDriver() throws ServiceException {
        try {
            supportHandler.resetDriver();
        } catch (HandlerException e) {
            throw ServiceExceptionHelper.logParse("重置驱动器时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void resetJudger() throws ServiceException {
        try {
            supportHandler.resetJudger();
        } catch (HandlerException e) {
            throw ServiceExceptionHelper.logParse("重置判断器时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void resetSinker() throws ServiceException {
        try {
            supportHandler.resetSinker();
        } catch (HandlerException e) {
            throw ServiceExceptionHelper.logParse("重置下沉器时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
