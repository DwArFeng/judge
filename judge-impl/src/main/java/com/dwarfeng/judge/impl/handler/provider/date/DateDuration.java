package com.dwarfeng.judge.impl.handler.provider.date;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Flux 风格的偏移时长。
 *
 * <p>
 * 支持单段或复合表达式，例如 <code>-500ms</code>、<code>-1h</code>、<code>1h30m</code>。
 * 月份使用 <code>mo</code>，分钟使用 <code>m</code>；不允许空格与小数。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
final class DateDuration {

    private static final Pattern SEGMENT_PATTERN = Pattern.compile("(-?\\d+)(ms|mo|y|w|d|h|m|s)");

    private final List<Segment> segments;
    private final String expression;

    private DateDuration(String expression, List<Segment> segments) {
        this.expression = expression;
        this.segments = Collections.unmodifiableList(new ArrayList<>(segments));
    }

    /**
     * 解析 Flux 风格 duration 字符串。
     *
     * @param expression duration 表达式。
     * @param preset     当前预设，用于异常定位。
     * @param index      参数索引，用于异常定位。
     * @return 解析结果。
     */
    public static DateDuration parse(String expression, String preset, int index) {
        if (expression == null) {
            throw new IllegalArgumentException("预设 " + preset + " 的 objs[" + index + "] 不能为空");
        }
        if (expression.isEmpty()) {
            throw new IllegalArgumentException("预设 " + preset + " 的 objs[" + index + "] 不能为空字符串");
        }
        if (Objects.equals(DateParameterHelper.DEFAULT_PLACEHOLDER, expression)) {
            throw new IllegalArgumentException("预设 " + preset + " 的 objs[" + index + "] 不允许为 \"-\"");
        }
        if (expression.indexOf(' ') >= 0 || expression.indexOf('.') >= 0) {
            throw new IllegalArgumentException(
                    "预设 " + preset + " 的 objs[" + index + "] 包含非法字符，duration 不允许空格与小数"
            );
        }
        Matcher matcher = SEGMENT_PATTERN.matcher(expression);
        int position = 0;
        List<Segment> parsedSegments = new ArrayList<>();
        while (matcher.find()) {
            if (matcher.start() != position) {
                throw new IllegalArgumentException(
                        "预设 " + preset + " 的 objs[" + index + "] 包含未知 duration 单位: "
                                + expression.substring(position)
                );
            }
            long amount = Long.parseLong(matcher.group(1));
            DurationUnit unit = DurationUnit.fromToken(matcher.group(2));
            parsedSegments.add(new Segment(amount, unit));
            position = matcher.end();
        }
        if (position != expression.length()) {
            throw new IllegalArgumentException(
                    "预设 " + preset + " 的 objs[" + index + "] 包含未知 duration 单位: "
                            + expression.substring(position)
            );
        }
        if (parsedSegments.isEmpty()) {
            throw new IllegalArgumentException("预设 " + preset + " 的 objs[" + index + "] 不是合法的 duration");
        }
        return new DateDuration(expression, parsedSegments);
    }

    /**
     * 返回原始 duration 表达式。
     *
     * @return duration 表达式。
     */
    public String getExpression() {
        return expression;
    }

    /**
     * 返回符号取反后的 duration。
     *
     * @return 取反后的 duration。
     */
    public DateDuration negate() {
        List<Segment> reversedSegments = new ArrayList<>(segments.size());
        for (Segment segment : segments) {
            reversedSegments.add(new Segment(-segment.amount, segment.unit));
        }
        if (expression.startsWith("-")) {
            return new DateDuration(expression.substring(1), reversedSegments);
        }
        return new DateDuration("-" + expression, reversedSegments);
    }

    /**
     * 将 duration 应用到指定时区时间点。
     *
     * @param zonedDateTime 基准时间。
     * @return 偏移后的时间。
     */
    public ZonedDateTime applyTo(ZonedDateTime zonedDateTime) {
        ZonedDateTime result = zonedDateTime;
        for (Segment segment : segments) {
            result = segment.applyTo(result);
        }
        return result;
    }

