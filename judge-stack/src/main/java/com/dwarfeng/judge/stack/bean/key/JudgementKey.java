package com.dwarfeng.judge.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 判断结果键。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class JudgementKey implements Key {

    private static final long serialVersionUID = 833599525061992519L;

    private Long taskLongId;
    private String dataStringId;

    public JudgementKey() {
    }

    public JudgementKey(Long taskLongId, String dataStringId) {
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

        JudgementKey that = (JudgementKey) o;
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
        return "JudgementKey{" +
                "taskLongId=" + taskLongId +
                ", dataStringId='" + dataStringId + '\'' +
                '}';
    }
}
