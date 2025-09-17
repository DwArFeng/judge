package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.SinkerInfo;
import com.dwarfeng.judge.stack.handler.Sinker;
import com.dwarfeng.judge.stack.handler.SinkerLocalCacheHandler;
import com.dwarfeng.judge.stack.handler.SinkerSession;
import com.dwarfeng.judge.stack.handler.SinkerSessionHoldHandler;
import com.dwarfeng.judge.stack.service.SinkerInfoMaintainService;
import com.dwarfeng.judge.stack.service.SinkerSessionQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class SinkerSessionQosServiceImpl implements SinkerSessionQosService {

    private final SinkerInfoMaintainService sinkerInfoMaintainService;

    private final SinkerLocalCacheHandler sinkerLocalCacheHandler;
    private final SinkerSessionHoldHandler sinkerSessionHoldHandler;

    private final ServiceExceptionMapper sem;

    public SinkerSessionQosServiceImpl(
            SinkerInfoMaintainService sinkerInfoMaintainService,
            SinkerLocalCacheHandler sinkerLocalCacheHandler,
            SinkerSessionHoldHandler sinkerSessionHoldHandler,
            ServiceExceptionMapper sem
    ) {
        this.sinkerInfoMaintainService = sinkerInfoMaintainService;
        this.sinkerLocalCacheHandler = sinkerLocalCacheHandler;
        this.sinkerSessionHoldHandler = sinkerSessionHoldHandler;
        this.sem = sem;
    }

    @Override
    public boolean exists(LongIdKey sinkerInfoKey) throws ServiceException {
        try {
            return sinkerLocalCacheHandler.exists(sinkerInfoKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断指定的下沉器会话是否存在时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public SinkerSessionDescription get(LongIdKey sinkerInfoKey) throws ServiceException {
        try {
            if (!sinkerLocalCacheHandler.exists(sinkerInfoKey)) {
                return null;
            }
            SinkerInfo sinkerInfo = sinkerInfoMaintainService.get(sinkerInfoKey);
            Sinker sinker = sinkerLocalCacheHandler.get(sinkerInfoKey);
            SinkerSession sinkerSession = sinkerSessionHoldHandler.get(sinkerInfoKey);
            return new SinkerSessionDescription(sinkerInfo, sinker, sinkerSession);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("获取指定下沉器的下沉器会话描述时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void closeAndClearHolding() throws ServiceException {
        try {
            sinkerSessionHoldHandler.closeAndClear();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("关闭并清除所有的下沉器会话时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
