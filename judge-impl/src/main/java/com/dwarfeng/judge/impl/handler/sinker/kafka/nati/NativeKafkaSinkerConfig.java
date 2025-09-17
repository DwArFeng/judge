package com.dwarfeng.judge.impl.handler.sinker.kafka.nati;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.Bean;

/**
 * 本地 kafka 下沉器配置。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class NativeKafkaSinkerConfig implements Bean {

    private static final long serialVersionUID = 7377567348736484854L;

    @JSONField(name = "#kafka_template_bean_name", ordinal = 1)
    private String kafkaTemplateBeanNameRem = "KafkaTemplate 的 Bean 名称。";

    @JSONField(name = "kafka_template_bean_name", ordinal = 2)
    private String kafkaTemplateBeanName;

    @JSONField(name = "#kafka_transaction_manager_bean_name", ordinal = 3)
    private String kafkaTransactionManagerBeanNameRem = "KafkaTransactionManager 的 Bean 名称。";

    @JSONField(name = "kafka_transaction_manager_bean_name", ordinal = 4)
    private String kafkaTransactionManagerBeanName;

    @JSONField(name = "#topic", ordinal = 5)
    private String topicRem = "Kafka 主题名称。";

    @JSONField(name = "topic", ordinal = 6)
    private String topic;

    public NativeKafkaSinkerConfig() {
    }

    public NativeKafkaSinkerConfig(String kafkaTemplateBeanName, String kafkaTransactionManagerBeanName, String topic) {
        this.kafkaTemplateBeanName = kafkaTemplateBeanName;
        this.kafkaTransactionManagerBeanName = kafkaTransactionManagerBeanName;
        this.topic = topic;
    }

    public String getKafkaTemplateBeanNameRem() {
        return kafkaTemplateBeanNameRem;
    }

    public void setKafkaTemplateBeanNameRem(String kafkaTemplateBeanNameRem) {
        this.kafkaTemplateBeanNameRem = kafkaTemplateBeanNameRem;
    }

    public String getKafkaTemplateBeanName() {
        return kafkaTemplateBeanName;
    }

    public void setKafkaTemplateBeanName(String kafkaTemplateBeanName) {
        this.kafkaTemplateBeanName = kafkaTemplateBeanName;
    }

    public String getKafkaTransactionManagerBeanNameRem() {
        return kafkaTransactionManagerBeanNameRem;
    }

    public void setKafkaTransactionManagerBeanNameRem(String kafkaTransactionManagerBeanNameRem) {
        this.kafkaTransactionManagerBeanNameRem = kafkaTransactionManagerBeanNameRem;
    }

    public String getKafkaTransactionManagerBeanName() {
        return kafkaTransactionManagerBeanName;
    }

    public void setKafkaTransactionManagerBeanName(String kafkaTransactionManagerBeanName) {
        this.kafkaTransactionManagerBeanName = kafkaTransactionManagerBeanName;
    }

    public String getTopicRem() {
        return topicRem;
    }

    public void setTopicRem(String topicRem) {
        this.topicRem = topicRem;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "NativeKafkaSinkerConfig{" +
                "kafkaTemplateBeanNameRem='" + kafkaTemplateBeanNameRem + '\'' +
                ", kafkaTemplateBeanName='" + kafkaTemplateBeanName + '\'' +
                ", kafkaTransactionManagerBeanNameRem='" + kafkaTransactionManagerBeanNameRem + '\'' +
                ", kafkaTransactionManagerBeanName='" + kafkaTransactionManagerBeanName + '\'' +
                ", topicRem='" + topicRem + '\'' +
                ", topic='" + topic + '\'' +
                '}';
    }
}
