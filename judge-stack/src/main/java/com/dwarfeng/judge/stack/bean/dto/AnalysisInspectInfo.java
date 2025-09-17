package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果查看信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class AnalysisInspectInfo implements Dto {

    private static final long serialVersionUID = 8411576973303839782L;

    private LongIdKey taskKey;
    private String dataStringId;

    public AnalysisInspectInfo() {
    }

    public AnalysisInspectInfo(LongIdKey taskKey, String dataStringId) {
        this.taskKey = taskKey;
        this.dataStringId = dataStringId;
    }

    public LongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(LongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public String getDataStringId() {
        return dataStringId;
    }

    public void setDataStringId(String dataStringId) {
        this.dataStringId = dataStringId;
    }

    @Override
    public String toString() {
        return "AnalysisInspectInfo{" +
                "taskKey=" + taskKey +
                ", dataStringId='" + dataStringId + '\'' +
                '}';
    }
}