    /**
     * 计算 duration 对应的毫秒偏移量。
     *
     * <p>
     * 仅适用于不包含日历语义单位 <code>mo</code>、<code>y</code> 的 duration。
     *
     * @param preset 当前预设，用于异常定位。
     * @param index  参数索引，用于异常定位。
     * @return 毫秒偏移量。
     */
    // 为了保证代码的可读性，此处代码不做简化。
    @SuppressWarnings("unused")
    public long toEpochMilliOffset(String preset, int index) {
        long totalMillis = 0L;
        for (Segment segment : segments) {
            if (segment.unit == DurationUnit.MO || segment.unit == DurationUnit.Y) {
                throw new IllegalArgumentException(
                        "预设 " + preset + " 的 objs[" + index + "] 包含日历语义单位，不能转换为固定毫秒偏移"
                );
            }
            totalMillis += segment.toEpochMilliOffset();
        }
        return totalMillis;
    }

    /**
     * 校验 duration 必须严格为正。
     *
     * @param preset 当前预设，用于异常定位。
     * @param index  参数索引，用于异常定位。
     */
    public void requireStrictlyPositive(String preset, int index) {
        ZonedDateTime base = ZonedDateTime.of(2020, 6, 15, 12, 0, 0, 0, DateParameterHelper.DEFAULT_ZONE_ID);
        long before = base.toInstant().toEpochMilli();
        long after = applyTo(base).toInstant().toEpochMilli();
        if (after <= before) {
            throw new IllegalArgumentException("预设 " + preset + " 的 objs[" + index + "] 必须为正 duration");
        }
    }

    /**
     * 校验 duration 不允许为零。
     *
     * @param preset 当前预设，用于异常定位。
     * @param index  参数索引，用于异常定位。
     */
    // 为了保证代码的可读性，此处代码不做简化。
    @SuppressWarnings("SameParameterValue")
    void requireNonZero(String preset, int index) {
        ZonedDateTime base = ZonedDateTime.of(2020, 6, 15, 12, 0, 0, 0, DateParameterHelper.DEFAULT_ZONE_ID);
        if (applyTo(base).toInstant().toEpochMilli() == base.toInstant().toEpochMilli()) {
            throw new IllegalArgumentException("预设 " + preset + " 的 objs[" + index + "] 不允许为 0ms");
        }
    }

    /**
     * duration 片段。
     */
    private static final class Segment {

        private final long amount;
        private final DurationUnit unit;

        private Segment(long amount, DurationUnit unit) {
            this.amount = amount;
            this.unit = unit;
        }

        private ZonedDateTime applyTo(ZonedDateTime zonedDateTime) {
            switch (unit) {
                case MS:
                    return zonedDateTime.plus(amount, java.time.temporal.ChronoUnit.MILLIS);
                case S:
                    return zonedDateTime.plusSeconds(amount);
                case M:
                    return zonedDateTime.plusMinutes(amount);
                case H:
                    return zonedDateTime.plusHours(amount);
                case D:
                    return zonedDateTime.plusDays(amount);
                case W:
                    return zonedDateTime.plusWeeks(amount);
                case MO:
                    return zonedDateTime.plusMonths(amount);
                case Y:
                    return zonedDateTime.plusYears(amount);
                default:
                    throw new IllegalStateException("未知 duration 单位: " + unit);
            }
        }

        private long toEpochMilliOffset() {
            switch (unit) {
                case MS:
                    return amount;
                case S:
                    return amount * 1000L;
                case M:
                    return amount * 60_000L;
                case H:
                    return amount * 3_600_000L;
                case D:
                    return amount * 86_400_000L;
                case W:
                    return amount * 7 * 86_400_000L;
                default:
                    throw new IllegalStateException("不支持转换为毫秒偏移的单位: " + unit);
            }
        }
    }

    /**
     * duration 单位。
     */
    private enum DurationUnit {
        MS("ms"),
        S("s"),
        M("m"),
        H("h"),
        D("d"),
        W("w"),
        MO("mo"),
        Y("y");

        private final String token;

        DurationUnit(String token) {
            this.token = token;
        }

        public static DurationUnit fromToken(String token) {
            for (DurationUnit unit : values()) {
                if (unit.token.equals(token)) {
                    return unit;
                }
            }
            throw new IllegalArgumentException("未知 duration 单位: " + token);
        }
    }
}
