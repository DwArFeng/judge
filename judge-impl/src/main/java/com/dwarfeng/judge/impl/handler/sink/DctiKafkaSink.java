package com.dwarfeng.judge.impl.handler.sink;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.dwarfeng.dcti.sdk.util.DataInfoUtil;
import com.dwarfeng.dcti.stack.bean.dto.DataInfo;
import com.dwarfeng.judge.impl.handler.Sink;
import com.dwarfeng.judge.sdk.bean.dto.FastJsonJudgementInfo;
import com.dwarfeng.judge.stack.bean.dto.JudgedValue;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Kafka水槽。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class DctiKafkaSink implements Sink {

    public static final String SUPPORT_TYPE = "dcti.kafka";

    @Autowired
    @Qualifier("kafkaSink.kafkaTemplate")
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${sink.kafka.topic}")
    private String topic;

    @Override
    public boolean supportType(String type) {
        return Objects.equals(SUPPORT_TYPE, type);
    }

    @Override
    @Transactional(transactionManager = "kafkaSink.kafkaTransactionManager")
    public void sinkData(JudgedValue judgedValue) {
        DataInfo dataInfo = new DataInfo(
                judgedValue.getJudgerKey().getLongId(),
                JSON.toJSONString(FastJsonJudgementInfo.of(judgedValue.getJudgementInfo()),
                        SerializerFeature.DisableCircularReferenceDetect),
                judgedValue.getHappenedDate()
        );
        kafkaTemplate.send(topic, DataInfoUtil.toMessage(dataInfo));
    }

    @Configuration
    public static class KafkaSinkConfiguration {

        private static final Logger LOGGER = LoggerFactory.getLogger(KafkaSinkConfiguration.class);

        @Value("${sink.kafka.bootstrap_servers}")
        private String producerBootstrapServers;
        @Value("${sink.kafka.retries}")
        private int retries;
        @Value("${sink.kafka.linger}")
        private long linger;
        @Value("${sink.kafka.buffer_memory}")
        private long bufferMemory;
        @Value("${sink.kafka.batch_size}")
        private int batchSize;
        @Value("${sink.kafka.acks}")
        private String acks;
        @Value("${sink.kafka.transaction_prefix}")
        private String transactionPrefix;

        @Bean("kafkaSink.producerProperties")
        public Map<String, Object> producerProperties() {
            LOGGER.info("配置Kafka生产者属性...");
            Map<String, Object> props = new HashMap<>();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, producerBootstrapServers);
            props.put(ProducerConfig.RETRIES_CONFIG, retries);
            props.put(ProducerConfig.BATCH_SIZE_CONFIG, batchSize);
            props.put(ProducerConfig.LINGER_MS_CONFIG, linger);
            props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, bufferMemory);
            props.put(ProducerConfig.ACKS_CONFIG, acks);
            LOGGER.debug("Kafka生产者属性配置完成...");
            return props;
        }

        @Bean("kafkaSink.producerFactory")
        public ProducerFactory<String, String> producerFactory() {
            LOGGER.info("配置Kafka生产者工厂...");
            Map<String, Object> properties = producerProperties();
            DefaultKafkaProducerFactory<String, String> factory = new DefaultKafkaProducerFactory<>(properties);
            factory.setTransactionIdPrefix(transactionPrefix);
            factory.setKeySerializer(new StringSerializer());
            factory.setValueSerializer(new StringSerializer());
            LOGGER.debug("Kafka生产者工厂配置完成");
            return factory;
        }

        @Bean("kafkaSink.kafkaTemplate")
        public KafkaTemplate<String, String> kafkaTemplate() {
            LOGGER.info("生成KafkaTemplate...");
            ProducerFactory<String, String> producerFactory = producerFactory();
            KafkaTemplate<String, String> kafkaTemplate = new KafkaTemplate<>(producerFactory, true);
            LOGGER.debug("KafkaTemplate生成完成...");
            return kafkaTemplate;
        }

        @Bean("kafkaSink.kafkaTransactionManager")
        public KafkaTransactionManager<String, String> kafkaTransactionManager() {
            LOGGER.info("生成KafkaTransactionManager...");
            ProducerFactory<String, String> producerFactory = producerFactory();
            LOGGER.debug("KafkaTransactionManager生成完成...");
            return new KafkaTransactionManager<>(producerFactory);
        }
    }
}
