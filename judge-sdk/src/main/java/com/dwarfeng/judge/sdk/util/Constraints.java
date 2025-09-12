package com.dwarfeng.judge.sdk.util;

/**
 * 约束类。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public final class Constraints {

    /**
     * 字符串 ID 的长度约束。
     */
    public static final int LENGTH_STRING_ID = 100;

    /**
     * 名称的长度约束。
     */
    public static final int LENGTH_NAME = 50;

    /**
     * 标签的长度约束。
     */
    public static final int LENGTH_LABEL = 50;

    /**
     * 过滤器、触发器类型的长度约束。
     */
    public static final int LENGTH_TYPE = 50;

    /**
     * 消息的长度约束。
     */
    public static final int LENGTH_MESSAGE = 200;

    /**
     * 备注的长度约束。
     */
    public static final int LENGTH_REMARK = 100;

    private Constraints() {
        throw new IllegalStateException("禁止实例化");
    }
}
