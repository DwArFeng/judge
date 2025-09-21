package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.SinkerMetaInspectInfo;
import com.dwarfeng.judge.stack.bean.dto.SinkerMetaInspectResult;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.bean.entity.SinkerRelation;
import com.dwarfeng.judge.stack.handler.SectionBindingLocalCacheHandler;
import com.dwarfeng.judge.stack.handler.SinkerMetaOperateHandler;
import com.dwarfeng.judge.stack.service.SectionMaintainService;
import com.dwarfeng.judge.stack.service.SinkerRelationMaintainService;
import com.dwarfeng.judge.stack.struct.SectionBinding;
import com.dwarfeng.judge.stack.struct.SinkerMetaInfo;
import com.dwarfeng.subgrade.impl.handler.Fetcher;
import com.dwarfeng.subgrade.impl.handler.GeneralLocalCacheHandler;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class SectionBindingLocalCacheHandlerImpl implements SectionBindingLocalCacheHandler {

    private final GeneralLocalCacheHandler<LongIdKey, SectionBinding> handler;

    public SectionBindingLocalCacheHandlerImpl(SectionBindingFetcher sectionBindingFetcher) {
        handler = new GeneralLocalCacheHandler<>(sectionBindingFetcher);
    }

    @BehaviorAnalyse
    @Override
    public boolean exists(LongIdKey key) throws HandlerException {
        return handler.exists(key);
    }

    @BehaviorAnalyse
    @Override
    public SectionBinding get(LongIdKey key) throws HandlerException {
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
    public static class SectionBindingFetcher implements Fetcher<LongIdKey, SectionBinding> {

        private final SectionMaintainService sectionMaintainService;
        private final SinkerRelationMaintainService sinkerRelationMaintainService;

        private final SinkerMetaOperateHandler sinkerMetaOperateHandler;

        public SectionBindingFetcher(
                SectionMaintainService sectionMaintainService,
                SinkerRelationMaintainService sinkerRelationMaintainService,
                SinkerMetaOperateHandler sinkerMetaOperateHandler
        ) {
            this.sectionMaintainService = sectionMaintainService;
            this.sinkerRelationMaintainService = sinkerRelationMaintainService;
            this.sinkerMetaOperateHandler = sinkerMetaOperateHandler;
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

        @SuppressWarnings("DuplicatedCode")
        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public SectionBinding fetch(LongIdKey key) throws Exception {
            // 定义部件绑定对象需要的所有信息。
            Map<LongIdKey, Map<String, SinkerMetaInfo>> map = new LinkedHashMap<>();

            // 取所有使能的关联信息。
            List<SinkerRelation> sinkerRelations = sinkerRelationMaintainService.lookupAsList(
                    SinkerRelationMaintainService.CHILD_FOR_SECTION_BINDING, new Object[]{key}
            );

            // 遍历所有的关联信息。
            for (SinkerRelation sinkerRelation : sinkerRelations) {
                // 解析下沉器信息主键。
                LongIdKey sinkerInfoKey = new LongIdKey(sinkerRelation.getKey().getSinkerLongId());
                // 查询元数据。
                SinkerMetaInspectResult inspectResult = sinkerMetaOperateHandler.inspect(
                        new SinkerMetaInspectInfo(key, sinkerInfoKey)
                );
                Map<String, SinkerMetaInfo> sinkerMateInfoMap = inspectResult.getMetaInfoMap().entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, entry -> new SinkerMetaInfo(
                                entry.getValue().getMetaValue(),
                                entry.getValue().getDefaultValue(),
                                entry.getValue().getEquivalentValue()
                        )));
                // 构造部件绑定项。
                map.put(sinkerInfoKey, sinkerMateInfoMap);
            }

            // 构建结果并返回。
            return new SectionBinding(map);
        }
    }
}
