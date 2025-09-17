package com.dwarfeng.judge.impl.handler.sinker.mock;

/**
 * 模拟下沉器常量类。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public final class MockSinkerConstants {

    public static final String INDICATOR_LABEL_SINK_DELAY = "sink_delay";
    public static final String INDICATOR_LABEL_LOG_LEVEL = "log_level";

    public static final String INDICATOR_VALUE_LOG_LEVEL_DEBUG = "debug";
    public static final String INDICATOR_VALUE_LOG_LEVEL_INFO = "info";
    public static final String INDICATOR_VALUE_LOG_LEVEL_WARN = "warn";
    public static final String INDICATOR_VALUE_LOG_LEVEL_ERROR = "error";

    private MockSinkerConstants() {
        throw new IllegalStateException("禁止实例化");
    }
}
