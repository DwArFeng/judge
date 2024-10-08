package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.AssignInfo;
import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.handler.AssignLocalCacheHandler;
import com.dwarfeng.judge.stack.handler.Driver;
import com.dwarfeng.judge.stack.handler.DriverHandler;
import com.dwarfeng.judge.stack.service.EnabledDriverInfoLookupService;
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
public class AssignLocalCacheHandlerImpl implements AssignLocalCacheHandler {

    private final DriveInfoFetcher driveInfoFetcher;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Map<LongIdKey, AssignInfo> infoMap = new HashMap<>();
    private final Set<LongIdKey> notExistSections = new HashSet<>();

    public AssignLocalCacheHandlerImpl(DriveInfoFetcher driveInfoFetcher) {
        this.driveInfoFetcher = driveInfoFetcher;
    }

    @Override
    public AssignInfo getAssignInfo(LongIdKey sectionKey) throws HandlerException {
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
                AssignInfo assignInfo = driveInfoFetcher.fetchInfo(sectionKey);
                if (Objects.nonNull(assignInfo)) {
                    infoMap.put(sectionKey, assignInfo);
                    return assignInfo;
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
    public static class DriveInfoFetcher {

        private final SectionMaintainService sectionMaintainService;
        private final EnabledDriverInfoLookupService enabledDriverInfoLookupService;

        private final DriverHandler driverHandler;

        public DriveInfoFetcher(
                SectionMaintainService sectionMaintainService,
                EnabledDriverInfoLookupService enabledDriverInfoLookupService,
                DriverHandler driverHandler
        ) {
            this.sectionMaintainService = sectionMaintainService;
            this.enabledDriverInfoLookupService = enabledDriverInfoLookupService;
            this.driverHandler = driverHandler;
        }

        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public AssignInfo fetchInfo(LongIdKey sectionKey) throws Exception {
            if (!sectionMaintainService.exists(sectionKey)) {
                return null;
            }

            Section section = sectionMaintainService.get(sectionKey);
            List<DriverInfo> driverInfos = enabledDriverInfoLookupService.getEnabledDriverInfos(sectionKey);

            Map<DriverInfo, Driver> driverMap = new HashMap<>();

            for (DriverInfo driverInfo : driverInfos) {
                driverMap.put(driverInfo, driverHandler.find(driverInfo.getType()));
            }

            return new AssignInfo(
                    section,
                    driverMap
            );
        }
    }
}
