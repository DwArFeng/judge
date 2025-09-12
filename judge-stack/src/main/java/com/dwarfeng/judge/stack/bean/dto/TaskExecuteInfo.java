package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务执行信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class TaskExecuteInfo implements Dto {

    private static final long serialVersionUID = -5996691865392265712L;

    private LongIdKey taskKey;

    public TaskExecuteInfo() {
    }

    public TaskExecuteInfo(LongIdKey taskKey) {
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
        return "TaskExecuteInfo{" +
                "taskKey=" + taskKey +
                '}';
    }
}
