package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.cache.EnabledJudgerInfoCache;
import com.dwarfeng.judge.stack.dao.JudgerInfoDao;
import com.dwarfeng.judge.stack.service.EnabledJudgerInfoLookupService;
import com.dwarfeng.judge.stack.service.JudgerInfoMaintainService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnabledJudgerInfoLookupServiceImpl implements EnabledJudgerInfoLookupService {

    private final JudgerInfoDao dao;
    private final EnabledJudgerInfoCache cache;

    private final ServiceExceptionMapper sem;

    @Value("${cache.timeout.key_list.enabled_judger_info}")
    private long timeout;

    public EnabledJudgerInfoLookupServiceImpl(
            JudgerInfoDao dao,
            EnabledJudgerInfoCache cache,
            ServiceExceptionMapper sem
    ) {
        this.dao = dao;
        this.cache = cache;
        this.sem = sem;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<JudgerInfo> getEnabledJudgerInfos(LongIdKey sectionKey) throws ServiceException {
        try {
            if (cache.exists(sectionKey)) {
                return cache.get(sectionKey);
            }
            List<JudgerInfo> lookup = dao.lookup(JudgerInfoMaintainService.ENABLED_CHILD_FOR_SECTION, new Object[]{sectionKey});
            cache.set(sectionKey, lookup, timeout);
            return lookup;
        } catch (Exception e) {
            throw ServiceExceptionHelper.logAndThrow("查询有效的过滤器信息时发生异常",
                    LogLevel.WARN, sem, e
            );
        }
    }
}
