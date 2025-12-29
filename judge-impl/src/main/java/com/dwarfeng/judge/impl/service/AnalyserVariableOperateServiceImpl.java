package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.dto.AnalyserVariableInspectInfo;
import com.dwarfeng.judge.stack.bean.dto.AnalyserVariableInspectResult;
import com.dwarfeng.judge.stack.bean.dto.AnalyserVariableRemoveInfo;
import com.dwarfeng.judge.stack.bean.dto.AnalyserVariableUpsertInfo;
import com.dwarfeng.judge.stack.handler.AnalyserVariableOperateHandler;
import com.dwarfeng.judge.stack.service.AnalyserVariableOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;

@Service
public class AnalyserVariableOperateServiceImpl implements AnalyserVariableOperateService {

    private final AnalyserVariableOperateHandler analyserVariableOperateHandler;

    private final ServiceExceptionMapper sem;

    public AnalyserVariableOperateServiceImpl(
            AnalyserVariableOperateHandler analyserVariableOperateHandler, ServiceExceptionMapper sem
    ) {
        this.analyserVariableOperateHandler = analyserVariableOperateHandler;
        this.sem = sem;
    }

    @Override
    @Nullable
    public AnalyserVariableInspectResult inspect(AnalyserVariableInspectInfo info) throws ServiceException {
        try {
            return analyserVariableOperateHandler.inspect(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看分析器变量时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void upsert(AnalyserVariableUpsertInfo info) throws ServiceException {
        try {
            analyserVariableOperateHandler.upsert(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("插入/更新分析器变量时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void remove(AnalyserVariableRemoveInfo info) throws ServiceException {
        try {
            analyserVariableOperateHandler.remove(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除分析器变量时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
