package com.dwarfeng.judge.impl.configuration;

import com.dwarfeng.judge.impl.bean.BeanMapper;
import com.dwarfeng.judge.impl.bean.entity.*;
import com.dwarfeng.judge.impl.bean.key.HibernateAnalyserVariableKey;
import com.dwarfeng.judge.impl.bean.key.HibernateAnalysisKey;
import com.dwarfeng.judge.impl.bean.key.HibernateJudgerVariableKey;
import com.dwarfeng.judge.impl.dao.preset.*;
import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.judge.stack.bean.key.AnalyserVariableKey;
import com.dwarfeng.judge.stack.bean.key.AnalysisKey;
import com.dwarfeng.judge.stack.bean.key.JudgerVariableKey;
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
    private final JudgementHistoryPresetCriteriaMaker judgementHistoryPresetCriteriaMaker;
    private final JudgementModalPresetCriteriaMaker judgementModalPresetCriteriaMaker;
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
            JudgementHistoryPresetCriteriaMaker judgementHistoryPresetCriteriaMaker,
            JudgementModalPresetCriteriaMaker judgementModalPresetCriteriaMaker,
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
            AnalysisPicturePackItemInfoPresetCriteriaMaker analysisPicturePackItemInfoPresetCriteriaMaker
    ) {
        this.hibernateTemplate = hibernateTemplate;
        this.analyserInfoPresetCriteriaMaker = analyserInfoPresetCriteriaMaker;
        this.analyserSupportPresetCriteriaMaker = analyserSupportPresetCriteriaMaker;
        this.analyserVariablePresetCriteriaMaker = analyserVariablePresetCriteriaMaker;
        this.analysisPresetCriteriaMaker = analysisPresetCriteriaMaker;
        this.driverInfoPresetCriteriaMaker = driverInfoPresetCriteriaMaker;
        this.driverSupportPresetCriteriaMaker = driverSupportPresetCriteriaMaker;
        this.judgementHistoryPresetCriteriaMaker = judgementHistoryPresetCriteriaMaker;
        this.judgementModalPresetCriteriaMaker = judgementModalPresetCriteriaMaker;
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
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, JudgementHistory, HibernateJudgementHistory>
    judgementHistoryHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(
                        JudgementHistory.class, HibernateJudgementHistory.class, BeanMapper.class
                ),
                HibernateJudgementHistory.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<JudgementHistory, HibernateJudgementHistory>
    judgementHistoryHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        JudgementHistory.class, HibernateJudgementHistory.class, BeanMapper.class
                ),
                HibernateJudgementHistory.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<JudgementHistory, HibernateJudgementHistory>
    judgementHistoryHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        JudgementHistory.class, HibernateJudgementHistory.class, BeanMapper.class
                ),
                HibernateJudgementHistory.class,
                judgementHistoryPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, JudgementModal, HibernateJudgementModal>
    judgementModalHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, BeanMapper.class),
                new MapStructBeanTransformer<>(JudgementModal.class, HibernateJudgementModal.class, BeanMapper.class),
                HibernateJudgementModal.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<JudgementModal, HibernateJudgementModal> judgementModalHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(JudgementModal.class, HibernateJudgementModal.class, BeanMapper.class),
                HibernateJudgementModal.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<JudgementModal, HibernateJudgementModal> judgementModalHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(JudgementModal.class, HibernateJudgementModal.class, BeanMapper.class),
                HibernateJudgementModal.class,
                judgementModalPresetCriteriaMaker
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
}
