package com.dwarfeng.judge.stack.handler;

import com.dwarfeng.judge.stack.bean.dto.VerdictInspectBySectionInfo;
import com.dwarfeng.judge.stack.bean.dto.VerdictInspectBySectionResult;
import com.dwarfeng.judge.stack.bean.dto.VerdictUpdateByTaskInfo;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 部件裁决结果操作处理器。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public interface VerdictOperateHandler extends Handler {

    /**
     * 按部件检查并返回当前全部裁决结果。
     *
     * @param info 部件裁决结果按部件查看信息。
     * @return 部件裁决结果按部件查看结果。
     * @throws HandlerException 处理器异常。
     */
    VerdictInspectBySectionResult inspectBySection(VerdictInspectBySectionInfo info) throws HandlerException;

    /**
     * 根据任务将全部判断结果同步到指定部件的裁决结果。
     *
     * @param info 根据任务更新裁决结果信息。
     * @throws HandlerException 处理器异常。
     */
    void updateByTask(VerdictUpdateByTaskInfo info) throws HandlerException;
}
