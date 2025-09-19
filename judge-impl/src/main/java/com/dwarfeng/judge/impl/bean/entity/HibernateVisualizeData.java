package com.dwarfeng.judge.impl.bean.entity;

import com.dwarfeng.judge.impl.bean.key.HibernateVisualizeDataKey;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

/**
 * Hibernate 可视化数据。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
@Entity
@IdClass(HibernateVisualizeDataKey.class)
@Table(name = "tbl_visualize_data")
public class HibernateVisualizeData implements Bean {

    private static final long serialVersionUID = -8711444215388136131L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "task_id", nullable = false)
    private Long taskLongId;

    @Id
    @Column(name = "perspective_id", length = Constraints.LENGTH_STRING_ID, nullable = false)
    private String perspectiveStringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateTask.class)
    @JoinColumns({ //
            @JoinColumn(name = "task_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateTask task;

    public HibernateVisualizeData() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateVisualizeDataKey getKey() {
        if (Objects.isNull(taskLongId) || Objects.isNull(perspectiveStringId)) {
            return null;
        }
        return new HibernateVisualizeDataKey(taskLongId, perspectiveStringId);
    }

    public void setKey(HibernateVisualizeDataKey key) {
        if (Objects.isNull(key)) {
            this.taskLongId = null;
            this.perspectiveStringId = null;
        } else {
            this.taskLongId = key.getTaskLongId();
            this.perspectiveStringId = key.getPerspectiveStringId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public HibernateTask getTask() {
        return task;
    }

    public void setTask(HibernateTask task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "taskLongId = " + taskLongId + ", " +
                "perspectiveStringId = " + perspectiveStringId + ", " +
                "content = " + content + ", " +
                "task = " + task + ")";
    }
}
