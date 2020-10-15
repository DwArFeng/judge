package com.dwarfeng.judge.sdk.util;

/**
 * 常量类。
 *
 * @author DwArFeng
 * @since 1.4.1
 */
public final class Constants {

    /**
     * 检查任务的执行间隔。
     */
    public static final long SCHEDULER_CHECK_INTERVAL = 5000L;

    private Constants() {
        throw new IllegalStateException("禁止实例化");
    }
}
