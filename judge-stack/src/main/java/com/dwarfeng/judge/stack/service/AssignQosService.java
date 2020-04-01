package com.dwarfeng.judge.stack.service;

import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 指派QoS服务。
 *
 * @author DwArFeng
 * @since beta-1.1.0
 */
public interface AssignQosService extends Service {

    /**
     * 启动指派服务。
     *
     * @throws ServiceException 服务异常。
     */
    void startAssign() throws ServiceException;

    /**
     * 停止指派服务。
     *
     * @throws ServiceException 服务异常。
     */
    void stopAssign() throws ServiceException;

    /**
     * 清除本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearLocalCache() throws ServiceException;
}
