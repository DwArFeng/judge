package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.TaskEventCreateInfo;
import com.dwarfeng.judge.stack.bean.dto.TaskEventCreateResult;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 任务事件操作处理器。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface TaskEventOperateHandler extends Handler {

    /**
     * 创建任务事件。
     *
     * @param info 任务事件的创建信息。
     * @return 生成的任务事件的主键。
     * @throws HandlerException 处理器异常。
     */
    TaskEventCreateResult create(TaskEventCreateInfo info) throws HandlerException;
}
