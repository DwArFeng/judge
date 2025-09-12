package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务死亡信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class TaskDieInfo implements Dto {

    private static final long serialVersionUID = -6285404711813196110L;

    private LongIdKey taskKey;

    public TaskDieInfo() {
    }

    public TaskDieInfo(LongIdKey taskKey) {
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
        return "TaskDieInfo{" +
                "taskKey=" + taskKey +
                '}';
    }
}
