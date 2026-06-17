package com.dwarfeng.judge.impl.handler.provider.date;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期提供器预设定义。
 *
 * <p>
 * 每个预设规定固定长度的 <code>objs</code> 参数，并提供对应的期望长度。
 *
 * @author DwArFeng
 * @since 2.6.1
 */
enum DatePreset {

    INSTANT_NOW("instant.now", 1),
    INSTANT_YESTERDAY("instant.yesterday", 2),
    INSTANT_LAST_WEEK("instant.last_week", 2),
    INSTANT_LAST_MONTH("instant.last_month", 2),
    INSTANT_LAST_YEAR("instant.last_year", 2),
    INSTANT_OFFSET("instant.offset", 3),

    RANGE_RELATIVE("range.relative", 3),
    RANGE_DAY("range.day", 3),
    RANGE_WEEK("range.week", 3),
    RANGE_MONTH("range.month", 3),
    RANGE_YEAR("range.year", 3),
    RANGE_OFFSET("range.offset", 4),

    SEQUENCE_INTERVAL("sequence.interval", 4),
    SEQUENCE_BUCKET("sequence.bucket", 4),
    SEQUENCE_RECENT_BUCKETS("sequence.recent_buckets", 4);

    private static final Map<String, DatePreset> PRESET_MAP;

    static {
        Map<String, DatePreset> map = new HashMap<>(16);
        for (DatePreset preset : values()) {
            map.put(preset.presetName, preset);
        }
        PRESET_MAP = Collections.unmodifiableMap(map);
    }

    private final String presetName;
    private final int expectedLength;

    DatePreset(String presetName, int expectedLength) {
        this.presetName = presetName;
        this.expectedLength = expectedLength;
    }

    // 为了保证代码的可读性，此处代码不做简化。
    @SuppressWarnings("unused")
    public String getPresetName() {
        return presetName;
    }

    public int getExpectedLength() {
        return expectedLength;
    }

    /**
     * 根据预设名称解析枚举值。
     *
     * @param presetName 预设名称。
     * @return 预设枚举。
     */
    public static DatePreset fromPresetName(String presetName) {
        DatePreset preset = PRESET_MAP.get(presetName);
        if (preset == null) {
            throw new IllegalArgumentException("未知日期预设: " + presetName);
        }
        return preset;
    }
}
