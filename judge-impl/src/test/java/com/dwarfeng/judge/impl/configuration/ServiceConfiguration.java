package com.dwarfeng.judge.impl.configuration;

import com.dwarfeng.judge.impl.service.operation.*;
import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.judge.stack.bean.key.*;
import com.dwarfeng.judge.stack.cache.*;
import com.dwarfeng.judge.stack.dao.*;
import com.dwarfeng.subgrade.impl.generation.ExceptionKeyGenerator;
import com.dwarfeng.subgrade.impl.service.CustomBatchCrudService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyEntireLookupService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyPresetLookupService;
import com.dwarfeng.subgrade.impl.service.GeneralBatchCrudService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    private final ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration;
    private final GenerateConfiguration generateConfiguration;

    private final AnalyserInfoCrudOperation analyserInfoCrudOperation;
    private final AnalyserInfoDao analyserInfoDao;
    private final AnalyserSupportDao analyserSupportDao;
    private final AnalyserSupportCache analyserSupportCache;
    private final AnalyserVariableDao analyserVariableDao;
    private final AnalyserVariableCache analyserVariableCache;
    private final AnalysisCrudOperation analysisCrudOperation;
    private final AnalysisDao analysisDao;
    private final DriverInfoDao driverInfoDao;
    private final DriverInfoCache driverInfoCache;
    private final DriverSupportDao driverSupportDao;
    private final DriverSupportCache driverSupportCache;
    private final JudgerInfoCrudOperation judgerInfoCrudOperation;
    private final JudgerInfoDao judgerInfoDao;
    private final JudgerSupportDao judgerSupportDao;
    private final JudgerSupportCache judgerSupportCache;
    private final JudgerVariableDao judgerVariableDao;
    private final JudgerVariableCache judgerVariableCache;
    private final SectionCrudOperation sectionCrudOperation;
    private final SectionDao sectionDao;
    private final TaskCrudOperation taskCrudOperation;
    private final TaskDao taskDao;
    private final TaskEventDao taskEventDao;
    private final TaskEventCache taskEventCache;
    private final AnalysisFileInfoCrudOperation analysisFileInfoCrudOperation;
    private final AnalysisFileInfoDao analysisFileInfoDao;
    private final AnalysisFilePackCrudOperation analysisFilePackCrudOperation;
    private final AnalysisFilePackDao analysisFilePackDao;
    private final AnalysisFilePackItemInfoCrudOperation analysisFilePackItemInfoCrudOperation;
    private final AnalysisFilePackItemInfoDao analysisFilePackItemInfoDao;
    private final AnalysisPictureInfoCrudOperation analysisPictureInfoCrudOperation;
    private final AnalysisPictureInfoDao analysisPictureInfoDao;
    private final AnalysisPicturePackCrudOperation analysisPicturePackCrudOperation;
    private final AnalysisPicturePackDao analysisPicturePackDao;
    private final AnalysisPicturePackItemInfoCrudOperation analysisPicturePackItemInfoCrudOperation;
    private final AnalysisPicturePackItemInfoDao analysisPicturePackItemInfoDao;
    private final JudgementCrudOperation judgementCrudOperation;
    private final JudgementDao judgementDao;
    private final SinkerInfoCrudOperation sinkerInfoCrudOperation;
    private final SinkerInfoDao sinkerInfoDao;
    private final SinkerMetaCache sinkerMetaCache;
    private final SinkerMetaDao sinkerMetaDao;
    private final SinkerMetaIndicatorCache sinkerMetaIndicatorCache;
    private final SinkerMetaIndicatorDao sinkerMetaIndicatorDao;
    private final SinkerRelationCache sinkerRelationCache;
    private final SinkerRelationDao sinkerRelationDao;
    private final SinkerSupportCache sinkerSupportCache;
    private final SinkerSupportDao sinkerSupportDao;
    private final SinkerVariableDao sinkerVariableDao;
    private final SinkerVariableCache sinkerVariableCache;
    private final ProviderInfoCache providerInfoCache;
    private final ProviderInfoDao providerInfoDao;
    private final ProviderSupportCache providerSupportCache;
    private final ProviderSupportDao providerSupportDao;
    private final VisualizerInfoDao visualizerInfoDao;
    private final VisualizerInfoCache visualizerInfoCache;
    private final VisualizerSupportDao visualizerSupportDao;
    private final VisualizerSupportCache visualizerSupportCache;
    private final VisualizeDataDao visualizeDataDao;
    private final VisualizeDataCache visualizeDataCache;

    @Value("${cache.timeout.entity.analyser_support}")
    private long analyserSupportTimeout;
    @Value("${cache.timeout.entity.analyser_variable}")
    private long analyserVariableTimeout;
    @Value("${cache.timeout.entity.driver_info}")
    private long driverInfoTimeout;
    @Value("${cache.timeout.entity.driver_support}")
    private long driverSupportTimeout;
    @Value("${cache.timeout.entity.judger_support}")
    private long judgerSupportTimeout;
    @Value("${cache.timeout.entity.judger_variable}")
    private long judgerVariableTimeout;
    @Value("${cache.timeout.entity.task_event}")
    private long taskEventTimeout;
    @Value("${cache.timeout.entity.sinker_meta}")
    private long sinkerMetaTimeout;
    @Value("${cache.timeout.entity.sinker_meta_indicator}")
    private long sinkerMetaIndicatorTimeout;
    @Value("${cache.timeout.entity.sinker_relation}")
    private long sinkerRelationTimeout;
    @Value("${cache.timeout.entity.sinker_support}")
    private long sinkerSupportTimeout;
    @Value("${cache.timeout.entity.sinker_variable}")
    private long sinkerVariableTimeout;
    @Value("${cache.timeout.entity.provider_info}")
    private long providerInfoTimeout;
    @Value("${cache.timeout.entity.provider_support}")
    private long providerSupportTimeout;
    @Value("${cache.timeout.entity.visualizer_info}")
    private long visualizerInfoTimeout;
    @Value("${cache.timeout.entity.visualizer_support}")
    private long visualizerSupportTimeout;
    @Value("${cache.timeout.entity.visualize_data}")
    private long visualizeDataTimeout;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration,
            GenerateConfiguration generateConfiguration,
            AnalyserInfoCrudOperation analyserInfoCrudOperation,
            AnalyserInfoDao analyserInfoDao,
            AnalyserSupportDao analyserSupportDao,
            AnalyserSupportCache analyserSupportCache,
            AnalyserVariableDao analyserVariableDao,
            AnalyserVariableCache analyserVariableCache,
            AnalysisCrudOperation analysisCrudOperation,
            AnalysisDao analysisDao,
            DriverInfoDao driverInfoDao,
            DriverInfoCache driverInfoCache,
            DriverSupportDao driverSupportDao,
            DriverSupportCache driverSupportCache,
            JudgerInfoCrudOperation judgerInfoCrudOperation,
            JudgerInfoDao judgerInfoDao,
            JudgerSupportDao judgerSupportDao,
            JudgerSupportCache judgerSupportCache,
            JudgerVariableDao judgerVariableDao,
            JudgerVariableCache judgerVariableCache,
            SectionCrudOperation sectionCrudOperation,
            SectionDao sectionDao,
            TaskCrudOperation taskCrudOperation,
            TaskDao taskDao,
            TaskEventDao taskEventDao,
            TaskEventCache taskEventCache,
            AnalysisFileInfoCrudOperation analysisFileInfoCrudOperation,
            AnalysisFileInfoDao analysisFileInfoDao,
            AnalysisFilePackCrudOperation analysisFilePackCrudOperation,
            AnalysisFilePackDao analysisFilePackDao,
            AnalysisFilePackItemInfoCrudOperation analysisFilePackItemInfoCrudOperation,
            AnalysisFilePackItemInfoDao analysisFilePackItemInfoDao,
            AnalysisPictureInfoCrudOperation analysisPictureInfoCrudOperation,
            AnalysisPictureInfoDao analysisPictureInfoDao,
            AnalysisPicturePackCrudOperation analysisPicturePackCrudOperation,
            AnalysisPicturePackDao analysisPicturePackDao,
            AnalysisPicturePackItemInfoCrudOperation analysisPicturePackItemInfoCrudOperation,
            AnalysisPicturePackItemInfoDao analysisPicturePackItemInfoDao,
            JudgementCrudOperation judgementCrudOperation,
            JudgementDao judgementDao,
            SinkerInfoCrudOperation sinkerInfoCrudOperation,
            SinkerInfoDao sinkerInfoDao,
            SinkerMetaCache sinkerMetaCache,
            SinkerMetaDao sinkerMetaDao,
            SinkerMetaIndicatorCache sinkerMetaIndicatorCache,
            SinkerMetaIndicatorDao sinkerMetaIndicatorDao,
            SinkerRelationCache sinkerRelationCache,
            SinkerRelationDao sinkerRelationDao,
            SinkerSupportCache sinkerSupportCache,
            SinkerSupportDao sinkerSupportDao,
            SinkerVariableDao sinkerVariableDao,
            SinkerVariableCache sinkerVariableCache,
            ProviderInfoCache providerInfoCache,
            ProviderInfoDao providerInfoDao,
            ProviderSupportCache providerSupportCache,
            ProviderSupportDao providerSupportDao,
            VisualizerInfoDao visualizerInfoDao,
            VisualizerInfoCache visualizerInfoCache,
            VisualizerSupportDao visualizerSupportDao,
            VisualizerSupportCache visualizerSupportCache,
            VisualizeDataDao visualizeDataDao,
            VisualizeDataCache visualizeDataCache
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.generateConfiguration = generateConfiguration;
        this.analyserInfoCrudOperation = analyserInfoCrudOperation;
        this.analyserInfoDao = analyserInfoDao;
        this.analyserSupportDao = analyserSupportDao;
        this.analyserSupportCache = analyserSupportCache;
        this.analyserVariableDao = analyserVariableDao;
        this.analyserVariableCache = analyserVariableCache;
        this.analysisCrudOperation = analysisCrudOperation;
        this.analysisDao = analysisDao;
        this.driverInfoDao = driverInfoDao;
        this.driverInfoCache = driverInfoCache;
        this.driverSupportDao = driverSupportDao;
        this.driverSupportCache = driverSupportCache;
        this.judgerInfoCrudOperation = judgerInfoCrudOperation;
        this.judgerInfoDao = judgerInfoDao;
        this.judgerSupportDao = judgerSupportDao;
        this.judgerSupportCache = judgerSupportCache;
        this.judgerVariableDao = judgerVariableDao;
        this.judgerVariableCache = judgerVariableCache;
        this.sectionCrudOperation = sectionCrudOperation;
        this.sectionDao = sectionDao;
        this.taskCrudOperation = taskCrudOperation;
        this.taskDao = taskDao;
        this.taskEventDao = taskEventDao;
        this.taskEventCache = taskEventCache;
        this.analysisFileInfoCrudOperation = analysisFileInfoCrudOperation;
        this.analysisFileInfoDao = analysisFileInfoDao;
        this.analysisFilePackCrudOperation = analysisFilePackCrudOperation;
        this.analysisFilePackDao = analysisFilePackDao;
        this.analysisFilePackItemInfoCrudOperation = analysisFilePackItemInfoCrudOperation;
        this.analysisFilePackItemInfoDao = analysisFilePackItemInfoDao;
        this.analysisPictureInfoCrudOperation = analysisPictureInfoCrudOperation;
        this.analysisPictureInfoDao = analysisPictureInfoDao;
        this.analysisPicturePackCrudOperation = analysisPicturePackCrudOperation;
        this.analysisPicturePackDao = analysisPicturePackDao;
        this.analysisPicturePackItemInfoCrudOperation = analysisPicturePackItemInfoCrudOperation;
        this.analysisPicturePackItemInfoDao = analysisPicturePackItemInfoDao;
        this.judgementCrudOperation = judgementCrudOperation;
        this.judgementDao = judgementDao;
        this.sinkerInfoCrudOperation = sinkerInfoCrudOperation;
        this.sinkerInfoDao = sinkerInfoDao;
        this.sinkerMetaCache = sinkerMetaCache;
        this.sinkerMetaDao = sinkerMetaDao;
        this.sinkerMetaIndicatorCache = sinkerMetaIndicatorCache;
        this.sinkerMetaIndicatorDao = sinkerMetaIndicatorDao;
        this.sinkerRelationCache = sinkerRelationCache;
        this.sinkerRelationDao = sinkerRelationDao;
        this.sinkerSupportCache = sinkerSupportCache;
        this.sinkerSupportDao = sinkerSupportDao;
        this.sinkerVariableDao = sinkerVariableDao;
        this.sinkerVariableCache = sinkerVariableCache;
        this.providerInfoCache = providerInfoCache;
        this.providerInfoDao = providerInfoDao;
        this.providerSupportCache = providerSupportCache;
        this.providerSupportDao = providerSupportDao;
        this.visualizerInfoDao = visualizerInfoDao;
        this.visualizerInfoCache = visualizerInfoCache;
        this.visualizerSupportDao = visualizerSupportDao;
        this.visualizerSupportCache = visualizerSupportCache;
        this.visualizeDataDao = visualizeDataDao;
        this.visualizeDataCache = visualizeDataCache;
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, AnalyserInfo> analyserInfoCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analyserInfoCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<AnalyserInfo> analyserInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analyserInfoDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<AnalyserInfo> analyserInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analyserInfoDao
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, AnalyserSupport> analyserSupportGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analyserSupportDao,
                analyserSupportCache,
                new ExceptionKeyGenerator<>(),
                analyserSupportTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<AnalyserSupport> analyserSupportDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analyserSupportDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<AnalyserSupport> analyserSupportDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analyserSupportDao
        );
    }

    @Bean
    public GeneralBatchCrudService<AnalyserVariableKey, AnalyserVariable> analyserVariableGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analyserVariableDao,
                analyserVariableCache,
                new ExceptionKeyGenerator<>(),
                analyserVariableTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<AnalyserVariable> analyserVariableDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analyserVariableDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<AnalyserVariable> analyserVariableDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analyserVariableDao
        );
    }

    @Bean
    public CustomBatchCrudService<AnalysisKey, Analysis> analysisCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisCrudOperation,
                new ExceptionKeyGenerator<>()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Analysis> analysisDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Analysis> analysisDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisDao
        );
    }

    @Bean
    public GeneralBatchCrudService<LongIdKey, DriverInfo> driverInfoGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                driverInfoDao,
                driverInfoCache,
                generateConfiguration.snowflakeLongIdKeyGenerator(),
                driverInfoTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<DriverInfo> driverInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                driverInfoDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<DriverInfo> driverInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                driverInfoDao
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, DriverSupport> driverSupportGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                driverSupportDao,
                driverSupportCache,
                new ExceptionKeyGenerator<>(),
                driverSupportTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<DriverSupport> driverSupportDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                driverSupportDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<DriverSupport> driverSupportDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                driverSupportDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, JudgerInfo> judgerInfoCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                judgerInfoCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<JudgerInfo> judgerInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                judgerInfoDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<JudgerInfo> judgerInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                judgerInfoDao
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, JudgerSupport> judgerSupportGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                judgerSupportDao,
                judgerSupportCache,
                new ExceptionKeyGenerator<>(),
                judgerSupportTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<JudgerSupport> judgerSupportDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                judgerSupportDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<JudgerSupport> judgerSupportDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                judgerSupportDao
        );
    }

    @Bean
    public GeneralBatchCrudService<JudgerVariableKey, JudgerVariable> judgerVariableGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                judgerVariableDao,
                judgerVariableCache,
                new ExceptionKeyGenerator<>(),
                judgerVariableTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<JudgerVariable> judgerVariableDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                judgerVariableDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<JudgerVariable> judgerVariableDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                judgerVariableDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, Task> taskCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                taskCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Task> taskDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                taskDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Task> taskDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                taskDao
        );
    }

    @Bean
    public GeneralBatchCrudService<LongIdKey, TaskEvent> taskEventGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                taskEventDao,
                taskEventCache,
                generateConfiguration.snowflakeLongIdKeyGenerator(),
                taskEventTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<TaskEvent> taskEventDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                taskEventDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<TaskEvent> taskEventDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                taskEventDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, Section> sectionCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sectionCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Section> sectionDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sectionDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Section> sectionDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sectionDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, AnalysisFileInfo> analysisFileInfoCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisFileInfoCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<AnalysisFileInfo> analysisFileInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisFileInfoDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<AnalysisFileInfo> analysisFileInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisFileInfoDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, AnalysisFilePack> analysisFilePackCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisFilePackCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<AnalysisFilePack> analysisFilePackDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisFilePackDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<AnalysisFilePack> analysisFilePackDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisFilePackDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, AnalysisFilePackItemInfo>
    analysisFilePackItemInfoCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisFilePackItemInfoCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<AnalysisFilePackItemInfo> analysisFilePackItemInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisFilePackItemInfoDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<AnalysisFilePackItemInfo> analysisFilePackItemInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisFilePackItemInfoDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, AnalysisPictureInfo> analysisPictureInfoCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisPictureInfoCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<AnalysisPictureInfo> analysisPictureInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisPictureInfoDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<AnalysisPictureInfo> analysisPictureInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisPictureInfoDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, AnalysisPicturePack> analysisPicturePackCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisPicturePackCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<AnalysisPicturePack> analysisPicturePackDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisPicturePackDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<AnalysisPicturePack> analysisPicturePackDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisPicturePackDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, AnalysisPicturePackItemInfo>
    analysisPicturePackItemInfoCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisPicturePackItemInfoCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<AnalysisPicturePackItemInfo>
    analysisPicturePackItemInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisPicturePackItemInfoDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<AnalysisPicturePackItemInfo>
    analysisPicturePackItemInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                analysisPicturePackItemInfoDao
        );
    }

    @Bean
    public CustomBatchCrudService<JudgementKey, Judgement> judgementCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                judgementCrudOperation,
                new ExceptionKeyGenerator<>()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Judgement> judgementDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                judgementDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Judgement> judgementDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                judgementDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, SinkerInfo> sinkerInfoBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sinkerInfoCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<SinkerInfo> sinkerInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sinkerInfoDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<SinkerInfo> sinkerInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sinkerInfoDao
        );
    }

    @Bean
    public GeneralBatchCrudService<SinkerMetaKey, SinkerMeta> sinkerMetaGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sinkerMetaDao,
                sinkerMetaCache,
                new ExceptionKeyGenerator<>(),
                sinkerMetaTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<SinkerMeta> sinkerMetaDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sinkerMetaDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<SinkerMeta> sinkerMetaDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sinkerMetaDao
        );
    }

    @Bean
    public GeneralBatchCrudService<SinkerMetaIndicatorKey, SinkerMetaIndicator>
    sinkerMetaIndicatorGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sinkerMetaIndicatorDao,
                sinkerMetaIndicatorCache,
                new ExceptionKeyGenerator<>(),
                sinkerMetaIndicatorTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<SinkerMetaIndicator> sinkerMetaIndicatorDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sinkerMetaIndicatorDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<SinkerMetaIndicator> sinkerMetaIndicatorDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sinkerMetaIndicatorDao
        );
    }

    @Bean
    public GeneralBatchCrudService<SinkerRelationKey, SinkerRelation> sinkerRelationGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sinkerRelationDao,
                sinkerRelationCache,
                new ExceptionKeyGenerator<>(),
                sinkerRelationTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<SinkerRelation> sinkerRelationDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sinkerRelationDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<SinkerRelation> sinkerRelationDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sinkerRelationDao
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, SinkerSupport> sinkerSupportGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sinkerSupportDao,
                sinkerSupportCache,
                new ExceptionKeyGenerator<>(),
                sinkerSupportTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<SinkerSupport> sinkerSupportDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sinkerSupportDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<SinkerSupport> sinkerSupportDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sinkerSupportDao
        );
    }

    @Bean
    public GeneralBatchCrudService<SinkerVariableKey, SinkerVariable> sinkerVariableBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sinkerVariableDao,
                sinkerVariableCache,
                new ExceptionKeyGenerator<>(),
                sinkerVariableTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<SinkerVariable> sinkerVariableDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sinkerVariableDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<SinkerVariable> sinkerVariableDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sinkerVariableDao
        );
    }

    @Bean
    public GeneralBatchCrudService<LongIdKey, ProviderInfo> providerInfoGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                providerInfoDao,
                providerInfoCache,
                generateConfiguration.snowflakeLongIdKeyGenerator(),
                providerInfoTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<ProviderInfo> providerInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                providerInfoDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<ProviderInfo> providerInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                providerInfoDao
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, ProviderSupport> providerSupportGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                providerSupportDao,
                providerSupportCache,
                new ExceptionKeyGenerator<>(),
                providerSupportTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<ProviderSupport> providerSupportDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                providerSupportDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<ProviderSupport> providerSupportDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                providerSupportDao
        );
    }

    @Bean
    public GeneralBatchCrudService<LongIdKey, VisualizerInfo> visualizerInfoGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                visualizerInfoDao,
                visualizerInfoCache,
                generateConfiguration.snowflakeLongIdKeyGenerator(),
                visualizerInfoTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<VisualizerInfo> visualizerInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                visualizerInfoDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<VisualizerInfo> visualizerInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                visualizerInfoDao
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, VisualizerSupport> visualizerSupportGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                visualizerSupportDao,
                visualizerSupportCache,
                new ExceptionKeyGenerator<>(),
                visualizerSupportTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<VisualizerSupport> visualizerSupportDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                visualizerSupportDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<VisualizerSupport> visualizerSupportDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                visualizerSupportDao
        );
    }

    @Bean
    public GeneralBatchCrudService<VisualizeDataKey, VisualizeData> visualizeDataGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                visualizeDataDao,
                visualizeDataCache,
                new ExceptionKeyGenerator<>(),
                visualizeDataTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<VisualizeData> visualizeDataDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                visualizeDataDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<VisualizeData> visualizeDataDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                visualizeDataDao
        );
    }
}
