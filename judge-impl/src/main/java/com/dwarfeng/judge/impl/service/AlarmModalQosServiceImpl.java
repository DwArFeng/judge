package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.dto.AlarmModalUpdateInfo;
import com.dwarfeng.judge.stack.handler.AlarmModalOperateHandler;
import com.dwarfeng.judge.stack.service.AlarmModalQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

/**
 * 报警模态 QoS 服务实现。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
@Service
public class AlarmModalQosServiceImpl implements AlarmModalQosService {

    private final AlarmModalOperateHandler alarmModalOperateHandler;

    private final ServiceExceptionMapper sem;

    public AlarmModalQosServiceImpl(AlarmModalOperateHandler alarmModalOperateHandler, ServiceExceptionMapper sem) {
        this.alarmModalOperateHandler = alarmModalOperateHandler;
        this.sem = sem;
    }

    @Override
    public void updateModal(AlarmModalUpdateInfo info) throws ServiceException {
        try {
            alarmModalOperateHandler.updateModal(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新报警模态时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
