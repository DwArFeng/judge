package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 判断结果删除信息。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class JudgementRemoveInfo implements Dto {

    private static final long serialVersionUID = -672205677740257030L;

    private LongIdKey taskKey;
    private String dataStringId;

    public JudgementRemoveInfo() {
    }

    public JudgementRemoveInfo(LongIdKey taskKey, String dataStringId) {
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
        return "JudgementRemoveInfo{" +
                "taskKey=" + taskKey +
                ", dataStringId='" + dataStringId + '\'' +
                '}';
    }
}
