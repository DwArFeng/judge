package com.dwarfeng.judge.stack.service;

import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 评估QoS服务。
 *
 * @author DwArFeng
 * @since beta-1.1.0
 */
public interface EvaluateQosService extends Service {

    /**
     * 启动评估服务。
     *
     * @throws ServiceException 服务异常。
     */
    void startEvaluate() throws ServiceException;

    /**
     * 停止评估服务。
     *
     * @throws ServiceException 服务异常。
     */
    void stopEvaluate() throws ServiceException;

    /**
     * 清除本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearLocalCache() throws ServiceException;
}
