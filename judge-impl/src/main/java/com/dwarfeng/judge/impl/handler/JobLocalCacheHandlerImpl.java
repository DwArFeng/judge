package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.entity.AlarmSetting;
import com.dwarfeng.judge.stack.bean.entity.AnalyserInfo;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.handler.*;
import com.dwarfeng.judge.stack.service.AlarmSettingMaintainService;
import com.dwarfeng.judge.stack.service.AnalyserInfoMaintainService;
import com.dwarfeng.judge.stack.service.JudgerInfoMaintainService;
import com.dwarfeng.judge.stack.service.SectionMaintainService;
import com.dwarfeng.judge.stack.struct.JobLocalCache;
import com.dwarfeng.subgrade.impl.handler.Fetcher;
import com.dwarfeng.subgrade.impl.handler.GeneralLocalCacheHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component
public class JobLocalCacheHandlerImpl implements JobLocalCacheHandler {

    private final GeneralLocalCacheHandler<LongIdKey, JobLocalCache> handler;

    public JobLocalCacheHandlerImpl(JobLocalCacheFetcher JobLocalCacheFetcher) {
        this.handler = new GeneralLocalCacheHandler<>(JobLocalCacheFetcher);
    }

    @BehaviorAnalyse
    @Override
    public boolean exists(LongIdKey key) throws HandlerException {
        return handler.exists(key);
    }

    @BehaviorAnalyse
    @Override
    public JobLocalCache get(LongIdKey key) throws HandlerException {
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
    public static class JobLocalCacheFetcher implements Fetcher<LongIdKey, JobLocalCache> {

        private final SectionMaintainService sectionMaintainService;
        private final AnalyserInfoMaintainService analyserInfoMaintainService;
        private final JudgerInfoMaintainService judgerInfoMaintainService;
        private final AlarmSettingMaintainService alarmSettingMaintainService;

        private final AnalyserHandler analyserHandler;
        private final JudgerHandler judgerHandler;

        public JobLocalCacheFetcher(
                SectionMaintainService sectionMaintainService,
                AnalyserInfoMaintainService analyserInfoMaintainService,
                JudgerInfoMaintainService judgerInfoMaintainService,
                AlarmSettingMaintainService alarmSettingMaintainService,
                AnalyserHandler analyserHandler,
                JudgerHandler judgerHandler
        ) {
            this.sectionMaintainService = sectionMaintainService;
            this.analyserInfoMaintainService = analyserInfoMaintainService;
            this.judgerInfoMaintainService = judgerInfoMaintainService;
            this.alarmSettingMaintainService = alarmSettingMaintainService;
            this.analyserHandler = analyserHandler;
            this.judgerHandler = judgerHandler;
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public boolean exists(LongIdKey key) throws Exception {
            return sectionMaintainService.exists(key);
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public JobLocalCache fetch(LongIdKey key) throws Exception {
            Section section = sectionMaintainService.get(key);

            List<LongIdKey> analyserInfoKeys = new ArrayList<>();
            Map<LongIdKey, AnalyserInfo> analyserInfoMap = new HashMap<>();
            Map<LongIdKey, Analyser> analyserMap = new HashMap<>();
            List<AnalyserInfo> analyserInfos = analyserInfoMaintainService.lookupAsList(
                    AnalyserInfoMaintainService.CHILD_FOR_SECTION_ENABLED_INDEX_ASC, new Object[]{key}
            );
            for (AnalyserInfo analyserInfo : analyserInfos) {
                analyserInfoKeys.add(analyserInfo.getKey());
                analyserInfoMap.put(analyserInfo.getKey(), analyserInfo);
                Analyser analyser = analyserHandler.make(analyserInfo.getType(), analyserInfo.getParam());
                analyserMap.put(analyserInfo.getKey(), analyser);
            }
            JudgerInfo judgerInfo = judgerInfoMaintainService.lookupFirst(
                    JudgerInfoMaintainService.CHILD_FOR_SECTION_ENABLED_INDEX_DESC, new Object[]{key}
            );
            LongIdKey judgerKey = null;
            Judger judger = null;
            if (Objects.nonNull(judgerInfo)) {
                judgerKey = judgerInfo.getKey();
                judger = judgerHandler.make(judgerInfo.getType(), judgerInfo.getParam());
            }
            List<AlarmSetting> alarmSettings = alarmSettingMaintainService.lookupAsList(
                    AlarmSettingMaintainService.CHILD_FOR_SECTION_ENABLED_THRESHOLD_DESC, new Object[]{key}
            );
            return new JobLocalCache(
                    section, analyserInfoKeys, analyserInfoMap, analyserMap, judgerKey, judgerInfo, judger,
                    alarmSettings
            );
        }
    }
}
