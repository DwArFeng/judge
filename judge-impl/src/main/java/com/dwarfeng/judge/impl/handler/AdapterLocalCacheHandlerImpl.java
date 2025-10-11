package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.entity.AdapterInfo;
import com.dwarfeng.judge.stack.handler.Adapter;
import com.dwarfeng.judge.stack.handler.AdapterHandler;
import com.dwarfeng.judge.stack.handler.AdapterLocalCacheHandler;
import com.dwarfeng.judge.stack.service.AdapterInfoMaintainService;
import com.dwarfeng.subgrade.impl.handler.Fetcher;
import com.dwarfeng.subgrade.impl.handler.GeneralLocalCacheHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Component
public class AdapterLocalCacheHandlerImpl implements AdapterLocalCacheHandler {

    private final GeneralLocalCacheHandler<LongIdKey, Adapter> handler;

    public AdapterLocalCacheHandlerImpl(AdapterFetcher adapterFetcher) {
        handler = new GeneralLocalCacheHandler<>(adapterFetcher);
    }

    @BehaviorAnalyse
    @Override
    public boolean exists(LongIdKey key) throws HandlerException {
        return handler.exists(key);
    }

    @BehaviorAnalyse
    @Override
    public Adapter get(LongIdKey key) throws HandlerException {
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
    public static class AdapterFetcher implements Fetcher<LongIdKey, Adapter> {

        private final AdapterInfoMaintainService adapterInfoMaintainService;

        private final AdapterHandler adapterHandler;

        public AdapterFetcher(
                AdapterInfoMaintainService adapterInfoMaintainService,
                AdapterHandler adapterHandler
        ) {
            this.adapterInfoMaintainService = adapterInfoMaintainService;
            this.adapterHandler = adapterHandler;
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public boolean exists(LongIdKey key) throws Exception {
            AdapterInfo adapterInfo = adapterInfoMaintainService.getIfExists(key);
            if (Objects.isNull(adapterInfo)) {
                return false;
            }
            return adapterInfo.isEnabled();
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public Adapter fetch(LongIdKey key) throws Exception {
            AdapterInfo adapterInfo = adapterInfoMaintainService.get(key);
            return adapterHandler.make(adapterInfo.getType(), adapterInfo.getParam());
        }
    }
}
