package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.SinkerInfo;
import com.dwarfeng.judge.stack.handler.Sinker;
import com.dwarfeng.judge.stack.handler.SinkerLocalCacheHandler;
import com.dwarfeng.judge.stack.service.SinkerInfoMaintainService;
import com.dwarfeng.judge.stack.service.SinkerQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class SinkerQosServiceImpl implements SinkerQosService {

    private final SinkerInfoMaintainService sinkerInfoMaintainService;

    private final SinkerLocalCacheHandler sinkerLocalCacheHandler;

    private final ServiceExceptionMapper sem;

    public SinkerQosServiceImpl(
            SinkerInfoMaintainService sinkerInfoMaintainService,
            SinkerLocalCacheHandler sinkerLocalCacheHandler,
            ServiceExceptionMapper sem
    ) {
        this.sinkerInfoMaintainService = sinkerInfoMaintainService;
        this.sinkerLocalCacheHandler = sinkerLocalCacheHandler;
        this.sem = sem;
    }

    @Override
    public boolean exists(LongIdKey sinkerInfoKey) throws ServiceException {
        try {
            return sinkerLocalCacheHandler.exists(sinkerInfoKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断指定的下沉器是否存在时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public SinkerDescription get(LongIdKey sinkerInfoKey) throws ServiceException {
        try {
            if (!sinkerLocalCacheHandler.exists(sinkerInfoKey)) {
                return null;
            }
            SinkerInfo sinkerInfo = sinkerInfoMaintainService.get(sinkerInfoKey);
            Sinker sinker = sinkerLocalCacheHandler.get(sinkerInfoKey);
            return new SinkerDescription(sinkerInfo, sinker);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("获取指定下沉器的下沉器描述时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void clearLocalCache() throws ServiceException {
        try {
            sinkerLocalCacheHandler.clear();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("清除下沉器本地缓存时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
