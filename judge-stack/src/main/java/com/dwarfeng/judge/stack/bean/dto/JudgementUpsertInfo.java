package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 判断结果插入或更新信息。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class JudgementUpsertInfo implements Dto {

    private static final long serialVersionUID = 3802481186037871063L;

    private LongIdKey taskKey;
    private String dataStringId;

    /**
     * 判断值。
     *
     * <p>
     * 一个归一化的数值，取值范围为 [0.0, 1.0]。
     */
    private double value;

    /**
     * 判断消息。
     *
     * <p>
     * 一段描述性的文本，用于说明判断值的含义或解释判断值如何得出。
     */
    private String message;

    public JudgementUpsertInfo() {
    }

    public JudgementUpsertInfo(LongIdKey taskKey, String dataStringId, double value, String message) {
        this.taskKey = taskKey;
        this.dataStringId = dataStringId;
        this.value = value;
        this.message = message;
    }

    public LongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(LongIdKey taskKey) {
        this.taskKey = taskKey;
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

    @Override
    public String toString() {
        return "JudgementUpsertInfo{" +
                "taskKey=" + taskKey +
                ", dataStringId='" + dataStringId + '\'' +
                ", value=" + value +
                ", message='" + message + '\'' +
                '}';
    }
}
