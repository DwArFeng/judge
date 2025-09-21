package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.entity.AnalyserInfo;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.bean.entity.VisualizerInfo;
import com.dwarfeng.judge.stack.handler.*;
import com.dwarfeng.judge.stack.service.AnalyserInfoMaintainService;
import com.dwarfeng.judge.stack.service.JudgerInfoMaintainService;
import com.dwarfeng.judge.stack.service.SectionMaintainService;
import com.dwarfeng.judge.stack.service.VisualizerInfoMaintainService;
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
        private final VisualizerInfoMaintainService visualizerInfoMaintainService;

        private final AnalyserHandler analyserHandler;
        private final JudgerHandler judgerHandler;
        private final VisualizerHandler visualizerHandler;

        public JobLocalCacheFetcher(
                SectionMaintainService sectionMaintainService,
                AnalyserInfoMaintainService analyserInfoMaintainService,
                JudgerInfoMaintainService judgerInfoMaintainService,
                VisualizerInfoMaintainService visualizerInfoMaintainService,
                AnalyserHandler analyserHandler,
                JudgerHandler judgerHandler,
                VisualizerHandler visualizerHandler
        ) {
            this.sectionMaintainService = sectionMaintainService;
            this.analyserInfoMaintainService = analyserInfoMaintainService;
            this.judgerInfoMaintainService = judgerInfoMaintainService;
            this.visualizerInfoMaintainService = visualizerInfoMaintainService;
            this.analyserHandler = analyserHandler;
            this.judgerHandler = judgerHandler;
            this.visualizerHandler = visualizerHandler;
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public boolean exists(LongIdKey key) throws Exception {
            Section section = sectionMaintainService.getIfExists(key);
            if (Objects.isNull(section)) {
                return false;
            }
            return section.isEnabled();
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

            List<LongIdKey> judgerInfoKeys = new ArrayList<>();
            Map<LongIdKey, JudgerInfo> judgerInfoMap = new HashMap<>();
            Map<LongIdKey, Judger> judgerMap = new HashMap<>();
            List<JudgerInfo> judgerInfos = judgerInfoMaintainService.lookupAsList(
                    JudgerInfoMaintainService.CHILD_FOR_SECTION_ENABLED_INDEX_ASC, new Object[]{key}
            );
            for (JudgerInfo judgerInfo : judgerInfos) {
                judgerInfoKeys.add(judgerInfo.getKey());
                judgerInfoMap.put(judgerInfo.getKey(), judgerInfo);
                Judger judger = judgerHandler.make(judgerInfo.getType(), judgerInfo.getParam());
                judgerMap.put(judgerInfo.getKey(), judger);
            }

            List<LongIdKey> visualizerInfoKeys = new ArrayList<>();
            Map<LongIdKey, VisualizerInfo> visualizerInfoMap = new HashMap<>();
            Map<LongIdKey, Visualizer> visualizerMap = new HashMap<>();
            List<VisualizerInfo> visualizerInfos = visualizerInfoMaintainService.lookupAsList(
                    VisualizerInfoMaintainService.CHILD_FOR_SECTION_ENABLED_INDEX_ASC, new Object[]{key}
            );
            for (VisualizerInfo visualizerInfo : visualizerInfos) {
                visualizerInfoKeys.add(visualizerInfo.getKey());
                visualizerInfoMap.put(visualizerInfo.getKey(), visualizerInfo);
                Visualizer visualizer = visualizerHandler.make(visualizerInfo.getType(), visualizerInfo.getParam());
                visualizerMap.put(visualizerInfo.getKey(), visualizer);
            }

            return new JobLocalCache(
                    section, analyserInfoKeys, analyserInfoMap, analyserMap, judgerInfoKeys, judgerInfoMap, judgerMap,
                    visualizerInfoKeys, visualizerInfoMap, visualizerMap
            );
        }
    }
}
