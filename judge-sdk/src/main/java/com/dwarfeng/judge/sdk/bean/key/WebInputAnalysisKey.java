package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.sdk.util.Constraints;
import com.dwarfeng.judge.stack.bean.key.AnalysisKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 分析结果键。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class WebInputAnalysisKey implements Key {

    private static final long serialVersionUID = 197702392836043563L;

    public static AnalysisKey toStackBean(WebInputAnalysisKey webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new AnalysisKey(
                    webInput.getTaskLongId(),
                    webInput.getDataStringId()
            );
        }
    }

    @JSONField(name = "task_long_id")
    @NotNull
    private Long taskLongId;

    @JSONField(name = "data_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String dataStringId;

    public WebInputAnalysisKey() {
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

        WebInputAnalysisKey that = (WebInputAnalysisKey) o;
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
        return "WebInputAnalysisKey{" +
                "taskLongId=" + taskLongId +
                ", dataStringId='" + dataStringId + '\'' +
                '}';
    }
}
