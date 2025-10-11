package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.AdapterInfo;
import com.dwarfeng.judge.stack.handler.Adapter;
import com.dwarfeng.judge.stack.handler.AdapterLocalCacheHandler;
import com.dwarfeng.judge.stack.service.AdapterInfoMaintainService;
import com.dwarfeng.judge.stack.service.AdapterQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class AdapterQosServiceImpl implements AdapterQosService {

    private final AdapterInfoMaintainService adapterInfoMaintainService;

    private final AdapterLocalCacheHandler adapterLocalCacheHandler;

    private final ServiceExceptionMapper sem;

    public AdapterQosServiceImpl(
            AdapterInfoMaintainService adapterInfoMaintainService,
            AdapterLocalCacheHandler adapterLocalCacheHandler,
            ServiceExceptionMapper sem
    ) {
        this.adapterInfoMaintainService = adapterInfoMaintainService;
        this.adapterLocalCacheHandler = adapterLocalCacheHandler;
        this.sem = sem;
    }

    @Override
    public boolean exists(LongIdKey adapterInfoKey) throws ServiceException {
        try {
            return adapterLocalCacheHandler.exists(adapterInfoKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断指定的适配器是否存在时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public AdapterDescription get(LongIdKey adapterInfoKey) throws ServiceException {
        try {
            if (!adapterLocalCacheHandler.exists(adapterInfoKey)) {
                return null;
            }
            AdapterInfo adapterInfo = adapterInfoMaintainService.get(adapterInfoKey);
            Adapter adapter = adapterLocalCacheHandler.get(adapterInfoKey);
            return new AdapterDescription(adapterInfo, adapter);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("获取指定适配器的适配器描述时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void clearLocalCache() throws ServiceException {
        try {
            adapterLocalCacheHandler.clear();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("清除适配器本地缓存时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
