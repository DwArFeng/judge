package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.handler.AssignLocalCacheHandler;
import com.dwarfeng.judge.stack.service.EnabledDriverInfoLookupService;
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
import java.util.stream.Collectors;

@Component
public class AssignLocalCacheHandlerImpl implements AssignLocalCacheHandler {

    @Autowired
    private DriveContextFetcher driveContextFetcher;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<LongIdKey, AssignContext> contextMap = new HashMap<>();
    private final Set<LongIdKey> notExistSections = new HashSet<>();
    private final List<LongIdKey> allSectionKeys = new ArrayList<>();

    @Override
    public List<LongIdKey> getSectionKeys() throws HandlerException {
        try {
            lock.readLock().lock();
            try {
                if (!allSectionKeys.isEmpty()) {
                    return new ArrayList<>(allSectionKeys);
                }
            } finally {
                lock.readLock().unlock();
            }
            lock.writeLock().lock();
            try {
                if (!allSectionKeys.isEmpty()) {
                    return new ArrayList<>(allSectionKeys);
                }
                List<LongIdKey> fetchedKeys = driveContextFetcher.fetchAllSectionKeys();
                allSectionKeys.clear();
                allSectionKeys.addAll(fetchedKeys);
                return new ArrayList<>(allSectionKeys);
            } finally {
                lock.writeLock().unlock();
            }
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    public AssignContext getAssignContext(LongIdKey sectionKey) throws HandlerException {
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
                AssignContext assignContext = driveContextFetcher.fetchContext(sectionKey);
                if (Objects.nonNull(assignContext)) {
                    contextMap.put(sectionKey, assignContext);
                    return assignContext;
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
            allSectionKeys.clear();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Component
    public static class DriveContextFetcher {

        @Autowired
        private SectionMaintainService sectionMaintainService;
        @Autowired
        private EnabledDriverInfoLookupService enabledDriverInfoLookupService;

        @BehaviorAnalyse
        @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
        public List<LongIdKey> fetchAllSectionKeys() throws Exception {
            return sectionMaintainService.lookup().getData()
                    .stream().map(Section::getKey).collect(Collectors.toList());
        }

        @BehaviorAnalyse
        @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true)
        public AssignContext fetchContext(LongIdKey sectionKey) throws Exception {
            if (!sectionMaintainService.exists(sectionKey)) {
                return null;
            }

            Section section = sectionMaintainService.get(sectionKey);
            List<DriverInfo> driverInfos = enabledDriverInfoLookupService.getEnabledDriverInfos(sectionKey);

            return new AssignContext(
                    section,
                    driverInfos
            );
        }
    }
}
