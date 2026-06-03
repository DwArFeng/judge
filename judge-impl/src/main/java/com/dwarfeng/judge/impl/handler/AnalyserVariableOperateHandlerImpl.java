package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.sdk.util.Constants;
import com.dwarfeng.judge.stack.bean.dto.AnalyserVariableInspectInfo;
import com.dwarfeng.judge.stack.bean.dto.AnalyserVariableInspectResult;
import com.dwarfeng.judge.stack.bean.dto.AnalyserVariableRemoveInfo;
import com.dwarfeng.judge.stack.bean.dto.AnalyserVariableUpsertInfo;
import com.dwarfeng.judge.stack.bean.entity.AnalyserVariable;
import com.dwarfeng.judge.stack.bean.key.AnalyserVariableKey;
import com.dwarfeng.judge.stack.handler.AnalyserVariableOperateHandler;
import com.dwarfeng.judge.stack.service.AnalyserVariableMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.Objects;

@Component
public class AnalyserVariableOperateHandlerImpl implements AnalyserVariableOperateHandler {

    private final AnalyserVariableMaintainService analyserVariableMaintainService;

    private final HandlerValidator handlerValidator;

    public AnalyserVariableOperateHandlerImpl(
            AnalyserVariableMaintainService analyserVariableMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.analyserVariableMaintainService = analyserVariableMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @Nullable
    @Override
    public AnalyserVariableInspectResult inspect(AnalyserVariableInspectInfo info) throws HandlerException {
        try {
            return inspect0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private AnalyserVariableInspectResult inspect0(AnalyserVariableInspectInfo info) throws Exception {
        LongIdKey analyserInfoKey = info.getAnalyserInfoKey();
        String analyserVariableId = info.getAnalyserVariableId();

        handlerValidator.makeSureAnalyserInfoExists(analyserInfoKey);

        AnalyserVariableKey analyserVariableKey = new AnalyserVariableKey(
                analyserInfoKey.getLongId(), analyserVariableId
        );
        AnalyserVariable analyserVariable = analyserVariableMaintainService.getIfExists(analyserVariableKey);

        if (Objects.isNull(analyserVariable)) {
            return null;
        }

        int valueType = analyserVariable.getValueType();
        Object value;
        switch (valueType) {
            case Constants.VARIABLE_VALUE_TYPE_STRING:
                value = analyserVariable.getStringValue();
                break;
            case Constants.VARIABLE_VALUE_TYPE_LONG:
                value = analyserVariable.getLongValue();
                break;
            case Constants.VARIABLE_VALUE_TYPE_DOUBLE:
                value = analyserVariable.getDoubleValue();
                break;
            case Constants.VARIABLE_VALUE_TYPE_BOOLEAN:
                value = analyserVariable.getBooleanValue();
                break;
            case Constants.VARIABLE_VALUE_TYPE_DATE:
                value = analyserVariable.getDateValue();
                break;
            default:
                throw new IllegalStateException("非法的 valueType 值: " + valueType);
        }
        return new AnalyserVariableInspectResult(valueType, value);
    }

    @Override
    public void upsert(AnalyserVariableUpsertInfo info) throws HandlerException {
        try {
            upsert0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void upsert0(AnalyserVariableUpsertInfo info) throws Exception {
        LongIdKey analyserInfoKey = info.getAnalyserInfoKey();
        String analyserVariableId = info.getAnalyserVariableId();
        int valueType = info.getValueType();
        Object value = info.getValue();

        handlerValidator.makeSureAnalyserInfoExists(analyserInfoKey);
        handlerValidator.makeSureVariableValueTypeValid(valueType, value);

        AnalyserVariable analyserVariable = new AnalyserVariable(
                new AnalyserVariableKey(analyserInfoKey.getLongId(), analyserVariableId),
                valueType, null, null, null, null, null
        );
        if (Objects.nonNull(value)) {
            switch (valueType) {
                case Constants.VARIABLE_VALUE_TYPE_STRING:
                    analyserVariable.setStringValue((String) value);
                    break;
                case Constants.VARIABLE_VALUE_TYPE_LONG:
                    analyserVariable.setLongValue((Long) value);
                    break;
                case Constants.VARIABLE_VALUE_TYPE_DOUBLE:
                    analyserVariable.setDoubleValue((Double) value);
                    break;
                case Constants.VARIABLE_VALUE_TYPE_BOOLEAN:
                    analyserVariable.setBooleanValue((Boolean) value);
                    break;
                case Constants.VARIABLE_VALUE_TYPE_DATE:
                    analyserVariable.setDateValue((Date) value);
                    break;
                default:
                    throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
            }
        }

        analyserVariableMaintainService.insertOrUpdate(analyserVariable);
    }

    @Override
    public void remove(AnalyserVariableRemoveInfo info) throws HandlerException {
        try {
            remove0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void remove0(AnalyserVariableRemoveInfo info) throws Exception {
        LongIdKey analyserInfoKey = info.getAnalyserInfoKey();
        String analyserVariableId = info.getAnalyserVariableId();

        handlerValidator.makeSureAnalyserInfoExists(analyserInfoKey);
        AnalyserVariableKey analyserVariableKey = new AnalyserVariableKey(
                analyserInfoKey.getLongId(), analyserVariableId
        );
        handlerValidator.makeSureAnalyserVariableExists(analyserVariableKey);

        analyserVariableMaintainService.delete(analyserVariableKey);
    }
}
