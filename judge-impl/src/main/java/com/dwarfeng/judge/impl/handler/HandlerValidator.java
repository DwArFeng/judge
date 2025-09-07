package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.stack.bean.key.AnalyserVariableKey;
import com.dwarfeng.judge.stack.bean.key.JudgerVariableKey;
import com.dwarfeng.judge.stack.exception.AnalyserInfoNotExistsException;
import com.dwarfeng.judge.stack.exception.AnalyserVariableNotExistsException;
import com.dwarfeng.judge.stack.exception.JudgerInfoNotExistsException;
import com.dwarfeng.judge.stack.exception.JudgerVariableNotExistsException;
import com.dwarfeng.judge.stack.service.AnalyserInfoMaintainService;
import com.dwarfeng.judge.stack.service.AnalyserVariableMaintainService;
import com.dwarfeng.judge.stack.service.JudgerInfoMaintainService;
import com.dwarfeng.judge.stack.service.JudgerVariableMaintainService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import org.springframework.stereotype.Component;

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

    public HandlerValidator(
            AnalyserInfoMaintainService analyserInfoMaintainService,
            JudgerInfoMaintainService judgerInfoMaintainService,
            AnalyserVariableMaintainService analyserVariableMaintainService,
            JudgerVariableMaintainService judgerVariableMaintainService
    ) {
        this.analyserInfoMaintainService = analyserInfoMaintainService;
        this.judgerInfoMaintainService = judgerInfoMaintainService;
        this.analyserVariableMaintainService = analyserVariableMaintainService;
        this.judgerVariableMaintainService = judgerVariableMaintainService;
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
}
