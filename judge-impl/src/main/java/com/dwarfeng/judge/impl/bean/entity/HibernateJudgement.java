package com.dwarfeng.judge.impl.bean.entity;

import com.dwarfeng.judge.impl.bean.key.HibernateJudgementKey;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Objects;

/**
 * Hibernate 判断结果。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
@Entity
@IdClass(HibernateJudgementKey.class)
@Table(name = "tbl_judgement")
public class HibernateJudgement implements Bean {

    private static final long serialVersionUID = -1443523848741582828L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "task_id", nullable = false)
    private Long taskLongId;

    @Id
    @Column(name = "data_id", length = Constraints.LENGTH_STRING_ID, nullable = false)
    private String dataStringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "value", nullable = false)
    private double value;

    @Column(name = "message", length = Constraints.LENGTH_MESSAGE)
    private String message;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateTask.class)
    @JoinColumns({ //
            @JoinColumn(name = "task_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateTask task;

    public HibernateJudgement() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateJudgementKey getKey() {
        if (Objects.isNull(taskLongId) || Objects.isNull(dataStringId)) {
            return null;
        }
        return new HibernateJudgementKey(taskLongId, dataStringId);
    }

    public void setKey(HibernateJudgementKey key) {
        if (Objects.isNull(key)) {
            this.taskLongId = null;
            this.dataStringId = null;
        } else {
            this.taskLongId = key.getTaskLongId();
            this.dataStringId = key.getDataStringId();
        }
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getTaskLongId() {
        return taskLongId;
    }

    public void setTaskLongId(Long taskLongId) {
        this.taskLongId = taskLongId;
    }

    public String getDataStringId() {
        return dataStringId;
    }

    public void setDataStringId(String dataStringId) {
        this.dataStringId = dataStringId;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
                "dataStringId = " + dataStringId + ", " +
                "value = " + value + ", " +
                "message = " + message + ", " +
                "task = " + task + ")";
    }
}
