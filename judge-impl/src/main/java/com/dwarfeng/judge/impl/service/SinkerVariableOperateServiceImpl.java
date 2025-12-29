package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.dto.SinkerVariableInspectInfo;
import com.dwarfeng.judge.stack.bean.dto.SinkerVariableInspectResult;
import com.dwarfeng.judge.stack.bean.dto.SinkerVariableRemoveInfo;
import com.dwarfeng.judge.stack.bean.dto.SinkerVariableUpsertInfo;
import com.dwarfeng.judge.stack.handler.SinkerVariableOperateHandler;
import com.dwarfeng.judge.stack.service.SinkerVariableOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;

@Service
public class SinkerVariableOperateServiceImpl implements SinkerVariableOperateService {

    private final SinkerVariableOperateHandler sinkerVariableOperateHandler;

    private final ServiceExceptionMapper sem;

    public SinkerVariableOperateServiceImpl(
            SinkerVariableOperateHandler sinkerVariableOperateHandler, ServiceExceptionMapper sem
    ) {
        this.sinkerVariableOperateHandler = sinkerVariableOperateHandler;
        this.sem = sem;
    }

    @Override
    @Nullable
    public SinkerVariableInspectResult inspect(SinkerVariableInspectInfo info) throws ServiceException {
        try {
            return sinkerVariableOperateHandler.inspect(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看下沉器变量时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void upsert(SinkerVariableUpsertInfo info) throws ServiceException {
        try {
            sinkerVariableOperateHandler.upsert(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("插入/更新下沉器变量时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void remove(SinkerVariableRemoveInfo info) throws ServiceException {
        try {
            sinkerVariableOperateHandler.remove(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除下沉器变量时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
