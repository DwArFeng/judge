package com.dwarfeng.judge.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * Hibernate 判断结果键。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class HibernateJudgementKey implements Key {

    private static final long serialVersionUID = -126546151999859307L;

    private Long taskLongId;
    private String dataStringId;

    public HibernateJudgementKey() {
    }

    public HibernateJudgementKey(Long taskLongId, String dataStringId) {
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

        HibernateJudgementKey that = (HibernateJudgementKey) o;
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
        return "HibernateJudgementKey{" +
                "taskLongId=" + taskLongId +
                ", dataStringId='" + dataStringId + '\'' +
                '}';
    }
}
