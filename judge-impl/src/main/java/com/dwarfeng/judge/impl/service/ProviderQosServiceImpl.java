package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.ProviderInfo;
import com.dwarfeng.judge.stack.handler.Provider;
import com.dwarfeng.judge.stack.handler.ProviderLocalCacheHandler;
import com.dwarfeng.judge.stack.service.ProviderInfoMaintainService;
import com.dwarfeng.judge.stack.service.ProviderQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class ProviderQosServiceImpl implements ProviderQosService {

    private final ProviderInfoMaintainService providerInfoMaintainService;

    private final ProviderLocalCacheHandler providerLocalCacheHandler;

    private final ServiceExceptionMapper sem;

    public ProviderQosServiceImpl(
            ProviderInfoMaintainService providerInfoMaintainService,
            ProviderLocalCacheHandler providerLocalCacheHandler,
            ServiceExceptionMapper sem
    ) {
        this.providerInfoMaintainService = providerInfoMaintainService;
        this.providerLocalCacheHandler = providerLocalCacheHandler;
        this.sem = sem;
    }

    @Override
    public boolean exists(LongIdKey providerInfoKey) throws ServiceException {
        try {
            return providerLocalCacheHandler.exists(providerInfoKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断指定的提供器是否存在时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public ProviderDescription get(LongIdKey providerInfoKey) throws ServiceException {
        try {
            if (!providerLocalCacheHandler.exists(providerInfoKey)) {
                return null;
            }
            ProviderInfo providerInfo = providerInfoMaintainService.get(providerInfoKey);
            Provider provider = providerLocalCacheHandler.get(providerInfoKey);
            return new ProviderDescription(providerInfo, provider);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("获取指定提供器的提供器描述时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void clearLocalCache() throws ServiceException {
        try {
            providerLocalCacheHandler.clear();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("清除提供器本地缓存时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
