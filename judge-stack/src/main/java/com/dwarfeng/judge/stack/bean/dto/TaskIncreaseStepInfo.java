package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务增加步数信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class TaskIncreaseStepInfo implements Dto {

    private static final long serialVersionUID = 1617856092566376176L;

    private LongIdKey taskKey;

    public TaskIncreaseStepInfo() {
    }

    public TaskIncreaseStepInfo(LongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public LongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(LongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    @Override
    public String toString() {
        return "TaskIncreaseStepInfo{" +
                "taskKey=" + taskKey +
                '}';
    }
}
