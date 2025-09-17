package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.judge.stack.bean.key.AnalysisKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * JSFixed FastJson 分析结果键。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public class JSFixedFastJsonAnalysisKey implements Key {

    private static final long serialVersionUID = -7802011328068799812L;

    public static JSFixedFastJsonAnalysisKey of(AnalysisKey analysisKey) {
        if (Objects.isNull(analysisKey)) {
            return null;
        } else {
            return new JSFixedFastJsonAnalysisKey(
                    analysisKey.getTaskLongId(),
                    analysisKey.getDataStringId()
            );
        }
    }

    @JSONField(name = "task_long_id", ordinal = 1, serializeUsing = ToStringSerializer.class)
    private Long taskLongId;

    @JSONField(name = "data_string_id", ordinal = 2)
    private String dataStringId;

    public JSFixedFastJsonAnalysisKey() {
    }

    public JSFixedFastJsonAnalysisKey(Long taskLongId, String dataStringId) {
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

        JSFixedFastJsonAnalysisKey that = (JSFixedFastJsonAnalysisKey) o;
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
        return "JSFixedFastJsonAnalysisKey{" +
                "taskLongId=" + taskLongId +
                ", dataStringId='" + dataStringId + '\'' +
                '}';
    }
}
