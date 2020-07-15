package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.handler.EvaluateLocalCacheHandler;
import com.dwarfeng.judge.stack.handler.Judger;
import com.dwarfeng.judge.stack.handler.JudgerHandler;
import com.dwarfeng.judge.stack.service.JudgerInfoMaintainService;
import com.dwarfeng.judge.stack.service.SectionMaintainService;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@SuppressWarnings("DuplicatedCode")
@Component
public class EvaluateLocalCacheHandlerImpl implements EvaluateLocalCacheHandler {

    @Autowired
    private JudgeContextFetcher judgeContextFetcher;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<LongIdKey, EvaluateContext> contextMap = new HashMap<>();
    private final Set<LongIdKey> notExistSections = new HashSet<>();

    @Override
    public boolean existsSection(LongIdKey sectionKey) throws HandlerException {
        try {
            lock.readLock().lock();
            try {
                if (contextMap.containsKey(sectionKey)) {
                    return true;
                }
                if (notExistSections.contains(sectionKey)) {
                    return false;
                }
            } finally {
                lock.readLock().unlock();
            }
            lock.writeLock().lock();
            try {
                if (contextMap.containsKey(sectionKey)) {
                    return true;
                }
                if (notExistSections.contains(sectionKey)) {
                    return false;
                }
                EvaluateContext evaluateContext = judgeContextFetcher.fetchContext(sectionKey);
                if (Objects.isNull(evaluateContext)) {
                    notExistSections.add(sectionKey);
                    return false;
                }
                contextMap.put(sectionKey, evaluateContext);
                return true;
            } finally {
                lock.writeLock().unlock();
            }
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public EvaluateContext getEvaluateContext(LongIdKey sectionKey) throws HandlerException {
        try {
            lock.readLock().lock();
            try {
                if (contextMap.containsKey(sectionKey)) {
                    return contextMap.get(sectionKey);
                }
                if (notExistSections.contains(sectionKey)) {
                    return null;
                }
            } finally {
                lock.readLock().unlock();
            }
            lock.writeLock().lock();
            try {
                if (contextMap.containsKey(sectionKey)) {
                    return contextMap.get(sectionKey);
                }
                if (notExistSections.contains(sectionKey)) {
                    return null;
                }
                EvaluateContext evaluateContext = judgeContextFetcher.fetchContext(sectionKey);
                if (Objects.nonNull(evaluateContext)) {
                    contextMap.put(sectionKey, evaluateContext);
                    return evaluateContext;
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
            contextMap.clear();
            notExistSections.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Component
    public static class JudgeContextFetcher {

        @Autowired
        private SectionMaintainService sectionMaintainService;
        @Autowired
        private JudgerInfoMaintainService judgerInfoMaintainService;

        @Autowired
        private JudgerHandler judgerHandler;

        @BehaviorAnalyse
        @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
        public EvaluateContext fetchContext(LongIdKey sectionKey) throws Exception {
            if (!sectionMaintainService.exists(sectionKey)) {
                return null;
            }

            Section section = sectionMaintainService.get(sectionKey);
            List<JudgerInfo> judgerInfos = judgerInfoMaintainService.lookup(
                    JudgerInfoMaintainService.CHILD_FOR_SECTION, new Object[]{sectionKey}).getData();

            List<Judger> judgers = new ArrayList<>();

            for (JudgerInfo judgerInfo : judgerInfos) {
                judgers.add(judgerHandler.make(judgerInfo));
            }

            return new EvaluateContext(
                    section,
                    judgers
            );
        }
    }
}
