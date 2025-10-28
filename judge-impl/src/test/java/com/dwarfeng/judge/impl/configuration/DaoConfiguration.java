package com.dwarfeng.judge.impl.configuration;

import com.dwarfeng.judge.impl.bean.BeanMapper;
import com.dwarfeng.judge.impl.bean.entity.*;
import com.dwarfeng.judge.impl.bean.key.*;
import com.dwarfeng.judge.impl.dao.preset.*;
import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.judge.stack.bean.key.*;
import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DefaultDeletionMod;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    private final HibernateTemplate hibernateTemplate;

    private final AnalyserInfoPresetCriteriaMaker analyserInfoPresetCriteriaMaker;
    private final AnalyserSupportPresetCriteriaMaker analyserSupportPresetCriteriaMaker;
    private final AnalyserVariablePresetCriteriaMaker analyserVariablePresetCriteriaMaker;
    private final AnalysisPresetCriteriaMaker analysisPresetCriteriaMaker;
    private final DriverInfoPresetCriteriaMaker driverInfoPresetCriteriaMaker;
    private final DriverSupportPresetCriteriaMaker driverSupportPresetCriteriaMaker;
    private final JudgerInfoPresetCriteriaMaker judgerInfoPresetCriteriaMaker;
    private final JudgerSupportPresetCriteriaMaker judgerSupportPresetCriteriaMaker;
    private final JudgerVariablePresetCriteriaMaker judgerVariablePresetCriteriaMaker;
    private final SectionPresetCriteriaMaker sectionPresetCriteriaMaker;
    private final TaskPresetCriteriaMaker taskPresetCriteriaMaker;
    private final TaskEventPresetCriteriaMaker taskEventPresetCriteriaMaker;
    private final AnalysisFileInfoPresetCriteriaMaker analysisFileInfoPresetCriteriaMaker;
    private final AnalysisFilePackPresetCriteriaMaker analysisFilePackPresetCriteriaMaker;
    private final AnalysisFilePackItemInfoPresetCriteriaMaker analysisFilePackItemInfoPresetCriteriaMaker;
    private final AnalysisPictureInfoPresetCriteriaMaker analysisPictureInfoPresetCriteriaMaker;
    private final AnalysisPicturePackPresetCriteriaMaker analysisPicturePackPresetCriteriaMaker;
    private final AnalysisPicturePackItemInfoPresetCriteriaMaker analysisPicturePackItemInfoPresetCriteriaMaker;
    private final JudgementPresetCriteriaMaker judgementPresetCriteriaMaker;
    private final SinkerInfoPresetCriteriaMaker sinkerInfoPresetCriteriaMaker;
    private final SinkerMetaIndicatorPresetCriteriaMaker sinkerMetaIndicatorPresetCriteriaMaker;
    private final SinkerMetaPresetCriteriaMaker sinkerMetaPresetCriteriaMaker;
    private final SinkerRelationPresetCriteriaMaker sinkerRelationPresetCriteriaMaker;
    private final SinkerSupportPresetCriteriaMaker sinkerSupportPresetCriteriaMaker;
    private final SinkerVariablePresetCriteriaMaker sinkerVariablePresetCriteriaMaker;
    private final ProviderInfoPresetCriteriaMaker providerInfoPresetCriteriaMaker;
    private final ProviderSupportPresetCriteriaMaker providerSupportPresetCriteriaMaker;
    private final VisualizerInfoPresetCriteriaMaker visualizerInfoPresetCriteriaMaker;
    private final VisualizerSupportPresetCriteriaMaker visualizerSupportPresetCriteriaMaker;
    private final VisualizeDataPresetCriteriaMaker visualizeDataPresetCriteriaMaker;
    private final AdapterInfoPresetCriteriaMaker adapterInfoPresetCriteriaMaker;
    private final AdapterSupportPresetCriteriaMaker adapterSupportPresetCriteriaMaker;
    private final FilterInfoPresetCriteriaMaker filterInfoPresetCriteriaMaker;
    private final FilterSupportPresetCriteriaMaker filterSupportPresetCriteriaMaker;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate hibernateTemplate,
            AnalyserInfoPresetCriteriaMaker analyserInfoPresetCriteriaMaker,
            AnalyserSupportPresetCriteriaMaker analyserSupportPresetCriteriaMaker,
            AnalyserVariablePresetCriteriaMaker analyserVariablePresetCriteriaMaker,
            AnalysisPresetCriteriaMaker analysisPresetCriteriaMaker,
            DriverInfoPresetCriteriaMaker driverInfoPresetCriteriaMaker,
            DriverSupportPresetCriteriaMaker driverSupportPresetCriteriaMaker,
            JudgerInfoPresetCriteriaMaker judgerInfoPresetCriteriaMaker,
            JudgerSupportPresetCriteriaMaker judgerSupportPresetCriteriaMaker,
            JudgerVariablePresetCriteriaMaker judgerVariablePresetCriteriaMaker,
            SectionPresetCriteriaMaker sectionPresetCriteriaMaker,
            TaskPresetCriteriaMaker taskPresetCriteriaMaker,
            TaskEventPresetCriteriaMaker taskEventPresetCriteriaMaker,
            AnalysisFileInfoPresetCriteriaMaker analysisFileInfoPresetCriteriaMaker,
            AnalysisFilePackPresetCriteriaMaker analysisFilePackPresetCriteriaMaker,
            AnalysisFilePackItemInfoPresetCriteriaMaker analysisFilePackItemInfoPresetCriteriaMaker,
            AnalysisPictureInfoPresetCriteriaMaker analysisPictureInfoPresetCriteriaMaker,
            AnalysisPicturePackPresetCriteriaMaker analysisPicturePackPresetCriteriaMaker,
            AnalysisPicturePackItemInfoPresetCriteriaMaker analysisPicturePackItemInfoPresetCriteriaMaker,
            JudgementPresetCriteriaMaker judgementPresetCriteriaMaker,
            SinkerInfoPresetCriteriaMaker sinkerInfoPresetCriteriaMaker,
            SinkerMetaIndicatorPresetCriteriaMaker sinkerMetaIndicatorPresetCriteriaMaker,
            SinkerMetaPresetCriteriaMaker sinkerMetaPresetCriteriaMaker,
            SinkerRelationPresetCriteriaMaker sinkerRelationPresetCriteriaMaker,
            SinkerSupportPresetCriteriaMaker sinkerSupportPresetCriteriaMaker,
            SinkerVariablePresetCriteriaMaker sinkerVariablePresetCriteriaMaker,
            ProviderInfoPresetCriteriaMaker providerInfoPresetCriteriaMaker,
            ProviderSupportPresetCriteriaMaker providerSupportPresetCriteriaMaker,
            VisualizerInfoPresetCriteriaMaker visualizerInfoPresetCriteriaMaker,
            VisualizerSupportPresetCriteriaMaker visualizerSupportPresetCriteriaMaker,
            VisualizeDataPresetCriteriaMaker visualizeDataPresetCriteriaMaker,
            AdapterInfoPresetCriteriaMaker adapterInfoPresetCriteriaMaker,
            AdapterSupportPresetCriteriaMaker adapterSupportPresetCriteriaMaker,
            FilterInfoPresetCriteriaMaker filterInfoPresetCriteriaMaker,
            FilterSupportPresetCriteriaMaker filterSupportPresetCriteriaMaker
    ) {
        this.hibernateTemplate = hibernateTemplate;
        this.analyserInfoPresetCriteriaMaker = analyserInfoPresetCriteriaMaker;
        this.analyserSupportPresetCriteriaMaker = analyserSupportPresetCriteriaMaker;
        this.analyserVariablePresetCriteriaMaker = analyserVariablePresetCriteriaMaker;
        this.analysisPresetCriteriaMaker = analysisPresetCriteriaMaker;
        this.driverInfoPresetCriteriaMaker = driverInfoPresetCriteriaMaker;
        this.driverSupportPresetCriteriaMaker = driverSupportPresetCriteriaMaker;
        this.judgerInfoPresetCriteriaMaker = judgerInfoPresetCriteriaMaker;
        this.judgerSupportPresetCriteriaMaker = judgerSupportPresetCriteriaMaker;
        this.judgerVariablePresetCriteriaMaker = judgerVariablePresetCriteriaMaker;
        this.sectionPresetCriteriaMaker = sectionPresetCriteriaMaker;
        this.taskPresetCriteriaMaker = taskPresetCriteriaMaker;
        this.taskEventPresetCriteriaMaker = taskEventPresetCriteriaMaker;
        this.analysisFileInfoPresetCriteriaMaker = analysisFileInfoPresetCriteriaMaker;
        this.analysisFilePackPresetCriteriaMaker = analysisFilePackPresetCriteriaMaker;
        this.analysisFilePackItemInfoPresetCriteriaMaker = analysisFilePackItemInfoPresetCriteriaMaker;
        this.analysisPictureInfoPresetCriteriaMaker = analysisPictureInfoPresetCriteriaMaker;
        this.analysisPicturePackPresetCriteriaMaker = analysisPicturePackPresetCriteriaMaker;
        this.analysisPicturePackItemInfoPresetCriteriaMaker = analysisPicturePackItemInfoPresetCriteriaMaker;
        this.judgementPresetCriteriaMaker = judgementPresetCriteriaMaker;
        this.sinkerInfoPresetCriteriaMaker = sinkerInfoPresetCriteriaMaker;
        this.sinkerMetaIndicatorPresetCriteriaMaker = sinkerMetaIndicatorPresetCriteriaMaker;
        this.sinkerMetaPresetCriteriaMaker = sinkerMetaPresetCriteriaMaker;
        this.sinkerRelationPresetCriteriaMaker = sinkerRelationPresetCriteriaMaker;
        this.sinkerSupportPresetCriteriaMaker = sinkerSupportPresetCriteriaMaker;
        this.sinkerVariablePresetCriteriaMaker = sinkerVariablePresetCriteriaMaker;
        this.providerInfoPresetCriteriaMaker = providerInfoPresetCriteriaMaker;
        this.providerSupportPresetCriteriaMaker = providerSupportPresetCriteriaMaker;
        this.visualizerInfoPresetCriteriaMaker = visualizerInfoPresetCriteriaMaker;
        this.visualizerSupportPresetCriteriaMaker = visualizerSupportPresetCriteriaMaker;
        this.visualizeDataPresetCriteriaMaker = visualizeDataPresetCriteriaMaker;
        this.adapterInfoPresetCriteriaMaker = adapterInfoPresetCriteriaMaker;
        this.adapterSupportPresetCriteriaMaker = adapterSupportPresetCriteriaMaker;
        this.filterInfoPresetCriteriaMaker = filterInfoPresetCriteriaMaker;
        this.filterSupportPresetCriteriaMaker = filterSupportPresetCriteriaMaker;
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, AnalyserInfo, HibernateAnalyserInfo>
    analyserInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(AnalyserInfo.class, HibernateAnalyserInfo.class, BeanMapper.class),
                HibernateAnalyserInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<AnalyserInfo, HibernateAnalyserInfo> analyserInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(AnalyserInfo.class, HibernateAnalyserInfo.class, BeanMapper.class),
                HibernateAnalyserInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<AnalyserInfo, HibernateAnalyserInfo> analyserInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(AnalyserInfo.class, HibernateAnalyserInfo.class, BeanMapper.class),
                HibernateAnalyserInfo.class,
                analyserInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, AnalyserSupport, HibernateAnalyserSupport>
    analyserSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(AnalyserSupport.class, HibernateAnalyserSupport.class, BeanMapper.class),
                HibernateAnalyserSupport.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<AnalyserSupport, HibernateAnalyserSupport>
    analyserSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(AnalyserSupport.class, HibernateAnalyserSupport.class, BeanMapper.class),
                HibernateAnalyserSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<AnalyserSupport, HibernateAnalyserSupport>
    analyserSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(AnalyserSupport.class, HibernateAnalyserSupport.class, BeanMapper.class),
                HibernateAnalyserSupport.class,
                analyserSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<AnalyserVariableKey, HibernateAnalyserVariableKey, AnalyserVariable,
            HibernateAnalyserVariable> analyserVariableHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        AnalyserVariableKey.class, HibernateAnalyserVariableKey.class, BeanMapper.class
                ),
                new MapStructBeanTransformer<>(
                        AnalyserVariable.class, HibernateAnalyserVariable.class, BeanMapper.class
                ),
                HibernateAnalyserVariable.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<AnalyserVariable, HibernateAnalyserVariable>
    nalyserVariableHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        AnalyserVariable.class, HibernateAnalyserVariable.class, BeanMapper.class
                ),
                HibernateAnalyserVariable.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<AnalyserVariable, HibernateAnalyserVariable>
    analyserVariableHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        AnalyserVariable.class, HibernateAnalyserVariable.class, BeanMapper.class
                ),
                HibernateAnalyserVariable.class,
                analyserVariablePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<AnalysisKey, HibernateAnalysisKey, Analysis, HibernateAnalysis>
    analysisHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(AnalysisKey.class, HibernateAnalysisKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(Analysis.class, HibernateAnalysis.class, BeanMapper.class),
                HibernateAnalysis.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Analysis, HibernateAnalysis> analysisHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(Analysis.class, HibernateAnalysis.class, BeanMapper.class),
                HibernateAnalysis.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Analysis, HibernateAnalysis> analysisHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(Analysis.class, HibernateAnalysis.class, BeanMapper.class),
                HibernateAnalysis.class,
                analysisPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, DriverInfo, HibernateDriverInfo>
    driverInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(DriverInfo.class, HibernateDriverInfo.class, BeanMapper.class),
                HibernateDriverInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<DriverInfo, HibernateDriverInfo> driverInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(DriverInfo.class, HibernateDriverInfo.class, BeanMapper.class),
                HibernateDriverInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<DriverInfo, HibernateDriverInfo> driverInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(DriverInfo.class, HibernateDriverInfo.class, BeanMapper.class),
                HibernateDriverInfo.class,
                driverInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, DriverSupport, HibernateDriverSupport>
    driverSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(DriverSupport.class, HibernateDriverSupport.class, BeanMapper.class),
                HibernateDriverSupport.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<DriverSupport, HibernateDriverSupport> driverSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(DriverSupport.class, HibernateDriverSupport.class, BeanMapper.class),
                HibernateDriverSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<DriverSupport, HibernateDriverSupport> driverSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(DriverSupport.class, HibernateDriverSupport.class, BeanMapper.class),
                HibernateDriverSupport.class,
                driverSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, JudgerInfo, HibernateJudgerInfo>
    judgerInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(JudgerInfo.class, HibernateJudgerInfo.class, BeanMapper.class),
                HibernateJudgerInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<JudgerInfo, HibernateJudgerInfo> judgerInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(JudgerInfo.class, HibernateJudgerInfo.class, BeanMapper.class),
                HibernateJudgerInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<JudgerInfo, HibernateJudgerInfo> judgerInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(JudgerInfo.class, HibernateJudgerInfo.class, BeanMapper.class),
                HibernateJudgerInfo.class,
                judgerInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, JudgerSupport, HibernateJudgerSupport>
    judgerSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(JudgerSupport.class, HibernateJudgerSupport.class, BeanMapper.class),
                HibernateJudgerSupport.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<JudgerSupport, HibernateJudgerSupport> judgerSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(JudgerSupport.class, HibernateJudgerSupport.class, BeanMapper.class),
                HibernateJudgerSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<JudgerSupport, HibernateJudgerSupport> judgerSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(JudgerSupport.class, HibernateJudgerSupport.class, BeanMapper.class),
                HibernateJudgerSupport.class,
                judgerSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<JudgerVariableKey, HibernateJudgerVariableKey, JudgerVariable,
            HibernateJudgerVariable> judgerVariableHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        JudgerVariableKey.class, HibernateJudgerVariableKey.class, BeanMapper.class
                ),
                new MapStructBeanTransformer<>(JudgerVariable.class, HibernateJudgerVariable.class, BeanMapper.class),
                HibernateJudgerVariable.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<JudgerVariable, HibernateJudgerVariable> judgerVariableHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(JudgerVariable.class, HibernateJudgerVariable.class, BeanMapper.class),
                HibernateJudgerVariable.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<JudgerVariable, HibernateJudgerVariable> judgerVariableHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(JudgerVariable.class, HibernateJudgerVariable.class, BeanMapper.class),
                HibernateJudgerVariable.class,
                judgerVariablePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, Task, HibernateTask>
    taskHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(Task.class, HibernateTask.class, BeanMapper.class),
                HibernateTask.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Task, HibernateTask> taskHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(Task.class, HibernateTask.class, BeanMapper.class),
                HibernateTask.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Task, HibernateTask> taskHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(Task.class, HibernateTask.class, BeanMapper.class),
                HibernateTask.class,
                taskPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, TaskEvent, HibernateTaskEvent>
    taskEventHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        TaskEvent.class, HibernateTaskEvent.class, BeanMapper.class
                ),
                HibernateTaskEvent.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<TaskEvent, HibernateTaskEvent>
    taskEventHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        TaskEvent.class, HibernateTaskEvent.class, BeanMapper.class
                ),
                HibernateTaskEvent.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<TaskEvent, HibernateTaskEvent>
    taskEventHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        TaskEvent.class, HibernateTaskEvent.class, BeanMapper.class
                ),
                HibernateTaskEvent.class,
                taskEventPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, Section, HibernateSection>
    sectionHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(Section.class, HibernateSection.class, BeanMapper.class),
                HibernateSection.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Section, HibernateSection> sectionHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(Section.class, HibernateSection.class, BeanMapper.class),
                HibernateSection.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Section, HibernateSection> sectionHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(Section.class, HibernateSection.class, BeanMapper.class),
                HibernateSection.class,
                sectionPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, AnalysisFileInfo, HibernateAnalysisFileInfo>
    analysisFileInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        AnalysisFileInfo.class, HibernateAnalysisFileInfo.class, BeanMapper.class
                ),
                HibernateAnalysisFileInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<AnalysisFileInfo, HibernateAnalysisFileInfo>
    analysisFileInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        AnalysisFileInfo.class, HibernateAnalysisFileInfo.class, BeanMapper.class
                ),
                HibernateAnalysisFileInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<AnalysisFileInfo, HibernateAnalysisFileInfo>
    analysisFileInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        AnalysisFileInfo.class, HibernateAnalysisFileInfo.class, BeanMapper.class
                ),
                HibernateAnalysisFileInfo.class,
                analysisFileInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, AnalysisFilePack, HibernateAnalysisFilePack>
    analysisFilePackHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        AnalysisFilePack.class, HibernateAnalysisFilePack.class, BeanMapper.class
                ),
                HibernateAnalysisFilePack.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<AnalysisFilePack, HibernateAnalysisFilePack>
    analysisFilePackHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        AnalysisFilePack.class, HibernateAnalysisFilePack.class, BeanMapper.class
                ),
                HibernateAnalysisFilePack.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<AnalysisFilePack, HibernateAnalysisFilePack>
    analysisFilePackHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        AnalysisFilePack.class, HibernateAnalysisFilePack.class, BeanMapper.class
                ),
                HibernateAnalysisFilePack.class,
                analysisFilePackPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, AnalysisFilePackItemInfo,
            HibernateAnalysisFilePackItemInfo> analysisFilePackItemInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        AnalysisFilePackItemInfo.class, HibernateAnalysisFilePackItemInfo.class, BeanMapper.class
                ),
                HibernateAnalysisFilePackItemInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<AnalysisFilePackItemInfo, HibernateAnalysisFilePackItemInfo>
    analysisFilePackItemInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        AnalysisFilePackItemInfo.class, HibernateAnalysisFilePackItemInfo.class, BeanMapper.class
                ),
                HibernateAnalysisFilePackItemInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<AnalysisFilePackItemInfo, HibernateAnalysisFilePackItemInfo>
    analysisFilePackItemInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        AnalysisFilePackItemInfo.class, HibernateAnalysisFilePackItemInfo.class, BeanMapper.class
                ),
                HibernateAnalysisFilePackItemInfo.class,
                analysisFilePackItemInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, AnalysisPictureInfo, HibernateAnalysisPictureInfo>
    analysisPictureInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        AnalysisPictureInfo.class, HibernateAnalysisPictureInfo.class, BeanMapper.class
                ),
                HibernateAnalysisPictureInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<AnalysisPictureInfo, HibernateAnalysisPictureInfo>
    analysisPictureInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        AnalysisPictureInfo.class, HibernateAnalysisPictureInfo.class, BeanMapper.class
                ),
                HibernateAnalysisPictureInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<AnalysisPictureInfo, HibernateAnalysisPictureInfo>
    analysisPictureInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        AnalysisPictureInfo.class, HibernateAnalysisPictureInfo.class, BeanMapper.class
                ),
                HibernateAnalysisPictureInfo.class,
                analysisPictureInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, AnalysisPicturePack, HibernateAnalysisPicturePack>
    analysisPicturePackHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        AnalysisPicturePack.class, HibernateAnalysisPicturePack.class, BeanMapper.class
                ),
                HibernateAnalysisPicturePack.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<AnalysisPicturePack, HibernateAnalysisPicturePack>
    analysisPicturePackHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        AnalysisPicturePack.class, HibernateAnalysisPicturePack.class, BeanMapper.class
                ),
                HibernateAnalysisPicturePack.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<AnalysisPicturePack, HibernateAnalysisPicturePack>
    analysisPicturePackHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        AnalysisPicturePack.class, HibernateAnalysisPicturePack.class, BeanMapper.class
                ),
                HibernateAnalysisPicturePack.class,
                analysisPicturePackPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, AnalysisPicturePackItemInfo,
            HibernateAnalysisPicturePackItemInfo> analysisPicturePackItemInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        AnalysisPicturePackItemInfo.class, HibernateAnalysisPicturePackItemInfo.class, BeanMapper.class
                ),
                HibernateAnalysisPicturePackItemInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<AnalysisPicturePackItemInfo, HibernateAnalysisPicturePackItemInfo>
    analysisPicturePackItemInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        AnalysisPicturePackItemInfo.class, HibernateAnalysisPicturePackItemInfo.class, BeanMapper.class
                ),
                HibernateAnalysisPicturePackItemInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<AnalysisPicturePackItemInfo, HibernateAnalysisPicturePackItemInfo>
    analysisPicturePackItemInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        AnalysisPicturePackItemInfo.class, HibernateAnalysisPicturePackItemInfo.class, BeanMapper.class
                ),
                HibernateAnalysisPicturePackItemInfo.class,
                analysisPicturePackItemInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<JudgementKey, HibernateJudgementKey, Judgement, HibernateJudgement>
    judgementHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(JudgementKey.class, HibernateJudgementKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(Judgement.class, HibernateJudgement.class, BeanMapper.class),
                HibernateJudgement.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Judgement, HibernateJudgement> judgementHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(Judgement.class, HibernateJudgement.class, BeanMapper.class),
                HibernateJudgement.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Judgement, HibernateJudgement> judgementHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(Judgement.class, HibernateJudgement.class, BeanMapper.class),
                HibernateJudgement.class,
                judgementPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, SinkerInfo, HibernateSinkerInfo>
    sinkerInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(SinkerInfo.class, HibernateSinkerInfo.class, BeanMapper.class),
                HibernateSinkerInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<SinkerInfo, HibernateSinkerInfo> sinkerInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(SinkerInfo.class, HibernateSinkerInfo.class, BeanMapper.class),
                HibernateSinkerInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<SinkerInfo, HibernateSinkerInfo> sinkerInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(SinkerInfo.class, HibernateSinkerInfo.class, BeanMapper.class),
                HibernateSinkerInfo.class,
                sinkerInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<SinkerMetaKey, HibernateSinkerMetaKey, SinkerMeta, HibernateSinkerMeta>
    sinkerMetaHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(SinkerMetaKey.class, HibernateSinkerMetaKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(SinkerMeta.class, HibernateSinkerMeta.class, BeanMapper.class),
                HibernateSinkerMeta.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<SinkerMeta, HibernateSinkerMeta> sinkerMetaHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(SinkerMeta.class, HibernateSinkerMeta.class, BeanMapper.class),
                HibernateSinkerMeta.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<SinkerMeta, HibernateSinkerMeta> sinkerMetaHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(SinkerMeta.class, HibernateSinkerMeta.class, BeanMapper.class),
                HibernateSinkerMeta.class,
                sinkerMetaPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<SinkerMetaIndicatorKey, HibernateSinkerMetaIndicatorKey, SinkerMetaIndicator,
            HibernateSinkerMetaIndicator> sinkerMetaIndicatorHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        SinkerMetaIndicatorKey.class, HibernateSinkerMetaIndicatorKey.class, BeanMapper.class
                ),
                new MapStructBeanTransformer<>(
                        SinkerMetaIndicator.class, HibernateSinkerMetaIndicator.class, BeanMapper.class
                ),
                HibernateSinkerMetaIndicator.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<SinkerMetaIndicator, HibernateSinkerMetaIndicator>
    sinkerMetaIndicatorHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        SinkerMetaIndicator.class, HibernateSinkerMetaIndicator.class, BeanMapper.class
                ),
                HibernateSinkerMetaIndicator.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<SinkerMetaIndicator, HibernateSinkerMetaIndicator>
    sinkerMetaIndicatorHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        SinkerMetaIndicator.class, HibernateSinkerMetaIndicator.class, BeanMapper.class
                ),
                HibernateSinkerMetaIndicator.class,
                sinkerMetaIndicatorPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<SinkerRelationKey, HibernateSinkerRelationKey, SinkerRelation, HibernateSinkerRelation>
    sinkerRelationHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        SinkerRelationKey.class, HibernateSinkerRelationKey.class, BeanMapper.class
                ),
                new MapStructBeanTransformer<>(
                        SinkerRelation.class, HibernateSinkerRelation.class, BeanMapper.class
                ),
                HibernateSinkerRelation.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<SinkerRelation, HibernateSinkerRelation> sinkerRelationHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        SinkerRelation.class, HibernateSinkerRelation.class, BeanMapper.class
                ),
                HibernateSinkerRelation.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<SinkerRelation, HibernateSinkerRelation> sinkerRelationHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        SinkerRelation.class, HibernateSinkerRelation.class, BeanMapper.class
                ),
                HibernateSinkerRelation.class,
                sinkerRelationPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, SinkerSupport, HibernateSinkerSupport>
    sinkerSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        SinkerSupport.class, HibernateSinkerSupport.class, BeanMapper.class
                ),
                HibernateSinkerSupport.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<SinkerSupport, HibernateSinkerSupport> sinkerSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        SinkerSupport.class, HibernateSinkerSupport.class, BeanMapper.class
                ),
                HibernateSinkerSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<SinkerSupport, HibernateSinkerSupport> sinkerSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        SinkerSupport.class, HibernateSinkerSupport.class, BeanMapper.class
                ),
                HibernateSinkerSupport.class,
                sinkerSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<SinkerVariableKey, HibernateSinkerVariableKey, SinkerVariable,
            HibernateSinkerVariable> sinkerVariableHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        SinkerVariableKey.class, HibernateSinkerVariableKey.class, BeanMapper.class
                ),
                new MapStructBeanTransformer<>(
                        SinkerVariable.class, HibernateSinkerVariable.class, BeanMapper.class
                ),
                HibernateSinkerVariable.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<SinkerVariable, HibernateSinkerVariable>
    sinkerVariableHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        SinkerVariable.class, HibernateSinkerVariable.class, BeanMapper.class
                ),
                HibernateSinkerVariable.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<SinkerVariable, HibernateSinkerVariable> sinkerVariableHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        SinkerVariable.class, HibernateSinkerVariable.class, BeanMapper.class
                ),
                HibernateSinkerVariable.class,
                sinkerVariablePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, ProviderInfo, HibernateProviderInfo> providerInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(ProviderInfo.class, HibernateProviderInfo.class, BeanMapper.class),
                HibernateProviderInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<ProviderInfo, HibernateProviderInfo> providerInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(ProviderInfo.class, HibernateProviderInfo.class, BeanMapper.class),
                HibernateProviderInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<ProviderInfo, HibernateProviderInfo> providerInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(ProviderInfo.class, HibernateProviderInfo.class, BeanMapper.class),
                HibernateProviderInfo.class,
                providerInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, ProviderSupport, HibernateProviderSupport> providerSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(ProviderSupport.class, HibernateProviderSupport.class, BeanMapper.class),
                HibernateProviderSupport.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<ProviderSupport, HibernateProviderSupport> providerSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(ProviderSupport.class, HibernateProviderSupport.class, BeanMapper.class),
                HibernateProviderSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<ProviderSupport, HibernateProviderSupport> providerSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(ProviderSupport.class, HibernateProviderSupport.class, BeanMapper.class),
                HibernateProviderSupport.class,
                providerSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, VisualizerInfo,
            HibernateVisualizerInfo> visualizerInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class
                ),
                new MapStructBeanTransformer<>(VisualizerInfo.class, HibernateVisualizerInfo.class, BeanMapper.class),
                HibernateVisualizerInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<VisualizerInfo, HibernateVisualizerInfo> visualizerInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(VisualizerInfo.class, HibernateVisualizerInfo.class, BeanMapper.class),
                HibernateVisualizerInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<VisualizerInfo, HibernateVisualizerInfo> visualizerInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(VisualizerInfo.class, HibernateVisualizerInfo.class, BeanMapper.class),
                HibernateVisualizerInfo.class,
                visualizerInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, VisualizerSupport, HibernateVisualizerSupport>
    visualizerSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        VisualizerSupport.class, HibernateVisualizerSupport.class, BeanMapper.class
                ),
                HibernateVisualizerSupport.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<VisualizerSupport, HibernateVisualizerSupport>
    visualizerSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        VisualizerSupport.class, HibernateVisualizerSupport.class, BeanMapper.class
                ),
                HibernateVisualizerSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<VisualizerSupport, HibernateVisualizerSupport>
    visualizerSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        VisualizerSupport.class, HibernateVisualizerSupport.class, BeanMapper.class
                ),
                HibernateVisualizerSupport.class,
                visualizerSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<VisualizeDataKey, HibernateVisualizeDataKey, VisualizeData,
            HibernateVisualizeData> visualizeDataHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        VisualizeDataKey.class, HibernateVisualizeDataKey.class, BeanMapper.class
                ),
                new MapStructBeanTransformer<>(VisualizeData.class, HibernateVisualizeData.class, BeanMapper.class),
                HibernateVisualizeData.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<VisualizeData, HibernateVisualizeData> visualizeDataHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(VisualizeData.class, HibernateVisualizeData.class, BeanMapper.class),
                HibernateVisualizeData.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<VisualizeData, HibernateVisualizeData> visualizeDataHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(VisualizeData.class, HibernateVisualizeData.class, BeanMapper.class),
                HibernateVisualizeData.class,
                visualizeDataPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, AdapterInfo, HibernateAdapterInfo> adapterInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(AdapterInfo.class, HibernateAdapterInfo.class, BeanMapper.class),
                HibernateAdapterInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<AdapterInfo, HibernateAdapterInfo> adapterInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(AdapterInfo.class, HibernateAdapterInfo.class, BeanMapper.class),
                HibernateAdapterInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<AdapterInfo, HibernateAdapterInfo> adapterInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(AdapterInfo.class, HibernateAdapterInfo.class, BeanMapper.class),
                HibernateAdapterInfo.class,
                adapterInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, AdapterSupport, HibernateAdapterSupport> adapterSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(AdapterSupport.class, HibernateAdapterSupport.class, BeanMapper.class),
                HibernateAdapterSupport.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<AdapterSupport, HibernateAdapterSupport> adapterSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(AdapterSupport.class, HibernateAdapterSupport.class, BeanMapper.class),
                HibernateAdapterSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<AdapterSupport, HibernateAdapterSupport> adapterSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(AdapterSupport.class, HibernateAdapterSupport.class, BeanMapper.class),
                HibernateAdapterSupport.class,
                adapterSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, FilterInfo, HibernateFilterInfo> filterInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(FilterInfo.class, HibernateFilterInfo.class, BeanMapper.class),
                HibernateFilterInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<FilterInfo, HibernateFilterInfo> filterInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(FilterInfo.class, HibernateFilterInfo.class, BeanMapper.class),
                HibernateFilterInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<FilterInfo, HibernateFilterInfo> filterInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(FilterInfo.class, HibernateFilterInfo.class, BeanMapper.class),
                HibernateFilterInfo.class,
                filterInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, FilterSupport, HibernateFilterSupport> filterSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(FilterSupport.class, HibernateFilterSupport.class, BeanMapper.class),
                HibernateFilterSupport.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<FilterSupport, HibernateFilterSupport> filterSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(FilterSupport.class, HibernateFilterSupport.class, BeanMapper.class),
                HibernateFilterSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<FilterSupport, HibernateFilterSupport> filterSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(FilterSupport.class, HibernateFilterSupport.class, BeanMapper.class),
                HibernateFilterSupport.class,
                filterSupportPresetCriteriaMaker
        );
    }
}
