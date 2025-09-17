package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务完成信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class TaskFinishInfo implements Dto {

    private static final long serialVersionUID = -1969261941505356391L;

    private LongIdKey taskKey;

    public TaskFinishInfo() {
    }

    public TaskFinishInfo(LongIdKey taskKey) {
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
        return "TaskFinishInfo{" +
                "taskKey=" + taskKey +
                '}';
    }
}
