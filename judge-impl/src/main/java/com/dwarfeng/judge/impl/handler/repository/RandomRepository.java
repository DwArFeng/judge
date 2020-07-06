package com.dwarfeng.judge.impl.handler.repository;

import com.dwarfeng.judge.impl.handler.Repository;
import com.dwarfeng.judge.stack.exception.RepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Random;

/**
 * 随机值仓库。
 *
 * <p>根据类型的不同返回各种随机值。</p>
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class RandomRepository implements Repository {

    public static final String SUPPORT_TYPE = "random";

    public static final String CATEGORY_LONG = "category_long";
    public static final String CATEGORY_INT = "category_int";
    public static final String CATEGORY_DOUBLE = "category_double";
    public static final String CATEGORY_BOOLEAN = "category_boolean";
    public static final String CATEGORY_FLOAT = "category_float";
    public static final String CATEGORY_GAUSSIAN = "category_gaussian";
    public static final String CATEGORY_BYTES = "category_bytes";

    @Autowired
    @Qualifier("randomRepository.random")
    private Random random;

    @Override
    public boolean supportType(String type) {
        return Objects.equals(SUPPORT_TYPE, type);
    }

    @Override
    public Object getData(String category, Object... args) throws RepositoryException {
        try {
            switch (category) {
                case CATEGORY_LONG:
                    return random.nextLong();
                case CATEGORY_INT:
                    if (args.length == 0) {
                        return random.nextInt();
                    } else {
                        return random.nextInt((int) args[0]);
                    }
                case CATEGORY_DOUBLE:
                    return random.nextDouble();
                case CATEGORY_BOOLEAN:
                    return random.nextBoolean();
                case CATEGORY_FLOAT:
                    return random.nextFloat();
                case CATEGORY_GAUSSIAN:
                    return random.nextGaussian();
                case CATEGORY_BYTES:
                    byte[] bytes = (byte[]) args[0];
                    random.nextBytes(bytes);
                    return bytes;
                default:
                    throw new IllegalArgumentException("未知的 category: " + category);
            }
        } catch (Exception e) {
            throw new RepositoryException(e);
        }
    }

    @Configuration
    public static class RandomRepositoryConfiguration {

        @Value("${repository.random.use_specific_random_seed}")
        private boolean useSpecificRandomSeed;
        @Value("${repository.random.random_seed}")
        private long randomSeed;

        @Bean("randomRepository.random")
        public Random random() {
            if (!useSpecificRandomSeed) {
                return new Random();
            } else {
                return new Random(randomSeed);
            }
        }
    }
}
