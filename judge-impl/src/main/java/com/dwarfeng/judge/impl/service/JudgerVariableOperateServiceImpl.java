package com.dwarfeng.judge.impl.service;

import com.dwarfeng.judge.stack.bean.dto.JudgerVariableInspectInfo;
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableInspectResult;
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableRemoveInfo;
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableUpsertInfo;
import com.dwarfeng.judge.stack.handler.JudgerVariableOperateHandler;
import com.dwarfeng.judge.stack.service.JudgerVariableOperateService;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;

@Service
public class JudgerVariableOperateServiceImpl implements JudgerVariableOperateService {

    private final JudgerVariableOperateHandler judgerVariableOperateHandler;

    private final ServiceExceptionMapper sem;

    public JudgerVariableOperateServiceImpl(
            JudgerVariableOperateHandler judgerVariableOperateHandler, ServiceExceptionMapper sem
    ) {
        this.judgerVariableOperateHandler = judgerVariableOperateHandler;
        this.sem = sem;
    }

    @Override
    @Nullable
    public JudgerVariableInspectResult inspect(JudgerVariableInspectInfo info) throws ServiceException {
        try {
            return judgerVariableOperateHandler.inspect(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("查看判断器变量时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void upsert(JudgerVariableUpsertInfo info) throws ServiceException {
        try {
            judgerVariableOperateHandler.upsert(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("插入/更新判断器变量时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void remove(JudgerVariableRemoveInfo info) throws ServiceException {
        try {
            judgerVariableOperateHandler.remove(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除判断器变量时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
