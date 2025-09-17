package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.dto.SinkerMetaCompleteInfo;
import com.dwarfeng.judge.stack.bean.dto.SinkerMetaResetInfo;
import com.dwarfeng.judge.stack.handler.SinkerMetaOperateHandler;
import com.dwarfeng.judge.stack.service.SinkerMetaOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class SinkerMetaOperateServiceImpl implements SinkerMetaOperateService {

    private final SinkerMetaOperateHandler sinkerMetaOperateHandler;

    private final ServiceExceptionMapper sem;

    public SinkerMetaOperateServiceImpl(
            SinkerMetaOperateHandler sinkerMetaOperateHandler, ServiceExceptionMapper sem
    ) {
        this.sinkerMetaOperateHandler = sinkerMetaOperateHandler;
        this.sem = sem;
    }

    @Override
    public void complete(SinkerMetaCompleteInfo info) throws ServiceException {
        try {
            sinkerMetaOperateHandler.complete(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下沉器元数据补全时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void reset(SinkerMetaResetInfo info) throws ServiceException {
        try {
            sinkerMetaOperateHandler.reset(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("下沉器元数据重置时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
