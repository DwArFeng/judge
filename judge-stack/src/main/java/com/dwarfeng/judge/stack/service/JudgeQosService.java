package com.dwarfeng.judge.stack.service;

import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 判断QoS服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface JudgeQosService extends Service {

    /**
     * 启动判断服务。
     *
     * @throws ServiceException 服务异常。
     */
    void startJudge() throws ServiceException;

    /**
     * 停止判断服务。
     *
     * @throws ServiceException 服务异常。
     */
    void stopJudge() throws ServiceException;

    /**
     * 清除本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearLocalCache() throws ServiceException;
}
