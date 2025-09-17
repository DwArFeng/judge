package com.dwarfeng.judge.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.judge.stack.bean.key.JudgementKey;
import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * JSFixed FastJson 判断结果键。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class JSFixedFastJsonJudgementKey implements Key {

    private static final long serialVersionUID = 4919169613641079266L;

    public static JSFixedFastJsonJudgementKey of(JudgementKey judgementKey) {
        if (Objects.isNull(judgementKey)) {
            return null;
        } else {
            return new JSFixedFastJsonJudgementKey(
                    judgementKey.getTaskLongId(),
                    judgementKey.getDataStringId()
            );
        }
    }

    @JSONField(name = "task_long_id", ordinal = 1, serializeUsing = ToStringSerializer.class)
    private Long taskLongId;

    @JSONField(name = "data_string_id", ordinal = 2)
    private String dataStringId;

    public JSFixedFastJsonJudgementKey() {
    }

    public JSFixedFastJsonJudgementKey(Long taskLongId, String dataStringId) {
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

        JSFixedFastJsonJudgementKey that = (JSFixedFastJsonJudgementKey) o;
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
        return "JSFixedFastJsonJudgementKey{" +
                "taskLongId=" + taskLongId +
                ", dataStringId='" + dataStringId + '\'' +
                '}';
    }
}
