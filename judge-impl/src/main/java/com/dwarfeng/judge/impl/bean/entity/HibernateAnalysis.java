package com.dwarfeng.judge.impl.bean.entity;

import com.dwarfeng.judge.impl.bean.key.HibernateAnalysisKey;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.subgrade.stack.bean.Bean;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Hibernate 分析结果。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
@Entity
@IdClass(HibernateAnalysisKey.class)
@Table(name = "tbl_analysis")
public class HibernateAnalysis implements Bean {

    private static final long serialVersionUID = -943078684486190102L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "task_id", nullable = false)
    private Long taskLongId;

    @Id
    @Column(name = "data_id", length = Constraints.LENGTH_STRING_ID, nullable = false)
    private String dataStringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "data_type", nullable = false)
    private int dataType;

    @Column(name = "string_value", columnDefinition = "TEXT")
    private String stringValue;

    @Column(name = "long_value")
    private Long longValue;

    @Column(name = "double_value")
    private Double doubleValue;

    @Column(name = "boolean_value")
    private Boolean booleanValue;

    @Column(name = "date_value")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateValue;

    @Column(name = "picture_value")
    private Long pictureValue;

    @Column(name = "picture_pack_value")
    private Long picturePackValue;

    @Column(name = "file_value")
    private Long fileValue;

    @Column(name = "file_pack_value")
    private Long filePackValue;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateTask.class)
    @JoinColumns({ //
            @JoinColumn(name = "task_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateTask task;

    public HibernateAnalysis() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateAnalysisKey getKey() {
        if (Objects.isNull(taskLongId) || Objects.isNull(dataStringId)) {
            return null;
        }
        return new HibernateAnalysisKey(taskLongId, dataStringId);
    }

    public void setKey(HibernateAnalysisKey key) {
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

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public Long getPictureValue() {
        return pictureValue;
    }

    public void setPictureValue(Long pictureValue) {
        this.pictureValue = pictureValue;
    }

    public Long getPicturePackValue() {
        return picturePackValue;
    }

    public void setPicturePackValue(Long picturePackValue) {
        this.picturePackValue = picturePackValue;
    }

    public Long getFileValue() {
        return fileValue;
    }

    public void setFileValue(Long fileValue) {
        this.fileValue = fileValue;
    }

    public Long getFilePackValue() {
        return filePackValue;
    }

    public void setFilePackValue(Long filePackValue) {
        this.filePackValue = filePackValue;
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
                "dataType = " + dataType + ", " +
                "stringValue = " + stringValue + ", " +
                "longValue = " + longValue + ", " +
                "doubleValue = " + doubleValue + ", " +
                "booleanValue = " + booleanValue + ", " +
                "dateValue = " + dateValue + ", " +
                "pictureValue = " + pictureValue + ", " +
                "picturePackValue = " + picturePackValue + ", " +
                "fileValue = " + fileValue + ", " +
                "filePackValue = " + filePackValue + ", " +
                "task = " + task + ")";
    }
}
