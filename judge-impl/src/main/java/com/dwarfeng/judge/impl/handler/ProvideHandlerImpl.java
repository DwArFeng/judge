package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.LookupInfo;
import com.dwarfeng.judge.stack.bean.dto.LookupResult;
import com.dwarfeng.judge.stack.exception.AdapterNotExistsException;
import com.dwarfeng.judge.stack.handler.*;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProvideHandlerImpl implements ProvideHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProvideHandlerImpl.class);

    private final ProviderSessionHoldHandler providerSessionHoldHandler;
    private final AdapterLocalCacheHandler adapterLocalCacheHandler;

    public ProvideHandlerImpl(
            ProviderSessionHoldHandler providerSessionHoldHandler,
            AdapterLocalCacheHandler adapterLocalCacheHandler
    ) {
        this.providerSessionHoldHandler = providerSessionHoldHandler;
        this.adapterLocalCacheHandler = adapterLocalCacheHandler;
    }

    @Override
    @BehaviorAnalyse
    public LookupResult lookup(LookupInfo info) throws HandlerException {
        try {
            return provide0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private LookupResult provide0(LookupInfo info) throws Exception {
        // 展开参数。
        LongIdKey providerInfoKey = info.getProviderInfoKey();
        LongIdKey adapterInfoKey = info.getAdapterInfoKey();
        String preset = info.getPreset();
        Object[] objs = info.getObjs();

        // 获取适配器
        if (Objects.nonNull(adapterInfoKey)) {
            Adapter adapter = adapterLocalCacheHandler.get(adapterInfoKey);
            if (Objects.isNull(adapter)) {
                LOGGER.debug("适配器不存在, 将抛出异常... ");
                throw new AdapterNotExistsException(adapterInfoKey);
            }
            objs = adapter.adapt(objs);
        }

        // 获取会话。
        ProviderSession providerSession = providerSessionHoldHandler.get(providerInfoKey);

        // 调用会话的方法，构造结果并返回。
        return providerSession.lookup(new LookupInfo(providerInfoKey, adapterInfoKey, preset, objs));
    }
}
