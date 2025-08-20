package com.dwarfeng.judge.stack.service;

import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 支持 QoS 服务。
 *
 * @author DwArFeng
 * @since 1.7.0
 */
public interface SupportQosService extends Service {

    /**
     * 重置驱动器。
     *
     * @throws ServiceException 服务异常。
     */
    void resetDriver() throws ServiceException;

    /**
     * 重置判断器。
     *
     * @throws ServiceException 服务异常。
     */
    void resetJudger() throws ServiceException;
}
