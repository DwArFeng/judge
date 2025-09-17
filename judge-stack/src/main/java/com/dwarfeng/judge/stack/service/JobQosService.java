package com.dwarfeng.judge.stack.service;

import com.dwarfeng.judge.stack.struct.JobLocalCache;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 作业 QOS 服务。
 *
 * @author DwArFeng
 * @since 2.0.0-beta
 */
public interface JobQosService extends Service {

    /**
     * 获取指定记录设置的作业信息。
     *
     * @param recordSettingKey 指定记录设置的主键。
     * @return 指定记录设置的作业信息。
     * @throws ServiceException 服务异常。
     */
    JobLocalCache getJobLocalCache(LongIdKey recordSettingKey) throws ServiceException;

    /**
     * 清除本地缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearLocalCache() throws ServiceException;
}
