package com.dwarfeng.judge.impl.configuration;

import com.dwarfeng.judge.impl.service.operation.DriverInfoCrudOperation;
import com.dwarfeng.judge.impl.service.operation.JudgerInfoCrudOperation;
import com.dwarfeng.judge.impl.service.operation.SectionCrudOperation;
import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.judge.stack.bean.key.VariableKey;
import com.dwarfeng.judge.stack.cache.DriverSupportCache;
import com.dwarfeng.judge.stack.cache.JudgerSupportCache;
import com.dwarfeng.judge.stack.cache.VariableCache;
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

    private final DriverInfoCrudOperation driverInfoCrudOperation;
    private final DriverInfoDao driverInfoDao;
    private final SectionCrudOperation sectionCrudOperation;
    private final SectionDao sectionDao;
    private final JudgerInfoCrudOperation judgerInfoCrudOperation;
    private final JudgerInfoDao judgerInfoDao;
    private final DriverSupportCache driverSupportCache;
    private final DriverSupportDao driverSupportDao;
    private final JudgerSupportCache judgerSupportCache;
    private final JudgerSupportDao judgerSupportDao;
    private final VariableDao variableDao;
    private final VariableCache variableCache;

    @Value("${cache.timeout.entity.driver_support}")
    private long driverSupportTimeout;
    @Value("${cache.timeout.entity.judger_support}")
    private long judgerSupportTimeout;
    @Value("${cache.timeout.entity.variable}")
    private long variableTimeout;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration,
            GenerateConfiguration generateConfiguration,
            DriverInfoCrudOperation driverInfoCrudOperation,
            VariableCache variableCache,
            DriverInfoDao driverInfoDao,
            SectionCrudOperation sectionCrudOperation,
            SectionDao sectionDao,
            JudgerInfoCrudOperation judgerInfoCrudOperation,
            JudgerInfoDao judgerInfoDao,
            VariableDao variableDao,
            DriverSupportCache driverSupportCache,
            DriverSupportDao driverSupportDao,
            JudgerSupportCache judgerSupportCache,
            JudgerSupportDao judgerSupportDao
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.generateConfiguration = generateConfiguration;
        this.driverInfoCrudOperation = driverInfoCrudOperation;
        this.variableCache = variableCache;
        this.driverInfoDao = driverInfoDao;
        this.sectionCrudOperation = sectionCrudOperation;
        this.sectionDao = sectionDao;
        this.judgerInfoCrudOperation = judgerInfoCrudOperation;
        this.judgerInfoDao = judgerInfoDao;
        this.variableDao = variableDao;
        this.driverSupportCache = driverSupportCache;
        this.driverSupportDao = driverSupportDao;
        this.judgerSupportCache = judgerSupportCache;
        this.judgerSupportDao = judgerSupportDao;
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, DriverInfo> driverInfoCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                driverInfoCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
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
    public CustomBatchCrudService<LongIdKey, JudgerInfo> judgerInfoCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                judgerInfoCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
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
    public DaoOnlyEntireLookupService<DriverInfo> driverInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                driverInfoDao
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
    public GeneralBatchCrudService<VariableKey, Variable> variableGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                variableDao,
                variableCache,
                new ExceptionKeyGenerator<>(),
                variableTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Variable> variableDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                variableDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Variable> variableDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                variableDao
        );
    }
}
