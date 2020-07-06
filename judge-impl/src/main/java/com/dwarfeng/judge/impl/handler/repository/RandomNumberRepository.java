package com.dwarfeng.judge.impl.handler.repository;

import com.dwarfeng.judge.impl.handler.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Random;

/**
 * 随机数仓库。
 *
 * <p>实时数据返回一个介于0.0 至 1.0的随机数，持久数据返回空值的仓库。</p>
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class RandomNumberRepository implements Repository {

    public static final String SUPPORT_TYPE = "random_number";

    @Value("${repository.random_number.section.min}")
    private double sectionMin;
    @Value("${repository.random_number.section.max}")
    private double sectionMax;

    @Autowired
    @Qualifier("randomNumberRepository.random")
    private Random random;

    @Override
    public boolean supportType(String type) {
        return Objects.equals(SUPPORT_TYPE, type);
    }

    @Override
    public Object getData(String category, Object... args) {
        return random.nextLong();
    }

    @Configuration
    public static class RandomNumberRepositoryConfiguration {

        @Value("${repository.random_number.use_specific_random_seed}")
        private boolean useSpecificRandomSeed;
        @Value("${repository.random_number.random_seed}")
        private long randomSeed;

        @Bean("randomNumberRepository.random")
        public Random random() {
            if (!useSpecificRandomSeed) {
                return new Random();
            } else {
                return new Random(randomSeed);
            }
        }
    }
}
