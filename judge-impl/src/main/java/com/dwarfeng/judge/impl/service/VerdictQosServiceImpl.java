package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.dto.VerdictInspectBySectionInfo;
import com.dwarfeng.judge.stack.bean.dto.VerdictInspectBySectionResult;
import com.dwarfeng.judge.stack.bean.dto.VerdictUpdateByTaskInfo;
import com.dwarfeng.judge.stack.handler.VerdictOperateHandler;
import com.dwarfeng.judge.stack.service.VerdictQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

/**
 * 部件裁决结果 QoS 服务实现。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
@Service
public class VerdictQosServiceImpl implements VerdictQosService {

    private final VerdictOperateHandler verdictOperateHandler;

    private final ServiceExceptionMapper sem;

    public VerdictQosServiceImpl(VerdictOperateHandler verdictOperateHandler, ServiceExceptionMapper sem) {
        this.verdictOperateHandler = verdictOperateHandler;
        this.sem = sem;
    }

    @Override
    public VerdictInspectBySectionResult inspectBySection(VerdictInspectBySectionInfo info) throws ServiceException {
        try {
            return verdictOperateHandler.inspectBySection(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("根据部件检查裁决时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void updateByTask(VerdictUpdateByTaskInfo info) throws ServiceException {
        try {
            verdictOperateHandler.updateByTask(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("根据任务更新裁决时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
