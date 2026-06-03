package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.sdk.util.Constants;
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableInspectInfo;
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableInspectResult;
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableRemoveInfo;
import com.dwarfeng.judge.stack.bean.dto.JudgerVariableUpsertInfo;
import com.dwarfeng.judge.stack.bean.entity.JudgerVariable;
import com.dwarfeng.judge.stack.bean.key.JudgerVariableKey;
import com.dwarfeng.judge.stack.handler.JudgerVariableOperateHandler;
import com.dwarfeng.judge.stack.service.JudgerVariableMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.Objects;

@Component
public class JudgerVariableOperateHandlerImpl implements JudgerVariableOperateHandler {

    private final JudgerVariableMaintainService judgerVariableMaintainService;

    private final HandlerValidator handlerValidator;

    public JudgerVariableOperateHandlerImpl(
            JudgerVariableMaintainService judgerVariableMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.judgerVariableMaintainService = judgerVariableMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @Nullable
    @Override
    public JudgerVariableInspectResult inspect(JudgerVariableInspectInfo info) throws HandlerException {
        try {
            return inspect0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private JudgerVariableInspectResult inspect0(JudgerVariableInspectInfo info) throws Exception {
        LongIdKey judgerInfoKey = info.getJudgerInfoKey();
        String judgerVariableId = info.getJudgerVariableId();

        handlerValidator.makeSureJudgerInfoExists(judgerInfoKey);

        JudgerVariableKey judgerVariableKey = new JudgerVariableKey(judgerInfoKey.getLongId(), judgerVariableId);
        JudgerVariable judgerVariable = judgerVariableMaintainService.getIfExists(judgerVariableKey);

        if (Objects.isNull(judgerVariable)) {
            return null;
        }

        int valueType = judgerVariable.getValueType();
        Object value;
        switch (valueType) {
            case Constants.VARIABLE_VALUE_TYPE_STRING:
                value = judgerVariable.getStringValue();
                break;
            case Constants.VARIABLE_VALUE_TYPE_LONG:
                value = judgerVariable.getLongValue();
                break;
            case Constants.VARIABLE_VALUE_TYPE_DOUBLE:
                value = judgerVariable.getDoubleValue();
                break;
            case Constants.VARIABLE_VALUE_TYPE_BOOLEAN:
                value = judgerVariable.getBooleanValue();
                break;
            case Constants.VARIABLE_VALUE_TYPE_DATE:
                value = judgerVariable.getDateValue();
                break;
            default:
                throw new IllegalStateException("非法的 valueType 值: " + valueType);
        }
        return new JudgerVariableInspectResult(valueType, value);
    }

    @Override
    public void upsert(JudgerVariableUpsertInfo info) throws HandlerException {
        try {
            upsert0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void upsert0(JudgerVariableUpsertInfo info) throws Exception {
        LongIdKey judgerInfoKey = info.getJudgerInfoKey();
        String judgerVariableId = info.getJudgerVariableId();
        int valueType = info.getValueType();
        Object value = info.getValue();

        handlerValidator.makeSureJudgerInfoExists(judgerInfoKey);
        handlerValidator.makeSureVariableValueTypeValid(valueType, value);

        JudgerVariable judgerVariable = new JudgerVariable(
                new JudgerVariableKey(judgerInfoKey.getLongId(), judgerVariableId),
                valueType, null, null, null, null, null
        );
        if (Objects.nonNull(value)) {
            switch (valueType) {
                case Constants.VARIABLE_VALUE_TYPE_STRING:
                    judgerVariable.setStringValue((String) value);
                    break;
                case Constants.VARIABLE_VALUE_TYPE_LONG:
                    judgerVariable.setLongValue((Long) value);
                    break;
                case Constants.VARIABLE_VALUE_TYPE_DOUBLE:
                    judgerVariable.setDoubleValue((Double) value);
                    break;
                case Constants.VARIABLE_VALUE_TYPE_BOOLEAN:
                    judgerVariable.setBooleanValue((Boolean) value);
                    break;
                case Constants.VARIABLE_VALUE_TYPE_DATE:
                    judgerVariable.setDateValue((Date) value);
                    break;
                default:
                    throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
            }
        }

        judgerVariableMaintainService.insertOrUpdate(judgerVariable);
    }

    @Override
    public void remove(JudgerVariableRemoveInfo info) throws HandlerException {
        try {
            remove0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void remove0(JudgerVariableRemoveInfo info) throws Exception {
        LongIdKey judgerInfoKey = info.getJudgerInfoKey();
        String judgerVariableId = info.getJudgerVariableId();

        handlerValidator.makeSureJudgerInfoExists(judgerInfoKey);
        JudgerVariableKey judgerVariableKey = new JudgerVariableKey(judgerInfoKey.getLongId(), judgerVariableId);
        handlerValidator.makeSureJudgerVariableExists(judgerVariableKey);

        judgerVariableMaintainService.delete(judgerVariableKey);
    }
}
