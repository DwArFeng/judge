package com.dwarfeng.judge.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 可视化数据插入/更新信息。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
public class VisualizeDataUpsertInfo implements Dto {

    private static final long serialVersionUID = 6281764189854264277L;

    private LongIdKey taskKey;
    private String perspectiveStringId;
    private String content;

    public VisualizeDataUpsertInfo() {
    }

    public VisualizeDataUpsertInfo(LongIdKey taskKey, String perspectiveStringId, String content) {
        this.taskKey = taskKey;
        this.perspectiveStringId = perspectiveStringId;
        this.content = content;
    }

    public LongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(LongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public String getPerspectiveStringId() {
        return perspectiveStringId;
    }

    public void setPerspectiveStringId(String perspectiveStringId) {
        this.perspectiveStringId = perspectiveStringId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "VisualizeDataUpsertInfo{" +
                "taskKey=" + taskKey +
                ", perspectiveStringId='" + perspectiveStringId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
