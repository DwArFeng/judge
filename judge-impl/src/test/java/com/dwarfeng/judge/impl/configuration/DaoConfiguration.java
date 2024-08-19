package com.dwarfeng.judge.impl.configuration;

import com.dwarfeng.judge.impl.bean.HibernateMapper;
import com.dwarfeng.judge.impl.bean.entity.*;
import com.dwarfeng.judge.impl.bean.key.HibernateVariableKey;
import com.dwarfeng.judge.impl.dao.preset.*;
import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.judge.stack.bean.key.VariableKey;
import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBaseDao;
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

    private final DriverInfoPresetCriteriaMaker driverInfoPresetCriteriaMaker;
    private final SectionPresetCriteriaMaker sectionPresetCriteriaMaker;
    private final JudgerInfoPresetCriteriaMaker judgerInfoPresetCriteriaMaker;
    private final DriverSupportPresetCriteriaMaker driverSupportPresetCriteriaMaker;
    private final JudgerSupportPresetCriteriaMaker judgerSupportPresetCriteriaMaker;
    private final VariablePresetCriteriaMaker variablePresetCriteriaMaker;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate hibernateTemplate,
            DriverInfoPresetCriteriaMaker driverInfoPresetCriteriaMaker,
            SectionPresetCriteriaMaker sectionPresetCriteriaMaker,
            JudgerInfoPresetCriteriaMaker judgerInfoPresetCriteriaMaker,
            DriverSupportPresetCriteriaMaker driverSupportPresetCriteriaMaker,
            JudgerSupportPresetCriteriaMaker judgerSupportPresetCriteriaMaker,
            VariablePresetCriteriaMaker variablePresetCriteriaMaker
    ) {
        this.hibernateTemplate = hibernateTemplate;
        this.driverInfoPresetCriteriaMaker = driverInfoPresetCriteriaMaker;
        this.sectionPresetCriteriaMaker = sectionPresetCriteriaMaker;
        this.judgerInfoPresetCriteriaMaker = judgerInfoPresetCriteriaMaker;
        this.driverSupportPresetCriteriaMaker = driverSupportPresetCriteriaMaker;
        this.judgerSupportPresetCriteriaMaker = judgerSupportPresetCriteriaMaker;
        this.variablePresetCriteriaMaker = variablePresetCriteriaMaker;
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, DriverInfo, HibernateDriverInfo>
    driverInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(DriverInfo.class, HibernateDriverInfo.class, HibernateMapper.class),
                HibernateDriverInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernatePresetLookupDao<DriverInfo, HibernateDriverInfo> driverInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(DriverInfo.class, HibernateDriverInfo.class, HibernateMapper.class),
                HibernateDriverInfo.class,
                driverInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, Section, HibernateSection>
    sectionHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Section.class, HibernateSection.class, HibernateMapper.class),
                HibernateSection.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Section, HibernateSection> sectionHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(Section.class, HibernateSection.class, HibernateMapper.class),
                HibernateSection.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Section, HibernateSection> sectionHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(Section.class, HibernateSection.class, HibernateMapper.class),
                HibernateSection.class,
                sectionPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, JudgerInfo, HibernateJudgerInfo>
    judgerInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(JudgerInfo.class, HibernateJudgerInfo.class, HibernateMapper.class),
                HibernateJudgerInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernatePresetLookupDao<JudgerInfo, HibernateJudgerInfo> judgerInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(JudgerInfo.class, HibernateJudgerInfo.class, HibernateMapper.class),
                HibernateJudgerInfo.class,
                judgerInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateEntireLookupDao<DriverInfo, HibernateDriverInfo> driverInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(DriverInfo.class, HibernateDriverInfo.class, HibernateMapper.class),
                HibernateDriverInfo.class
        );
    }

    @Bean
    public HibernateEntireLookupDao<JudgerInfo, HibernateJudgerInfo> judgerInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(JudgerInfo.class, HibernateJudgerInfo.class, HibernateMapper.class),
                HibernateJudgerInfo.class
        );
    }

    @Bean
    public HibernateBaseDao<StringIdKey, HibernateStringIdKey, DriverSupport, HibernateDriverSupport>
    driverSupportHibernateBaseDao() {
        return new HibernateBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(
                        DriverSupport.class, HibernateDriverSupport.class, HibernateMapper.class
                ),
                HibernateDriverSupport.class
        );
    }

    @Bean
    public HibernateEntireLookupDao<DriverSupport, HibernateDriverSupport> driverSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        DriverSupport.class, HibernateDriverSupport.class, HibernateMapper.class
                ),
                HibernateDriverSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<DriverSupport, HibernateDriverSupport> driverSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        DriverSupport.class, HibernateDriverSupport.class, HibernateMapper.class
                ),
                HibernateDriverSupport.class,
                driverSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBaseDao<StringIdKey, HibernateStringIdKey, JudgerSupport, HibernateJudgerSupport>
    judgerSupportHibernateBaseDao() {
        return new HibernateBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(
                        JudgerSupport.class, HibernateJudgerSupport.class, HibernateMapper.class
                ),
                HibernateJudgerSupport.class
        );
    }

    @Bean
    public HibernateEntireLookupDao<JudgerSupport, HibernateJudgerSupport> judgerSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        JudgerSupport.class, HibernateJudgerSupport.class, HibernateMapper.class
                ),
                HibernateJudgerSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<JudgerSupport, HibernateJudgerSupport> judgerSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(
                        JudgerSupport.class, HibernateJudgerSupport.class, HibernateMapper.class
                ),
                HibernateJudgerSupport.class,
                judgerSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<VariableKey, HibernateVariableKey, Variable, HibernateVariable>
    variableHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(VariableKey.class, HibernateVariableKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Variable.class, HibernateVariable.class, HibernateMapper.class),
                HibernateVariable.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Variable, HibernateVariable> variableHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(Variable.class, HibernateVariable.class, HibernateMapper.class),
                HibernateVariable.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Variable, HibernateVariable> variableHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                hibernateTemplate,
                new MapStructBeanTransformer<>(Variable.class, HibernateVariable.class, HibernateMapper.class),
                HibernateVariable.class,
                variablePresetCriteriaMaker
        );
    }
}
