package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.dto.AlarmModalUpdateInfo;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 报警模态 QoS 服务。
 *
 * @author DwArFeng
 * @since 3.2.0
 */
public interface AlarmModalQosService extends Service {

    /**
     * 根据最新报警模态信息更新单条报警模态。
     *
     * @param info 报警模态更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateModal(AlarmModalUpdateInfo info) throws ServiceException;
}
