package com.dwarfeng.judge.impl.handler.provider.date;

import com.dwarfeng.judge.sdk.handler.provider.AbstractProviderSession;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日期提供器会话。
 *
 * <p>
 * 根据 <code>ProviderSession.LookupInfo.preset</code> 生成时间点、时间范围和时间序列，
 * 所有输出统一为毫秒时间戳 <code>Long</code>。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DateProviderSession extends AbstractProviderSession {

    private static final String FIELD_TIMESTAMP = "timestamp";
    private static final String FIELD_START_TIMESTAMP = "start_timestamp";
    private static final String FIELD_END_TIMESTAMP = "end_timestamp";

    @Override
    protected void doOpenSession() {
        // 无状态实现。
    }

    @Override
    protected LookupResult doLookupData(LookupInfo info) {
        if (info == null) {
            throw new IllegalArgumentException("LookupInfo 不能为空");
        }
        String presetName = info.getPreset();
        if (presetName == null || presetName.trim().isEmpty()) {
            throw new IllegalArgumentException("preset 不能为空");
        }
        DatePreset preset = DatePreset.fromPresetName(presetName);
        DateParameterHelper.requireExactLength(info.getObjs(), presetName, preset.getExpectedLength());
        switch (preset) {
            case INSTANT_NOW:
                return executeInstantNow(presetName, info.getObjs());
            case INSTANT_YESTERDAY:
                return executeInstantYesterday(presetName, info.getObjs());
            case INSTANT_LAST_WEEK:
                return executeInstantLastWeek(presetName, info.getObjs());
            case INSTANT_LAST_MONTH:
                return executeInstantLastMonth(presetName, info.getObjs());
            case INSTANT_LAST_YEAR:
                return executeInstantLastYear(presetName, info.getObjs());
            case INSTANT_OFFSET:
                return executeInstantOffset(presetName, info.getObjs());
            case RANGE_RELATIVE:
                return executeRangeRelative(presetName, info.getObjs());
            case RANGE_DAY:
                return executeRangeDay(presetName, info.getObjs());
            case RANGE_WEEK:
                return executeRangeWeek(presetName, info.getObjs());
            case RANGE_MONTH:
                return executeRangeMonth(presetName, info.getObjs());
            case RANGE_YEAR:
                return executeRangeYear(presetName, info.getObjs());
            case RANGE_OFFSET:
                return executeRangeOffset(presetName, info.getObjs());
            case SEQUENCE_INTERVAL:
                return executeSequenceInterval(presetName, info.getObjs());
            case SEQUENCE_BUCKET:
                return executeSequenceBucket(presetName, info.getObjs());
            case SEQUENCE_RECENT_BUCKETS:
                return executeSequenceRecentBuckets(presetName, info.getObjs());
            default:
                throw new IllegalArgumentException("未知日期预设: " + presetName);
        }
    }

    private LookupResult executeInstantNow(String preset, Object[] objs) {
        String timezone = DateParameterHelper.parseOptionalTimezone(objs[0], preset, 0);
        long timestamp = System.currentTimeMillis();
        List<Map<String, Object>> datas = singleTimestampRow(timestamp);
        Map<String, Object> meta = baseMeta(preset, timezone, datas.size());
        meta.put("base_timestamp", timestamp);
        return new LookupResult(datas, meta);
    }

    private LookupResult executeInstantYesterday(String preset, Object[] objs) {
        return executeInstantCalendarOffset(preset, objs, "1d", "-1d");
    }

    private LookupResult executeInstantLastWeek(String preset, Object[] objs) {
        return executeInstantCalendarOffset(preset, objs, "1w", "-1w");
    }

    private LookupResult executeInstantLastMonth(String preset, Object[] objs) {
        return executeInstantCalendarOffset(preset, objs, "1mo", "-1mo");
    }

    private LookupResult executeInstantLastYear(String preset, Object[] objs) {
        return executeInstantCalendarOffset(preset, objs, "1y", "-1y");
    }

    @SuppressWarnings("DuplicatedCode")
    private LookupResult executeInstantCalendarOffset(
            String preset, Object[] objs, String unitLabel, String offsetExpression
    ) {
        long baseTimestamp = DateParameterHelper.parseOptionalBaseTimestamp(objs[0], preset, 0);
        String timezone = DateParameterHelper.parseOptionalTimezone(objs[1], preset, 1);
        ZoneId zoneId = ZoneId.of(timezone);
        ZonedDateTime base = ZonedDateTime.ofInstant(Instant.ofEpochMilli(baseTimestamp), zoneId);
        DateDuration offset = DateDuration.parse(offsetExpression, preset, 0);
        long timestamp = offset.applyTo(base).toInstant().toEpochMilli();
        List<Map<String, Object>> datas = singleTimestampRow(timestamp);
        Map<String, Object> meta = baseMeta(preset, timezone, datas.size());
        meta.put("base_timestamp", baseTimestamp);
        meta.put("offset", offsetExpression);
        meta.put("unit", unitLabel);
        return new LookupResult(datas, meta);
    }

    @SuppressWarnings("DuplicatedCode")
    private LookupResult executeInstantOffset(String preset, Object[] objs) {
        DateDuration offset = DateParameterHelper.requireDuration(objs[0], preset, 0);
        long baseTimestamp = DateParameterHelper.parseOptionalBaseTimestamp(objs[1], preset, 1);
        String timezone = DateParameterHelper.parseOptionalTimezone(objs[2], preset, 2);
        ZoneId zoneId = ZoneId.of(timezone);
        ZonedDateTime base = ZonedDateTime.ofInstant(Instant.ofEpochMilli(baseTimestamp), zoneId);
        long timestamp = offset.applyTo(base).toInstant().toEpochMilli();
        List<Map<String, Object>> datas = singleTimestampRow(timestamp);
        Map<String, Object> meta = baseMeta(preset, timezone, datas.size());
        meta.put("base_timestamp", baseTimestamp);
        meta.put("offset", offset.getExpression());
        return new LookupResult(datas, meta);
    }

    private LookupResult executeRangeRelative(String preset, Object[] objs) {
        DateDuration offset = DateParameterHelper.requireDuration(objs[0], preset, 0);
        offset.requireNonZero(preset, 0);
        long baseTimestamp = DateParameterHelper.parseOptionalBaseTimestamp(objs[1], preset, 1);
        String timezone = DateParameterHelper.parseOptionalTimezone(objs[2], preset, 2);
        ZoneId zoneId = ZoneId.of(timezone);
        ZonedDateTime base = ZonedDateTime.ofInstant(Instant.ofEpochMilli(baseTimestamp), zoneId);
        long offsetMillis = offset.applyTo(base).toInstant().toEpochMilli() - baseTimestamp;
        long startTimestamp;
        long endTimestamp;
        if (offsetMillis < 0) {
            startTimestamp = baseTimestamp + offsetMillis;
            endTimestamp = baseTimestamp;
        } else {
            startTimestamp = baseTimestamp;
            endTimestamp = baseTimestamp + offsetMillis;
        }
        return buildSingleRangeResult(preset, timezone, baseTimestamp, offset.getExpression(), startTimestamp, endTimestamp);
    }

    private LookupResult executeRangeDay(String preset, Object[] objs) {
        return executeNaturalRange(preset, objs, NaturalRangeType.DAY);
    }

    private LookupResult executeRangeWeek(String preset, Object[] objs) {
        return executeNaturalRange(preset, objs, NaturalRangeType.WEEK);
    }

    private LookupResult executeRangeMonth(String preset, Object[] objs) {
        return executeNaturalRange(preset, objs, NaturalRangeType.MONTH);
    }

    private LookupResult executeRangeYear(String preset, Object[] objs) {
        return executeNaturalRange(preset, objs, NaturalRangeType.YEAR);
    }

    @SuppressWarnings("DuplicatedCode")
    private LookupResult executeNaturalRange(String preset, Object[] objs, NaturalRangeType rangeType) {
        int periodOffset = DateParameterHelper.parseOptionalPeriodOffset(objs[0], preset, 0);
        long baseTimestamp = DateParameterHelper.parseOptionalBaseTimestamp(objs[1], preset, 1);
        String timezone = DateParameterHelper.parseOptionalTimezone(objs[2], preset, 2);
        ZoneId zoneId = ZoneId.of(timezone);
        ZonedDateTime base = ZonedDateTime.ofInstant(Instant.ofEpochMilli(baseTimestamp), zoneId);
        ZonedDateTime start;
        ZonedDateTime end;
        switch (rangeType) {
            case DAY:
                ZonedDateTime targetDay = base.plusDays(periodOffset);
                start = targetDay.toLocalDate().atStartOfDay(zoneId);
                end = start.plusDays(1);
                break;
            case WEEK:
                ZonedDateTime targetWeek = base.plusWeeks(periodOffset);
                start = targetWeek.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                        .toLocalDate().atStartOfDay(zoneId);
                end = start.plusWeeks(1);
                break;
            case MONTH:
                ZonedDateTime targetMonth = base.plusMonths(periodOffset);
                YearMonth yearMonth = YearMonth.from(targetMonth);
                start = yearMonth.atDay(1).atStartOfDay(zoneId);
                end = start.plusMonths(1);
                break;
            case YEAR:
                ZonedDateTime targetYear = base.plusYears(periodOffset);
                Year year = Year.from(targetYear);
                start = year.atDay(1).atStartOfDay(zoneId);
                end = start.plusYears(1);
                break;
            default:
                throw new IllegalStateException("未知自然周期类型: " + rangeType);
        }
        long startTimestamp = start.toInstant().toEpochMilli();
        long endTimestamp = end.toInstant().toEpochMilli();
        Map<String, Object> meta = baseMeta(preset, timezone, 1);
        meta.put("base_timestamp", baseTimestamp);
        meta.put("offset", periodOffset);
        meta.put("start_timestamp", startTimestamp);
        meta.put("end_timestamp", endTimestamp);
        return new LookupResult(singleRangeRow(startTimestamp, endTimestamp), meta);
    }

    private LookupResult executeRangeOffset(String preset, Object[] objs) {
        long startTimestamp = DateParameterHelper.requireLong(objs[0], preset, 0);
        long endTimestamp = DateParameterHelper.requireLong(objs[1], preset, 1);
        DateParameterHelper.requireStartBeforeEnd(startTimestamp, endTimestamp, preset);
        DateDuration offset = DateParameterHelper.requireDuration(objs[2], preset, 2);
        String timezone = DateParameterHelper.parseOptionalTimezone(objs[3], preset, 3);
        ZoneId zoneId = ZoneId.of(timezone);
        ZonedDateTime start = ZonedDateTime.ofInstant(Instant.ofEpochMilli(startTimestamp), zoneId);
        ZonedDateTime end = ZonedDateTime.ofInstant(Instant.ofEpochMilli(endTimestamp), zoneId);
        long shiftedStart = offset.applyTo(start).toInstant().toEpochMilli();
        long shiftedEnd = offset.applyTo(end).toInstant().toEpochMilli();
        return buildSingleRangeResult(
                preset, timezone, startTimestamp, offset.getExpression(), shiftedStart, shiftedEnd
        );
    }

    @SuppressWarnings("DuplicatedCode")
    private LookupResult executeSequenceInterval(String preset, Object[] objs) {
        long startTimestamp = DateParameterHelper.requireLong(objs[0], preset, 0);
        long endTimestamp = DateParameterHelper.requireLong(objs[1], preset, 1);
        DateParameterHelper.requireStartBeforeEnd(startTimestamp, endTimestamp, preset);
        DateDuration step = DateParameterHelper.requireDuration(objs[2], preset, 2);
        step.requireStrictlyPositive(preset, 2);
        String timezone = DateParameterHelper.parseOptionalTimezone(objs[3], preset, 3);
        ZoneId zoneId = ZoneId.of(timezone);
        List<Map<String, Object>> datas = new ArrayList<>();
        ZonedDateTime current = ZonedDateTime.ofInstant(Instant.ofEpochMilli(startTimestamp), zoneId);
        while (current.toInstant().toEpochMilli() < endTimestamp) {
            datas.add(singleTimestampRow(current.toInstant().toEpochMilli()).get(0));
            if (datas.size() > DateParameterHelper.MAX_SEQUENCE_COUNT) {
                throw new IllegalArgumentException(
                        "预设 " + preset + " 生成结果超过最大限制 " + DateParameterHelper.MAX_SEQUENCE_COUNT
                );
            }
            current = step.applyTo(current);
        }
        Map<String, Object> meta = baseMeta(preset, timezone, datas.size());
        meta.put("start_timestamp", startTimestamp);
        meta.put("end_timestamp", endTimestamp);
        meta.put("offset", step.getExpression());
        return new LookupResult(datas, meta);
    }

    @SuppressWarnings("DuplicatedCode")
    private LookupResult executeSequenceBucket(String preset, Object[] objs) {
        long startTimestamp = DateParameterHelper.requireLong(objs[0], preset, 0);
        long endTimestamp = DateParameterHelper.requireLong(objs[1], preset, 1);
        DateParameterHelper.requireStartBeforeEnd(startTimestamp, endTimestamp, preset);
        DateDuration bucketSize = DateParameterHelper.requireDuration(objs[2], preset, 2);
        bucketSize.requireStrictlyPositive(preset, 2);
        String timezone = DateParameterHelper.parseOptionalTimezone(objs[3], preset, 3);
        ZoneId zoneId = ZoneId.of(timezone);
        List<Map<String, Object>> datas = buildFixedStepBuckets(
                preset, startTimestamp, endTimestamp, bucketSize, zoneId
        );
        Map<String, Object> meta = baseMeta(preset, timezone, datas.size());
        meta.put("start_timestamp", startTimestamp);
        meta.put("end_timestamp", endTimestamp);
        meta.put("offset", bucketSize.getExpression());
        return new LookupResult(datas, meta);
    }

    private LookupResult executeSequenceRecentBuckets(String preset, Object[] objs) {
        DateDuration bucketSize = DateParameterHelper.requireDuration(objs[0], preset, 0);
        bucketSize.requireStrictlyPositive(preset, 0);
        int count = DateParameterHelper.requirePositiveCount(objs[1], preset, 1);
        long baseTimestamp = DateParameterHelper.parseOptionalBaseTimestamp(objs[2], preset, 2);
        String timezone = DateParameterHelper.parseOptionalTimezone(objs[3], preset, 3);
        ZoneId zoneId = ZoneId.of(timezone);
        DateDuration backwardBucketSize = bucketSize.negate();
        List<Map<String, Object>> datas = new ArrayList<>(count);
        ZonedDateTime bucketEnd = ZonedDateTime.ofInstant(Instant.ofEpochMilli(baseTimestamp), zoneId);
        for (int index = 0; index < count; index++) {
            ZonedDateTime bucketStart = backwardBucketSize.applyTo(bucketEnd);
            long startMillis = bucketStart.toInstant().toEpochMilli();
            long endMillis = bucketEnd.toInstant().toEpochMilli();
            datas.add(0, singleRangeRow(startMillis, endMillis).get(0));
            bucketEnd = bucketStart;
        }
        Map<String, Object> meta = baseMeta(preset, timezone, datas.size());
        meta.put("base_timestamp", baseTimestamp);
        meta.put("count", count);
        meta.put("offset", bucketSize.getExpression());
        return new LookupResult(datas, meta);
    }

    private List<Map<String, Object>> buildFixedStepBuckets(
            String preset, long startTimestamp, long endTimestamp, DateDuration step, ZoneId zoneId
    ) {
        List<Map<String, Object>> datas = new ArrayList<>();
        ZonedDateTime current = ZonedDateTime.ofInstant(Instant.ofEpochMilli(startTimestamp), zoneId);
        ZonedDateTime end = ZonedDateTime.ofInstant(Instant.ofEpochMilli(endTimestamp), zoneId);
        while (current.toInstant().toEpochMilli() < endTimestamp) {
            ZonedDateTime next = step.applyTo(current);
            long bucketEndMillis = Math.min(next.toInstant().toEpochMilli(), endTimestamp);
            datas.add(singleRangeRow(current.toInstant().toEpochMilli(), bucketEndMillis).get(0));
            current = next;
            if (datas.size() > DateParameterHelper.MAX_SEQUENCE_COUNT) {
                throw new IllegalArgumentException(
                        "预设 " + preset + " 生成结果超过最大限制 " + DateParameterHelper.MAX_SEQUENCE_COUNT
                );
            }
            if (!current.isBefore(end)) {
                break;
            }
        }
        return datas;
    }

    @SuppressWarnings("DuplicatedCode")
    private LookupResult buildSingleRangeResult(
            String preset, String timezone, long baseTimestamp, String offsetText,
            long startTimestamp, long endTimestamp
    ) {
        Map<String, Object> meta = baseMeta(preset, timezone, 1);
        meta.put("base_timestamp", baseTimestamp);
        meta.put("offset", offsetText);
        meta.put("start_timestamp", startTimestamp);
        meta.put("end_timestamp", endTimestamp);
        return new LookupResult(singleRangeRow(startTimestamp, endTimestamp), meta);
    }

    private List<Map<String, Object>> singleTimestampRow(long timestamp) {
        List<Map<String, Object>> datas = new ArrayList<>(1);
        Map<String, Object> row = new HashMap<>(2);
        row.put(FIELD_TIMESTAMP, timestamp);
        datas.add(row);
        return datas;
    }

    private List<Map<String, Object>> singleRangeRow(long startTimestamp, long endTimestamp) {
        List<Map<String, Object>> datas = new ArrayList<>(1);
        Map<String, Object> row = new HashMap<>(4);
        row.put(FIELD_START_TIMESTAMP, startTimestamp);
        row.put(FIELD_END_TIMESTAMP, endTimestamp);
        datas.add(row);
        return datas;
    }

    private Map<String, Object> baseMeta(String preset, String timezone, int dataCount) {
        Map<String, Object> meta = new HashMap<>(8);
        meta.put("preset", preset);
        meta.put("timezone", timezone);
        meta.put("data_count", dataCount);
        return meta;
    }

    @Override
    protected void doCloseSession() {
        // 无状态实现。
    }

    /**
     * 自然周期类型。
     */
    private enum NaturalRangeType {
        DAY,
        WEEK,
        MONTH,
        YEAR
    }

    @Override
    public String toString() {
        return "DateProviderSession{}";
    }
}
