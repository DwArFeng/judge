package com.dwarfeng.judge.node.configuration;

import com.dwarfeng.judge.impl.service.operation.*;
import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.judge.stack.bean.key.AnalyserVariableKey;
import com.dwarfeng.judge.stack.bean.key.AnalysisKey;
import com.dwarfeng.judge.stack.bean.key.JudgerVariableKey;
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

    private final AlarmHistoryDao alarmHistoryDao;
    private final AlarmHistoryCache alarmHistoryCache;
    private final AlarmModalDao alarmModalDao;
    private final AlarmModalCache alarmModalCache;
    private final AlarmSettingDao alarmSettingDao;
    private final AlarmSettingCache alarmSettingCache;
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
    private final JudgementHistoryDao judgementHistoryDao;
    private final JudgementHistoryCache judgementHistoryCache;
    private final JudgementModalDao judgementModalDao;
    private final JudgementModalCache judgementModalCache;
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
    private final AlarmLevelIndicatorDao alarmLevelIndicatorDao;
    private final AlarmLevelIndicatorCache alarmLevelIndicatorCache;

    @Value("${cache.timeout.entity.alarm_history}")
    private long alarmHistoryTimeout;
    @Value("${cache.timeout.entity.alarm_modal}")
    private long alarmModalTimeout;
    @Value("${cache.timeout.entity.alarm_setting}")
    private long alarmSettingTimeout;
    @Value("${cache.timeout.entity.analyser_support}")
    private long analyserSupportTimeout;
    @Value("${cache.timeout.entity.analyser_variable}")
    private long analyserVariableTimeout;
    @Value("${cache.timeout.entity.driver_info}")
    private long driverInfoTimeout;
    @Value("${cache.timeout.entity.driver_support}")
    private long driverSupportTimeout;
    @Value("${cache.timeout.entity.judgement_history}")
    private long judgementHistoryTimeout;
    @Value("${cache.timeout.entity.judgement_modal}")
    private long judgementModalTimeout;
    @Value("${cache.timeout.entity.judger_support}")
    private long judgerSupportTimeout;
    @Value("${cache.timeout.entity.judger_variable}")
    private long judgerVariableTimeout;
    @Value("${cache.timeout.entity.task_event}")
    private long taskEventTimeout;
    @Value("${cache.timeout.entity.alarm_level_indicator}")
    private long alarmLevelIndicatorTimeout;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration,
            GenerateConfiguration generateConfiguration,
            AlarmHistoryDao alarmHistoryDao,
            AlarmHistoryCache alarmHistoryCache,
            AlarmModalDao alarmModalDao,
            AlarmModalCache alarmModalCache,
            AlarmSettingDao alarmSettingDao,
            AlarmSettingCache alarmSettingCache,
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
            JudgementHistoryDao judgementHistoryDao,
            JudgementHistoryCache judgementHistoryCache,
            JudgementModalDao judgementModalDao,
            JudgementModalCache judgementModalCache,
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
            AlarmLevelIndicatorDao alarmLevelIndicatorDao,
            AlarmLevelIndicatorCache alarmLevelIndicatorCache
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.generateConfiguration = generateConfiguration;
        this.alarmHistoryDao = alarmHistoryDao;
        this.alarmHistoryCache = alarmHistoryCache;
        this.alarmModalDao = alarmModalDao;
        this.alarmModalCache = alarmModalCache;
        this.alarmSettingDao = alarmSettingDao;
        this.alarmSettingCache = alarmSettingCache;
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
        this.judgementHistoryDao = judgementHistoryDao;
        this.judgementHistoryCache = judgementHistoryCache;
        this.judgementModalDao = judgementModalDao;
        this.judgementModalCache = judgementModalCache;
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
        this.alarmLevelIndicatorDao = alarmLevelIndicatorDao;
        this.alarmLevelIndicatorCache = alarmLevelIndicatorCache;
    }

    @Bean
    public GeneralBatchCrudService<LongIdKey, AlarmHistory> alarmHistoryGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                alarmHistoryDao,
                alarmHistoryCache,
                generateConfiguration.snowflakeLongIdKeyGenerator(),
                alarmHistoryTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<AlarmHistory> alarmHistoryDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                alarmHistoryDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<AlarmHistory> alarmHistoryDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                alarmHistoryDao
        );
    }

    @Bean
    public GeneralBatchCrudService<LongIdKey, AlarmModal> alarmModalGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                alarmModalDao,
                alarmModalCache,
                generateConfiguration.snowflakeLongIdKeyGenerator(),
                alarmModalTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<AlarmModal> alarmModalDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                alarmModalDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<AlarmModal> alarmModalDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                alarmModalDao
        );
    }

    @Bean
    public GeneralBatchCrudService<LongIdKey, AlarmSetting> alarmSettingGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                alarmSettingDao,
                alarmSettingCache,
                generateConfiguration.snowflakeLongIdKeyGenerator(),
                alarmSettingTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<AlarmSetting> alarmSettingDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                alarmSettingDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<AlarmSetting> alarmSettingDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                alarmSettingDao
        );
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
    public GeneralBatchCrudService<LongIdKey, JudgementHistory> judgementHistoryGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                judgementHistoryDao,
                judgementHistoryCache,
                generateConfiguration.snowflakeLongIdKeyGenerator(),
                judgementHistoryTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<JudgementHistory> judgementHistoryDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                judgementHistoryDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<JudgementHistory> judgementHistoryDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                judgementHistoryDao
        );
    }

    @Bean
    public GeneralBatchCrudService<LongIdKey, JudgementModal> judgementModalGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                judgementModalDao,
                judgementModalCache,
                generateConfiguration.snowflakeLongIdKeyGenerator(),
                judgementModalTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<JudgementModal> judgementModalDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                judgementModalDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<JudgementModal> judgementModalDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                judgementModalDao
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
    public GeneralBatchCrudService<StringIdKey, AlarmLevelIndicator> alarmLevelIndicatorGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                alarmLevelIndicatorDao,
                alarmLevelIndicatorCache,
                new ExceptionKeyGenerator<>(),
                alarmLevelIndicatorTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<AlarmLevelIndicator> alarmLevelIndicatorDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                alarmLevelIndicatorDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<AlarmLevelIndicator> alarmLevelIndicatorDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                alarmLevelIndicatorDao
        );
    }
}
