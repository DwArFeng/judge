package com.dwarfeng.judge.impl.configuration;

import com.dwarfeng.judge.sdk.bean.BeanMapper;
import com.dwarfeng.judge.sdk.bean.entity.*;
import com.dwarfeng.judge.sdk.bean.key.formatter.AnalyserVariableStringKeyFormatter;
import com.dwarfeng.judge.sdk.bean.key.formatter.AnalysisStringKeyFormatter;
import com.dwarfeng.judge.sdk.bean.key.formatter.JudgerVariableStringKeyFormatter;
import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.judge.stack.bean.key.AnalyserVariableKey;
import com.dwarfeng.judge.stack.bean.key.AnalysisKey;
import com.dwarfeng.judge.stack.bean.key.JudgerVariableKey;
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
    @Value("${cache.prefix.entity.judgement_history}")
    private String judgementHistoryPrefix;
    @Value("${cache.prefix.entity.judgement_modal}")
    private String judgementModalPrefix;
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
    public RedisBatchBaseCache<LongIdKey, JudgementHistory, FastJsonJudgementHistory>
    judgementHistoryRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonJudgementHistory>) template,
                new LongIdStringKeyFormatter(judgementHistoryPrefix),
                new MapStructBeanTransformer<>(JudgementHistory.class, FastJsonJudgementHistory.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, JudgementModal, FastJsonJudgementModal> judgementModalRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonJudgementModal>) template,
                new LongIdStringKeyFormatter(judgementModalPrefix),
                new MapStructBeanTransformer<>(JudgementModal.class, FastJsonJudgementModal.class, BeanMapper.class)
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
}
