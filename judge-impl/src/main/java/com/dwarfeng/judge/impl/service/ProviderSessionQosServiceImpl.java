package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.ProviderInfo;
import com.dwarfeng.judge.stack.handler.Provider;
import com.dwarfeng.judge.stack.handler.ProviderLocalCacheHandler;
import com.dwarfeng.judge.stack.handler.ProviderSession;
import com.dwarfeng.judge.stack.handler.ProviderSessionHoldHandler;
import com.dwarfeng.judge.stack.service.ProviderInfoMaintainService;
import com.dwarfeng.judge.stack.service.ProviderSessionQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class ProviderSessionQosServiceImpl implements ProviderSessionQosService {

    private final ProviderInfoMaintainService providerInfoMaintainService;

    private final ProviderLocalCacheHandler providerLocalCacheHandler;
    private final ProviderSessionHoldHandler providerSessionHoldHandler;

    private final ServiceExceptionMapper sem;

    public ProviderSessionQosServiceImpl(
            ProviderInfoMaintainService providerInfoMaintainService,
            ProviderLocalCacheHandler providerLocalCacheHandler,
            ProviderSessionHoldHandler providerSessionHoldHandler,
            ServiceExceptionMapper sem
    ) {
        this.providerInfoMaintainService = providerInfoMaintainService;
        this.providerLocalCacheHandler = providerLocalCacheHandler;
        this.providerSessionHoldHandler = providerSessionHoldHandler;
        this.sem = sem;
    }

    @Override
    public boolean exists(LongIdKey providerInfoKey) throws ServiceException {
        try {
            return providerLocalCacheHandler.exists(providerInfoKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断指定的提供器会话是否存在时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public ProviderSessionDescription get(LongIdKey providerInfoKey) throws ServiceException {
        try {
            if (!providerLocalCacheHandler.exists(providerInfoKey)) {
                return null;
            }
            ProviderInfo providerInfo = providerInfoMaintainService.get(providerInfoKey);
            Provider provider = providerLocalCacheHandler.get(providerInfoKey);
            ProviderSession providerSession = providerSessionHoldHandler.get(providerInfoKey);
            return new ProviderSessionDescription(providerInfo, provider, providerSession);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("获取指定提供器的提供器会话描述时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void closeAndClearHolding() throws ServiceException {
        try {
            providerSessionHoldHandler.closeAndClear();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("关闭并清除所有的提供器会话时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
