package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务开始信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class TaskStartInfo implements Dto {

    private static final long serialVersionUID = -4329057922087385537L;

    private LongIdKey taskKey;

    public TaskStartInfo() {
    }

    public TaskStartInfo(LongIdKey taskKey) {
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
        return "TaskStartInfo{" +
                "taskKey=" + taskKey +
                '}';
    }
}
