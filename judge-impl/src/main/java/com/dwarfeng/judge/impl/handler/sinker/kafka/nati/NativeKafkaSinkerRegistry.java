package com.dwarfeng.judge.impl.handler.sinker.kafka.nati;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.judge.sdk.handler.sinker.AbstractSinkerRegistry;
import com.dwarfeng.judge.stack.exception.SinkerException;
import com.dwarfeng.judge.stack.exception.SinkerMakeException;
import com.dwarfeng.judge.stack.handler.Sinker;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 本地 kafka 下沉器注册。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NativeKafkaSinkerRegistry extends AbstractSinkerRegistry {

    public static final String SINKER_TYPE = "kafka.native";

    private final ApplicationContext ctx;

    private static final Map<String, IndicatorProvideInfo> INDICATOR_PROVIDE_INFO_MAP;

    static {
        final Map<String, IndicatorProvideInfo> INDICATOR_PROVIDE_INFO_MAP_DEJA_VU = new HashMap<>();

        // Native Kafka 分区。
        INDICATOR_PROVIDE_INFO_MAP_DEJA_VU.put(
                NativeKafkaSinkerConstants.INDICATOR_LABEL_PARTITION,
                new IndicatorProvideInfo(
                        "分区", "any",
                        "Native Kafka 分区。该指示器的值为数字或 any，表示数据发送到的 Kafka 分区。" +
                                "如果填写数字，则表示精确的分区；如果填写 any，则表示任意分区。" +
                                "默认值为 any。"
                )
        );

        INDICATOR_PROVIDE_INFO_MAP = Collections.unmodifiableMap(INDICATOR_PROVIDE_INFO_MAP_DEJA_VU);
    }

    public NativeKafkaSinkerRegistry(ApplicationContext ctx) {
        super(SINKER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "本地 kafka 下沉器。";
    }

    @Override
    public String provideDescription() {
        return "使用本地类序列化数据，并将其发送到 kafka 的下沉器。";
    }

    @Override
    public String provideExampleParam() {
        NativeKafkaSinkerConfig config = new NativeKafkaSinkerConfig(
                "nativeKafkaSinkerKafkaTemplate", "nativeKafkaSinkerKafkaTransactionManager", "your-topic-here"
        );
        return JSON.toJSONString(config, true);
    }

    @Override
    public Map<String, IndicatorProvideInfo> provideIndicatorMap() {
        return INDICATOR_PROVIDE_INFO_MAP;
    }

    @Override
    public Sinker makeSinker(String type, String param) throws SinkerException {
        try {
            NativeKafkaSinkerConfig config = JSON.parseObject(param, NativeKafkaSinkerConfig.class);
            return ctx.getBean(NativeKafkaSinker.class, ctx, config);
        } catch (Exception e) {
            throw new SinkerMakeException(e);
        }
    }
}
