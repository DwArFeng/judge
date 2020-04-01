package com.dwarfeng.judge.node.maintain.configuration;

import com.dwarfeng.judge.impl.bean.entity.*;
import com.dwarfeng.judge.impl.dao.preset.*;
import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.subgrade.impl.bean.DozerBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DefaultDeletionMod;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    @Autowired
    private ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration;

    @Autowired
    private HibernateTemplate hibernateTemplate;
    @Autowired
    private Mapper mapper;

    @Autowired
    private DriverInfoPresetCriteriaMaker driverInfoPresetCriteriaMaker;
    @Autowired
    private SectionPresetCriteriaMaker sectionPresetCriteriaMaker;
    @Autowired
    private JudgerInfoPresetCriteriaMaker judgerInfoPresetCriteriaMaker;
    @Autowired
    private DriverSupportPresetCriteriaMaker driverSupportPresetCriteriaMaker;
    @Autowired
    private JudgerSupportPresetCriteriaMaker judgerSupportPresetCriteriaMaker;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, DriverInfo, HibernateDriverInfo> driverInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(DriverInfo.class, HibernateDriverInfo.class, mapper),
                HibernateDriverInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernatePresetLookupDao<DriverInfo, HibernateDriverInfo> driverInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(DriverInfo.class, HibernateDriverInfo.class, mapper),
                HibernateDriverInfo.class,
                driverInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, Section, HibernateSection> sectionHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(Section.class, HibernateSection.class, mapper),
                HibernateSection.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Section, HibernateSection> sectionHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(Section.class, HibernateSection.class, mapper),
                HibernateSection.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Section, HibernateSection> sectionHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(Section.class, HibernateSection.class, mapper),
                HibernateSection.class,
                sectionPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, JudgerInfo, HibernateJudgerInfo> judgerInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, mapper),
                new DozerBeanTransformer<>(JudgerInfo.class, HibernateJudgerInfo.class, mapper),
                HibernateJudgerInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernatePresetLookupDao<JudgerInfo, HibernateJudgerInfo> judgerInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(JudgerInfo.class, HibernateJudgerInfo.class, mapper),
                HibernateJudgerInfo.class,
                judgerInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateEntireLookupDao<DriverInfo, HibernateDriverInfo> driverInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(DriverInfo.class, HibernateDriverInfo.class, mapper),
                HibernateDriverInfo.class
        );
    }

    @Bean
    public HibernateEntireLookupDao<JudgerInfo, HibernateJudgerInfo> judgerInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(JudgerInfo.class, HibernateJudgerInfo.class, mapper),
                HibernateJudgerInfo.class
        );
    }

    @Bean
    public HibernateBaseDao<StringIdKey, HibernateStringIdKey, DriverSupport, HibernateDriverSupport> driverSupportHibernateBaseDao() {
        return new HibernateBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(DriverSupport.class, HibernateDriverSupport.class, mapper),
                HibernateDriverSupport.class
        );
    }

    @Bean
    public HibernateEntireLookupDao<DriverSupport, HibernateDriverSupport> driverSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(DriverSupport.class, HibernateDriverSupport.class, mapper),
                HibernateDriverSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<DriverSupport, HibernateDriverSupport> driverSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(DriverSupport.class, HibernateDriverSupport.class, mapper),
                HibernateDriverSupport.class,
                driverSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBaseDao<StringIdKey, HibernateStringIdKey, JudgerSupport, HibernateJudgerSupport> judgerSupportHibernateBaseDao() {
        return new HibernateBaseDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, mapper),
                new DozerBeanTransformer<>(JudgerSupport.class, HibernateJudgerSupport.class, mapper),
                HibernateJudgerSupport.class
        );
    }

    @Bean
    public HibernateEntireLookupDao<JudgerSupport, HibernateJudgerSupport> judgerSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(JudgerSupport.class, HibernateJudgerSupport.class, mapper),
                HibernateJudgerSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<JudgerSupport, HibernateJudgerSupport> judgerSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new DozerBeanTransformer<>(JudgerSupport.class, HibernateJudgerSupport.class, mapper),
                HibernateJudgerSupport.class,
                judgerSupportPresetCriteriaMaker
        );
    }
}
