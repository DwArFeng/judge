package com.dwarfeng.judge.node.configuration;

import com.dwarfeng.judge.sdk.bean.BeanMapper;
import com.dwarfeng.judge.sdk.bean.entity.*;
import com.dwarfeng.judge.sdk.bean.key.formatter.*;
import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.judge.stack.bean.key.*;
import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.redis.formatter.LongIdStringKeyFormatter;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringIdStringKeyFormatter;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfiguration {

    private final RedisTemplate<String, ?> template;

    @Value("${cache.prefix.entity.analyser_info}")
    private String analyserInfoPrefix;
    @Value("${cache.prefix.entity.analyser_support}")
    private String analyserSupportPrefix;
    @Value("${cache.prefix.entity.analyser_variable}")
    private String analyserVariablePrefix;
    @Value("${cache.prefix.entity.analysis}")
    private String analysisPrefix;
    @Value("${cache.prefix.entity.driver_info}")
    private String driverInfoPrefix;
    @Value("${cache.prefix.entity.driver_support}")
    private String driverSupportPrefix;
    @Value("${cache.prefix.entity.judger_info}")
    private String judgerInfoPrefix;
    @Value("${cache.prefix.entity.judger_support}")
    private String judgerSupportPrefix;
    @Value("${cache.prefix.entity.judger_variable}")
    private String judgerVariablePrefix;
    @Value("${cache.prefix.entity.section}")
    private String sectionPrefix;
    @Value("${cache.prefix.entity.task}")
    private String taskPrefix;
    @Value("${cache.prefix.entity.task_event}")
    private String taskEventPrefix;
    @Value("${cache.prefix.entity.analysis_file_info}")
    private String analysisFileInfoPrefix;
    @Value("${cache.prefix.entity.analysis_file_pack}")
    private String analysisFilePackPrefix;
    @Value("${cache.prefix.entity.analysis_file_pack_item_info}")
    private String analysisFilePackItemInfoPrefix;
    @Value("${cache.prefix.entity.analysis_picture_info}")
    private String analysisPictureInfoPrefix;
    @Value("${cache.prefix.entity.analysis_picture_pack}")
    private String analysisPicturePackPrefix;
    @Value("${cache.prefix.entity.analysis_picture_pack_item_info}")
    private String analysisPicturePackItemInfoPrefix;
    @Value("${cache.prefix.entity.judgement}")
    private String judgementPrefix;
    @Value("${cache.prefix.entity.sinker_info}")
    private String sinkerInfoPrefix;
    @Value("${cache.prefix.entity.sinker_meta}")
    private String sinkerMetaPrefix;
    @Value("${cache.prefix.entity.sinker_meta_indicator}")
    private String sinkerMetaIndicatorPrefix;
    @Value("${cache.prefix.entity.sinker_relation}")
    private String sinkerRelationPrefix;
    @Value("${cache.prefix.entity.sinker_support}")
    private String sinkerSupportPrefix;
    @Value("${cache.prefix.entity.sinker_variable}")
    private String sinkerVariablePrefix;
    @Value("${cache.prefix.entity.provider_info}")
    private String providerInfoPrefix;
    @Value("${cache.prefix.entity.provider_support}")
    private String providerSupportPrefix;
    @Value("${cache.prefix.entity.visualizer_info}")
    private String visualizerInfoPrefix;
    @Value("${cache.prefix.entity.visualizer_support}")
    private String visualizerSupportPrefix;
    @Value("${cache.prefix.entity.visualize_data}")
    private String visualizeDataPrefix;
    @Value("${cache.prefix.entity.adapter_info}")
    private String adapterInfoPrefix;
    @Value("${cache.prefix.entity.adapter_support}")
    private String adapterSupportPrefix;

    public CacheConfiguration(RedisTemplate<String, ?> template) {
        this.template = template;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, AnalyserInfo, FastJsonAnalyserInfo> analyserInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAnalyserInfo>) template,
                new LongIdStringKeyFormatter(analyserInfoPrefix),
                new MapStructBeanTransformer<>(AnalyserInfo.class, FastJsonAnalyserInfo.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, AnalyserSupport, FastJsonAnalyserSupport>
    analyserSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAnalyserSupport>) template,
                new StringIdStringKeyFormatter(analyserSupportPrefix),
                new MapStructBeanTransformer<>(AnalyserSupport.class, FastJsonAnalyserSupport.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<AnalyserVariableKey, AnalyserVariable, FastJsonAnalyserVariable>
    analyserVariableRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAnalyserVariable>) template,
                new AnalyserVariableStringKeyFormatter(analyserVariablePrefix),
                new MapStructBeanTransformer<>(AnalyserVariable.class, FastJsonAnalyserVariable.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<AnalysisKey, Analysis, FastJsonAnalysis> analysisRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAnalysis>) template,
                new AnalysisStringKeyFormatter(analysisPrefix),
                new MapStructBeanTransformer<>(Analysis.class, FastJsonAnalysis.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, DriverInfo, FastJsonDriverInfo> driverInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonDriverInfo>) template,
                new LongIdStringKeyFormatter(driverInfoPrefix),
                new MapStructBeanTransformer<>(DriverInfo.class, FastJsonDriverInfo.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, DriverSupport, FastJsonDriverSupport> driverSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonDriverSupport>) template,
                new StringIdStringKeyFormatter(driverSupportPrefix),
                new MapStructBeanTransformer<>(DriverSupport.class, FastJsonDriverSupport.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, JudgerInfo, FastJsonJudgerInfo> judgerInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonJudgerInfo>) template,
                new LongIdStringKeyFormatter(judgerInfoPrefix),
                new MapStructBeanTransformer<>(JudgerInfo.class, FastJsonJudgerInfo.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, JudgerSupport, FastJsonJudgerSupport> judgerSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonJudgerSupport>) template,
                new StringIdStringKeyFormatter(judgerSupportPrefix),
                new MapStructBeanTransformer<>(JudgerSupport.class, FastJsonJudgerSupport.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<JudgerVariableKey, JudgerVariable, FastJsonJudgerVariable>
    judgerVariableRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonJudgerVariable>) template,
                new JudgerVariableStringKeyFormatter(judgerVariablePrefix),
                new MapStructBeanTransformer<>(JudgerVariable.class, FastJsonJudgerVariable.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, Section, FastJsonSection> sectionRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonSection>) template,
                new LongIdStringKeyFormatter(sectionPrefix),
                new MapStructBeanTransformer<>(Section.class, FastJsonSection.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, Task, FastJsonTask> taskRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonTask>) template,
                new LongIdStringKeyFormatter(taskPrefix),
                new MapStructBeanTransformer<>(Task.class, FastJsonTask.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, TaskEvent, FastJsonTaskEvent>
    taskEventRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonTaskEvent>) template,
                new LongIdStringKeyFormatter(taskEventPrefix),
                new MapStructBeanTransformer<>(TaskEvent.class, FastJsonTaskEvent.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, AnalysisFileInfo, FastJsonAnalysisFileInfo>
    analysisFileInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAnalysisFileInfo>) template,
                new LongIdStringKeyFormatter(analysisFileInfoPrefix),
                new MapStructBeanTransformer<>(AnalysisFileInfo.class, FastJsonAnalysisFileInfo.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, AnalysisFilePack, FastJsonAnalysisFilePack>
    analysisFilePackRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAnalysisFilePack>) template,
                new LongIdStringKeyFormatter(analysisFilePackPrefix),
                new MapStructBeanTransformer<>(AnalysisFilePack.class, FastJsonAnalysisFilePack.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, AnalysisFilePackItemInfo, FastJsonAnalysisFilePackItemInfo>
    analysisFilePackItemInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAnalysisFilePackItemInfo>) template,
                new LongIdStringKeyFormatter(analysisFilePackItemInfoPrefix),
                new MapStructBeanTransformer<>(
                        AnalysisFilePackItemInfo.class, FastJsonAnalysisFilePackItemInfo.class, BeanMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, AnalysisPictureInfo, FastJsonAnalysisPictureInfo>
    analysisPictureInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAnalysisPictureInfo>) template,
                new LongIdStringKeyFormatter(analysisPictureInfoPrefix),
                new MapStructBeanTransformer<>(
                        AnalysisPictureInfo.class, FastJsonAnalysisPictureInfo.class, BeanMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, AnalysisPicturePack, FastJsonAnalysisPicturePack>
    analysisPicturePackRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAnalysisPicturePack>) template,
                new LongIdStringKeyFormatter(analysisPicturePackPrefix),
                new MapStructBeanTransformer<>(
                        AnalysisPicturePack.class, FastJsonAnalysisPicturePack.class, BeanMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, AnalysisPicturePackItemInfo, FastJsonAnalysisPicturePackItemInfo>
    analysisPicturePackItemInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAnalysisPicturePackItemInfo>) template,
                new LongIdStringKeyFormatter(analysisPicturePackItemInfoPrefix),
                new MapStructBeanTransformer<>(
                        AnalysisPicturePackItemInfo.class, FastJsonAnalysisPicturePackItemInfo.class, BeanMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<JudgementKey, Judgement, FastJsonJudgement> judgementRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonJudgement>) template,
                new JudgementStringKeyFormatter(judgementPrefix),
                new MapStructBeanTransformer<>(Judgement.class, FastJsonJudgement.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, SinkerInfo, FastJsonSinkerInfo> sinkerInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonSinkerInfo>) template,
                new LongIdStringKeyFormatter(sinkerInfoPrefix),
                new MapStructBeanTransformer<>(SinkerInfo.class, FastJsonSinkerInfo.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<SinkerMetaKey, SinkerMeta, FastJsonSinkerMeta> sinkerMetaRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonSinkerMeta>) template,
                new SinkerMetaStringKeyFormatter(sinkerMetaPrefix),
                new MapStructBeanTransformer<>(SinkerMeta.class, FastJsonSinkerMeta.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<SinkerMetaIndicatorKey, SinkerMetaIndicator, FastJsonSinkerMetaIndicator>
    sinkerMetaIndicatorRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonSinkerMetaIndicator>) template,
                new SinkerMetaIndicatorStringKeyFormatter(sinkerMetaIndicatorPrefix),
                new MapStructBeanTransformer<>(
                        SinkerMetaIndicator.class, FastJsonSinkerMetaIndicator.class, BeanMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<SinkerRelationKey, SinkerRelation, FastJsonSinkerRelation>
    sinkerRelationRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonSinkerRelation>) template,
                new SinkerRelationStringKeyFormatter(sinkerRelationPrefix),
                new MapStructBeanTransformer<>(
                        SinkerRelation.class, FastJsonSinkerRelation.class, BeanMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, SinkerSupport, FastJsonSinkerSupport> sinkerSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonSinkerSupport>) template,
                new StringIdStringKeyFormatter(sinkerSupportPrefix),
                new MapStructBeanTransformer<>(SinkerSupport.class, FastJsonSinkerSupport.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<SinkerVariableKey, SinkerVariable, FastJsonSinkerVariable>
    sinkerVariableRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonSinkerVariable>) template,
                new SinkerVariableStringKeyFormatter(sinkerVariablePrefix),
                new MapStructBeanTransformer<>(
                        SinkerVariable.class, FastJsonSinkerVariable.class, BeanMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, ProviderInfo, FastJsonProviderInfo> providerInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonProviderInfo>) template,
                new LongIdStringKeyFormatter(providerInfoPrefix),
                new MapStructBeanTransformer<>(ProviderInfo.class, FastJsonProviderInfo.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, ProviderSupport, FastJsonProviderSupport> providerSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonProviderSupport>) template,
                new StringIdStringKeyFormatter(providerSupportPrefix),
                new MapStructBeanTransformer<>(ProviderSupport.class, FastJsonProviderSupport.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, VisualizerInfo, FastJsonVisualizerInfo>
    visualizerInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonVisualizerInfo>) template,
                new LongIdStringKeyFormatter(visualizerInfoPrefix),
                new MapStructBeanTransformer<>(VisualizerInfo.class, FastJsonVisualizerInfo.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, VisualizerSupport, FastJsonVisualizerSupport>
    visualizerSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonVisualizerSupport>) template,
                new StringIdStringKeyFormatter(visualizerSupportPrefix),
                new MapStructBeanTransformer<>(
                        VisualizerSupport.class, FastJsonVisualizerSupport.class, BeanMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<VisualizeDataKey, VisualizeData, FastJsonVisualizeData>
    visualizeDataRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonVisualizeData>) template,
                new VisualizeDataStringKeyFormatter(visualizeDataPrefix),
                new MapStructBeanTransformer<>(VisualizeData.class, FastJsonVisualizeData.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, AdapterInfo, FastJsonAdapterInfo> adapterInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAdapterInfo>) template,
                new LongIdStringKeyFormatter(adapterInfoPrefix),
                new MapStructBeanTransformer<>(AdapterInfo.class, FastJsonAdapterInfo.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, AdapterSupport, FastJsonAdapterSupport> adapterSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAdapterSupport>) template,
                new StringIdStringKeyFormatter(adapterSupportPrefix),
                new MapStructBeanTransformer<>(AdapterSupport.class, FastJsonAdapterSupport.class, BeanMapper.class)
        );
    }
}
