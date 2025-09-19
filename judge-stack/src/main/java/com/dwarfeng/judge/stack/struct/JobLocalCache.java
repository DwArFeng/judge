package com.dwarfeng.judge.stack.struct;

import com.dwarfeng.judge.stack.bean.entity.AnalyserInfo;
import com.dwarfeng.judge.stack.bean.entity.JudgerInfo;
import com.dwarfeng.judge.stack.bean.entity.Section;
import com.dwarfeng.judge.stack.bean.entity.VisualizerInfo;
import com.dwarfeng.judge.stack.handler.Analyser;
import com.dwarfeng.judge.stack.handler.Judger;
import com.dwarfeng.judge.stack.handler.Visualizer;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.List;
import java.util.Map;

/**
 * 作业本地缓存。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
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
     * 判断器信息主键列表。
     *
     * <p>
     * 保证主键对应的判断器使能。
     *
     * <p>
     * 按照主键对应的判断器索引升序排序。
     *
     * <p>
     * 如果没有符合条件的判断器信息，则返回空列表。
     */
    private final List<LongIdKey> judgerInfoKeys;
    private final Map<LongIdKey, JudgerInfo> judgerInfoMap;
    private final Map<LongIdKey, Judger> judgerMap;

    /**
     * 可视化器信息主键列表。
     *
     * <p>
     * 保证主键对应的可视化器使能。
     *
     * <p>
     * 按照主键对应的可视化器索引升序排序。
     *
     * <p>
     * 如果没有符合条件的可视化器信息，则返回空列表。
     */
    private final List<LongIdKey> visualizerInfoKeys;
    private final Map<LongIdKey, VisualizerInfo> visualizerInfoMap;
    private final Map<LongIdKey, Visualizer> visualizerMap;

    public JobLocalCache(
            Section section, List<LongIdKey> analyserInfoKeys, Map<LongIdKey, AnalyserInfo> analyserInfoMap,
            Map<LongIdKey, Analyser> analyserMap, List<LongIdKey> judgerInfoKeys,
            Map<LongIdKey, JudgerInfo> judgerInfoMap, Map<LongIdKey, Judger> judgerMap,
            List<LongIdKey> visualizerInfoKeys, Map<LongIdKey, VisualizerInfo> visualizerInfoMap,
            Map<LongIdKey, Visualizer> visualizerMap
    ) {
        this.section = section;
        this.analyserInfoKeys = analyserInfoKeys;
        this.analyserInfoMap = analyserInfoMap;
        this.analyserMap = analyserMap;
        this.judgerInfoKeys = judgerInfoKeys;
        this.judgerInfoMap = judgerInfoMap;
        this.judgerMap = judgerMap;
        this.visualizerInfoKeys = visualizerInfoKeys;
        this.visualizerInfoMap = visualizerInfoMap;
        this.visualizerMap = visualizerMap;
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

    public List<LongIdKey> getJudgerInfoKeys() {
        return judgerInfoKeys;
    }

    public Map<LongIdKey, JudgerInfo> getJudgerInfoMap() {
        return judgerInfoMap;
    }

    public Map<LongIdKey, Judger> getJudgerMap() {
        return judgerMap;
    }

    public List<LongIdKey> getVisualizerInfoKeys() {
        return visualizerInfoKeys;
    }

    public Map<LongIdKey, VisualizerInfo> getVisualizerInfoMap() {
        return visualizerInfoMap;
    }

    public Map<LongIdKey, Visualizer> getVisualizerMap() {
        return visualizerMap;
    }

    @Override
    public String toString() {
        return "JobLocalCache{" +
                "section=" + section +
                ", analyserInfoKeys=" + analyserInfoKeys +
                ", analyserInfoMap=" + analyserInfoMap +
                ", analyserMap=" + analyserMap +
                ", judgerInfoKeys=" + judgerInfoKeys +
                ", judgerInfoMap=" + judgerInfoMap +
                ", judgerMap=" + judgerMap +
                ", visualizerInfoKeys=" + visualizerInfoKeys +
                ", visualizerInfoMap=" + visualizerInfoMap +
                ", visualizerMap=" + visualizerMap +
                '}';
    }
}
