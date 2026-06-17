package com.dwarfeng.judge.impl.handler.provider.date;

import java.time.ZoneId;
import java.util.Objects;

/**
 * 日期提供器参数解析辅助类。
 *
 * <p>
 * 负责校验固定长度 <code>objs</code>、解析默认占位符 <code>"-"</code>，并将简单参数转换为
 * 毫秒时间戳、时区、周期偏移和 duration 字符串。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
final class DateParameterHelper {

    static final String DEFAULT_PLACEHOLDER = "-";
    static final String DEFAULT_TIMEZONE = "Asia/Shanghai";
    static final ZoneId DEFAULT_ZONE_ID = ZoneId.of(DEFAULT_TIMEZONE);
    static final int MAX_SEQUENCE_COUNT = 10_000;

    /**
     * 校验 <code>objs</code> 长度必须等于预设规定长度。
     *
     * @param objs           对象数组。
     * @param preset         预设名称。
     * @param expectedLength 期望长度。
     */
    public static void requireExactLength(Object[] objs, String preset, int expectedLength) {
        if (objs == null) {
            throw new IllegalArgumentException(
                    "预设 " + preset + " 缺少必填参数，期望 " + expectedLength + " 个 objs 元素"
            );
        }
        if (objs.length != expectedLength) {
            throw new IllegalArgumentException(
                    "预设 " + preset + " 的 objs 长度不匹配，期望 " + expectedLength + " 个元素，实际 "
                            + objs.length + " 个"
            );
        }
    }

    /**
     * 解析必填 duration 参数。
     *
     * @param obj    参数对象。
     * @param preset 预设名称。
     * @param index  参数索引。
     * @return 解析结果。
     */
    public static DateDuration requireDuration(Object obj, String preset, int index) {
        return DateDuration.parse(asRequiredString(obj, preset, index), preset, index);
    }

    /**
     * 解析可选时区参数，默认 <code>Asia/Shanghai</code>。
     *
     * @param obj    参数对象。
     * @param preset 预设名称。
     * @param index  参数索引。
     * @return 时区 ID 字符串。
     */
    public static String parseOptionalTimezone(Object obj, String preset, int index) {
        if (isDefaultPlaceholder(obj)) {
            return DEFAULT_TIMEZONE;
        }
        String timezone = asRequiredString(obj, preset, index);
        try {
            ZoneId ignored = ZoneId.of(timezone);
        } catch (Exception e) {
            throw new IllegalArgumentException("预设 " + preset + " 的 objs[" + index + "] 不是合法时区: " + timezone);
        }
        return timezone;
    }

    /**
     * 解析可选时区并返回 <code>ZoneId</code>。
     *
     * @param obj    参数对象。
     * @param preset 预设名称。
     * @param index  参数索引。
     * @return 时区对象。
     */
    // 为了保证代码的可读性，此处代码不做简化。
    @SuppressWarnings("unused")
    public static ZoneId parseOptionalZoneId(Object obj, String preset, int index) {
        return ZoneId.of(parseOptionalTimezone(obj, preset, index));
    }

    /**
     * 解析可选基准时间戳，默认当前系统时间。
     *
     * @param obj    参数对象。
     * @param preset 预设名称。
     * @param index  参数索引。
     * @return 毫秒时间戳。
     */
    public static long parseOptionalBaseTimestamp(Object obj, String preset, int index) {
        if (isDefaultPlaceholder(obj)) {
            return System.currentTimeMillis();
        }
        return requireLong(obj, preset, index);
    }

    /**
     * 解析必填毫秒时间戳。
     *
     * @param obj    参数对象。
     * @param preset 预设名称。
     * @param index  参数索引。
     * @return 毫秒时间戳。
     */
    public static long requireLong(Object obj, String preset, int index) {
        if (obj == null) {
            throw new IllegalArgumentException("预设 " + preset + " 的 objs[" + index + "] 不能为空");
        }
        if (isDefaultPlaceholder(obj)) {
            throw new IllegalArgumentException("预设 " + preset + " 的 objs[" + index + "] 不允许为 \"-\"");
        }
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        if (obj instanceof String) {
            String text = ((String) obj).trim();
            if (text.isEmpty()) {
                throw new IllegalArgumentException("预设 " + preset + " 的 objs[" + index + "] 不能为空字符串");
            }
            try {
                return Long.parseLong(text);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        "预设 " + preset + " 的 objs[" + index + "] 不是合法 Long: " + text
                );
            }
        }
        throw typeMismatch(preset, index, obj, "Long");
    }

    /**
     * 解析必填正整数。
     *
     * @param obj    参数对象。
     * @param preset 预设名称。
     * @param index  参数索引。
     * @return 正整数。
     */
    // 为了保证代码的可读性，此处代码不做简化。
    @SuppressWarnings("SameParameterValue")
    public static int requirePositiveCount(Object obj, String preset, int index) {
        int count = requireInteger(obj, preset, index);
        if (count <= 0) {
            throw new IllegalArgumentException("预设 " + preset + " 的 objs[" + index + "] 必须大于 0");
        }
        if (count > MAX_SEQUENCE_COUNT) {
            throw new IllegalArgumentException(
                    "预设 " + preset + " 的 objs[" + index + "] 超过最大限制 " + MAX_SEQUENCE_COUNT
            );
        }
        return count;
    }

    /**
     * 解析可选周期偏移，默认 <code>0</code>。
     *
     * @param obj    参数对象。
     * @param preset 预设名称。
     * @param index  参数索引。
     * @return 周期偏移。
     */
    // 为了保证代码的可读性，此处代码不做简化。
    @SuppressWarnings("SameParameterValue")
    public static int parseOptionalPeriodOffset(Object obj, String preset, int index) {
        if (isDefaultPlaceholder(obj)) {
            return 0;
        }
        return requireInteger(obj, preset, index);
    }

    /**
     * 校验起始时间必须严格小于结束时间。
     *
     * @param startTimestamp 起始时间戳。
     * @param endTimestamp   结束时间戳。
     * @param preset         预设名称。
     */
    public static void requireStartBeforeEnd(long startTimestamp, long endTimestamp, String preset) {
        if (startTimestamp >= endTimestamp) {
            throw new IllegalArgumentException(
                    "预设 " + preset + " 要求 start_timestamp 严格小于 end_timestamp"
            );
        }
    }

    private static int requireInteger(Object obj, String preset, int index) {
        if (obj == null) {
            throw new IllegalArgumentException("预设 " + preset + " 的 objs[" + index + "] 不能为空");
        }
        if (isDefaultPlaceholder(obj)) {
            throw new IllegalArgumentException("预设 " + preset + " 的 objs[" + index + "] 不允许为 \"-\"");
        }
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        if (obj instanceof String) {
            String text = ((String) obj).trim();
            if (text.isEmpty()) {
                throw new IllegalArgumentException("预设 " + preset + " 的 objs[" + index + "] 不能为空字符串");
            }
            try {
                return Integer.parseInt(text);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(
                        "预设 " + preset + " 的 objs[" + index + "] 不是合法 Integer: " + text
                );
            }
        }
        throw typeMismatch(preset, index, obj, "Integer");
    }

    private static String asRequiredString(Object obj, String preset, int index) {
        if (obj == null) {
            throw new IllegalArgumentException("预设 " + preset + " 的 objs[" + index + "] 不能为空");
        }
        if (!(obj instanceof String)) {
            throw typeMismatch(preset, index, obj, "String");
        }
        String text = ((String) obj).trim();
        if (text.isEmpty()) {
            throw new IllegalArgumentException("预设 " + preset + " 的 objs[" + index + "] 不能为空字符串");
        }
        if (Objects.equals(DEFAULT_PLACEHOLDER, text)) {
            throw new IllegalArgumentException("预设 " + preset + " 的 objs[" + index + "] 不允许为 \"-\"");
        }
        return text;
    }

    private static boolean isDefaultPlaceholder(Object obj) {
        return obj instanceof String && Objects.equals(DEFAULT_PLACEHOLDER, ((String) obj).trim());
    }

    private static IllegalArgumentException typeMismatch(String preset, int index, Object obj, String expected) {
        return new IllegalArgumentException(
                "预设 " + preset + " 的 objs[" + index + "] 类型不匹配，期望 " + expected + "，实际 "
                        + (obj == null ? "null" : obj.getClass().getName())
        );
    }

    private DateParameterHelper() {
        throw new IllegalStateException("禁止实例化");
    }
}
