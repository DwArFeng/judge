package com.dwarfeng.judge.impl.handler.sinker.kafka.nati;

/**
 * 本地 kafka 下沉器常量类。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public final class NativeKafkaSinkerConstants {

    public static final String INDICATOR_LABEL_PARTITION = "partition";

    public static final String INDICATOR_VALUE_PARTITION_ALL = "all";

    private NativeKafkaSinkerConstants() {
        throw new IllegalStateException("禁止实例化");
    }
}
