package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.entity.ProviderInfo;
import com.dwarfeng.judge.stack.handler.Provider;
import com.dwarfeng.judge.stack.handler.ProviderHandler;
import com.dwarfeng.judge.stack.handler.ProviderLocalCacheHandler;
import com.dwarfeng.judge.stack.service.ProviderInfoMaintainService;
import com.dwarfeng.subgrade.impl.handler.Fetcher;
import com.dwarfeng.subgrade.impl.handler.GeneralLocalCacheHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProviderLocalCacheHandlerImpl implements ProviderLocalCacheHandler {

    private final GeneralLocalCacheHandler<LongIdKey, Provider> handler;

    public ProviderLocalCacheHandlerImpl(ProviderFetcher providerFetcher) {
        handler = new GeneralLocalCacheHandler<>(providerFetcher);
    }

    @BehaviorAnalyse
    @Override
    public boolean exists(LongIdKey key) throws HandlerException {
        return handler.exists(key);
    }

    @BehaviorAnalyse
    @Override
    public Provider get(LongIdKey key) throws HandlerException {
        return handler.get(key);
    }

    @BehaviorAnalyse
    @Override
    public boolean remove(LongIdKey key) {
        return handler.remove(key);
    }

    @BehaviorAnalyse
    @Override
    public void clear() {
        handler.clear();
    }

    @Component
    public static class ProviderFetcher implements Fetcher<LongIdKey, Provider> {

        private final ProviderInfoMaintainService providerInfoMaintainService;

        private final ProviderHandler providerHandler;

        public ProviderFetcher(
                ProviderInfoMaintainService providerInfoMaintainService,
                ProviderHandler providerHandler
        ) {
            this.providerInfoMaintainService = providerInfoMaintainService;
            this.providerHandler = providerHandler;
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public boolean exists(LongIdKey key) throws Exception {
            return providerInfoMaintainService.exists(key);
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public Provider fetch(LongIdKey key) throws Exception {
            ProviderInfo providerInfo = providerInfoMaintainService.get(key);
            return providerHandler.make(providerInfo.getType(), providerInfo.getParam());
        }
    }
}
