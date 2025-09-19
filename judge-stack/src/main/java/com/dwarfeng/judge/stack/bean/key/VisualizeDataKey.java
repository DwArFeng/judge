package com.dwarfeng.judge.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 可视化数据键。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class VisualizeDataKey implements Key {

    private static final long serialVersionUID = -4975931358310861310L;

    private Long taskLongId;
    private String perspectiveStringId;

    public VisualizeDataKey() {
    }

    public VisualizeDataKey(Long taskLongId, String perspectiveStringId) {
        this.taskLongId = taskLongId;
        this.perspectiveStringId = perspectiveStringId;
    }

    public Long getTaskLongId() {
        return taskLongId;
    }

    public void setTaskLongId(Long taskLongId) {
        this.taskLongId = taskLongId;
    }

    public String getPerspectiveStringId() {
        return perspectiveStringId;
    }

    public void setPerspectiveStringId(String perspectiveStringId) {
        this.perspectiveStringId = perspectiveStringId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        VisualizeDataKey that = (VisualizeDataKey) o;
        return Objects.equals(taskLongId, that.taskLongId) &&
                Objects.equals(perspectiveStringId, that.perspectiveStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(taskLongId);
        result = 31 * result + Objects.hashCode(perspectiveStringId);
        return result;
    }

    @Override
    public String toString() {
        return "VisualizeDataKey{" +
                "taskLongId=" + taskLongId +
                ", perspectiveStringId='" + perspectiveStringId + '\'' +
                '}';
    }
}
