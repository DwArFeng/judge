package com.dwarfeng.judge.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.judge.stack.bean.dto.VerdictUpdateByTaskInfo;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 根据任务更新部件裁决结果信息。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public class WebInputVerdictUpdateByTaskInfo implements Dto {

    private static final long serialVersionUID = 5096008948273019182L;

    public static VerdictUpdateByTaskInfo toStackBean(WebInputVerdictUpdateByTaskInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VerdictUpdateByTaskInfo(
                    WebInputLongIdKey.toStackBean(webInput.getTaskKey()),
                    WebInputLongIdKey.toStackBean(webInput.getSectionKey())
            );
        }
    }

    @JSONField(name = "task_key")
    @NotNull
    @Valid
    private WebInputLongIdKey taskKey;

    @JSONField(name = "section_key")
    @NotNull
    @Valid
    private WebInputLongIdKey sectionKey;

    public WebInputVerdictUpdateByTaskInfo() {
    }

    public WebInputLongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(WebInputLongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public WebInputLongIdKey getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(WebInputLongIdKey sectionKey) {
        this.sectionKey = sectionKey;
    }

    @Override
    public String toString() {
        return "WebInputVerdictUpdateByTaskInfo{" +
                "taskKey=" + taskKey +
                ", sectionKey=" + sectionKey +
                '}';
    }
}
