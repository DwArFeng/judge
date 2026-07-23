package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 根据任务更新部件裁决结果信息。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class VerdictUpdateByTaskInfo implements Dto {

    private static final long serialVersionUID = 4039610084585269013L;

    private LongIdKey taskKey;
    private LongIdKey sectionKey;

    public VerdictUpdateByTaskInfo() {
    }

    public VerdictUpdateByTaskInfo(LongIdKey taskKey, LongIdKey sectionKey) {
        this.taskKey = taskKey;
        this.sectionKey = sectionKey;
    }

    public LongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(LongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public LongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(LongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    @Override
    public String toString() {
        return "VerdictUpdateByTaskInfo{" +
                "taskKey=" + taskKey +
                ", sectionKey=" + sectionKey +
                '}';
    }
}
