package com.dwarfeng.judge.impl.handler.driver;

import com.dwarfeng.dcti.sdk.util.DataInfoUtil;
import com.dwarfeng.dcti.stack.bean.dto.DataInfo;
import com.dwarfeng.judge.sdk.handler.DriverProvider;
import com.dwarfeng.judge.stack.bean.entity.DriverInfo;
import com.dwarfeng.judge.stack.exception.DriverException;
import com.dwarfeng.judge.stack.handler.Driver;
import com.dwarfeng.judge.stack.service.EvaluateService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Dcti标准数据采集接口Kafka驱动提供器。
 *
 * @author DwArFeng
 * @since 1.0.0
 */
@Component
public class DctiKafkaDriverProvider implements DriverProvider {

    public static final String SUPPORT_TYPE = "dcti_kafka_driver";

    private final DctiKafkaDriver dctiKafkaDriver;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public DctiKafkaDriverProvider(DctiKafkaDriver dctiKafkaDriver) {
        this.dctiKafkaDriver = dctiKafkaDriver;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(SUPPORT_TYPE, type);
    }

    @Override
    public Driver provide() {
        return dctiKafkaDriver;
    }

    @Component
    public static class DctiKafkaDriver implements Driver {

        private static final Logger LOGGER = LoggerFactory.getLogger(DctiKafkaDriver.class);

        private final EvaluateService evaluateService;

        @Value("${driver.dcti.kafka.listener_id}")
        private String listenerId;

        private final Map<Long, LongIdKey> registerMap = new HashMap<>();
        private final Lock lock = new ReentrantLock();

        public DctiKafkaDriver(EvaluateService evaluateService) {
            this.evaluateService = evaluateService;
        }

        @Override
        public void register(DriverInfo driverInfo) throws DriverException {
            lock.lock();
            try {
                LongIdKey sectionKey = driverInfo.getSectionKey();
                Long dataInfoKey = Long.parseLong(driverInfo.getContent());
                registerMap.put(dataInfoKey, sectionKey);
            } catch (Exception e) {
                throw new DriverException(e);
            } finally {
                lock.unlock();
            }
        }

        @Override
        public void unregisterAll() {
            lock.lock();
            try {
                registerMap.clear();
            } finally {
                lock.unlock();
            }
        }

        @KafkaListener(id = "${driver.dcti.kafka.listener_id}", containerFactory = "dctiKafkaDriver.kafkaListenerContainerFactory",
                topics = "${driver.dcti.kafka.listener_topic}")
        public void handleDataInfo(String message, Acknowledgment ack) {
            lock.lock();
            try {
                // 无论之后的驱动判断是否正常，都只判断一次，因此首先提交ack。
                ack.acknowledge();
                // 驱动判断。
                DataInfo dataInfo = DataInfoUtil.fromMessage(message);
                Long dataInfoKey = dataInfo.getPointLongId();
                LongIdKey sectionKey = registerMap.getOrDefault(dataInfoKey, null);
                if (Objects.nonNull(sectionKey)) {
                    evaluateService.evaluate(sectionKey);
                }
            } catch (Exception e) {
                LOGGER.warn("处理dcti dataInfo时出现异常，将忽略驱动判断 1 次，异常信息如下", e);
            } finally {
                lock.unlock();
            }
        }

    }

    @Configuration
    @EnableKafka
    public static class KafkaDriverConfiguration {

        private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDriverConfiguration.class);

        @Value("${driver.dcti.kafka.bootstrap_servers}")
        private String consumerBootstrapServers;
        @Value("${driver.dcti.kafka.session_timeout_ms}")
        private int sessionTimeoutMs;
        @Value("${driver.dcti.kafka.group}")
        private String group;
        @Value("${driver.dcti.kafka.auto_offset_reset}")
        private String autoOffsetReset;
        @Value("${driver.dcti.kafka.concurrency}")
        private int concurrency;
        @Value("${driver.dcti.kafka.poll_timeout}")
        private int pollTimeout;
        @Value("${driver.dcti.kafka.auto_startup}")
        private boolean autoStartup;

        @Bean("dctiKafkaDriver.consumerProperties")
        public Map<String, Object> consumerProperties() {
            LOGGER.info("配置Kafka消费者属性...");
            Map<String, Object> props = new HashMap<>();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, consumerBootstrapServers);
            // 本实例使用ack手动提交，因此禁止自动提交的功能。
            props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
            props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeoutMs);
            props.put(ConsumerConfig.GROUP_ID_CONFIG, group);
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
            LOGGER.debug("Kafka消费者属性配置完成...");
            return props;
        }

        @Bean("dctiKafkaDriver.consumerFactory")
        public ConsumerFactory<String, String> consumerFactory() {
            LOGGER.info("配置Kafka消费者工厂...");
            Map<String, Object> properties = consumerProperties();
            DefaultKafkaConsumerFactory<String, String> factory = new DefaultKafkaConsumerFactory<>(properties);
            factory.setKeyDeserializer(new StringDeserializer());
            factory.setValueDeserializer(new StringDeserializer());
            LOGGER.debug("Kafka消费者工厂配置完成");
            return factory;
        }

        @Bean("dctiKafkaDriver.kafkaListenerContainerFactory")
        public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
            LOGGER.info("配置Kafka侦听容器工厂...");
            ConsumerFactory<String, String> consumerFactory = consumerFactory();
            ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory);
            factory.setConcurrency(concurrency);
            factory.getContainerProperties().setPollTimeout(pollTimeout);
            factory.setAutoStartup(autoStartup);
            // 配置ACK模式为手动立即提交。
            factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.MANUAL_IMMEDIATE);
            LOGGER.info("配置Kafka侦听容器工厂...");
            return factory;
        }
    }
}
