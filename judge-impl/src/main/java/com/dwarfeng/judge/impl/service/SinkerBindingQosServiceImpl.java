package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.handler.SinkerBindingLocalCacheHandler;
import com.dwarfeng.judge.stack.service.SinkerBindingQosService;
import com.dwarfeng.judge.stack.struct.SinkerBinding;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class SinkerBindingQosServiceImpl implements SinkerBindingQosService {

    private final SinkerBindingLocalCacheHandler sinkerBindingLocalCacheHandler;

    private final ServiceExceptionMapper sem;

    public SinkerBindingQosServiceImpl(
            SinkerBindingLocalCacheHandler sinkerBindingLocalCacheHandler,
            ServiceExceptionMapper sem
    ) {
        this.sinkerBindingLocalCacheHandler = sinkerBindingLocalCacheHandler;
        this.sem = sem;
    }

    @Override
    public SinkerBinding getSinkerBinding(LongIdKey sinkerInfoKey) throws ServiceException {
        try {
            return sinkerBindingLocalCacheHandler.get(sinkerInfoKey);
        } catch (HandlerException e) {
            throw ServiceExceptionHelper.logParse("获取指定下沉器信息对应的下沉器绑定时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void clearLocalCache() throws ServiceException {
        try {
            sinkerBindingLocalCacheHandler.clear();
        } catch (HandlerException e) {
            throw ServiceExceptionHelper.logParse("清除本地缓存时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
