package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.entity.FilterInfo;
import com.dwarfeng.judge.stack.handler.Filter;
import com.dwarfeng.judge.stack.handler.FilterLocalCacheHandler;
import com.dwarfeng.judge.stack.service.FilterInfoMaintainService;
import com.dwarfeng.judge.stack.service.FilterQosService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class FilterQosServiceImpl implements FilterQosService {

    private final FilterInfoMaintainService filterInfoMaintainService;

    private final FilterLocalCacheHandler filterLocalCacheHandler;

    private final ServiceExceptionMapper sem;

    public FilterQosServiceImpl(
            FilterInfoMaintainService filterInfoMaintainService,
            FilterLocalCacheHandler filterLocalCacheHandler,
            ServiceExceptionMapper sem
    ) {
        this.filterInfoMaintainService = filterInfoMaintainService;
        this.filterLocalCacheHandler = filterLocalCacheHandler;
        this.sem = sem;
    }

    @Override
    public boolean exists(LongIdKey filterInfoKey) throws ServiceException {
        try {
            return filterLocalCacheHandler.exists(filterInfoKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("判断指定的过滤器是否存在时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public FilterDescription get(LongIdKey filterInfoKey) throws ServiceException {
        try {
            if (!filterLocalCacheHandler.exists(filterInfoKey)) {
                return null;
            }
            FilterInfo filterInfo = filterInfoMaintainService.get(filterInfoKey);
            Filter filter = filterLocalCacheHandler.get(filterInfoKey);
            return new FilterDescription(filterInfo, filter);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("获取指定过滤器的过滤器描述时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void clearLocalCache() throws ServiceException {
        try {
            filterLocalCacheHandler.clear();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("清除过滤器本地缓存时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
