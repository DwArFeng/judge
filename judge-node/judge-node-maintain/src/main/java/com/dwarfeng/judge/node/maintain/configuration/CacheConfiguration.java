package com.dwarfeng.judge.node.maintain.configuration;

import com.dwarfeng.judge.sdk.bean.entity.*;
import com.dwarfeng.judge.sdk.bean.key.formatter.VariableStringKeyFormatter;
import com.dwarfeng.judge.stack.bean.entity.*;
import com.dwarfeng.judge.stack.bean.key.VariableKey;
import com.dwarfeng.subgrade.impl.bean.DozerBeanTransformer;
import com.dwarfeng.subgrade.impl.cache.RedisBaseCache;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.impl.cache.RedisKeyListCache;
import com.dwarfeng.subgrade.sdk.redis.formatter.LongIdStringKeyFormatter;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringIdStringKeyFormatter;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfiguration {

    @Autowired
    private RedisTemplate<String, ?> template;
    @Autowired
    private Mapper mapper;

    @Value("${cache.prefix.entity.driver_info}")
    private String driverInfoPrefix;
    @Value("${cache.prefix.entity.section}")
    private String sectionPrefix;
    @Value("${cache.prefix.entity.judger_info}")
    private String judgerInfoPrefix;
    @Value("${cache.prefix.list.enabled_driver_info}")
    private String enabledDriverInfoPrefix;
    @Value("${cache.prefix.list.enabled_judger_info}")
    private String enabledJudgerInfoPrefix;
    @Value("${cache.prefix.entity.driver_support}")
    private String driverSupportPrefix;
    @Value("${cache.prefix.entity.judger_support}")
    private String judgerSupportPrefix;
    @Value("${cache.prefix.entity.variable}")
    private String variablePrefix;

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, DriverInfo, FastJsonDriverInfo> driverInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonDriverInfo>) template,
                new LongIdStringKeyFormatter(driverInfoPrefix),
                new DozerBeanTransformer<>(DriverInfo.class, FastJsonDriverInfo.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, Section, FastJsonSection> sectionRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonSection>) template,
                new LongIdStringKeyFormatter(sectionPrefix),
                new DozerBeanTransformer<>(Section.class, FastJsonSection.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, JudgerInfo, FastJsonJudgerInfo> judgerInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonJudgerInfo>) template,
                new LongIdStringKeyFormatter(judgerInfoPrefix),
                new DozerBeanTransformer<>(JudgerInfo.class, FastJsonJudgerInfo.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisKeyListCache<LongIdKey, DriverInfo, FastJsonDriverInfo> driverInfoEnabledRedisKeyListCache() {
        return new RedisKeyListCache<>(
                (RedisTemplate<String, FastJsonDriverInfo>) template,
                new LongIdStringKeyFormatter(enabledDriverInfoPrefix),
                new DozerBeanTransformer<>(DriverInfo.class, FastJsonDriverInfo.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisKeyListCache<LongIdKey, JudgerInfo, FastJsonJudgerInfo> judgerInfoEnabledRedisKeyListCache() {
        return new RedisKeyListCache<>(
                (RedisTemplate<String, FastJsonJudgerInfo>) template,
                new LongIdStringKeyFormatter(enabledJudgerInfoPrefix),
                new DozerBeanTransformer<>(JudgerInfo.class, FastJsonJudgerInfo.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBaseCache<StringIdKey, DriverSupport, FastJsonDriverSupport> driverSupportRedisBaseCache() {
        return new RedisBaseCache<>(
                (RedisTemplate<String, FastJsonDriverSupport>) template,
                new StringIdStringKeyFormatter(driverSupportPrefix),
                new DozerBeanTransformer<>(DriverSupport.class, FastJsonDriverSupport.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBaseCache<StringIdKey, JudgerSupport, FastJsonJudgerSupport> judgerSupportRedisBaseCache() {
        return new RedisBaseCache<>(
                (RedisTemplate<String, FastJsonJudgerSupport>) template,
                new StringIdStringKeyFormatter(judgerSupportPrefix),
                new DozerBeanTransformer<>(JudgerSupport.class, FastJsonJudgerSupport.class, mapper)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<VariableKey, Variable, FastJsonVariable> variableRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonVariable>) template,
                new VariableStringKeyFormatter(variablePrefix),
                new DozerBeanTransformer<>(Variable.class, FastJsonVariable.class, mapper)
        );
    }
}
