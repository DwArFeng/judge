package com.dwarfeng.judge.stack.struct;

import com.dwarfeng.judge.stack.bean.entity.AlarmSetting;
import com.dwarfeng.judge.stack.bean.entity.AnalyserInfo;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.handler.Analyser;
import com.dwarfeng.judge.stack.handler.Judger;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;

/**
 * 作业本地缓存。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public final class JobLocalCache {

    private final Section section;

    /**
     * 分析器信息主键列表。
     *
     * <p>
     * 保证主键对应的分析器使能。
     *
     * <p>
     * 按照主键对应的分析器索引升序排序。
     *
     * <p>
     * 如果没有符合条件的分析器信息，则返回空列表。
     */
    private final List<LongIdKey> analyserInfoKeys;
    private final Map<LongIdKey, AnalyserInfo> analyserInfoMap;
    private final Map<LongIdKey, Analyser> analyserMap;

    /**
     * 判断器信息主键。
     *
     * <p>
     * 保证主键对应的判断器使能。
     *
     * <p>
     * 按照判断器信息索引字段降序排序，取第一个判断器信息。
     *
     * <p>
     * 如果判断器信息索引字段相等，则按照主键升序排序，取第一个判断器信息。
     *
     * <p>
     * 如果没有符合条件的判断器信息，则该字段为 <code>null</code>。
     */
    @Nullable
    private final LongIdKey judgerInfoKey;

    /**
     * 判断器信息。
     *
     * <p>
     * 判断器主键对应的信息，获取逻辑可参照 {@link #judgerInfoKey} 字段。
     *
     * <p>
     * 如果没有符合条件的判断器信息，则该字段为 <code>null</code>。
     */
    private final JudgerInfo judgerInfo;

    /**
     * 判断器。
     *
     * <p>
     * 判断器信息对应的判断器。
     *
     * <p>
     * 如果没有符合条件的判断器信息，则该字段为 <code>null</code>。
     */
    @Nullable
    private final Judger judger;

    /**
     * 报警设置列表。
     *
     * <p>
     * 保证报警设置使能。
     *
     * <p>
     * 按照报警设置的阈值字段降序排序。
     *
     * <p>
     * 如果阈值字段相等，则按照主键升序排序。
     *
     * <p>
     * 如果没有符合条件的报警设置，则返回空列表。
     */
    private final List<AlarmSetting> alarmSettings;

    public JobLocalCache(
            Section section, List<LongIdKey> analyserInfoKeys, Map<LongIdKey, AnalyserInfo> analyserInfoMap,
            Map<LongIdKey, Analyser> analyserMap, @Nullable LongIdKey judgerInfoKey, JudgerInfo judgerInfo,
            @Nullable Judger judger, List<AlarmSetting> alarmSettings
    ) {
        this.section = section;
        this.analyserInfoKeys = analyserInfoKeys;
        this.analyserInfoMap = analyserInfoMap;
        this.analyserMap = analyserMap;
        this.judgerInfoKey = judgerInfoKey;
        this.judgerInfo = judgerInfo;
        this.judger = judger;
        this.alarmSettings = alarmSettings;
    }

    public Section getSection() {
        return section;
    }

    public List<LongIdKey> getAnalyserInfoKeys() {
        return analyserInfoKeys;
    }

    public Map<LongIdKey, AnalyserInfo> getAnalyserInfoMap() {
        return analyserInfoMap;
    }

    public Map<LongIdKey, Analyser> getAnalyserMap() {
        return analyserMap;
    }

    @Nullable
    public LongIdKey getJudgerInfoKey() {
        return judgerInfoKey;
    }

    public JudgerInfo getJudgerInfo() {
        return judgerInfo;
    }

    @Nullable
    public Judger getJudger() {
        return judger;
    }

    public List<AlarmSetting> getAlarmSettings() {
        return alarmSettings;
    }

    @Override
    public String toString() {
        return "JobLocalCache{" +
                "section=" + section +
                ", analyserInfoKeys=" + analyserInfoKeys +
                ", analyserInfoMap=" + analyserInfoMap +
                ", analyserMap=" + analyserMap +
                ", judgerInfoKey=" + judgerInfoKey +
                ", judgerInfo=" + judgerInfo +
                ", judger=" + judger +
                ", alarmSettings=" + alarmSettings +
                '}';
    }
}
