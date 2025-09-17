package com.dwarfeng.judge.impl.handler.sinker.kafka.nati;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.judge.sdk.bean.dto.FastJsonSinkInfo;
import com.dwarfeng.judge.sdk.handler.sinker.AbstractSinkerSession;
import com.dwarfeng.judge.stack.bean.dto.SinkInfo;
import com.dwarfeng.judge.stack.struct.SinkerBinding;
import com.dwarfeng.judge.stack.struct.SinkerMetaInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 本地 kafka 下沉器会话。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NativeKafkaSinkerSession extends AbstractSinkerSession {

    private static final Logger LOGGER = LoggerFactory.getLogger(NativeKafkaSinkerSession.class);

    private final ApplicationContext ctx;

    private final NativeKafkaSinkerConfig config;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Map<LongIdKey, NativeKafkaSinkerSinkInfo> sinkInfoMap = new HashMap<>();

    private KafkaTemplate<String, String> kafkaTemplate;
    private KafkaTransactionManager<String, String> kafkaTransactionManager;

    public NativeKafkaSinkerSession(ApplicationContext ctx, NativeKafkaSinkerConfig config) {
        this.ctx = ctx;
        this.config = config;
    }

    @Override
    protected void doOpenSession() throws Exception {
        lock.writeLock().lock();
        try {
            doOpenSession0();
        } finally {
            lock.writeLock().unlock();
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void doOpenSession0() throws Exception {
        // 生成 KafkaTemplate。
        String kafkaTemplateBeanName = config.getKafkaTemplateBeanName();
        // 此处类型转换由修改 opt-sinker.xml 的运维人员保证类型安全。
        @SuppressWarnings("unchecked") KafkaTemplate<String, String> kafkaTemplateDejaVu =
                (KafkaTemplate<String, String>) ctx.getBean(kafkaTemplateBeanName);
        kafkaTemplate = kafkaTemplateDejaVu;

        // 生成 KafkaTransactionManager。
        String kafkaTransactionManagerBeanName = config.getKafkaTransactionManagerBeanName();
        // 此处类型转换由修改 opt-sinker.xml 的运维人员保证类型安全。
        @SuppressWarnings("unchecked") KafkaTransactionManager<String, String> kafkaTransactionManagerDejaVu =
                (KafkaTransactionManager<String, String>) ctx.getBean(kafkaTransactionManagerBeanName);
        kafkaTransactionManager = kafkaTransactionManagerDejaVu;

        // 获取 sectionSinkerMetaInfoMapMap。
        LongIdKey sinkerInfoKey = context.getSinkerInfoKey();
        SinkerBinding sinkerBinding = context.getSinkerBinding(sinkerInfoKey);
        if (Objects.isNull(sinkerBinding)) {
            LOGGER.warn("模拟下沉器会话打开失败, 将抛异常。");
            LOGGER.warn("不应该执行到此处, 请联系开发人员");
            throw new IllegalStateException("下沉器信息为 " + sinkerInfoKey + " 的下沉器对应的绑定信息不存在");
        }
        Map<LongIdKey, Map<String, SinkerMetaInfo>> sectionSinkerMetaInfoMapMap = sinkerBinding.getMap();

        // 遍历 sectionSinkerMetaInfoMapMap，解析每个点的下沉信息，并将其添加到 sinkInfoMap 中。
        for (Map.Entry<LongIdKey, Map<String, SinkerMetaInfo>> entry : sectionSinkerMetaInfoMapMap.entrySet()) {
            LongIdKey sectionKey = entry.getKey();
            Map<String, SinkerMetaInfo> sinkerMetaInfoMap = entry.getValue();
            sinkInfoMap.put(sectionKey, parseSinkInfo(sectionKey, sinkerMetaInfoMap));
        }
    }

    private NativeKafkaSinkerSinkInfo parseSinkInfo(LongIdKey sectionKey, Map<String, SinkerMetaInfo> sinkerMetaInfoMap) {
        // 获取指示器值并进行检查。
        String patternString = sinkerMetaInfoMap.get(NativeKafkaSinkerConstants.INDICATOR_LABEL_PARTITION)
                .getEquivalentValue();
        if (Objects.isNull(patternString)) {
            LOGGER.warn("Native Kafka 下沉器打开下沉器会话失败, 将抛异常。");
            LOGGER.warn("不应该执行到此处, 请联系开发人员");
            String message = "下沉器信息为 " + sectionKey + " 的下沉器对应的指示器 " +
                    NativeKafkaSinkerConstants.INDICATOR_LABEL_PARTITION + " 的值为 null";
            throw new IllegalStateException(message);
        }

        // 创建 partition。
        Integer partition;
        if (Objects.equals(patternString, NativeKafkaSinkerConstants.INDICATOR_VALUE_PARTITION_ALL)) {
            partition = null;
        } else {
            partition = Integer.parseInt(patternString);
        }

        // 构建 NativeKafkaSinkerSinkInfo 并返回。
        return new NativeKafkaSinkerSinkInfo(partition);
    }


    @Override
    protected void doSink(SinkInfo info) {
        lock.readLock().lock();
        try {
            doSink0(info);
        } finally {
            lock.readLock().unlock();
        }
    }

    private void doSink0(SinkInfo info) {
        // 展开参数。
        LongIdKey sectionKey = info.getSectionKey();

        // 获取下沉信息。
        NativeKafkaSinkerSinkInfo sinkInfo = sinkInfoMap.get(sectionKey);

        // 获取 partition。
        Integer partition = sinkInfo.getPartition();

        // 开启事务。
        TransactionStatus transactionStatus = kafkaTransactionManager.getTransaction(
                new DefaultTransactionDefinition()
        );
        try {
            // 执行下沉。
            kafkaTemplate.send(
                    config.getTopic(), partition, sectionKey.getLongId() + "",
                    JSON.toJSONString(FastJsonSinkInfo.of(info))
            );
            // 提交事务。
            kafkaTransactionManager.commit(transactionStatus);
        } catch (Exception e) {
            // 回滚事务。
            kafkaTransactionManager.rollback(transactionStatus);
            // 日志输出。
            String message = "Native Kafka 下沉器下沉数据失败, 将忽略数据下沉 1 次, 下沉信息: " +
                    info + ", 异常信息如下: ";
            LOGGER.warn(message, e);
        }
    }

    @Override
    protected void doCloseSession() {
        lock.writeLock().lock();
        try {
            doCloseSession0();
        } finally {
            lock.writeLock().unlock();
        }
    }

    private void doCloseSession0() {
        // 重置 KafkaTemplate。
        kafkaTemplate = null;
        // 重置 KafkaTransactionManager。
        kafkaTransactionManager = null;
        // 清空 sinkInfoMap。
        sinkInfoMap.clear();
    }

    @Override
    public String toString() {
        return "NativeKafkaSinkerSession{" +
                "ctx=" + ctx +
                ", config=" + config +
                ", lock=" + lock +
                ", sinkInfoMap=" + sinkInfoMap +
                ", newKafkaTemplate=" + kafkaTemplate +
                ", newKafkaTransactionManager=" + kafkaTransactionManager +
                ", context=" + context +
                '}';
    }

    /**
     * 本地 kafka 下沉器下沉信息。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    private static final class NativeKafkaSinkerSinkInfo {

        private final Integer partition;

        public NativeKafkaSinkerSinkInfo(Integer partition) {
            this.partition = partition;
        }

        public Integer getPartition() {
            return partition;
        }

        @Override
        public String toString() {
            return "NativeKafkaSinkerSinkInfo{" +
                    "partition=" + partition +
                    '}';
        }
    }
}
