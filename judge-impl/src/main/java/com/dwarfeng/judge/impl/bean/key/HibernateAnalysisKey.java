package com.dwarfeng.judge.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * Hibernate 分析结果键。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class HibernateAnalysisKey implements Key {

    private static final long serialVersionUID = 162569336638527006L;

    private Long taskLongId;
    private String dataStringId;

    public HibernateAnalysisKey() {
    }

    public HibernateAnalysisKey(Long taskLongId, String dataStringId) {
        this.taskLongId = taskLongId;
        this.dataStringId = dataStringId;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        HibernateAnalysisKey that = (HibernateAnalysisKey) o;
        return Objects.equals(taskLongId, that.taskLongId) &&
                Objects.equals(dataStringId, that.dataStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(taskLongId);
        result = 31 * result + Objects.hashCode(dataStringId);
        return result;
    }

    @Override
    public String toString() {
        return "HibernateAnalysisKey{" +
                "taskLongId=" + taskLongId +
                ", dataStringId='" + dataStringId + '\'' +
                '}';
    }
}
