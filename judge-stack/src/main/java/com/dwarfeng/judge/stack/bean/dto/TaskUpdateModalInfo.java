package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务更新模态信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class TaskUpdateModalInfo implements Dto {

    private static final long serialVersionUID = 2526024414401582954L;

    private LongIdKey taskKey;
    private String anchorMessage;

    public TaskUpdateModalInfo() {
    }

    public TaskUpdateModalInfo(LongIdKey taskKey, String anchorMessage) {
        this.taskKey = taskKey;
        this.anchorMessage = anchorMessage;
    }

    public LongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(LongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public String getAnchorMessage() {
        return anchorMessage;
    }

    public void setAnchorMessage(String anchorMessage) {
        this.anchorMessage = anchorMessage;
    }

    @Override
    public String toString() {
        return "TaskUpdateModalInfo{" +
                "taskKey=" + taskKey +
                ", anchorMessage='" + anchorMessage + '\'' +
                '}';
    }
}
