package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 清除结束结果。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class PurgeFinishedResult implements Dto {

    private static final long serialVersionUID = 4557873985601116898L;

    /**
     * 任务实体的删除数量。
     */
    private int taskDeletionCount;

    /**
     * 任务是否发散。
     *
     * <p>
     * 发散的意思是，在任务执行间隔中，生成的实体数量大于清除的数量。<br>
     * 如果不处理发散情况，会导致数据的积压。
     */
    private boolean taskDivergent;

    public PurgeFinishedResult() {
    }

    public PurgeFinishedResult(int taskDeletionCount, boolean taskDivergent) {
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
        return "PurgeFinishedResult{" +
                "taskDeletionCount=" + taskDeletionCount +
                ", taskDivergent=" + taskDivergent +
                '}';
    }
}
