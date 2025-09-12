package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.handler.JobLocalCacheHandler;
import com.dwarfeng.judge.stack.service.JobQosService;
import com.dwarfeng.judge.stack.struct.JobLocalCache;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class JobQosServiceImpl implements JobQosService {

    private final JobLocalCacheHandler jobLocalCacheHandler;

    private final ServiceExceptionMapper sem;

    public JobQosServiceImpl(JobLocalCacheHandler jobLocalCacheHandler, ServiceExceptionMapper sem) {
        this.jobLocalCacheHandler = jobLocalCacheHandler;
        this.sem = sem;
    }

    @Override
    public JobLocalCache getJobLocalCache(LongIdKey recordSettingKey) throws ServiceException {
        try {
            return jobLocalCacheHandler.get(recordSettingKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("获取指定记录设置的作业信息时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void clearLocalCache() throws ServiceException {
        try {
            jobLocalCacheHandler.clear();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("清除本地缓存时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
