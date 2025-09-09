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
    private final AnalysisFileInfoMaintainService analysisFileInfoMaintainService;
    private final AnalysisFilePackItemInfoMaintainService analysisFilePackItemInfoMaintainService;
    private final AnalysisFilePackMaintainService analysisFilePackMaintainService;
    private final AnalysisPictureInfoMaintainService analysisPictureInfoMaintainService;
    private final AnalysisPicturePackItemInfoMaintainService analysisPicturePackItemInfoMaintainService;
    private final AnalysisPicturePackMaintainService analysisPicturePackMaintainService;

    public HandlerValidator(
            AnalyserInfoMaintainService analyserInfoMaintainService,
            JudgerInfoMaintainService judgerInfoMaintainService,
            AnalyserVariableMaintainService analyserVariableMaintainService,
            JudgerVariableMaintainService judgerVariableMaintainService,
            SectionMaintainService sectionMaintainService,
            TaskMaintainService taskMaintainService,
            AnalysisFileInfoMaintainService analysisFileInfoMaintainService,
            AnalysisFilePackItemInfoMaintainService analysisFilePackItemInfoMaintainService,
            AnalysisFilePackMaintainService analysisFilePackMaintainService,
            AnalysisPictureInfoMaintainService analysisPictureInfoMaintainService,
            AnalysisPicturePackItemInfoMaintainService analysisPicturePackItemInfoMaintainService,
            AnalysisPicturePackMaintainService analysisPicturePackMaintainService
    ) {
        this.analyserInfoMaintainService = analyserInfoMaintainService;
        this.judgerInfoMaintainService = judgerInfoMaintainService;
        this.analyserVariableMaintainService = analyserVariableMaintainService;
        this.judgerVariableMaintainService = judgerVariableMaintainService;
        this.sectionMaintainService = sectionMaintainService;
        this.taskMaintainService = taskMaintainService;
        this.analysisFileInfoMaintainService = analysisFileInfoMaintainService;
        this.analysisFilePackItemInfoMaintainService = analysisFilePackItemInfoMaintainService;
        this.analysisFilePackMaintainService = analysisFilePackMaintainService;
        this.analysisPictureInfoMaintainService = analysisPictureInfoMaintainService;
        this.analysisPicturePackItemInfoMaintainService = analysisPicturePackItemInfoMaintainService;
        this.analysisPicturePackMaintainService = analysisPicturePackMaintainService;
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

    public void makeSureAnalysisFileExists(LongIdKey analysisFileKey) throws HandlerException {
        try {
            if (!analysisFileInfoMaintainService.exists(analysisFileKey)) {
                throw new AnalysisFileNotExistsException(analysisFileKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureAnalysisFilePackItemExists(LongIdKey analysisFilePackItemKey) throws HandlerException {
        try {
            if (!analysisFilePackItemInfoMaintainService.exists(analysisFilePackItemKey)) {
                throw new AnalysisFilePackItemNotExistsException(analysisFilePackItemKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureAnalysisFilePackExists(LongIdKey analysisFilePackKey) throws HandlerException {
        try {
            if (!analysisFilePackMaintainService.exists(analysisFilePackKey)) {
                throw new AnalysisFilePackNotExistsException(analysisFilePackKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureAnalysisPictureExists(LongIdKey analysisPictureKey) throws HandlerException {
        try {
            if (!analysisPictureInfoMaintainService.exists(analysisPictureKey)) {
                throw new AnalysisPictureNotExistsException(analysisPictureKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureAnalysisPicturePackItemExists(LongIdKey analysisPicturePackItemKey) throws HandlerException {
        try {
            if (!analysisPicturePackItemInfoMaintainService.exists(analysisPicturePackItemKey)) {
                throw new AnalysisPicturePackItemNotExistsException(analysisPicturePackItemKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureAnalysisPicturePackExists(LongIdKey analysisPicturePackKey) throws HandlerException {
        try {
            if (!analysisPicturePackMaintainService.exists(analysisPicturePackKey)) {
                throw new AnalysisPicturePackNotExistsException(analysisPicturePackKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }
}
