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

    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Map<LongIdKey, DriveContext> contextMap = new HashMap<>();
    private Set<LongIdKey> notExistSections = new HashSet<>();
    private List<LongIdKey> allSectionKeys = new ArrayList<>();

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
    public DriveContext getDriveContext(LongIdKey sectionKey) throws HandlerException {
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
                DriveContext driveContext = driveContextFetcher.fetchContext(sectionKey);
                if (Objects.nonNull(driveContext)) {
                    contextMap.put(sectionKey, driveContext);
                    return driveContext;
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
        public DriveContext fetchContext(LongIdKey sectionKey) throws Exception {
            if (!sectionMaintainService.exists(sectionKey)) {
                return null;
            }

            Section section = sectionMaintainService.get(sectionKey);
            List<DriverInfo> driverInfos = enabledDriverInfoLookupService.getEnabledDriverInfos(sectionKey);

            return new DriveContext(
                    section,
                    driverInfos
            );
        }
    }
}
