package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.sdk.util.Constants;
import com.dwarfeng.judge.stack.bean.entity.Task;
import com.dwarfeng.judge.stack.bean.key.AnalyserVariableKey;
import com.dwarfeng.judge.stack.bean.key.JudgerVariableKey;
import com.dwarfeng.judge.stack.exception.*;
import com.dwarfeng.judge.stack.service.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

/**
 * 处理器验证器。
 *
 * <p>
 * 为处理器提供公共的验证方法。
 *
 * @author DwArFeng
 * @since 2.0.0
 */
@Component
public class HandlerValidator {

    private final AnalyserInfoMaintainService analyserInfoMaintainService;
    private final JudgerInfoMaintainService judgerInfoMaintainService;
    private final AnalyserVariableMaintainService analyserVariableMaintainService;
    private final JudgerVariableMaintainService judgerVariableMaintainService;
    private final SectionMaintainService sectionMaintainService;
    private final TaskMaintainService taskMaintainService;

    public HandlerValidator(
            AnalyserInfoMaintainService analyserInfoMaintainService,
            JudgerInfoMaintainService judgerInfoMaintainService,
            AnalyserVariableMaintainService analyserVariableMaintainService,
            JudgerVariableMaintainService judgerVariableMaintainService,
            SectionMaintainService sectionMaintainService,
            TaskMaintainService taskMaintainService
    ) {
        this.analyserInfoMaintainService = analyserInfoMaintainService;
        this.judgerInfoMaintainService = judgerInfoMaintainService;
        this.analyserVariableMaintainService = analyserVariableMaintainService;
        this.judgerVariableMaintainService = judgerVariableMaintainService;
        this.sectionMaintainService = sectionMaintainService;
        this.taskMaintainService = taskMaintainService;
    }

    public void makeSureAnalyserInfoExists(LongIdKey analyserInfoKey) throws HandlerException {
        try {
            if (!analyserInfoMaintainService.exists(analyserInfoKey)) {
                throw new AnalyserInfoNotExistsException(analyserInfoKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureJudgerInfoExists(LongIdKey judgerInfoKey) throws HandlerException {
        try {
            if (!judgerInfoMaintainService.exists(judgerInfoKey)) {
                throw new JudgerInfoNotExistsException(judgerInfoKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureAnalyserVariableExists(AnalyserVariableKey analyserVariableKey) throws HandlerException {
        try {
            if (!analyserVariableMaintainService.exists(analyserVariableKey)) {
                throw new AnalyserVariableNotExistsException(analyserVariableKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureJudgerVariableExists(JudgerVariableKey judgerVariableKey) throws HandlerException {
        try {
            if (!judgerVariableMaintainService.exists(judgerVariableKey)) {
                throw new JudgerVariableNotExistsException(judgerVariableKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureSectionExists(LongIdKey sectionKey) throws HandlerException {
        try {
            if (!sectionMaintainService.exists(sectionKey)) {
                throw new SectionNotExistsException(sectionKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureTaskExists(LongIdKey taskKey) throws HandlerException {
        try {
            if (!taskMaintainService.exists(taskKey)) {
                throw new TaskNotExistsException(taskKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureTaskStatusValid(LongIdKey taskKey, Set<Integer> validStatusSet)
            throws HandlerException {
        try {
            Task task = taskMaintainService.getIfExists(taskKey);
            if (Objects.isNull(task)) {
                throw new TaskNotExistsException(taskKey);
            }
            int status = task.getStatus();
            if (!Constants.taskStatusSpace().contains(status)) {
                throw new TaskStatusMismatchException(validStatusSet, status);
            }
            if (!validStatusSet.contains(status)) {
                throw new TaskStatusMismatchException(validStatusSet, status);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }
}
