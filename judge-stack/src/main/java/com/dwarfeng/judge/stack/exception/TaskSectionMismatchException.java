package com.dwarfeng.judge.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 任务与部件归属不匹配异常。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class TaskSectionMismatchException extends HandlerException {

    private static final long serialVersionUID = -689668728951317735L;

    private final LongIdKey taskKey;
    private final LongIdKey taskSectionKey;
    private final LongIdKey expectedSectionKey;

    public TaskSectionMismatchException(LongIdKey taskKey, LongIdKey taskSectionKey, LongIdKey expectedSectionKey) {
        this.taskKey = taskKey;
        this.taskSectionKey = taskSectionKey;
        this.expectedSectionKey = expectedSectionKey;
    }

    public TaskSectionMismatchException(
            Throwable cause, LongIdKey taskKey, LongIdKey taskSectionKey, LongIdKey expectedSectionKey
    ) {
        super(cause);
        this.taskKey = taskKey;
        this.taskSectionKey = taskSectionKey;
        this.expectedSectionKey = expectedSectionKey;
    }

    public LongIdKey getTaskKey() {
        return taskKey;
    }

    public LongIdKey getTaskSectionKey() {
        return taskSectionKey;
    }

    public LongIdKey getExpectedSectionKey() {
        return expectedSectionKey;
    }

    @Override
    public String getMessage() {
        return "任务与部件归属不匹配, 任务主键: " + taskKey + ", 任务所属部件: " + taskSectionKey
                + ", 期望部件: " + expectedSectionKey;
    }
}
