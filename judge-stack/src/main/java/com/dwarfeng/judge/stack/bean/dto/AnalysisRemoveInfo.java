package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 分析结果删除信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class AnalysisRemoveInfo implements Dto {

    private static final long serialVersionUID = -7286390398065651995L;

    private LongIdKey taskKey;
    private String dataStringId;

    public AnalysisRemoveInfo() {
    }

    public AnalysisRemoveInfo(LongIdKey taskKey, String dataStringId) {
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
        return "AnalysisRemoveInfo{" +
                "taskKey=" + taskKey +
                ", dataStringId='" + dataStringId + '\'' +
                '}';
    }
}
