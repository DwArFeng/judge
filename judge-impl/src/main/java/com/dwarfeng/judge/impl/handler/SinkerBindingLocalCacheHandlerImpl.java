package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.SinkerMetaInspectInfo;
import com.dwarfeng.judge.stack.bean.dto.SinkerMetaInspectResult;
import com.dwarfeng.judge.stack.bean.entity.SinkerInfo;
import com.dwarfeng.judge.stack.bean.entity.SinkerRelation;
import com.dwarfeng.judge.stack.handler.SinkerBindingLocalCacheHandler;
import com.dwarfeng.judge.stack.handler.SinkerMetaOperateHandler;
import com.dwarfeng.judge.stack.service.SinkerInfoMaintainService;
import com.dwarfeng.judge.stack.service.SinkerRelationMaintainService;
import com.dwarfeng.judge.stack.struct.SinkerBinding;
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
public class SinkerBindingLocalCacheHandlerImpl implements SinkerBindingLocalCacheHandler {

    private final GeneralLocalCacheHandler<LongIdKey, SinkerBinding> handler;

    public SinkerBindingLocalCacheHandlerImpl(SinkerBindingSinker sinkerBindingSinker) {
        handler = new GeneralLocalCacheHandler<>(sinkerBindingSinker);
    }

    @BehaviorAnalyse
    @Override
    public boolean exists(LongIdKey key) throws HandlerException {
        return handler.exists(key);
    }

    @BehaviorAnalyse
    @Override
    public SinkerBinding get(LongIdKey key) throws HandlerException {
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
    public static class SinkerBindingSinker implements Fetcher<LongIdKey, SinkerBinding> {

        private final SinkerInfoMaintainService sinkerInfoMaintainService;
        private final SinkerRelationMaintainService sinkerRelationMaintainService;

        private final SinkerMetaOperateHandler sinkerMetaOperateHandler;

        public SinkerBindingSinker(
                SinkerInfoMaintainService sinkerInfoMaintainService,
                SinkerRelationMaintainService sinkerRelationMaintainService,
                SinkerMetaOperateHandler sinkerMetaOperateHandler
        ) {
            this.sinkerInfoMaintainService = sinkerInfoMaintainService;
            this.sinkerRelationMaintainService = sinkerRelationMaintainService;
            this.sinkerMetaOperateHandler = sinkerMetaOperateHandler;
        }

        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public boolean exists(LongIdKey key) throws Exception {
            SinkerInfo sinkerInfo = sinkerInfoMaintainService.getIfExists(key);
            if (Objects.isNull(sinkerInfo)) {
                return false;
            }
            return sinkerInfo.isEnabled();
        }

        @SuppressWarnings("DuplicatedCode")
        @Override
        @BehaviorAnalyse
        @Transactional(
                transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class
        )
        public SinkerBinding fetch(LongIdKey key) throws Exception {
            // 定义下沉器绑定对象需要的所有信息。
            Map<LongIdKey, Map<String, SinkerMetaInfo>> map = new LinkedHashMap<>();

            // 取所有使能的关联信息。
            List<SinkerRelation> sinkerRelations = sinkerRelationMaintainService.lookupAsList(
                    SinkerRelationMaintainService.CHILD_FOR_SINKER_INFO_BINDING,
                    new Object[]{key}
            );

            // 遍历所有的关联信息。
            for (SinkerRelation sinkerRelation : sinkerRelations) {
                // 解析部件主键。
                LongIdKey sectionKey = new LongIdKey(sinkerRelation.getKey().getSectionLongId());
                // 查询元数据。
                SinkerMetaInspectResult inspectResult = sinkerMetaOperateHandler.inspect(
                        new SinkerMetaInspectInfo(sectionKey, key)
                );
                Map<String, SinkerMetaInfo> sinkerMateInfoMap = inspectResult.getMetaInfoMap().entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, entry -> new SinkerMetaInfo(
                                entry.getValue().getMetaValue(),
                                entry.getValue().getDefaultValue(),
                                entry.getValue().getEquivalentValue()
                        )));
                // 构造下沉器绑定项。
                map.put(sectionKey, sinkerMateInfoMap);
            }

            // 构建结果并返回。
            return new SinkerBinding(map);
        }
    }
}
