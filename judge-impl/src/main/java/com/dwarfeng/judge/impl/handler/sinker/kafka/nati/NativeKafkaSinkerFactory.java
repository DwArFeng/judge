package com.dwarfeng.judge.impl.handler.sinker.kafka.nati;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 本地 kafka 下沉器工厂类。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class NativeKafkaSinkerFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(NativeKafkaSinkerFactory.class);

    @SuppressWarnings("DuplicatedCode")
    public static ProducerFactory<String, String> newProducerFactory(
            String bootstrapServers, int retries, int batchSize, long linger, long bufferMemory, String acks,
            String transactionPrefix
    ) {
        LOGGER.info("配置 Kafka 生产者属性...");
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.RETRIES_CONFIG, retries);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
        props.put(ProducerConfig.LINGER_MS_CONFIG, linger);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
        props.put(ProducerConfig.ACKS_CONFIG, acks);
        LOGGER.debug("Kafka 生产者属性配置完成...");
        LOGGER.info("配置 Kafka 生产者工厂...");
        DefaultKafkaProducerFactory<String, String> factory = new DefaultKafkaProducerFactory<>(props);
        factory.setTransactionIdPrefix(transactionPrefix);
        factory.setKeySerializer(new StringSerializer());
        factory.setValueSerializer(new StringSerializer());
        LOGGER.debug("Kafka 生产者工厂配置完成");
        return factory;
    }

    public static KafkaTemplate<String, String> newKafkaTemplate(ProducerFactory<String, String> producerFactory) {
        LOGGER.info("生成KafkaTemplate...");
        KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory, true);
        LOGGER.debug("KafkaTemplate生成完成...");
        return kafkaTemplate;
    }

    public static KafkaTransactionManager<String, String> newKafkaTransactionManager(
            ProducerFactory<String, String> producerFactory
    ) {
        LOGGER.info("生成KafkaTransactionManager...");
        LOGGER.debug("KafkaTransactionManager生成完成...");
        return new KafkaTransactionManager<>(producerFactory);
    }
}
