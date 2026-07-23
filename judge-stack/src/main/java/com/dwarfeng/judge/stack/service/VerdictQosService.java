package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.dto.VerdictInspectBySectionInfo;
import com.dwarfeng.judge.stack.bean.dto.VerdictInspectBySectionResult;
import com.dwarfeng.judge.stack.bean.dto.VerdictUpdateByTaskInfo;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 部件裁决结果 QoS 服务。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public interface VerdictQosService extends Service {

    /**
     * 按部件检查并返回当前全部裁决结果。
     *
     * @param info 部件裁决结果按部件查看信息。
     * @return 部件裁决结果按部件查看结果。
     * @throws ServiceException 服务异常。
     */
    VerdictInspectBySectionResult inspectBySection(VerdictInspectBySectionInfo info) throws ServiceException;

    /**
     * 根据任务将全部判断结果同步到指定部件的裁决结果。
     *
     * @param info 根据任务更新裁决结果信息。
     * @throws ServiceException 服务异常。
     */
    void updateByTask(VerdictUpdateByTaskInfo info) throws ServiceException;
}
