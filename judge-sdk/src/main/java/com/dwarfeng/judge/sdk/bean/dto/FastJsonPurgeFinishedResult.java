package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.PurgeFinishedResult;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.util.Objects;

/**
 * FastJson 清除结束结果。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class FastJsonPurgeFinishedResult implements Dto {

    private static final long serialVersionUID = -3295580874275836559L;

    public static FastJsonPurgeFinishedResult of(PurgeFinishedResult purgeFinishResult) {
        if (Objects.isNull(purgeFinishResult)) {
            return null;
        } else {
            return new FastJsonPurgeFinishedResult(
                    purgeFinishResult.getTaskDeletionCount(),
                    purgeFinishResult.isTaskDivergent()
            );
        }
    }

    @JSONField(name = "derive_history_deletion_count", ordinal = 1)
    private int taskDeletionCount;

    @JSONField(name = "derive_history_divergent", ordinal = 2)
    private boolean taskDivergent;

    public FastJsonPurgeFinishedResult() {
    }

    public FastJsonPurgeFinishedResult(int taskDeletionCount, boolean taskDivergent) {
        this.taskDeletionCount = taskDeletionCount;
        this.taskDivergent = taskDivergent;
    }

    public int getTaskDeletionCount() {
        return taskDeletionCount;
    }

    public void setTaskDeletionCount(int taskDeletionCount) {
        this.taskDeletionCount = taskDeletionCount;
    }

    public boolean isTaskDivergent() {
        return taskDivergent;
    }

    public void setTaskDivergent(boolean taskDivergent) {
        this.taskDivergent = taskDivergent;
    }

    @Override
    public String toString() {
        return "FastJsonPurgeFinishedResult{" +
                "taskDeletionCount=" + taskDeletionCount +
                ", taskDivergent=" + taskDivergent +
                '}';
    }
}
