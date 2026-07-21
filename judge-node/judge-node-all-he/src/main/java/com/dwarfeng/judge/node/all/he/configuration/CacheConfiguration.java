package com.dwarfeng.judge.node.all.he.configuration;

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

    @Value("${com.dwarfeng.judge.cache.prefix.entity.analyser_info}")
    private String analyserInfoPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.analyser_support}")
    private String analyserSupportPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.analyser_variable}")
    private String analyserVariablePrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.analysis}")
    private String analysisPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.driver_info}")
    private String driverInfoPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.driver_support}")
    private String driverSupportPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.judger_info}")
    private String judgerInfoPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.judger_support}")
    private String judgerSupportPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.judger_variable}")
    private String judgerVariablePrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.section}")
    private String sectionPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.task}")
    private String taskPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.task_event}")
    private String taskEventPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.analysis_file_info}")
    private String analysisFileInfoPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.analysis_file_pack}")
    private String analysisFilePackPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.analysis_file_pack_item_info}")
    private String analysisFilePackItemInfoPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.analysis_picture_info}")
    private String analysisPictureInfoPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.analysis_picture_pack}")
    private String analysisPicturePackPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.analysis_picture_pack_item_info}")
    private String analysisPicturePackItemInfoPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.judgement}")
    private String judgementPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.sinker_info}")
    private String sinkerInfoPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.sinker_meta}")
    private String sinkerMetaPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.sinker_meta_indicator}")
    private String sinkerMetaIndicatorPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.sinker_relation}")
    private String sinkerRelationPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.sinker_support}")
    private String sinkerSupportPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.sinker_variable}")
    private String sinkerVariablePrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.provider_info}")
    private String providerInfoPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.provider_support}")
    private String providerSupportPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.visualizer_info}")
    private String visualizerInfoPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.visualizer_support}")
    private String visualizerSupportPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.visualize_data}")
    private String visualizeDataPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.adapter_info}")
    private String adapterInfoPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.adapter_support}")
    private String adapterSupportPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.filter_info}")
    private String filterInfoPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.filter_support}")
    private String filterSupportPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.alarm}")
    private String alarmPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.alarm_history}")
    private String alarmHistoryPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.alarm_modal}")
    private String alarmModalPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.alarm_setting}")
    private String alarmSettingPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.alarm_type_indicator}")
    private String alarmTypeIndicatorPrefix;
    @Value("${com.dwarfeng.judge.cache.prefix.entity.verdict}")
    private String verdictPrefix;

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
    public RedisBatchBaseCache<StringIdKey, ProviderSupport, FastJsonProviderSupport>
    providerSupportRedisBatchBaseCache() {
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
    public RedisBatchBaseCache<StringIdKey, AdapterSupport, FastJsonAdapterSupport>
    adapterSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAdapterSupport>) template,
                new StringIdStringKeyFormatter(adapterSupportPrefix),
                new MapStructBeanTransformer<>(AdapterSupport.class, FastJsonAdapterSupport.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, FilterInfo, FastJsonFilterInfo> filterInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonFilterInfo>) template,
                new LongIdStringKeyFormatter(filterInfoPrefix),
                new MapStructBeanTransformer<>(FilterInfo.class, FastJsonFilterInfo.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, FilterSupport, FastJsonFilterSupport> filterSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonFilterSupport>) template,
                new StringIdStringKeyFormatter(filterSupportPrefix),
                new MapStructBeanTransformer<>(FilterSupport.class, FastJsonFilterSupport.class, BeanMapper.class)
        );
    }


    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<AlarmKey, Alarm, FastJsonAlarm> alarmRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAlarm>) template,
                new AlarmStringKeyFormatter(alarmPrefix),
                new MapStructBeanTransformer<>(Alarm.class, FastJsonAlarm.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, AlarmHistory, FastJsonAlarmHistory> alarmHistoryRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAlarmHistory>) template,
                new LongIdStringKeyFormatter(alarmHistoryPrefix),
                new MapStructBeanTransformer<>(AlarmHistory.class, FastJsonAlarmHistory.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<AlarmKey, AlarmModal, FastJsonAlarmModal> alarmModalRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAlarmModal>) template,
                new AlarmStringKeyFormatter(alarmModalPrefix),
                new MapStructBeanTransformer<>(AlarmModal.class, FastJsonAlarmModal.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, AlarmSetting, FastJsonAlarmSetting> alarmSettingRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAlarmSetting>) template,
                new LongIdStringKeyFormatter(alarmSettingPrefix),
                new MapStructBeanTransformer<>(AlarmSetting.class, FastJsonAlarmSetting.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, AlarmTypeIndicator, FastJsonAlarmTypeIndicator>
    alarmTypeIndicatorRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonAlarmTypeIndicator>) template,
                new StringIdStringKeyFormatter(alarmTypeIndicatorPrefix),
                new MapStructBeanTransformer<>(
                        AlarmTypeIndicator.class, FastJsonAlarmTypeIndicator.class, BeanMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<VerdictKey, Verdict, FastJsonVerdict> verdictRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonVerdict>) template,
                new VerdictStringKeyFormatter(verdictPrefix),
                new MapStructBeanTransformer<>(Verdict.class, FastJsonVerdict.class, BeanMapper.class)
        );
    }
}
