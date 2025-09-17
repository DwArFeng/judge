package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 判断结果查看信息。
 *
 * @author DwArFeng
 * @since 2.1.0-beta
 */
public class JudgementInspectInfo implements Dto {

    private static final long serialVersionUID = 1106002997085054771L;

    private LongIdKey taskKey;
    private String dataStringId;

    public JudgementInspectInfo() {
    }

    public JudgementInspectInfo(LongIdKey taskKey, String dataStringId) {
        this.taskKey = taskKey;
        this.dataStringId = dataStringId;
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

    @Override
    public String toString() {
        return "JudgementInspectInfo{" +
                "taskKey=" + taskKey +
                ", dataStringId='" + dataStringId + '\'' +
                '}';
    }
}
