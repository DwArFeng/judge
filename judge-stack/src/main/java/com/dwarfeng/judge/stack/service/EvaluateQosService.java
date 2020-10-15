package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.bean.ConsumerStatus;
import com.dwarfeng.judge.stack.bean.EvaluateInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
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
    void start() throws ServiceException;

    /**
     * 停止评估服务。
     *
     * @throws ServiceException 服务异常。
     */
    void stop() throws ServiceException;

    /**
     * 获取指定部件的评估信息。
     *
     * @param sectionKey 指定的部件。
     * @return 指定的部件对应评估信息，或者是null。
     * @throws ServiceException 服务异常。
     */
    EvaluateInfo getContext(LongIdKey sectionKey) throws ServiceException;

    /**
     * 清除本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearLocalCache() throws ServiceException;

    /**
     * 获取消费者的消费者状态。
     *
     * @return 消费者状态。
     * @throws ServiceException 服务异常。
     */
    ConsumerStatus getConsumerStatus() throws ServiceException;

    /**
     * 设置消费者的参数。
     *
     * @param bufferSize 缓冲器的大小。
     * @param thread     消费者的线程数量。
     * @throws ServiceException 服务异常。
     */
    void setConsumerParameters(Integer bufferSize, Integer thread) throws ServiceException;
}
