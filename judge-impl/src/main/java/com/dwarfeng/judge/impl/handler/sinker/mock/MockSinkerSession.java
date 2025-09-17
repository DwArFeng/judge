package com.dwarfeng.judge.impl.handler.sinker.mock;

import com.dwarfeng.judge.sdk.handler.sinker.AbstractSinkerSession;
import com.dwarfeng.judge.stack.bean.dto.SinkInfo;
import com.dwarfeng.judge.stack.struct.SinkerBinding;
import com.dwarfeng.judge.stack.struct.SinkerMetaInfo;
import com.dwarfeng.subgrade.sdk.log.SingleLevelLoggerFactory;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.subgrade.stack.log.SingleLevelLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 模拟下沉器会话。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MockSinkerSession extends AbstractSinkerSession {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockSinkerSession.class);

    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private final Map<LongIdKey, MockSinkerSinkInfo> sinkInfoMap = new HashMap<>();

    public MockSinkerSession() {
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
        // 冗余清空 sinkInfoMap，以保证业务逻辑的正确性。
        sinkInfoMap.clear();

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

    private MockSinkerSinkInfo parseSinkInfo(LongIdKey sectionKey, Map<String, SinkerMetaInfo> sinkerMetaInfoMap) {
        // 获取指示器值并进行检查。
        String sinkDelayString = sinkerMetaInfoMap.get(MockSinkerConstants.INDICATOR_LABEL_SINK_DELAY)
                .getEquivalentValue();
        if (Objects.isNull(sinkDelayString)) {
            LOGGER.warn("模拟下沉器打开下沉器会话失败, 将抛异常。");
            LOGGER.warn("不应该执行到此处, 请联系开发人员");
            String message = "下沉器信息为 " + sectionKey + " 的下沉器对应的指示器 " +
                    MockSinkerConstants.INDICATOR_LABEL_SINK_DELAY + " 的值为 null";
            throw new IllegalStateException(message);
        }
        String logLevelString = sinkerMetaInfoMap.get(MockSinkerConstants.INDICATOR_LABEL_LOG_LEVEL)
                .getEquivalentValue();
        if (Objects.isNull(logLevelString)) {
            LOGGER.warn("模拟下沉器打开下沉器会话失败, 将抛异常。");
            LOGGER.warn("不应该执行到此处, 请联系开发人员");
            String message = "下沉器信息为 " + sectionKey + " 的下沉器对应的指示器 " +
                    MockSinkerConstants.INDICATOR_LABEL_LOG_LEVEL + " 的值为 null";
            throw new IllegalStateException(message);
        }

        // 解析下沉延迟。
        final long sinkDelay = Long.parseLong(sinkDelayString);

        // 创建日志记录器。
        SingleLevelLogger logger;
        switch (logLevelString) {
            case MockSinkerConstants.INDICATOR_VALUE_LOG_LEVEL_DEBUG:
                logger = SingleLevelLoggerFactory.newInstance(LOGGER, LogLevel.DEBUG);
                break;
            case MockSinkerConstants.INDICATOR_VALUE_LOG_LEVEL_INFO:
                logger = SingleLevelLoggerFactory.newInstance(LOGGER, LogLevel.INFO);
                break;
            case MockSinkerConstants.INDICATOR_VALUE_LOG_LEVEL_WARN:
                logger = SingleLevelLoggerFactory.newInstance(LOGGER, LogLevel.WARN);
                break;
            case MockSinkerConstants.INDICATOR_VALUE_LOG_LEVEL_ERROR:
                logger = SingleLevelLoggerFactory.newInstance(LOGGER, LogLevel.ERROR);
                break;
            default:
                throw new IllegalArgumentException("Unsupported log level: " + logLevelString);
        }

        // 构建 MockSinkerSinkInfo 并返回。
        return new MockSinkerSinkInfo(sinkDelay, logger);
    }

    @Override
    protected void doSink(SinkInfo info) throws Exception {
        lock.readLock().lock();
        try {
            doSink0(info);
        } finally {
            lock.readLock().unlock();
        }
    }

    private void doSink0(SinkInfo info) throws InterruptedException {
        // 展开参数。
        LongIdKey sectionKey = info.getSectionKey();

        // 获取下沉信息。
        MockSinkerSinkInfo sinkInfo = sinkInfoMap.get(sectionKey);

        // 如果下沉信息不存在，则抛出异常。
        if (Objects.isNull(sinkInfo)) {
            LOGGER.warn("模拟下沉器会话下沉失败, 将抛异常。");
            LOGGER.warn("不应该执行到此处, 请联系开发人员");
            throw new IllegalStateException("数据点主键为 " + sectionKey + " 的下沉信息不存在");
        }

        // 展开参数。
        long sinkDelay = sinkInfo.getSinkDelay();
        SingleLevelLogger logger = sinkInfo.getLogger();

        // 模拟下沉延迟。
        if (sinkDelay > 0) {
            Thread.sleep(sinkDelay);
        }

        // 记录下沉信息。
        logger.log("模拟下沉器会话下沉数据, 下沉信息: {}", info);
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
        // 清空 sinkInfoMap。
        sinkInfoMap.clear();
    }

    @Override
    public String toString() {
        return "MockSinkerSession{" +
                "lock=" + lock +
                ", sinkInfoMap=" + sinkInfoMap +
                ", context=" + context +
                '}';
    }

    /**
     * 模拟下沉器下沉信息。
     *
     * @author DwArFeng
     * @since 2.1.0-beta
     */
    private static final class MockSinkerSinkInfo {

        private final long sinkDelay;
        private final SingleLevelLogger logger;

        public MockSinkerSinkInfo(long sinkDelay, SingleLevelLogger logger) {
            this.sinkDelay = sinkDelay;
            this.logger = logger;
        }

        public long getSinkDelay() {
            return sinkDelay;
        }

        public SingleLevelLogger getLogger() {
            return logger;
        }

        @Override
        public String toString() {
            return "MockSinkerSinkInfo{" +
                    "sinkDelay=" + sinkDelay +
                    ", logger=" + logger +
                    '}';
        }
    }
}
