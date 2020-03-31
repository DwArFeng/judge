package com.dwarfeng.judge.node.all.configuration;

import com.dwarfeng.judge.sdk.bean.entity.FastJsonDriverInfo;
import com.dwarfeng.judge.sdk.bean.entity.FastJsonJudgerInfo;
import com.dwarfeng.judge.sdk.bean.entity.FastJsonSection;
import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.subgrade.impl.bean.DozerBeanTransformer;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.impl.cache.RedisKeyListCache;
import com.dwarfeng.subgrade.sdk.redis.formatter.LongIdStringKeyFormatter;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
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
}
