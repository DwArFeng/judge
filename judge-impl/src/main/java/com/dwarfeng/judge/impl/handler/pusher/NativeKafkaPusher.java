package com.dwarfeng.judge.impl.handler.pusher;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.judge.sdk.bean.dto.FastJsonPurgeFinishedResult;
import com.dwarfeng.judge.sdk.bean.entity.FastJsonAlarmModal;
import com.dwarfeng.judge.sdk.bean.entity.FastJsonSection;
import com.dwarfeng.judge.sdk.handler.pusher.AbstractPusher;
import com.dwarfeng.judge.stack.bean.dto.PurgeFinishedResult;
import com.dwarfeng.judge.stack.bean.entity.AlarmModal;
import com.dwarfeng.judge.stack.bean.entity.Section;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 本地 Kafka 推送器。
 *
 * @author DwArFeng
 * @since 1.8.0
 */
@Component
public class NativeKafkaPusher extends AbstractPusher {

    public static final String PUSHER_TYPE = "kafka.native";

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${com.dwarfeng.judge.pusher.kafka.native.topic.task_finished}")
    private String taskFinishedTopic;
    @Value("${com.dwarfeng.judge.pusher.kafka.native.topic.task_failed}")
    private String taskFailedTopic;
    @Value("${com.dwarfeng.judge.pusher.kafka.native.topic.task_expired}")
    private String taskExpiredTopic;
    @Value("${com.dwarfeng.judge.pusher.kafka.native.topic.task_died}")
    private String taskDiedTopic;
    @Value("${com.dwarfeng.judge.pusher.kafka.native.topic.job_reset}")
    private String jobResetTopic;
    @Value("${com.dwarfeng.judge.pusher.kafka.native.topic.supervise_reset}")
    private String superviseResetTopic;
    @Value("${com.dwarfeng.judge.pusher.kafka.native.topic.sink_reset}")
    private String sinkResetTopic;
    @Value("${com.dwarfeng.judge.pusher.kafka.native.topic.provide_reset}")
    private String provideResetTopic;
    @Value("${com.dwarfeng.judge.pusher.kafka.native.topic.adapt_reset}")
    private String adaptResetTopic;
    @Value("${com.dwarfeng.judge.pusher.kafka.native.topic.filter_reset}")
    private String filterResetTopic;
    @Value("${com.dwarfeng.judge.pusher.kafka.native.topic.purge_finished}")
    private String purgeFinishedTopic;
    @Value("${com.dwarfeng.judge.pusher.kafka.native.topic.purge_failed}")
    private String purgeFailedTopic;
    @Value("${com.dwarfeng.judge.pusher.kafka.native.topic.alarm_appeared}")
    private String alarmAppearedTopic;
    @Value("${com.dwarfeng.judge.pusher.kafka.native.topic.alarm_level_increased}")
    private String alarmLevelIncreasedTopic;
    @Value("${com.dwarfeng.judge.pusher.kafka.native.topic.alarm_level_decreased}")
    private String alarmLevelDecreasedTopic;
    @Value("${com.dwarfeng.judge.pusher.kafka.native.topic.alarm_disappeared}")
    private String alarmDisappearedTopic;

