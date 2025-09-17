package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.dto.SinkerMetaCompleteInfo;
import com.dwarfeng.judge.stack.bean.dto.SinkerMetaInspectInfo;
import com.dwarfeng.judge.stack.bean.dto.SinkerMetaInspectResult;
import com.dwarfeng.judge.stack.bean.dto.SinkerMetaResetInfo;
import com.dwarfeng.judge.stack.bean.entity.SinkerInfo;
import com.dwarfeng.judge.stack.bean.entity.SinkerMeta;
import com.dwarfeng.judge.stack.bean.entity.SinkerMetaIndicator;
import com.dwarfeng.judge.stack.bean.key.SinkerMetaKey;
import com.dwarfeng.judge.stack.handler.SinkerMetaOperateHandler;
import com.dwarfeng.judge.stack.service.SinkerInfoMaintainService;
import com.dwarfeng.judge.stack.service.SinkerMetaIndicatorMaintainService;
import com.dwarfeng.judge.stack.service.SinkerMetaMaintainService;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class SinkerMetaOperateHandlerImpl implements SinkerMetaOperateHandler {

    private final SinkerInfoMaintainService sinkerInfoMaintainService;
    private final SinkerMetaMaintainService sinkerMetaMaintainService;
    private final SinkerMetaIndicatorMaintainService sinkerMetaIndicatorMaintainService;

    private final HandlerValidator handlerValidator;

    public SinkerMetaOperateHandlerImpl(
            SinkerInfoMaintainService sinkerInfoMaintainService,
            SinkerMetaMaintainService sinkerMetaMaintainService,
            SinkerMetaIndicatorMaintainService sinkerMetaIndicatorMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.sinkerInfoMaintainService = sinkerInfoMaintainService;
        this.sinkerMetaMaintainService = sinkerMetaMaintainService;
        this.sinkerMetaIndicatorMaintainService = sinkerMetaIndicatorMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    @BehaviorAnalyse
    public SinkerMetaInspectResult inspect(SinkerMetaInspectInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sectionKey = info.getSectionKey();
            LongIdKey sinkerInfoKey = info.getSinkerInfoKey();

            // 确认部件存在。
            handlerValidator.makeSureSectionExists(sectionKey);
            // 确认下沉器信息存在。
            handlerValidator.makeSureSinkerInfoExists(sinkerInfoKey);

            // 定义元数据映射和指示器映射。
            final Map<String, SinkerMeta> metaMap = new HashMap<>();
            final Map<String, SinkerMetaIndicator> indicatorMap = new HashMap<>();
            // 定义 metaId 集合。
            final Set<String> metaIdSet = new HashSet<>();

            // 查询部件与下沉器信息所对应的元数据，并依据 metaId 推送至元数据映射中，metaId 同时推送至 metaId 集合中。
            List<SinkerMeta> sinkerMetas = sinkerMetaMaintainService.lookupAsList(
                    SinkerMetaMaintainService.CHILD_FOR_SECTION_SINKER_INFO, new Object[]{sectionKey, sinkerInfoKey}
            );
            for (SinkerMeta sinkerMeta : sinkerMetas) {
                String metaId = sinkerMeta.getKey().getMetaStringId();
                metaMap.put(metaId, sinkerMeta);
                metaIdSet.add(metaId);
            }
            // 查询下沉器信息对应的元数据指示器，并依据 metaId 推送至指示器映射中，metaId 同时推送至 metaId 集合中。
            SinkerInfo sinkerInfo = sinkerInfoMaintainService.get(sinkerInfoKey);
            List<SinkerMetaIndicator> sinkerMetaIndicators = sinkerMetaIndicatorMaintainService.lookupAsList(
                    SinkerMetaIndicatorMaintainService.SINKER_TYPE_EQ, new Object[]{sinkerInfo.getType()}
            );
            for (SinkerMetaIndicator sinkerMetaIndicator : sinkerMetaIndicators) {
                String metaId = sinkerMetaIndicator.getKey().getMetaStringId();
                indicatorMap.put(metaId, sinkerMetaIndicator);
                metaIdSet.add(metaId);
            }

            // 定义元数据信息映射。
            final Map<String, SinkerMetaInspectResult.MetaInfo> metaInfoMap = new HashMap<>();

            // 遍历 metaId 集合，将元数据映射和指示器映射中的数据按照业务逻辑转换为 SinkerMetaInspectResult.MetaInfo，
            // 并推送至 metaInfoMap 中。
            for (String metaId : metaIdSet) {
                String metaValue = Optional.ofNullable(metaMap.get(metaId)).map(SinkerMeta::getValue).orElse(null);
                String defaultValue = Optional.ofNullable(indicatorMap.get(metaId))
                        .map(SinkerMetaIndicator::getDefaultValue).orElse(null);
                String equivalentValue = Objects.nonNull(metaValue) ? metaValue : defaultValue;
                metaInfoMap.put(metaId, new SinkerMetaInspectResult.MetaInfo(metaValue, defaultValue, equivalentValue));
            }

            // 构造结果并返回。
            return new SinkerMetaInspectResult(metaInfoMap);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    @BehaviorAnalyse
    public void complete(SinkerMetaCompleteInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sectionKey = info.getSectionKey();
            LongIdKey sinkerInfoKey = info.getSinkerInfoKey();

            // 确认部件存在。
            handlerValidator.makeSureSectionExists(sectionKey);
            // 确认下沉器信息存在。
            handlerValidator.makeSureSinkerInfoExists(sinkerInfoKey);

            // 定义元数据映射和指示器映射。
            final Map<String, SinkerMeta> metaMap = new HashMap<>();
            final Map<String, SinkerMetaIndicator> indicatorMap = new HashMap<>();

            // 查询部件与下沉器信息所对应的元数据，并依据 metaId 推送至元数据映射中。
            List<SinkerMeta> sinkerMetas = sinkerMetaMaintainService.lookupAsList(
                    SinkerMetaMaintainService.CHILD_FOR_SECTION_SINKER_INFO, new Object[]{sectionKey, sinkerInfoKey}
            );
            for (SinkerMeta sinkerMeta : sinkerMetas) {
                String metaId = sinkerMeta.getKey().getMetaStringId();
                metaMap.put(metaId, sinkerMeta);
            }
            // 查询下沉器信息对应的元数据指示器，并依据 metaId 推送至指示器映射中。
            SinkerInfo sinkerInfo = sinkerInfoMaintainService.get(sinkerInfoKey);
            List<SinkerMetaIndicator> sinkerMetaIndicators = sinkerMetaIndicatorMaintainService.lookupAsList(
                    SinkerMetaIndicatorMaintainService.SINKER_TYPE_EQ, new Object[]{sinkerInfo.getType()}
            );
            for (SinkerMetaIndicator sinkerMetaIndicator : sinkerMetaIndicators) {
                String metaId = sinkerMetaIndicator.getKey().getMetaStringId();
                indicatorMap.put(metaId, sinkerMetaIndicator);
            }

            // 定义待插入元数据列表。
            final List<SinkerMeta> sinkerMetasToInsert = new ArrayList<>();

            // 遍历指示器映射，如果元数据映射中不存在指示器对应的元数据，则构造元数据，添加至待插入列表。
            for (Map.Entry<String, SinkerMetaIndicator> entry : indicatorMap.entrySet()) {
                if (metaMap.containsKey(entry.getKey())) {
                    continue;
                }
                SinkerMetaIndicator sinkerMetaIndicator = entry.getValue();
                SinkerMeta sinkerMeta = indicatorToMeta(
                        sectionKey, sinkerInfoKey, sinkerMetaIndicator, "由 judge 补全时自动生成的下沉器元数据"
                );
                sinkerMetasToInsert.add(sinkerMeta);
            }

            // 批量插入。
            sinkerMetaMaintainService.batchInsert(sinkerMetasToInsert);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    @Override
    @BehaviorAnalyse
    public void reset(SinkerMetaResetInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sectionKey = info.getSectionKey();
            LongIdKey sinkerInfoKey = info.getSinkerInfoKey();

            // 确认部件存在。
            handlerValidator.makeSureSectionExists(sectionKey);
            // 确认下沉器信息存在。
            handlerValidator.makeSureSinkerInfoExists(sinkerInfoKey);

            // 查询部件与下沉器信息所对应的元数据，并全部删除。
            List<SinkerMetaKey> sinkerMetaKeysToDelete = sinkerMetaMaintainService.lookupAsList(
                    SinkerMetaMaintainService.CHILD_FOR_SECTION_SINKER_INFO, new Object[]{sectionKey, sinkerInfoKey}
            ).stream().map(SinkerMeta::getKey).collect(Collectors.toList());
            sinkerMetaMaintainService.batchDelete(sinkerMetaKeysToDelete);

            // 定义待插入元数据列表。
            final List<SinkerMeta> sinkerMetasToInsert = new ArrayList<>();

            // 查询下沉器信息对应的元数据指示器，并构造对应的元数据，添加至待插入列表。
            SinkerInfo sinkerInfo = sinkerInfoMaintainService.get(sinkerInfoKey);
            List<SinkerMetaIndicator> sinkerMetaIndicators = sinkerMetaIndicatorMaintainService.lookupAsList(
                    SinkerMetaIndicatorMaintainService.SINKER_TYPE_EQ, new Object[]{sinkerInfo.getType()}
            );
            for (SinkerMetaIndicator sinkerMetaIndicator : sinkerMetaIndicators) {
                SinkerMeta sinkerMeta = indicatorToMeta(
                        sectionKey, sinkerInfoKey, sinkerMetaIndicator, "由 judge 重置时自动生成的下沉器元数据"
                );
                sinkerMetasToInsert.add(sinkerMeta);
            }

            // 批量插入。
            sinkerMetaMaintainService.batchInsert(sinkerMetasToInsert);
        } catch (HandlerException e) {
            throw e;
        } catch (Exception e) {
            throw new HandlerException(e);
        }
    }

    private SinkerMeta indicatorToMeta(
            LongIdKey sectionKey, LongIdKey sinkerInfoKey, SinkerMetaIndicator sinkerMetaIndicator, String remark
    ) {
        return new SinkerMeta(
                new SinkerMetaKey(
                        sectionKey.getLongId(), sinkerInfoKey.getLongId(),
                        sinkerMetaIndicator.getKey().getMetaStringId()
                ),
                sinkerMetaIndicator.getDefaultValue(),
                remark
        );
    }
}
