package com.dwarfeng.judge.impl.configuration;

import com.dwarfeng.judge.impl.service.operation.DriverInfoCrudOperation;
import com.dwarfeng.judge.impl.service.operation.JudgerInfoCrudOperation;
import com.dwarfeng.judge.impl.service.operation.SectionCrudOperation;
import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.judge.stack.cache.DriverSupportCache;
import com.dwarfeng.judge.stack.cache.JudgerSupportCache;
import com.dwarfeng.judge.stack.dao.*;
import com.dwarfeng.sfds.api.integration.subgrade.SnowFlakeLongIdKeyFetcher;
import com.dwarfeng.subgrade.impl.bean.key.ExceptionKeyFetcher;
import com.dwarfeng.subgrade.impl.service.CustomBatchCrudService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyEntireLookupService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyPresetLookupService;
import com.dwarfeng.subgrade.impl.service.GeneralCrudService;
import com.dwarfeng.subgrade.stack.bean.key.KeyFetcher;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    @Autowired
    private ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration;

    @Bean
    public KeyFetcher<LongIdKey> longIdKeyKeyFetcher() {
        return new SnowFlakeLongIdKeyFetcher();
    }

    @Autowired
    private DriverInfoCrudOperation driverInfoCrudOperation;
    @Autowired
    private DriverInfoDao driverInfoDao;
    @Autowired
    private SectionCrudOperation sectionCrudOperation;
    @Autowired
    private SectionDao sectionDao;
    @Autowired
    private JudgerInfoCrudOperation judgerInfoCrudOperation;
    @Autowired
    private JudgerInfoDao judgerInfoDao;
    @Autowired
    private DriverSupportCache driverSupportCache;
    @Autowired
    private DriverSupportDao driverSupportDao;
    @Autowired
    private JudgerSupportCache judgerSupportCache;
    @Autowired
    private JudgerSupportDao judgerSupportDao;

    @Value("${cache.timeout.entity.driver_support}")
    private long driverSupportTimeout;
    @Value("${cache.timeout.entity.judger_support}")
    private long judgerSupportTimeout;

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
}
