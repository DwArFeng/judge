package com.dwarfeng.judge.node.configuration;

import com.dwarfeng.judge.impl.service.operation.DriverInfoCrudOperation;
import com.dwarfeng.judge.impl.service.operation.JudgerInfoCrudOperation;
import com.dwarfeng.judge.impl.service.operation.SectionCrudOperation;
import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.judge.stack.bean.key.VariableKey;
import com.dwarfeng.judge.stack.cache.DriverSupportCache;
import com.dwarfeng.judge.stack.cache.JudgerSupportCache;
import com.dwarfeng.judge.stack.cache.VariableCache;
import com.dwarfeng.judge.stack.dao.*;
import com.dwarfeng.sfds.api.integration.subgrade.SnowFlakeLongIdKeyFetcher;
import com.dwarfeng.subgrade.impl.bean.key.ExceptionKeyFetcher;
import com.dwarfeng.subgrade.impl.service.*;
import com.dwarfeng.subgrade.stack.bean.key.KeyFetcher;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    private final ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration;

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
    public KeyFetcher<LongIdKey> longIdKeyKeyFetcher() {
        return new SnowFlakeLongIdKeyFetcher();
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, DriverInfo> driverInfoCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                driverInfoCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<DriverInfo> driverInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                driverInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, Section> sectionCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                sectionCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Section> sectionDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                sectionDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Section> sectionDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                sectionDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, JudgerInfo> judgerInfoCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                judgerInfoCrudOperation,
                longIdKeyKeyFetcher(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<JudgerInfo> judgerInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                judgerInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<DriverInfo> driverInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                driverInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<JudgerInfo> judgerInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                judgerInfoDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralCrudService<StringIdKey, DriverSupport> driverSupportGeneralCrudService() {
        return new GeneralCrudService<>(
                driverSupportDao,
                driverSupportCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                driverSupportTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<DriverSupport> driverSupportDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                driverSupportDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<DriverSupport> driverSupportDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                driverSupportDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralCrudService<StringIdKey, JudgerSupport> judgerSupportGeneralCrudService() {
        return new GeneralCrudService<>(
                judgerSupportDao,
                judgerSupportCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                judgerSupportTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<JudgerSupport> judgerSupportDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                judgerSupportDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<JudgerSupport> judgerSupportDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                judgerSupportDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public GeneralBatchCrudService<VariableKey, Variable> variableGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                variableDao,
                variableCache,
                new ExceptionKeyFetcher<>(),
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                variableTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Variable> variableDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                variableDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Variable> variableDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                variableDao,
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN
        );
    }
}
