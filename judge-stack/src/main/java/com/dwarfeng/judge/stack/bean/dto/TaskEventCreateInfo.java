package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务事件创建信息。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class TaskEventCreateInfo implements Dto {

    private static final long serialVersionUID = 7700329066740496526L;

    private LongIdKey taskKey;
    private String message;

    public TaskEventCreateInfo() {
    }

    public TaskEventCreateInfo(LongIdKey taskKey, String message) {
        this.taskKey = taskKey;
        this.message = message;
    }

    public LongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(LongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TaskEventCreateInfo{" +
                "taskKey=" + taskKey +
                ", message='" + message + '\'' +
                '}';
    }
}
