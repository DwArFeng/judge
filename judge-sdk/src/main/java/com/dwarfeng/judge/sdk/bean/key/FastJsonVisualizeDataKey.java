package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.key.VisualizeDataKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * FastJson 可视化数据键。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class FastJsonVisualizeDataKey implements Key {

    private static final long serialVersionUID = -5494279470985600566L;

    public static FastJsonVisualizeDataKey of(VisualizeDataKey visualizeDataKey) {
        if (Objects.isNull(visualizeDataKey)) {
            return null;
        } else {
            return new FastJsonVisualizeDataKey(
                    visualizeDataKey.getTaskLongId(),
                    visualizeDataKey.getPerspectiveStringId()
            );
        }
    }

    @JSONField(name = "task_long_id", ordinal = 1)
    private Long taskLongId;

    @JSONField(name = "perspective_string_id", ordinal = 2)
    private String perspectiveStringId;

    public FastJsonVisualizeDataKey() {
    }

    public FastJsonVisualizeDataKey(Long taskLongId, String perspectiveStringId) {
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

        FastJsonVisualizeDataKey that = (FastJsonVisualizeDataKey) o;
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
        return "FastJsonVisualizeDataKey{" +
                "taskLongId=" + taskLongId +
                ", perspectiveStringId='" + perspectiveStringId + '\'' +
                '}';
    }
}
