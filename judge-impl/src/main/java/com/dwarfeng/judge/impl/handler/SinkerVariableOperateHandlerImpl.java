package com.dwarfeng.judge.impl.handler;

import com.dwarfeng.judge.sdk.util.Constants;
import com.dwarfeng.judge.stack.bean.dto.SinkerVariableInspectInfo;
import com.dwarfeng.judge.stack.bean.dto.SinkerVariableInspectResult;
import com.dwarfeng.judge.stack.bean.dto.SinkerVariableRemoveInfo;
import com.dwarfeng.judge.stack.bean.dto.SinkerVariableUpsertInfo;
import com.dwarfeng.judge.stack.bean.entity.SinkerVariable;
import com.dwarfeng.judge.stack.bean.key.SinkerVariableKey;
import com.dwarfeng.judge.stack.handler.SinkerVariableOperateHandler;
import com.dwarfeng.judge.stack.service.SinkerVariableMaintainService;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.Objects;

@Component
public class SinkerVariableOperateHandlerImpl implements SinkerVariableOperateHandler {

    private final SinkerVariableMaintainService sinkerVariableMaintainService;

    private final HandlerValidator handlerValidator;

    public SinkerVariableOperateHandlerImpl(
            SinkerVariableMaintainService sinkerVariableMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.sinkerVariableMaintainService = sinkerVariableMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @Nullable
    @Override
    public SinkerVariableInspectResult inspect(SinkerVariableInspectInfo info) throws HandlerException {
        try {
            return inspect0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private SinkerVariableInspectResult inspect0(SinkerVariableInspectInfo info) throws Exception {
        LongIdKey sinkerInfoKey = info.getSinkerInfoKey();
        String sinkerVariableId = info.getSinkerVariableId();

        handlerValidator.makeSureSinkerInfoExists(sinkerInfoKey);

        SinkerVariableKey sinkerVariableKey = new SinkerVariableKey(sinkerInfoKey.getLongId(), sinkerVariableId);
        SinkerVariable sinkerVariable = sinkerVariableMaintainService.getIfExists(sinkerVariableKey);

        if (Objects.isNull(sinkerVariable)) {
            return null;
        }

        int valueType = sinkerVariable.getValueType();
        Object value;
        switch (valueType) {
            case Constants.VARIABLE_VALUE_TYPE_STRING:
                value = sinkerVariable.getStringValue();
                break;
            case Constants.VARIABLE_VALUE_TYPE_LONG:
                value = sinkerVariable.getLongValue();
                break;
            case Constants.VARIABLE_VALUE_TYPE_DOUBLE:
                value = sinkerVariable.getDoubleValue();
                break;
            case Constants.VARIABLE_VALUE_TYPE_BOOLEAN:
                value = sinkerVariable.getBooleanValue();
                break;
            case Constants.VARIABLE_VALUE_TYPE_DATE:
                value = sinkerVariable.getDateValue();
                break;
            default:
                throw new IllegalStateException("非法的 valueType 值: " + valueType);
        }
        return new SinkerVariableInspectResult(valueType, value);
    }

    @Override
    public void upsert(SinkerVariableUpsertInfo info) throws HandlerException {
        try {
            upsert0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void upsert0(SinkerVariableUpsertInfo info) throws Exception {
        LongIdKey sinkerInfoKey = info.getSinkerInfoKey();
        String sinkerVariableId = info.getSinkerVariableId();
        int valueType = info.getValueType();
        Object value = info.getValue();

        handlerValidator.makeSureSinkerInfoExists(sinkerInfoKey);
        handlerValidator.makeSureVariableValueTypeValid(valueType, value);

        SinkerVariable sinkerVariable = new SinkerVariable(
                new SinkerVariableKey(sinkerInfoKey.getLongId(), sinkerVariableId),
                valueType, null, null, null, null, null
        );
        if (Objects.nonNull(value)) {
            switch (valueType) {
                case Constants.VARIABLE_VALUE_TYPE_STRING:
                    sinkerVariable.setStringValue((String) value);
                    break;
                case Constants.VARIABLE_VALUE_TYPE_LONG:
                    sinkerVariable.setLongValue((Long) value);
                    break;
                case Constants.VARIABLE_VALUE_TYPE_DOUBLE:
                    sinkerVariable.setDoubleValue((Double) value);
                    break;
                case Constants.VARIABLE_VALUE_TYPE_BOOLEAN:
                    sinkerVariable.setBooleanValue((Boolean) value);
                    break;
                case Constants.VARIABLE_VALUE_TYPE_DATE:
                    sinkerVariable.setDateValue((Date) value);
                    break;
                default:
                    throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
            }
        }

        sinkerVariableMaintainService.insertOrUpdate(sinkerVariable);
    }

    @Override
    public void remove(SinkerVariableRemoveInfo info) throws HandlerException {
        try {
            remove0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void remove0(SinkerVariableRemoveInfo info) throws Exception {
        LongIdKey sinkerInfoKey = info.getSinkerInfoKey();
        String sinkerVariableId = info.getSinkerVariableId();

        handlerValidator.makeSureSinkerInfoExists(sinkerInfoKey);
        SinkerVariableKey sinkerVariableKey = new SinkerVariableKey(sinkerInfoKey.getLongId(), sinkerVariableId);
        handlerValidator.makeSureSinkerVariableExists(sinkerVariableKey);

        sinkerVariableMaintainService.delete(sinkerVariableKey);
    }
}