    public NativeKafkaPusher(
            @Qualifier("nativeKafkaPusher.kafkaTemplate") KafkaTemplate<String, String> kafkaTemplate
    ) {
        super(PUSHER_TYPE);
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void taskFinished(Section section) {
        kafkaTemplate.send(taskFinishedTopic, JSON.toJSONString(FastJsonSection.of(section)));
    }

    @Override
    public void taskFailed(Section section) {
        kafkaTemplate.send(taskFailedTopic, JSON.toJSONString(FastJsonSection.of(section)));
    }

    @Override
    public void taskExpired(Section section) {
        kafkaTemplate.send(taskExpiredTopic, JSON.toJSONString(FastJsonSection.of(section)));
    }

    @Override
    public void taskDied(Section section) {
        kafkaTemplate.send(taskDiedTopic, JSON.toJSONString(FastJsonSection.of(section)));
    }

    @Override
    public void superviseReset() {
        kafkaTemplate.send(superviseResetTopic, StringUtils.EMPTY);
    }

    @Override
    public void adaptReset() {
        kafkaTemplate.send(adaptResetTopic, StringUtils.EMPTY);
    }

    @Override
    public void jobReset() {
        kafkaTemplate.send(jobResetTopic, StringUtils.EMPTY);
    }

    @Override
    public void provideReset() {
        kafkaTemplate.send(provideResetTopic, StringUtils.EMPTY);
    }

    @Override
    public void filterReset() {
        kafkaTemplate.send(filterResetTopic, StringUtils.EMPTY);
    }

    @Override
    public void sinkReset() {
        kafkaTemplate.send(sinkResetTopic, StringUtils.EMPTY);
    }

    @Override
    public void purgeFinished(PurgeFinishedResult result) {
        kafkaTemplate.send(purgeFinishedTopic, JSON.toJSONString(FastJsonPurgeFinishedResult.of(result)));
    }

    @Override
    public void purgeFailed() {
        kafkaTemplate.send(purgeFailedTopic, StringUtils.EMPTY);
    }

    @Override
    public void alarmAppeared(AlarmModal alarmModal) {
        kafkaTemplate.send(alarmAppearedTopic, JSON.toJSONString(FastJsonAlarmModal.of(alarmModal)));
    }

    @Override
    public void alarmLevelIncreased(AlarmModal alarmModal) {
        kafkaTemplate.send(alarmLevelIncreasedTopic, JSON.toJSONString(FastJsonAlarmModal.of(alarmModal)));
    }

    @Override
    public void alarmLevelDecreased(AlarmModal alarmModal) {
        kafkaTemplate.send(alarmLevelDecreasedTopic, JSON.toJSONString(FastJsonAlarmModal.of(alarmModal)));
    }

    @Override
    public void alarmDisappeared(AlarmModal alarmModal) {
        kafkaTemplate.send(alarmDisappearedTopic, JSON.toJSONString(FastJsonAlarmModal.of(alarmModal)));
    }

    @Override
    public String toString() {
        return "NativeKafkaPusher{" +
                "kafkaTemplate=" + kafkaTemplate +
                ", taskFinishedTopic='" + taskFinishedTopic + '\'' +
                ", taskFailedTopic='" + taskFailedTopic + '\'' +
                ", taskExpiredTopic='" + taskExpiredTopic + '\'' +
                ", taskDiedTopic='" + taskDiedTopic + '\'' +
                ", jobResetTopic='" + jobResetTopic + '\'' +
                ", superviseResetTopic='" + superviseResetTopic + '\'' +
                ", sinkResetTopic='" + sinkResetTopic + '\'' +
                ", provideResetTopic='" + provideResetTopic + '\'' +
                ", adaptResetTopic='" + adaptResetTopic + '\'' +
                ", filterResetTopic='" + filterResetTopic + '\'' +
                ", purgeFinishedTopic='" + purgeFinishedTopic + '\'' +
                ", purgeFailedTopic='" + purgeFailedTopic + '\'' +
                ", alarmAppearedTopic='" + alarmAppearedTopic + '\'' +
                ", alarmLevelIncreasedTopic='" + alarmLevelIncreasedTopic + '\'' +
                ", alarmLevelDecreasedTopic='" + alarmLevelDecreasedTopic + '\'' +
                ", alarmDisappearedTopic='" + alarmDisappearedTopic + '\'' +
                ", pusherType='" + pusherType + '\'' +
                '}';
    }

    @Configuration
    public static class KafkaPusherConfiguration {

        private static final Logger LOGGER = LoggerFactory.getLogger(KafkaPusherConfiguration.class);

        @Value("${com.dwarfeng.judge.pusher.kafka.native.bootstrap_servers}")
        private String producerBootstrapServers;
        @Value("${com.dwarfeng.judge.pusher.kafka.native.retries}")
        private int retries;
        @Value("${com.dwarfeng.judge.pusher.kafka.native.linger}")
        private long linger;
        @Value("${com.dwarfeng.judge.pusher.kafka.native.buffer_memory}")
        private long bufferMemory;
        @Value("${com.dwarfeng.judge.pusher.kafka.native.batch_size}")
        private int batchSize;
        @Value("${com.dwarfeng.judge.pusher.kafka.native.acks}")
        private String acks;
        @Value("${com.dwarfeng.judge.pusher.kafka.native.transaction_prefix}")
        private String transactionPrefix;

        @SuppressWarnings("DuplicatedCode")
        @Bean("nativeKafkaPusher.producerProperties")
        public Map<String, Object> producerProperties() {
            LOGGER.info("配置 Kafka 生产者属性...");
            Map<String, Object> props = new HashMap<>();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, producerBootstrapServers);
            props.put(ProducerConfig.RETRIES_CONFIG, retries);
            props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
            props.put(ProducerConfig.LINGER_MS_CONFIG, linger);
            props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
            props.put(ProducerConfig.ACKS_CONFIG, acks);
            LOGGER.debug("Kafka 生产者属性配置完成...");
            return props;
        }

        @SuppressWarnings("DuplicatedCode")
        @Bean("nativeKafkaPusher.producerFactory")
        public ProducerFactory<String, String> producerFactory() {
            LOGGER.info("配置 Kafka 生产者工厂...");
            Map<String, Object> properties = producerProperties();
            DefaultKafkaProducerFactory<String, String> factory = new DefaultKafkaProducerFactory<>(properties);
            factory.setTransactionIdPrefix(transactionPrefix);
            factory.setKeySerializer(new StringSerializer());
            factory.setValueSerializer(new StringSerializer());
            LOGGER.debug("Kafka 生产者工厂配置完成");
            return factory;
        }

        @Bean("nativeKafkaPusher.kafkaTemplate")
        public KafkaTemplate<String, String> kafkaTemplate() {
            LOGGER.info("生成 KafkaTemplate...");
            ProducerFactory<String, String> producerFactory = producerFactory();
            KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory, true);
            LOGGER.debug("KafkaTemplate 生成完成...");
            return kafkaTemplate;
        }

        @Bean("nativeKafkaPusher.kafkaTransactionManager")
        public KafkaTransactionManager<String, String> kafkaTransactionManager() {
            LOGGER.info("生成 KafkaTransactionManager...");
            ProducerFactory<String, String> producerFactory = producerFactory();
            LOGGER.debug("KafkaTransactionManager 生成完成...");
            return new KafkaTransactionManager<>(producerFactory);
        }
    }
}
