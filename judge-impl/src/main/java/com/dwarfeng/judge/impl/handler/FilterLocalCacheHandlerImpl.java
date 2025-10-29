package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.entity.FilterInfo;
import com.dwarfeng.judge.stack.handler.Filter;
import com.dwarfeng.judge.stack.handler.FilterHandler;
import com.dwarfeng.judge.stack.handler.FilterLocalCacheHandler;
import com.dwarfeng.judge.stack.service.FilterInfoMaintainService;
import com.dwarfeng.subgrade.impl.handler.Fetcher;
import com.dwarfeng.subgrade.impl.handler.GeneralLocalCacheHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Component
public class FilterLocalCacheHandlerImpl implements FilterLocalCacheHandler {

    private final GeneralLocalCacheHandler<LongIdKey, Filter> handler;

    public FilterLocalCacheHandlerImpl(FilterFetcher filterFetcher) {
        handler = new GeneralLocalCacheHandler<>(filterFetcher);
    }

    @BehaviorAnalyse
    @Override
    public boolean exists(LongIdKey key) throws HandlerException {
        return handler.exists(key);
    }

    @BehaviorAnalyse
    @Override
    public Filter get(LongIdKey key) throws HandlerException {
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
    public static class FilterFetcher implements Fetcher<LongIdKey, Filter> {

        private final FilterInfoMaintainService filterInfoMaintainService;

        private final FilterHandler filterHandler;

        public FilterFetcher(
                FilterInfoMaintainService filterInfoMaintainService,
                FilterHandler filterHandler
        ) {
            this.filterInfoMaintainService = filterInfoMaintainService;
            this.filterHandler = filterHandler;
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public boolean exists(LongIdKey key) throws Exception {
            FilterInfo filterInfo = filterInfoMaintainService.getIfExists(key);
            if (Objects.isNull(filterInfo)) {
                return false;
            }
            return filterInfo.isEnabled();
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public Filter fetch(LongIdKey key) throws Exception {
            FilterInfo filterInfo = filterInfoMaintainService.get(key);
            return filterHandler.make(filterInfo.getType(), filterInfo.getParam());
        }
    }
}
