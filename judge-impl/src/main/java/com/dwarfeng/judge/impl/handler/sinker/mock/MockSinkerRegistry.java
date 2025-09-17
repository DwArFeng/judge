package com.dwarfeng.judge.impl.handler.sinker.mock;

import com.dwarfeng.judge.sdk.handler.sinker.AbstractSinkerRegistry;
import com.dwarfeng.judge.stack.exception.SinkerException;
import com.dwarfeng.judge.stack.exception.SinkerMakeException;
import com.dwarfeng.judge.stack.handler.Sinker;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 模拟下沉器注册。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
@Component
public class MockSinkerRegistry extends AbstractSinkerRegistry {

    public static final String SINKER_TYPE = "mock";

    private final ApplicationContext ctx;

    private static final Map<String, IndicatorProvideInfo> INDICATOR_PROVIDE_INFO_MAP;

    static {
        final Map<String, IndicatorProvideInfo> INDICATOR_PROVIDE_INFO_MAP_DEJA_VU = new HashMap<>();

        // 模拟下沉器的下沉延时。
        INDICATOR_PROVIDE_INFO_MAP_DEJA_VU.put(
                MockSinkerConstants.INDICATOR_LABEL_SINK_DELAY,
                new IndicatorProvideInfo(
                        "下沉延时", "0",
                        "模拟下沉器的下沉延时。该指示器的值为数字，表示下沉的延时，单位为毫秒。" +
                                "如果该值小于等于 0，则表示不进行下沉延时。"
                )
        );

        // 模拟下沉器的日志等级。
        INDICATOR_PROVIDE_INFO_MAP_DEJA_VU.put(
                MockSinkerConstants.INDICATOR_LABEL_LOG_LEVEL,
                new IndicatorProvideInfo(
                        "日志等级", "info",
                        "模拟下沉器的日志等级。该指示器的值为枚举值，合法的值有：debug、info、warn、error。" +
                                "必须使用小写字符。"
                )
        );

        INDICATOR_PROVIDE_INFO_MAP = Collections.unmodifiableMap(INDICATOR_PROVIDE_INFO_MAP_DEJA_VU);
    }

    public MockSinkerRegistry(ApplicationContext ctx) {
        super(SINKER_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "模拟下沉器";
    }

    @Override
    public String provideDescription() {
        return "生成模拟的下沉过程，用于测试和调试目的。该下沉器会模拟下沉延时，并根据配置的日志等级输出日志。" +
                "可以通过指示器来配置下沉延时和日志等级。";
    }

    @Override
    public String provideExampleParam() {
        return StringUtils.EMPTY;
    }

    @Override
    public Map<String, IndicatorProvideInfo> provideIndicatorMap() {
        return INDICATOR_PROVIDE_INFO_MAP;
    }

    @Override
    public Sinker makeSinker(String type, String param) throws SinkerException {
        try {
            return ctx.getBean(MockSinker.class, ctx);
        } catch (Exception e) {
            throw new SinkerMakeException(e);
        }
    }

    @Override
    public String toString() {
        return "MockSinkerRegistry{" +
                "ctx=" + ctx +
                ", sinkerType='" + sinkerType + '\'' +
                '}';
    }
}
