package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.EvaluateInfo;
import com.dwarfeng.judge.stack.handler.EvaluateHandler;
import com.dwarfeng.judge.stack.handler.EvaluateLocalCacheHandler;
import com.dwarfeng.judge.stack.service.EvaluateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private EvaluateHandler evaluateHandler;
    @Autowired
    private EvaluateLocalCacheHandler evaluateLocalCacheHandler;
    @Autowired
    private ServiceExceptionMapper sem;

    @Override
    public void evaluate(LongIdKey sectionKey) throws ServiceException {
        try {
            EvaluateInfo evaluateInfo = evaluateLocalCacheHandler.getEvaluateInfo(sectionKey);
            evaluateHandler.evaluate(evaluateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("判断部件时发生异常",
                    LogLevel.WARN, sem, e
            );
        }
    }
}
