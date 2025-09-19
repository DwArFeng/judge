package com.dwarfeng.judge.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * Hibernate 可视化数据键。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class HibernateVisualizeDataKey implements Key {

    private static final long serialVersionUID = -8325544546160318500L;

    private Long taskLongId;
    private String perspectiveStringId;

    public HibernateVisualizeDataKey() {
    }

    public HibernateVisualizeDataKey(Long taskLongId, String perspectiveStringId) {
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

        HibernateVisualizeDataKey that = (HibernateVisualizeDataKey) o;
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
        return "HibernateVisualizeDataKey{" +
                "taskLongId=" + taskLongId +
                ", perspectiveStringId='" + perspectiveStringId + '\'' +
                '}';
    }
}
