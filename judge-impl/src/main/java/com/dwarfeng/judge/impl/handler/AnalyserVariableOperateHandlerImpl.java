package com.dwarfeng.judge.impl.handler;

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

    private AnalyserVariableInspectResult inspect0(AnalyserVariableInspectInfo info) throws Exception {
        // 展开参数。
        LongIdKey analyserInfoKey = info.getAnalyserInfoKey();
        String analyserVariableId = info.getAnalyserVariableId();

        // 确认部件存在。
        handlerValidator.makeSureAnalyserInfoExists(analyserInfoKey);

        // 调用维护服务获取分析器变量。
        AnalyserVariableKey analyserVariableKey = new AnalyserVariableKey(
                analyserInfoKey.getLongId(), analyserVariableId
        );
        AnalyserVariable analyserVariable = analyserVariableMaintainService.getIfExists(analyserVariableKey);

        // 如果分析器变量不存在，则返回 null。
        if (Objects.isNull(analyserVariable)) {
            return null;
        }

        // 构建返回值并返回。
        String value = analyserVariable.getValue();
        return new AnalyserVariableInspectResult(value);
    }

    @Override
    public void upsert(AnalyserVariableUpsertInfo info) throws HandlerException {
        try {
            upsert0(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void upsert0(AnalyserVariableUpsertInfo info) throws Exception {
        // 展开参数。
        LongIdKey analyserInfoKey = info.getAnalyserInfoKey();
        String analyserVariableId = info.getAnalyserVariableId();
        String value = info.getValue();

        // 确认部件存在。
        handlerValidator.makeSureAnalyserInfoExists(analyserInfoKey);

        // 构建分析器变量。
        AnalyserVariable analyserVariable = new AnalyserVariable(
                new AnalyserVariableKey(analyserInfoKey.getLongId(), analyserVariableId), value
        );

        // 调用维护服务插入/更新分析器变量。
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
        // 展开参数。
        LongIdKey analyserInfoKey = info.getAnalyserInfoKey();
        String analyserVariableId = info.getAnalyserVariableId();

        // 确认部件存在。
        handlerValidator.makeSureAnalyserInfoExists(analyserInfoKey);
        // 确认分析器变量存在。
        AnalyserVariableKey analyserVariableKey = new AnalyserVariableKey(
                analyserInfoKey.getLongId(), analyserVariableId
        );
        handlerValidator.makeSureAnalyserVariableExists(analyserVariableKey);

        // 调用维护服务删除分析器变量。
        analyserVariableMaintainService.delete(analyserVariableKey);
    }
}
