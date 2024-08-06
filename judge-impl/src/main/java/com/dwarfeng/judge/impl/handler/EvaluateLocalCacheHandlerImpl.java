package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.EvaluateInfo;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.handler.EvaluateLocalCacheHandler;
import com.dwarfeng.judge.stack.handler.Judger;
import com.dwarfeng.judge.stack.handler.JudgerHandler;
import com.dwarfeng.judge.stack.service.EnabledJudgerInfoLookupService;
import com.dwarfeng.judge.stack.service.SectionMaintainService;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@SuppressWarnings("DuplicatedCode")
@Component
public class EvaluateLocalCacheHandlerImpl implements EvaluateLocalCacheHandler {

    private final JudgeContextFetcher judgeContextFetcher;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<LongIdKey, EvaluateInfo> infoMap = new HashMap<>();
    private final Set<LongIdKey> notExistSections = new HashSet<>();

    public EvaluateLocalCacheHandlerImpl(JudgeContextFetcher judgeContextFetcher) {
        this.judgeContextFetcher = judgeContextFetcher;
    }

    @Override
    public EvaluateInfo getEvaluateInfo(LongIdKey sectionKey) throws HandlerException {
        try {
            lock.readLock().lock();
            try {
                if (infoMap.containsKey(sectionKey)) {
                    return infoMap.get(sectionKey);
                }
                if (notExistSections.contains(sectionKey)) {
                    return null;
                }
            } finally {
                lock.readLock().unlock();
            }
            lock.writeLock().lock();
            try {
                if (infoMap.containsKey(sectionKey)) {
                    return infoMap.get(sectionKey);
                }
                if (notExistSections.contains(sectionKey)) {
                    return null;
                }
                EvaluateInfo evaluateInfo = judgeContextFetcher.fetchInfo(sectionKey);
                if (Objects.nonNull(evaluateInfo)) {
                    infoMap.put(sectionKey, evaluateInfo);
                    return evaluateInfo;
                }
                notExistSections.add(sectionKey);
                return null;
            } finally {
                lock.writeLock().unlock();
            }
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public void clear() {
        lock.writeLock().lock();
        try {
            infoMap.clear();
            notExistSections.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Component
    public static class JudgeContextFetcher {

        private final SectionMaintainService sectionMaintainService;
        private final EnabledJudgerInfoLookupService enabledJudgerInfoLookupService;

        private final JudgerHandler judgerHandler;

        public JudgeContextFetcher(
                SectionMaintainService sectionMaintainService,
                EnabledJudgerInfoLookupService enabledJudgerInfoLookupService,
                JudgerHandler judgerHandler
        ) {
            this.sectionMaintainService = sectionMaintainService;
            this.enabledJudgerInfoLookupService = enabledJudgerInfoLookupService;
            this.judgerHandler = judgerHandler;
        }

        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public EvaluateInfo fetchInfo(LongIdKey sectionKey) throws Exception {
            if (!sectionMaintainService.exists(sectionKey)) {
                return null;
            }

            Section section = sectionMaintainService.get(sectionKey);
            List<JudgerInfo> judgerInfos = enabledJudgerInfoLookupService.getEnabledJudgerInfos(sectionKey);

            Map<JudgerInfo, Judger> judgerMap = new HashMap<>();

            for (JudgerInfo judgerInfo : judgerInfos) {
                judgerMap.put(judgerInfo, judgerHandler.make(judgerInfo));
            }

            return new EvaluateInfo(section, judgerMap);
        }
    }
}
