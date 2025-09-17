package com.dwarfeng.judge.impl.handler;

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

    private SinkerVariableInspectResult inspect0(SinkerVariableInspectInfo info) throws Exception {
        // 展开参数。
        LongIdKey sinkerInfoKey = info.getSinkerInfoKey();
        String sinkerVariableId = info.getSinkerVariableId();

        // 确认部件存在。
        handlerValidator.makeSureSinkerInfoExists(sinkerInfoKey);

        // 调用维护服务获取下沉器变量。
        SinkerVariableKey sinkerVariableKey = new SinkerVariableKey(sinkerInfoKey.getLongId(), sinkerVariableId);
        SinkerVariable sinkerVariable = sinkerVariableMaintainService.getIfExists(sinkerVariableKey);

        // 如果下沉器变量不存在，则返回 null。
        if (Objects.isNull(sinkerVariable)) {
            return null;
        }

        // 构建返回值并返回。
        String value = sinkerVariable.getValue();
        return new SinkerVariableInspectResult(value);
    }

    @Override
    public void upsert(SinkerVariableUpsertInfo info) throws HandlerException {
        try {
            upsert0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void upsert0(SinkerVariableUpsertInfo info) throws Exception {
        // 展开参数。
        LongIdKey sinkerInfoKey = info.getSinkerInfoKey();
        String sinkerVariableId = info.getSinkerVariableId();
        String value = info.getValue();

        // 确认部件存在。
        handlerValidator.makeSureSinkerInfoExists(sinkerInfoKey);

        // 构建下沉器变量。
        SinkerVariable sinkerVariable = new SinkerVariable(
                new SinkerVariableKey(sinkerInfoKey.getLongId(), sinkerVariableId), value
        );

        // 调用维护服务插入/更新下沉器变量。
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
        // 展开参数。
        LongIdKey sinkerInfoKey = info.getSinkerInfoKey();
        String sinkerVariableId = info.getSinkerVariableId();

        // 确认部件存在。
        handlerValidator.makeSureSinkerInfoExists(sinkerInfoKey);
        // 确认下沉器变量存在。
        SinkerVariableKey sinkerVariableKey = new SinkerVariableKey(sinkerInfoKey.getLongId(), sinkerVariableId);
        handlerValidator.makeSureSinkerVariableExists(sinkerVariableKey);

        // 调用维护服务删除下沉器变量。
        sinkerVariableMaintainService.delete(sinkerVariableKey);
    }
}
