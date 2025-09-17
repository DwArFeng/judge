package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 作业创建结果。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class JobCreateResult implements Dto {

    private static final long serialVersionUID = 821826706147575051L;

    private LongIdKey taskKey;

    public JobCreateResult() {
    }

    public JobCreateResult(LongIdKey taskKey) {
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
        return "JobCreateResult{" +
                "taskKey=" + taskKey +
                '}';
    }
}
