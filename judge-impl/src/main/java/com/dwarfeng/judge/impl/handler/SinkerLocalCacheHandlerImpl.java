package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.entity.SinkerInfo;
import com.dwarfeng.judge.stack.handler.Sinker;
import com.dwarfeng.judge.stack.handler.SinkerHandler;
import com.dwarfeng.judge.stack.handler.SinkerLocalCacheHandler;
import com.dwarfeng.judge.stack.service.SinkerInfoMaintainService;
import com.dwarfeng.subgrade.impl.handler.Fetcher;
import com.dwarfeng.subgrade.impl.handler.GeneralLocalCacheHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Component
public class SinkerLocalCacheHandlerImpl implements SinkerLocalCacheHandler {

    private final GeneralLocalCacheHandler<LongIdKey, Sinker> handler;

    public SinkerLocalCacheHandlerImpl(SinkerFetcher sinkerFetcher) {
        handler = new GeneralLocalCacheHandler<>(sinkerFetcher);
    }

    @BehaviorAnalyse
    @Override
    public boolean exists(LongIdKey key) throws HandlerException {
        return handler.exists(key);
    }

    @BehaviorAnalyse
    @Override
    public Sinker get(LongIdKey key) throws HandlerException {
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
    public static class SinkerFetcher implements Fetcher<LongIdKey, Sinker> {

        private final SinkerInfoMaintainService sinkerInfoMaintainService;

        private final SinkerHandler sinkerHandler;

        public SinkerFetcher(
                SinkerInfoMaintainService sinkerInfoMaintainService,
                SinkerHandler sinkerHandler
        ) {
            this.sinkerInfoMaintainService = sinkerInfoMaintainService;
            this.sinkerHandler = sinkerHandler;
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public boolean exists(LongIdKey key) throws Exception {
            SinkerInfo sinkerInfo = sinkerInfoMaintainService.getIfExists(key);
            if (Objects.isNull(sinkerInfo)) {
                return false;
            }
            return sinkerInfo.isEnabled();
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public Sinker fetch(LongIdKey key) throws Exception {
            SinkerInfo sinkerInfo = sinkerInfoMaintainService.get(key);
            return sinkerHandler.make(sinkerInfo.getType(), sinkerInfo.getParam());
        }
    }
}
