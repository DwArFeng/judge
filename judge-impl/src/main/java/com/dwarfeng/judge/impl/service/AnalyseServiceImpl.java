package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.handler.AnalyseHandler;
import com.dwarfeng.judge.stack.service.AnalyseService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalyseServiceImpl implements AnalyseService {

    @Autowired
    private AnalyseHandler analyseHandler;
    @Autowired
    private ServiceExceptionMapper sem;

    @Override
    public void analyse(LongIdKey sectionKey) throws ServiceException {
        try {
            analyseHandler.analyse(sectionKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("判断部件时发生异常",
                    LogLevel.WARN, sem, e
            );
        }
    }
}
