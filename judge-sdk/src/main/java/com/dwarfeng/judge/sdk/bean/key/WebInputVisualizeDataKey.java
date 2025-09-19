package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.key.VisualizeDataKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * FastJson 可视化数据键。
 *
 * @author DwArFeng
 * @since 2.2.0
 */
public class WebInputVisualizeDataKey implements Key {

    private static final long serialVersionUID = 3685703778045772104L;

    public static VisualizeDataKey toStackBean(WebInputVisualizeDataKey webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VisualizeDataKey(
                    webInput.getTaskLongId(),
                    webInput.getPerspectiveStringId()
            );
        }
    }

    @JSONField(name = "task_long_id")
    @NotNull
    private Long taskLongId;

    @JSONField(name = "perspective_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String perspectiveStringId;

    public WebInputVisualizeDataKey() {
    }

    public WebInputVisualizeDataKey(Long taskLongId, String perspectiveStringId) {
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

        WebInputVisualizeDataKey that = (WebInputVisualizeDataKey) o;
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
        return "WebInputVisualizeDataKey{" +
                "taskLongId=" + taskLongId +
                ", perspectiveStringId='" + perspectiveStringId + '\'' +
                '}';
    }
}
