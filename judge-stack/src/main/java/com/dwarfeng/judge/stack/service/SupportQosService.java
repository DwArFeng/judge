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
     * 重置分析器。
     *
     * @throws ServiceException 服务异常。
     * @since 2.0.0-beta
     */
    void resetAnalyser() throws ServiceException;

    /**
     * 重置驱动器。
     *
     * @throws ServiceException 服务异常。
     * @since 2.0.0-beta
     */
    void resetDriver() throws ServiceException;

    /**
     * 重置判断器。
     *
     * @throws ServiceException 服务异常。
     * @since 2.0.0-beta
     */
    void resetJudger() throws ServiceException;

    /**
     * 重置下沉器。
     *
     * @throws ServiceException 服务异常。
     * @since 2.1.0-beta
     */
    void resetSinker() throws ServiceException;

    /**
     * 重置提供器。
     *
     * @throws ServiceException 服务异常。
     * @since 2.1.0-beta
     */
    void resetProvider() throws ServiceException;
}
