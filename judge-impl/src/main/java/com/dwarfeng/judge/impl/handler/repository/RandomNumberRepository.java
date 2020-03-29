package com.dwarfeng.judge.impl.handler.repository;

import com.dwarfeng.judge.impl.handler.Repository;
import com.dwarfeng.judge.stack.bean.dto.PersistenceValue;
import com.dwarfeng.subgrade.sdk.bean.dto.PagingUtil;
import com.dwarfeng.subgrade.stack.bean.dto.PagedData;
import com.dwarfeng.subgrade.stack.bean.dto.PagingInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
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

    private final Random random;

    public RandomNumberRepository(@Autowired @Qualifier("randomNumberRepository.random") Random random) {
        this.random = random;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(SUPPORT_TYPE, type);
    }

    @Override
    public String realtimeValue(LongIdKey pointKey) {
        return Double.toString(random.nextDouble());
    }

    @Override
    public PagedData<PersistenceValue> persistenceValue(
            LongIdKey pointKey, Date startDate, Date endDate) {
        return PagingUtil.pagedData(Collections.emptyList());
    }

    @Override
    public PagedData<PersistenceValue> persistenceValue(
            LongIdKey pointKey, Date startDate, Date endDate, PagingInfo pagingInfo) {
        return PagingUtil.pagedData(Collections.emptyList());
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
