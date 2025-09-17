package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.handler.SectionBindingLocalCacheHandler;
import com.dwarfeng.judge.stack.service.SectionBindingQosService;
import com.dwarfeng.judge.stack.struct.SectionBinding;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

@Service
public class SectionBindingQosServiceImpl implements SectionBindingQosService {

    private final SectionBindingLocalCacheHandler sectionBindingLocalCacheHandler;

    private final ServiceExceptionMapper sem;

    public SectionBindingQosServiceImpl(
            SectionBindingLocalCacheHandler sectionBindingLocalCacheHandler,
            ServiceExceptionMapper sem
    ) {
        this.sectionBindingLocalCacheHandler = sectionBindingLocalCacheHandler;
        this.sem = sem;
    }

    @Override
    public SectionBinding getSectionBinding(LongIdKey sectionInfoKey) throws ServiceException {
        try {
            return sectionBindingLocalCacheHandler.get(sectionInfoKey);
        } catch (HandlerException e) {
            throw ServiceExceptionHelper.logParse("获取指定部件器信息对应的部件器绑定时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void clearLocalCache() throws ServiceException {
        try {
            sectionBindingLocalCacheHandler.clear();
        } catch (HandlerException e) {
            throw ServiceExceptionHelper.logParse("清除本地缓存时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
